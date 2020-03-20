package GameShop.java.controllers;

import GameShop.java.models.adaptors.EditGamesAdaptor;
import GameShop.java.models.adaptors.GameTableAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.GameService;
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

public class EditGamesController implements Initializable {
    private final Router router = new Router();

    @FXML private TableView gameTableView;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn consoleColumn;
    @FXML private TableColumn costColumn;
    @FXML private TableColumn availableColumn;
    @FXML private TableColumn editGameColumn;

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
        addButtonsToTable();
    }

    @FXML
    private void handleGoHome(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    private void showGames() {
        ObservableList items = FXCollections.observableArrayList(GameService.getAllGames());
        gameTableView.getItems().setAll(items);
    }

    private void addButtonsToTable() {
        EditGamesAdaptor.addButtonToTable(editGameColumn, router);
    }
}
