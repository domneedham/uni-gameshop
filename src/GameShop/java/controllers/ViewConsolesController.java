package GameShop.java.controllers;

import GameShop.java.controllers.interfaces.ServiceDependency;
import GameShop.java.models.adaptors.ConsoleTableAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.interfaces.IConsoleService;
import GameShop.java.services.interfaces.IService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewConsolesController implements Initializable, ServiceDependency {
    private final Router router = new Router();
    private IConsoleService consoleService;

    @FXML private TableView consoleTableView;

    @FXML private CheckBox showAll;

    @FXML private TableColumn nameColumn, formColumn, bitColumn, availableColumn, costColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private void setupTable() {
        consoleTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ConsoleTableAdaptor.setNameValues(nameColumn);
        ConsoleTableAdaptor.setFormValues(formColumn);
        ConsoleTableAdaptor.setBitValues(bitColumn);
        ConsoleTableAdaptor.setAvailableValues(availableColumn);
        ConsoleTableAdaptor.setCostValues(costColumn);
    }

    @FXML
    private void handleGoHome(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.HOME, event);
    }

    @FXML private void updateList() {
        showConsoles();
    }

    private void showConsoles() {
        ObservableList items;
        if (showAll.isSelected()) {
            items = FXCollections.observableArrayList(consoleService.getAllConsoles());
        } else {
            items = FXCollections.observableArrayList(consoleService.getAvailableConsoles());
        }

        consoleTableView.setItems(items);
    }

    @Override
    public void assignService(IService service) {
        this.consoleService = (IConsoleService) service;

        setupTable();
        showConsoles();
    }
}
