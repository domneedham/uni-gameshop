package GameShop.java.controllers;

import GameShop.java.general.AlertBox;
import GameShop.java.models.adaptors.CreateRentalAdaptor;
import GameShop.java.models.adaptors.GameTableAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateRentalController implements Initializable {
    private Router router = new Router();

    @FXML private ChoiceBox customerChoiceBox;
    @FXML private ChoiceBox consoleChoiceBox;
    @FXML private HBox gameTableWrapper;
    @FXML private TableView gameTableView;
    @FXML private CheckBox consoleRequired;
    @FXML private DatePicker datePicker;

    @FXML private TableColumn idColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn buttonColumn;

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
        GameTableAdaptor.setIdVales(idColumn);
        GameTableAdaptor.setNameValues(nameColumn);
    }

    private void fillDefaults() {
        customerChoiceBox.getSelectionModel().select(BasketService.getCustomer());
        consoleChoiceBox.getSelectionModel().select(BasketService.getConsole());
        consoleRequired.setSelected(BasketService.isConsoleRequired());
        datePicker.setValue(BasketService.getDate());
    }

    private void getCustomers() {
        ObservableList items = FXCollections.observableArrayList(CustomerService.getAllCustomers());
        customerChoiceBox.getItems().setAll(items);
    }

    private void getConsoles() {
        ObservableList consoles = FXCollections.observableArrayList(ConsoleService.getAvailableConsoles());
        consoleChoiceBox.getItems().setAll(consoles);
    }

    private void getGames() {
        ObservableList items = FXCollections.observableArrayList();
        CreateRentalAdaptor.getGamesForConsole(items, CreateRentalAdaptor.getConsoleId(consoleChoiceBox));
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

        // if the console is wanted - clear the games from the basket when it changes
        // as you can't rent games for more than one console if the console is being rented
        if (consoleRequired.isSelected()) {
            BasketService.removeGame(CreateRentalAdaptor.getGamesToRemoveFromBasket());
        }

        BasketService.setConsole(CreateRentalAdaptor.getConsole(consoleChoiceBox));
        getGames();
    }

    @FXML
    private void handleCustomerChange() {
        BasketService.setCustomer(CreateRentalAdaptor.getCustomer(customerChoiceBox));
    }

    @FXML
    private void handleDateChange() { BasketService.setDate(datePicker.getValue()) ;}

    @FXML
    private void reviewRental(ActionEvent event) throws IOException {
        if (customerChoiceBox.getSelectionModel().isEmpty()) {
            AlertBox.showMessage(Alert.AlertType.ERROR, "Add a customer to the rental");
        } else if (consoleChoiceBox.getSelectionModel().isEmpty()) {
            AlertBox.showMessage(Alert.AlertType.ERROR, "Add a console to the rental");
        } else if (datePicker.getValue() == null) {
            AlertBox.showMessage(Alert.AlertType.ERROR, "You need to pick a date");
        } else if (BasketService.getGames().isEmpty() && !consoleRequired.isSelected()) {
            AlertBox.showMessage(Alert.AlertType.ERROR, "You need to add games or choose to rent the console");
        } else {
            router.changeRoute(RouteNames.VIEW_BASKET, event);
        }
    }

    private void addButtonToTable() {
        CreateRentalAdaptor.addAddButtonToGameTable(buttonColumn);
    }
}
