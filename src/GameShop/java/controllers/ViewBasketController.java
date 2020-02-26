package GameShop.java.controllers;

import GameShop.java.models.Basket;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewBasketController implements Initializable {
    private Router router = new Router();

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.CREATE_RENTAL, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(Basket.logBasket());
    }
}
