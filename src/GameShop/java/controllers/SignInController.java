package GameShop.java.controllers;

import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    private Router router = new Router();

    private final String adminUsername = "admin";
    private final String adminPassword = "admin";

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    // set text to show error if sign is wrong
    private Label signInError;

    @FXML
    private void clearForm() {
        username.clear();
        password.clear();
        signInError.setText("");
    }

    @FXML
    private void handleSignIn(ActionEvent event) throws IOException {
        if (username.getText().equals(adminUsername) && password.getText().equals(adminPassword)) {
            router.changeRoute(RouteNames.SHOP_KEEPER_HOME, event);
        } else {
            signInError.setText("Incorrect sign in details");
        }
    }

    @FXML
    private void handleGoHome(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.HOME, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}
