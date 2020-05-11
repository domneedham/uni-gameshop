package GameShop.java.controllers;

import GameShop.java.controllers.interfaces.MultiServiceDependency;
import GameShop.java.models.adaptors.GameTableAdaptor;
import GameShop.java.models.adaptors.ViewGamesAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.interfaces.IConsoleService;
import GameShop.java.services.interfaces.IGameService;
import GameShop.java.services.interfaces.IService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewGamesController implements Initializable, MultiServiceDependency {
    private final Router router = new Router();
    private IGameService gameService;
    private IConsoleService consoleService;

    @FXML private HBox consoleChoiceWrapper;
    @FXML private CheckBox specificConsole;
    @FXML private ChoiceBox consoleChoiceBox;
    @FXML private TableView gameTableView;
    @FXML private CheckBox showAll;
    @FXML private TableColumn nameColumn, consoleColumn, costColumn, availableColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void assignServices(IService[] services) {
        for (var service : services) {
            if (service instanceof IGameService) {
                this.gameService = (IGameService) service;
            }
            if (service instanceof IConsoleService) {
                this.consoleService = (IConsoleService) service;
            }
        }

        setupTable();
        showGames();
        getConsoles();
    }

    @FXML
    private void handleGoHome(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.HOME, event);
    }

    @FXML
    private void updateList() {
        showGames();
    }

    @FXML
    private void handleSpecificConsoleToggle() {
        consoleChoiceWrapper.setVisible(specificConsole.isSelected());

        // reset table to show all games if specific console is turned off
        if (!specificConsole.isSelected()) {
            showGames();
        }
    }

    @FXML
    private void handleConsoleChange() {
        if (specificConsole.isSelected()) {
            ObservableList items;
            if (showAll.isSelected()) {
                items = FXCollections.observableArrayList(gameService.getAllGamesForConsole(ViewGamesAdaptor.getSelectedConsole(consoleChoiceBox)));
            } else {
                items = FXCollections.observableArrayList(gameService.getAvailableGamesForConsole(ViewGamesAdaptor.getSelectedConsole(consoleChoiceBox)));
            }

            gameTableView.getItems().setAll(items);
        } else {
            showGames();
        }
    }

    private void showGames() {
        // if specific console is selected
        // call method that handles games to view when a console is selected
        if (specificConsole.isSelected()) {
            handleConsoleChange();
            // return to cancel overriding items
            return;
        }

        ObservableList items;
        if (showAll.isSelected()) {
            items = FXCollections.observableArrayList(gameService.getAllGames());
        } else {
            items = FXCollections.observableArrayList(gameService.getAvailableGames());
        }

        gameTableView.getItems().setAll(items);
    }

    private void setupTable() {
        gameTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        GameTableAdaptor.setNameValues(nameColumn);
        GameTableAdaptor.setConsoleValues(consoleColumn);
        GameTableAdaptor.setAvailableValues(availableColumn);
        GameTableAdaptor.setCostValues(costColumn);
    }

    private void getConsoles() {
        ObservableList items = FXCollections.observableArrayList(consoleService.getAllConsoles());
        consoleChoiceBox.getItems().setAll(items);
    }
}
