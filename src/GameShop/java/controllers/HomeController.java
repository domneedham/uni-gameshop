package GameShop.java.controllers;

import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private Router router = new Router();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleSignIn(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.SIGN_IN, event);
    }

    @FXML
    private void handleViewGames(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.VIEW_GAMES, event);
    }

    @FXML
    private void handleViewConsoles(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.VIEW_CONSOLES, event);
    }

    @FXML
    private void handleQuitApplication() {
        System.exit(0);
    }
}
