package GameShop.java.models.adaptors;

import GameShop.java.general.AlertBox;
import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.services.BasketService;
import GameShop.java.services.GameService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.ArrayList;

public class CreateRentalAdaptor {

    public static Customer getCustomer(ChoiceBox<Customer> choiceBox) {
        return choiceBox.getSelectionModel().getSelectedItem();
    }

    public static Console getConsole(ChoiceBox<Console> choiceBox) {
        return choiceBox.getSelectionModel().getSelectedItem();
    }

    public static String getConsoleId(ChoiceBox<Console> choiceBox) {
        return getConsole(choiceBox).getId();
    }

    public static boolean isConsoleAvailable(ChoiceBox<Console> choiceBox) {
        return getConsole(choiceBox).isAvailable();
    }

    public static void getGamesForConsole(ObservableList<Game> items, String consoleId) {
        ArrayList<Game> allGames = GameService.getAvailableGames();
        for (Game g: allGames) {
            if (g.getConsole().getId().equals(consoleId)) {
                items.add(g);
            }
        }
    }

    public static ArrayList<Game> getGamesToRemoveFromBasket() {
        ArrayList<Game> games = BasketService.getGames();
        ArrayList<Game> gamesToRemove = new ArrayList<>();

        for (Game game : games) {
            if (game.getConsole() != BasketService.getConsole()) {
                gamesToRemove.add(game);
            }
        }
        return gamesToRemove;
    }

    public static void addAddButtonToGameTable(TableColumn<Game, Button> tableColumn) {
        Callback<TableColumn<Game, Button>, TableCell<Game, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Game, Button> call(final TableColumn<Game, Button> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Add");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Game game = getTableView().getItems().get(getIndex());
                            if (BasketService.gameInBasket(game)) {
                                BasketService.removeGame(game);
                                if (!BasketService.gameInBasket(game)) {
                                    btn.setText("Add");
                                } else {
                                    AlertBox.showMessage(Alert.AlertType.ERROR, "Failed to remove game from the basket");
                                }
                            } else {
                                if (BasketService.maxGamesInBasket()) {
                                    AlertBox.showMessage(Alert.AlertType.ERROR, "Max games already in the basket");
                                    return;
                                }
                                BasketService.addGame(game);
                                if (BasketService.gameInBasket(game)) {
                                    btn.setText("Remove");
                                } else {
                                    AlertBox.showMessage(Alert.AlertType.ERROR, "Failed to add game to the basket");
                                }
                            }
                        });
                    }

                    @Override
                    public void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Game game = param.getTableView().getItems().get(getIndex());
                            if (BasketService.gameInBasket(game)) {
                                btn.setText("Remove");
                            }
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        tableColumn.setCellFactory(cellFactory);

    }
}
