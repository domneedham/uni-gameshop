package GameShop.java.controllers;

import GameShop.java.controllers.interfaces.ServiceDependency;
import GameShop.java.models.adaptors.EditGamesAdaptor;
import GameShop.java.models.adaptors.GameTableAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.interfaces.IGameService;
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

public class EditGamesController implements Initializable, ServiceDependency {
    private final Router router = new Router();
    private IGameService gameService;

    @FXML private TableView gameTableView;
    @FXML private TableColumn nameColumn, consoleColumn, costColumn, availableColumn, editGameColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    private void setupTable() {
        gameTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
        ObservableList items = FXCollections.observableArrayList(gameService.getAllGames());
        gameTableView.getItems().setAll(items);
    }

    private void addButtonsToTable() {
        EditGamesAdaptor.addButtonToTable(editGameColumn, router);
    }

    @Override
    public void assignService(IService service) {
        this.gameService = (IGameService) service;
        setupTable();
        showGames();
    }
}
