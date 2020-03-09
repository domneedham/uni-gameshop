package GameShop.java.controllers;

import GameShop.java.models.Customer;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.CustomerFXMLTableService;
import GameShop.java.services.CustomerService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewCustomersController implements Initializable {
    private final Router router = new Router();

    @FXML private TableView<Customer> customerTableView;
    @FXML private TableColumn<Customer, String> idColumn;
    @FXML private TableColumn<Customer, String> forenameColumn;
    @FXML private TableColumn<Customer, String> surnameColumn;
    @FXML private TableColumn<Customer, String> emailColumn;
    @FXML private TableColumn<Customer, String> telephoneColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CustomerFXMLTableService.getId(val.getValue())));
        forenameColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CustomerFXMLTableService.getForename(val.getValue())));
        surnameColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CustomerFXMLTableService.getSurname(val.getValue())));
        emailColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CustomerFXMLTableService.getEmail(val.getValue())));
        telephoneColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CustomerFXMLTableService.getTelephone(val.getValue())));
        showCustomers();

    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    private void showCustomers() {
        ObservableList<Customer> items = FXCollections.observableArrayList(CustomerService.getAllCustomers());
        customerTableView.getItems().setAll(items);
    }
}