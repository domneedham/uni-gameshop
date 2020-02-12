package GameShop.java.controllers;

import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewGamesController implements Initializable {
    private final Router router = new Router();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void handleGoHome(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.HOME, event);
    }
}
