package GameShop.java.controllers;

import GameShop.java.controllers.interfaces.MultiServiceDependency;
import GameShop.java.models.adaptors.ViewRentalAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.interfaces.ICustomerService;
import GameShop.java.services.interfaces.IRentalService;
import GameShop.java.services.interfaces.IService;
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

public class ViewRentalsController implements Initializable, MultiServiceDependency {
    private final Router router = new Router();
    private IRentalService rentalService;
    private ICustomerService customerService;

    @FXML private HBox customerChoiceWrapper;
    @FXML private CheckBox specificCustomer;
    @FXML private ChoiceBox customerChoiceBox;
    @FXML private TableView rentalTableView;
    @FXML private TableColumn customerColumn, dateRentedColumn, dateDueColumn, consoleColumn, gamesColumn, costColumn, buttonColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void assignServices(IService[] services) {
        for (var service : services) {
            if (service instanceof IRentalService) {
                this.rentalService = (IRentalService) service;
            }
            if (service instanceof ICustomerService) {
                this.customerService = (ICustomerService) service;
            }
        }

        setupTable();
        showRentals();
        getAllCustomers();
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
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
            ObservableList rentals = FXCollections.observableArrayList(rentalService.getRentalsForCustomer(ViewRentalAdaptor.getSelectedCustomer(customerChoiceBox)));
            rentalTableView.getItems().setAll(rentals);
        } else {
            showRentals();
        }
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
        ObservableList rentals = FXCollections.observableArrayList(rentalService.getRentals());
        rentalTableView.getItems().setAll(rentals);
    }

    private void getAllCustomers() {
        ObservableList items = FXCollections.observableArrayList(customerService.getAllCustomers());
        customerChoiceBox.getItems().setAll(items);
    }
}
