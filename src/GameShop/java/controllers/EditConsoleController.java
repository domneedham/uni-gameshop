package GameShop.java.controllers;

import GameShop.java.models.adaptors.EditConsoleAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.ConsoleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditConsoleController implements Initializable, IControllerCommunication {
    private final Router router = new Router();

    @FXML Text idText;
    @FXML TextField nameTextField;
    @FXML CheckBox inForRepairCheckBox;

    @Override
    public void passId(String id) {
        EditConsoleAdaptor.getConsole(ConsoleService.getById(id), this);
    }

    public void consoleDetailsToEdit(String id, String name, boolean inForRepair) {
        idText.setText(id);
        nameTextField.setText(name);
        inForRepairCheckBox.setSelected(inForRepair);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.EDIT_CONSOLES, event);
    }

    @FXML
    public void handleSaveChanges(ActionEvent event) throws IOException {
        ConsoleService.modifyConsole(ConsoleService.getById(idText.getText()), nameTextField.getText(), inForRepairCheckBox.isSelected());
        router.changeRoute(RouteNames.EDIT_CONSOLES, event);
    }
}