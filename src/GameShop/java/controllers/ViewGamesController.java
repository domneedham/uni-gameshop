package GameShop.java.controllers;

import GameShop.java.models.Game;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.ConsoleService;
import GameShop.java.services.GameFXMLTableService;
import GameShop.java.services.GameService;
import javafx.beans.property.ReadOnlyObjectWrapper;
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

    @FXML private TableView<Game> gameTableView;

    @FXML private CheckBox showAll;

    @FXML private TableColumn<Game, String> idColumn;
    @FXML private TableColumn<Game, String> nameColumn;
    @FXML private TableColumn<Game, String> consoleColumn;
    @FXML private TableColumn<Game, String> costColumn;
    @FXML private TableColumn<Game, String> availableColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(GameFXMLTableService.getId(val.getValue())));
        nameColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(GameFXMLTableService.getName(val.getValue())));
        consoleColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(GameFXMLTableService.getConsoleName(val.getValue())));
        costColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(GameFXMLTableService.getCost(val.getValue())));
        availableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(GameFXMLTableService.getAvailable(val.getValue())));
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
        ObservableList<Game> items;
        if (showAll.isSelected()) {
            items = FXCollections.observableArrayList(GameService.getAllGames());
        } else {
            items = FXCollections.observableArrayList(GameService.getAvailableGames());
        }

        gameTableView.getItems().setAll(items);
    }
}
