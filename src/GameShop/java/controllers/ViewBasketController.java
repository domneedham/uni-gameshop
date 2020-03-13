package GameShop.java.controllers;

import GameShop.java.models.Game;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.BasketService;
import GameShop.java.services.CreateRentalFXMLService;
import GameShop.java.services.GameFXMLTableService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewBasketController implements Initializable {
    private Router router = new Router();

    @FXML private Text customer;
    @FXML private Text console;
    @FXML private Text consoleRequired;
    @FXML private Text dateText;
    @FXML private TableView<Game> gameTableView;
    @FXML private TableColumn<Game, String> idColumn;
    @FXML private TableColumn<Game, String> nameColumn;

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.CREATE_RENTAL, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupGameTable();
        populateData();
    }

    @FXML
    private void submitRental(ActionEvent event) throws IOException {
        BasketService.submitBasket();
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    private void setupGameTable() {
        gameTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        idColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(GameFXMLTableService.getId(val.getValue())));
        nameColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(GameFXMLTableService.getName(val.getValue())));
    }

    private void populateData() {
        customer.setText(CreateRentalFXMLService.formatCustomer(BasketService.getCustomer()));
        console.setText(CreateRentalFXMLService.formatConsole(BasketService.getConsole()));
        consoleRequired.setText(CreateRentalFXMLService.formatConsoleRequired(BasketService.isConsoleRequired()));
        dateText.setText(BasketService.getDate().toString());
        gameTableView.getItems().setAll(BasketService.getGames());
    }
}
