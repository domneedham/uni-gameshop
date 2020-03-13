package GameShop.java.controllers;

import GameShop.java.models.adaptors.GameTableAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.GameService;
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

public class ViewGamesController implements Initializable {
    private final Router router = new Router();

    @FXML private TableView gameTableView;

    @FXML private CheckBox showAll;

    @FXML private TableColumn idColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn consoleColumn;
    @FXML private TableColumn costColumn;
    @FXML private TableColumn availableColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
        showGames();
    }

    private void setupTable() {
        gameTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        GameTableAdaptor.setIdVales(idColumn);
        GameTableAdaptor.setNameValues(nameColumn);
        GameTableAdaptor.setConsoleValues(consoleColumn);
        GameTableAdaptor.setAvailableValues(availableColumn);
        GameTableAdaptor.setCostValues(costColumn);
    }

    @FXML
    private void handleGoHome(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.HOME, event);
    }

    @FXML
    private void updateList() {
        showGames();
    }

    private void showGames() {
        ObservableList items;
        if (showAll.isSelected()) {
            items = FXCollections.observableArrayList(GameService.getAllGames());
        } else {
            items = FXCollections.observableArrayList(GameService.getAvailableGames());
        }

        gameTableView.getItems().setAll(items);
    }
}
