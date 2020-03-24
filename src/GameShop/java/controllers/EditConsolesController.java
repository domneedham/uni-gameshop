package GameShop.java.controllers;

import GameShop.java.models.adaptors.ConsoleTableAdaptor;
import GameShop.java.models.adaptors.EditConsolesAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.ConsoleService;
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

public class EditConsolesController implements Initializable {
    private final Router router = new Router();

    @FXML private TableView consoleTableView;
    @FXML private TableColumn idColumn, nameColumn, formColumn, bitColumn, availableColumn, costColumn, editConsoleColumn;

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
        addButtonsToTable();
    }

    @FXML
    private void handleGoHome(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    private void showConsoles() {
        ObservableList items = FXCollections.observableArrayList(ConsoleService.getAllConsoles());
        consoleTableView.setItems(items);
    }

    private void addButtonsToTable() {
        EditConsolesAdaptor.addButtonToTable(editConsoleColumn, router);
    }
}
