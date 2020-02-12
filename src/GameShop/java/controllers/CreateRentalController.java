package GameShop.java.controllers;

import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateRentalController implements Initializable {
    private Router router = new Router();

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}
