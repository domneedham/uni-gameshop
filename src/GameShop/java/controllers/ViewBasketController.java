package GameShop.java.controllers;

import GameShop.java.models.Basket;
import GameShop.java.models.Game;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
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

    private void setupGameTable() {
        gameTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        idColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(GameFXMLTableService.getId(val.getValue())));
        nameColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(GameFXMLTableService.getName(val.getValue())));
    }

    private void populateData() {
        customer.setText(CreateRentalFXMLService.formatCustomer(Basket.getCustomer()));
        console.setText(CreateRentalFXMLService.formatConsole(Basket.getConsole()));
        consoleRequired.setText(CreateRentalFXMLService.formatConsoleRequired(Basket.isConsoleRequired()));
        gameTableView.getItems().setAll(Basket.getGames());
    }
}
