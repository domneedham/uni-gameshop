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
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditGameController implements Initializable, IControllerCommunication {
    private final Router router = new Router();

    @FXML Text idText;
    @FXML TextField nameTextField;
    @FXML CheckBox inForRepairCheckBox;

    @Override
    public void passId(String id) {
        EditGameAdaptor.getGame(GameService.getById(id), this);
    }

    public void gameDetailsToEdit(String id, String name, boolean inForRepair) {
        idText.setText(id);
        nameTextField.setText(name);
        inForRepairCheckBox.setSelected(inForRepair);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.EDIT_GAMES, event);
    }

    @FXML
    public void handleSaveChanges(ActionEvent event) throws IOException {
        GameService.modifyGame(GameService.getById(idText.getText()), nameTextField.getText(), inForRepairCheckBox.isSelected());
        router.changeRoute(RouteNames.EDIT_GAMES, event);
    }
}
