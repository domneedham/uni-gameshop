package GameShop.java.controllers;

import GameShop.java.models.Customer;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewCustomersController implements Initializable {
    private final Router router = new Router();

    @FXML
    private TableView<Customer> customers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCustomers();
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    private void showCustomers() {
        ObservableList<Customer> items = FXCollections.observableArrayList(CustomerService.getAllCustomers());
        customers.getItems().setAll(items);
    }
}