package GameShop.java.controllers;

import GameShop.java.controllers.interfaces.ServiceDependency;
import GameShop.java.general.AlertBox;
import GameShop.java.models.adaptors.GameTableAdaptor;
import GameShop.java.models.helpers.FXMLTableFormat;
import GameShop.java.models.helpers.RentalFXMLTableFormat;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.interfaces.IBasketService;
import GameShop.java.services.interfaces.IService;
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

public class ViewBasketController implements Initializable, ServiceDependency {
    private final Router router = new Router();
    private IBasketService basketService;

    @FXML private Text customer, console, consoleRequired, dateText, costText;
    @FXML private TableView gameTableView;
    @FXML private TableColumn nameColumn, consoleColumn, costColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void assignService(IService service) {
        this.basketService = (IBasketService) service;

        setupGameTable();
        populateData();
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.CREATE_RENTAL, event);
    }


    @FXML
    private void submitRental(ActionEvent event) {
        try {
            basketService.submitBasket();
            router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
        } catch (Error | Exception e) {
            AlertBox.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }

    }

    private void setupGameTable() {
        gameTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        GameTableAdaptor.setNameValues(nameColumn);
        GameTableAdaptor.setConsoleValues(consoleColumn);
        GameTableAdaptor.setCostValues(costColumn);
    }

    private void populateData() {
        customer.setText(FXMLTableFormat.formatString(basketService.getCustomer().getFullName()));
        console.setText(RentalFXMLTableFormat.formatConsoleName(basketService.getConsole()));
        consoleRequired.setText(FXMLTableFormat.formatBoolean(basketService.isConsoleRequired()));
        dateText.setText(FXMLTableFormat.formatDate(basketService.getDate()));
        costText.setText(FXMLTableFormat.formatCost(basketService.calculateCost()));
        gameTableView.getItems().setAll(basketService.getGames());
    }
}
