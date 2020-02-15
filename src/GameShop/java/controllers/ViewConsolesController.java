package GameShop.java.controllers;

import GameShop.java.models.Console;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.ConsoleService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewConsolesController implements Initializable {
    private final Router router = new Router();

    @FXML private TableView<Console> consoles;

    @FXML private CheckBox showAll;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
