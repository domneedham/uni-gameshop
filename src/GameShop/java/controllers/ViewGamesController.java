package GameShop.java.controllers;

import GameShop.java.models.adaptors.GameTableAdaptor;
import GameShop.java.models.adaptors.ViewGamesAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.ConsoleService;
import GameShop.java.services.GameService;
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

public class ViewGamesController implements Initializable {
    private final Router router = new Router();

    @FXML private HBox consoleChoiceWrapper;

    @FXML private CheckBox specificConsole;
    @FXML private ChoiceBox consoleChoiceBox;

    @FXML private TableView gameTableView;

    @FXML private CheckBox showAll;

    @FXML private TableColumn idColumn, nameColumn, consoleColumn, costColumn, availableColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
        showGames();
        getConsoles();
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
        // if specific console is selected
        // call method that handles games to view when a console is selected
        if (specificConsole.isSelected()) {
            handleConsoleChange();
            // return to cancel overriding items
            return;
        }

        ObservableList items;
        if (showAll.isSelected()) {
            items = FXCollections.observableArrayList(GameService.getAllGames());
        } else {
            items = FXCollections.observableArrayList(GameService.getAvailableGames());
        }

        gameTableView.getItems().setAll(items);
    }

    private void getConsoles() {
        ObservableList items = FXCollections.observableArrayList(ConsoleService.getAllConsoles());
        consoleChoiceBox.getItems().setAll(items);
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
                items = FXCollections.observableArrayList(GameService.getAllGamesForConsole(ViewGamesAdaptor.getSelectedConsole(consoleChoiceBox)));
            } else {
                items = FXCollections.observableArrayList(GameService.getAvailableGamesForConsole(ViewGamesAdaptor.getSelectedConsole(consoleChoiceBox)));
            }

            gameTableView.getItems().setAll(items);
        } else {
            showGames();
        }
    }
}
