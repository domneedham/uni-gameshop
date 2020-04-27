package GameShop.java.controllers;

import GameShop.java.controllers.interfaces.ServiceDependency;
import GameShop.java.models.adaptors.CustomerTableAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.interfaces.ICustomerService;
import GameShop.java.services.interfaces.IService;
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

public class ViewCustomersController implements Initializable, ServiceDependency {
    private final Router router = new Router();
    private ICustomerService customerService;

    @FXML private TableView customerTableView;
    @FXML private TableColumn forenameColumn, surnameColumn, emailColumn, telephoneColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private void setupTable() {
        customerTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        CustomerTableAdaptor.setForenameValues(forenameColumn);
        CustomerTableAdaptor.setSurnameValues(surnameColumn);
        CustomerTableAdaptor.setEmailValues(emailColumn);
        CustomerTableAdaptor.setTelephoneValues(telephoneColumn);
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    private void showCustomers() {
        ObservableList items = FXCollections.observableArrayList(customerService.getAllCustomers());
        customerTableView.getItems().setAll(items);
    }

    @Override
    public void assignService(IService service) {
        this.customerService = (ICustomerService) service;

        setupTable();
        showCustomers();
    }
}