package GameShop.java.controllers;

import GameShop.java.models.Console;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.ConsoleService;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewConsolesController implements Initializable {
    private final Router router = new Router();

    @FXML private TableView<Console> consoles;

    @FXML private CheckBox showAll;

    @FXML private TableColumn<Console, String> idColumn;
    @FXML private TableColumn<Console, String> nameColumn;
    @FXML private TableColumn<Console, String> formColumn;
    @FXML private TableColumn<Console, String> bitColumn;
    @FXML private TableColumn<Console, String> availableColumn;
    @FXML private TableColumn<Console, String> costColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(ConsoleService.getId(val.getValue())));
        nameColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(ConsoleService.getName(val.getValue())));
        formColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(ConsoleService.getForm(val.getValue())));
        bitColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(ConsoleService.getBit(val.getValue())));
        availableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(ConsoleService.getAvailable(val.getValue())));
        costColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(ConsoleService.getFormattedCost(val.getValue())));
        showConsoles();

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

        consoles.setItems(items);
    }
}
