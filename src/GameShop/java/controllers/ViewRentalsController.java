package GameShop.java.controllers;

import GameShop.java.models.Rental;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.RentalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewRentalsController implements Initializable {
    private Router router = new Router();

    @FXML private ListView<Rental> rentalListView;

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showRentals();
    }

    private void showRentals() {
        ObservableList<Rental> rentals;
        rentals = FXCollections.observableArrayList(RentalService.getRentals());
        rentalListView.getItems().setAll(rentals);
    }
}
