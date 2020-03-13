package GameShop.java.controllers;

import GameShop.java.models.adaptors.ConsoleTableAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.ConsoleService;
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

public class ViewConsolesController implements Initializable {
    private final Router router = new Router();

    @FXML private TableView consoleTableView;

    @FXML private CheckBox showAll;

    @FXML private TableColumn idColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn formColumn;
    @FXML private TableColumn bitColumn;
    @FXML private TableColumn availableColumn;
    @FXML private TableColumn costColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
        showConsoles();
    }

    private void setupTable() {
        consoleTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ConsoleTableAdaptor.setIdValues(idColumn);
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
            items = FXCollections.observableArrayList(ConsoleService.getAllConsoles());
        } else {
            items = FXCollections.observableArrayList(ConsoleService.getAvailableConsoles());
        }

        consoleTableView.setItems(items);
    }
}
