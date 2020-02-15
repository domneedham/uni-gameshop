package GameShop.java.controllers;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.ConsoleService;
import GameShop.java.services.CustomerService;
import GameShop.java.services.GameService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateRentalController implements Initializable {
    private Router router = new Router();

    @FXML private ChoiceBox<Customer> customerChoiceBox;
    @FXML private ChoiceBox<Console> consoleChoiceBox;
    @FXML private TableView<Game> gameTableView;

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        getCustomers();
        getConsoles();
    }

    private void getCustomers() {
        ObservableList<Customer> items = FXCollections.observableArrayList(CustomerService.getAllCustomers());
        customerChoiceBox.getItems().setAll(items);
    }

    private void getConsoles() {
        ObservableList<Console> items = FXCollections.observableArrayList(ConsoleService.getAvailableConsoles());
        consoleChoiceBox.getItems().setAll(items);
    }

    private void getGames() {
        ArrayList<Game> allGames = GameService.getAvailableGames();
        ObservableList<Game> items = FXCollections.observableArrayList();

        for (Game g: allGames) {
            if (g.getConsole().getId().equals(consoleChoiceBox.getSelectionModel().getSelectedItem().getId())) {
                items.add(g);
            }
        }
        gameTableView.getItems().setAll(items);
    }

    @FXML
    private void handleConsoleChange() {
        getGames();
    }

    @FXML
    private void logGames() {
        ObservableList<Game> selectedGames = gameTableView.getSelectionModel().getSelectedItems();
        System.out.println(selectedGames);
        System.out.println(selectedGames.size());
    }

}
