package GameShop.java.controllers;

import GameShop.java.models.adaptors.ViewRentalAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.CustomerService;
import GameShop.java.services.RentalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewRentalsController implements Initializable {
    private final Router router = new Router();

    @FXML private HBox customerChoiceWrapper;

    @FXML private CheckBox specificCustomer;
    @FXML private ChoiceBox customerChoiceBox;

    @FXML private TableView rentalTableView;
    @FXML private TableColumn customerColumn, dateRentedColumn, dateDueColumn, consoleColumn, gamesColumn, costColumn, buttonColumn;

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
        showRentals();
        getAllCustomers();
    }

    private void setupTable() {
        rentalTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ViewRentalAdaptor.setCustomerValues(customerColumn);
        ViewRentalAdaptor.setDateRentedValues(dateRentedColumn);
        ViewRentalAdaptor.setDateDueValues(dateDueColumn);
        ViewRentalAdaptor.setConsoleValues(consoleColumn);
        ViewRentalAdaptor.setGameValues(gamesColumn);
        ViewRentalAdaptor.setCostValues(costColumn);
        addButtonToTable();
    }

    private void addButtonToTable() {
        ViewRentalAdaptor.addButtonToTable(buttonColumn);
    }

    private void showRentals() {
        ObservableList rentals = FXCollections.observableArrayList(RentalService.getRentals());
        rentalTableView.getItems().setAll(rentals);
    }

    private void getAllCustomers() {
        ObservableList items = FXCollections.observableArrayList(CustomerService.getAllCustomers());
        customerChoiceBox.getItems().setAll(items);
    }

    @FXML
    private void handleSpecificCustomerToggle() {
        customerChoiceWrapper.setVisible(specificCustomer.isSelected());

        // reset table to show all customers if specific customer is turned off
        if (!specificCustomer.isSelected()) {
            showRentals();
        }
    }

    @FXML
    private void handleCustomerChange() {
        if (specificCustomer.isSelected()) {
            ObservableList rentals = FXCollections.observableArrayList(RentalService.getRentalsForCustomer(ViewRentalAdaptor.getSelectedCustomer(customerChoiceBox)));
            rentalTableView.getItems().setAll(rentals);
        } else {
            showRentals();
        }
    }
}
