package GameShop.java.controllers;

import GameShop.java.general.AlertBox;
import GameShop.java.models.Basket;
import GameShop.java.models.adaptors.GameTableAdaptor;
import GameShop.java.models.helpers.FXMLTableFormat;
import GameShop.java.models.helpers.RentalFXMLTableFormat;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.BasketService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewBasketController implements Initializable {
    private final Router router = new Router();

    @FXML private Text customer, console, consoleRequired, dateText, costText;
    @FXML private TableView gameTableView;
    @FXML private TableColumn idColumn,  nameColumn, consoleColumn, costColumn;

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
        try {
            BasketService.submitBasket();
            router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
        } catch (Error e) {
            AlertBox.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }

    }

    private void setupGameTable() {
        gameTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        GameTableAdaptor.setIdVales(idColumn);
        GameTableAdaptor.setNameValues(nameColumn);
        GameTableAdaptor.setConsoleValues(consoleColumn);
        GameTableAdaptor.setCostValues(costColumn);
    }

    private void populateData() {
        customer.setText(FXMLTableFormat.formatString(BasketService.getCustomer().getFullName()));
        console.setText(RentalFXMLTableFormat.formatConsoleName(BasketService.getConsole()));
        consoleRequired.setText(FXMLTableFormat.formatBoolean(BasketService.isConsoleRequired()));
        dateText.setText(FXMLTableFormat.formatDate(BasketService.getDate()));
        costText.setText(FXMLTableFormat.formatCost(BasketService.calculateCost()));
        gameTableView.getItems().setAll(BasketService.getGames());
    }
}
