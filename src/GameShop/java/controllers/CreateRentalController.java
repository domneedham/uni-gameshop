package GameShop.java.controllers;

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
    @FXML private HBox gameTableWrapper;
    @FXML private TableView<Game> gameTableView;
    @FXML private CheckBox consoleRequired;
    @FXML private DatePicker datePicker;

    @FXML private TableColumn<Game, String> idColumn;
    @FXML private TableColumn<Game, String> nameColumn;
    @FXML private TableColumn<Game, Button> buttonColumn;

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        BasketService.clearBasket();
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
        getCustomers();
        getConsoles();
        if (BasketService.isBasketPopulated()) { fillDefaults(); }

    }

    private void setupTable() {
        gameTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        addButtonToTable();
        // TODO
        // change these from read only
        // which may remove needed to call addButtonToTable() in getGames method
        idColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(GameFXMLTableService.getId(val.getValue())));
        nameColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(GameFXMLTableService.getName(val.getValue())));
    }

    private void fillDefaults() {
        customerChoiceBox.getSelectionModel().select(BasketService.getCustomer());
        consoleChoiceBox.getSelectionModel().select(BasketService.getConsole());
        consoleRequired.setSelected(BasketService.isConsoleRequired());
    }

    private void getCustomers() {
        ObservableList<Customer> items = FXCollections.observableArrayList(CustomerService.getAllCustomers());
        customerChoiceBox.getItems().setAll(items);
    }

    private void getConsoles() {
        ObservableList<Console> consoles = FXCollections.observableArrayList(ConsoleService.getAvailableConsoles());
        consoleChoiceBox.getItems().setAll(consoles);
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
        // fixes button displaying remove if item not in basket anyway
        addButtonToTable();
    }

    @FXML
    private void handleConsoleRequiredChange() {
        BasketService.setConsoleRequired(consoleRequired.isSelected());
    }

    @FXML
    private void handleConsoleChange() {
        if (!gameTableWrapper.isVisible()) {
            gameTableWrapper.setVisible(true);
        }
        BasketService.setConsole(consoleChoiceBox.getSelectionModel().getSelectedItem());

        ArrayList<Game> games = BasketService.getGames();
        ArrayList<Game> gamesToRemove = new ArrayList<>();
        for (Game game : games) {
            if (game.getConsole() != BasketService.getConsole()) {
                gamesToRemove.add(game);
            }
        }
        BasketService.removeGame(gamesToRemove);
        getGames();
    }

    @FXML
    private void handleCustomerChange() {
        BasketService.setCustomer(customerChoiceBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleDateChange() { BasketService.setDate(datePicker.getValue()) ;}

    @FXML
    private void reviewRental(ActionEvent event) throws IOException {
        if (customerChoiceBox.getSelectionModel().isEmpty()) {
            System.out.println("Add a customer to the rental");
        } else if (consoleChoiceBox.getSelectionModel().isEmpty()) {
            System.out.println("Add a console to the rental");
        } else if (datePicker.getValue() == null) {
            System.out.println("You need to pick a date");
        } else if (BasketService.getGames().isEmpty() && !consoleRequired.isSelected()) {
            System.out.println("You need to add games or choose to rent the console");
        } else {
            router.changeRoute(RouteNames.VIEW_BASKET, event);
        }
    }

    private void addButtonToTable() {
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
                                }
                            } else {
                                BasketService.addGame(game);
                                if (BasketService.gameInBasket(game)) {
                                    btn.setText("Remove");
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

        buttonColumn.setCellFactory(cellFactory);
    }
}
