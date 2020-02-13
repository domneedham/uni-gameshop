package GameShop.java.controllers;

import GameShop.java.models.Game;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.GameService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewGamesController implements Initializable {
    private final Router router = new Router();

    @FXML
    private ListView<Game> games;

    @FXML
    private CheckBox showAll;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showGames();
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

        games.setItems(items);
    }
}
