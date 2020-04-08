package GameShop.java.controllers;

import GameShop.java.general.AlertBox;
import GameShop.java.models.adaptors.CreateRentalAdaptor;
import GameShop.java.models.adaptors.GameTableAdaptor;
import GameShop.java.models.helpers.FXMLTableFormat;
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
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateRentalController implements Initializable {
    private final Router router = new Router();

    @FXML private ChoiceBox customerChoiceBox, consoleChoiceBox;
    @FXML private HBox gameTableWrapper;
    @FXML private TableView gameTableView;
    @FXML private CheckBox consoleRequired;
    @FXML private DatePicker datePicker;
    @FXML private Text costText;

    @FXML private TableColumn nameColumn, costColumn, buttonColumn;

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
        GameTableAdaptor.setNameValues(nameColumn);
        GameTableAdaptor.setCostValues(costColumn);
    }

    private void fillDefaults() {
        customerChoiceBox.getSelectionModel().select(BasketService.getCustomer());
        consoleChoiceBox.getSelectionModel().select(BasketService.getConsole());
        consoleRequired.setSelected(BasketService.isConsoleRequired());
        datePicker.setValue(BasketService.getDate());
        costText.setText(FXMLTableFormat.formatCost(BasketService.calculateCost()));
    }

    private void getCustomers() {
        ObservableList items = FXCollections.observableArrayList(CustomerService.getAllCustomers());
        customerChoiceBox.getItems().setAll(items);
    }

    private void getConsoles() {
        ObservableList consoles = FXCollections.observableArrayList(ConsoleService.getAllConsoles());
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
        if (consoleRequired.isSelected() && !consoleChoiceBox.getSelectionModel().isEmpty()) {
            setConsole();
        } else {
            BasketService.unrequireConsole();
            consoleRequired.setSelected(false);
        }
        updateCost();
    }

    @FXML
    private void handleConsoleChange() {
        if (!gameTableWrapper.isVisible()) {
            gameTableWrapper.setVisible(true);
        }

        if (consoleRequired.isSelected()) {
            // ensure console can be rented since customer wants console
            setConsole();
        }

        // if the console is unavailable
        // unrequire the console from the basket
        // uncheck the required box as it can not be rented
        if (!CreateRentalAdaptor.isConsoleAvailable(consoleChoiceBox)) {
            BasketService.unrequireConsole();
            consoleRequired.setSelected(false);
        }
        getGames();
        updateCost();
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
        } else if (datePicker.getValue() == null) {
            AlertBox.showMessage(Alert.AlertType.ERROR, "You need to pick a date");
        } else if (BasketService.getGames().isEmpty() && !consoleRequired.isSelected()) {
            AlertBox.showMessage(Alert.AlertType.ERROR, "You need to add games or choose to rent the console");
        } else {
            router.changeRoute(RouteNames.VIEW_BASKET, event);
        }
    }

    private void addButtonToTable() {
        CreateRentalAdaptor.addAddButtonToGameTable(buttonColumn, this);
    }

    private void setConsole() {
        try {
            BasketService.requireConsole(CreateRentalAdaptor.getConsole(consoleChoiceBox));
        } catch (Exception e) {
            // ensure box does not show as ticked
            consoleRequired.setSelected(false);
            AlertBox.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public void updateCost() {
        costText.setText(FXMLTableFormat.formatCost(BasketService.calculateCost()));
    }
}
