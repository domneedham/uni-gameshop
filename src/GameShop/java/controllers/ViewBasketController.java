package GameShop.java.controllers;

import GameShop.java.models.adaptors.GameTableAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.BasketService;
import GameShop.java.services.CreateRentalFXMLService;
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
    private final Router router = new Router();

    @FXML private Text customer, console, consoleRequired, dateText;
    @FXML private TableView gameTableView;
    @FXML private TableColumn idColumn,  nameColumn;

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
        GameTableAdaptor.setIdVales(idColumn);
        GameTableAdaptor.setNameValues(nameColumn);
    }

    private void populateData() {
        customer.setText(CreateRentalFXMLService.formatCustomer(BasketService.getCustomer()));
        console.setText(CreateRentalFXMLService.formatConsole(BasketService.getConsole()));
        consoleRequired.setText(CreateRentalFXMLService.formatConsoleRequired(BasketService.isConsoleRequired()));
        dateText.setText(BasketService.getDate().toString());
        gameTableView.getItems().setAll(BasketService.getGames());
    }
}
