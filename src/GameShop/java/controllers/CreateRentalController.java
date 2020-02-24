package GameShop.java.controllers;

import GameShop.java.models.Basket;
import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateRentalController implements Initializable {
    private Router router = new Router();

    @FXML private ChoiceBox<Customer> customerChoiceBox;
    @FXML private ChoiceBox<Console> consoleChoiceBox;
    @FXML private Button reviewRentalButton;
    @FXML private HBox gameTableWrapper;
    @FXML private TableView<Game> gameTableView;
    @FXML private CheckBox consoleRequired;

    @FXML private TableColumn<Game, String> idColumn;
    @FXML private TableColumn<Game, String> nameColumn;

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        Basket.clearBasket();
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        idColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CreateRentalFXMLService.getId(val.getValue())));
        nameColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CreateRentalFXMLService.getName(val.getValue())));
        addButtonToTable();
        getCustomers();
        getConsoles();
    }

    private void getCustomers() {
        ObservableList<Customer> items = FXCollections.observableArrayList(CustomerService.getAllCustomers());
        customerChoiceBox.getItems().setAll(items);
    }

    private void getConsoles() {
        ObservableList<Console> items = FXCollections.observableArrayList(ConsoleService.getAvailableConsoles());
        consoleChoiceBox.getItems().setAll(items);
    }

    private void getGames() {
        ArrayList<Game> allGames = GameService.getAvailableGames();
        ObservableList<Game> items = FXCollections.observableArrayList();

        for (Game g: allGames) {
            if (g.getConsole().getId().equals(consoleChoiceBox.getSelectionModel().getSelectedItem().getId())) {
                items.add(g);
            }
        }
        gameTableView.getItems().setAll(items);
    }

    @FXML
    private void handleConsoleRequiredChange() {
        Basket.setConsoleRequired(consoleRequired.isSelected());
    }

    @FXML
    private void handleConsoleChange() {
        if (!gameTableWrapper.isVisible()) {
            gameTableWrapper.setVisible(true);
        }
        Basket.setConsole(consoleChoiceBox.getSelectionModel().getSelectedItem());
        Basket.clearGames();
        getGames();
    }

    @FXML
    private void handleCustomerChange() {
        Basket.setCustomer(customerChoiceBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void reviewRental() {
        if (customerChoiceBox.getSelectionModel().isEmpty()) {
            System.out.println("Add a customer to the rental");
        } else if (consoleChoiceBox.getSelectionModel().isEmpty()) {
            System.out.println("Add a console to the rental");
        } else {
            System.out.println(Basket.logBasket());
        }

    }

    private void addButtonToTable() {
        TableColumn<Game, Void> buttonColumn = new TableColumn<>("Add to Basket");

        Callback<TableColumn<Game, Void>, TableCell<Game, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Game, Void> call(final TableColumn<Game, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Add");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Game game = getTableView().getItems().get(getIndex());
                            if (Basket.gameInBasket(game)) {
                                Basket.removeGame(game);
                                if (!Basket.gameInBasket(game)) {
                                    btn.setText("Add");
                                }
                            } else {
                                Basket.addGame(game);
                                if (Basket.gameInBasket(game)) {
                                    btn.setText("Remove");
                                }
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        buttonColumn.setCellFactory(cellFactory);
        gameTableView.getColumns().add(buttonColumn);
    }
}
