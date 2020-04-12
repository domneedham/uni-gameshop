package GameShop.java.controllers;

import GameShop.java.models.adaptors.EditGameAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.GameService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditGameController implements Initializable, ControllerCommunication {
    private final Router router = new Router();
    private String gameId;

    @FXML TextField nameTextField;
    @FXML CheckBox inForRepairCheckBox;

    @Override
    public void passId(String id) {
        gameId = id;
        EditGameAdaptor.getGame(GameService.getById(id), this);
    }

    public void gameDetailsToEdit(String id, String name, boolean inForRepair) {
        nameTextField.setText(name);
        inForRepairCheckBox.setSelected(inForRepair);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @FXML
    public void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.EDIT_GAMES, event);
    }

    @FXML
    public void handleSaveChanges(ActionEvent event) throws IOException {
        GameService.modifyGame(GameService.getById(gameId), nameTextField.getText(), inForRepairCheckBox.isSelected());
        router.changeRoute(RouteNames.EDIT_GAMES, event);
    }
}
