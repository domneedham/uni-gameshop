package GameShop.java.controllers;

import GameShop.java.controllers.interfaces.ControllerCommunication;
import GameShop.java.controllers.interfaces.ServiceDependency;
import GameShop.java.models.adaptors.EditConsoleAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.interfaces.IConsoleService;
import GameShop.java.services.interfaces.IService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditConsoleController implements Initializable, ControllerCommunication, ServiceDependency {
    private final Router router = new Router();
    private IConsoleService consoleService;
    private String gameId;

    @FXML TextField nameTextField;
    @FXML CheckBox inForRepairCheckBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @Override
    public void passId(String id) {
        gameId = id;
        EditConsoleAdaptor.getConsole(consoleService.getById(id), this);
    }

    @Override
    public void assignService(IService service) {
        this.consoleService = (IConsoleService) service;
    }

    @FXML
    public void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.EDIT_CONSOLES, event);
    }

    @FXML
    public void handleSaveChanges(ActionEvent event) throws IOException {
        consoleService.modifyConsole(consoleService.getById(gameId), nameTextField.getText(), inForRepairCheckBox.isSelected());
        router.changeRoute(RouteNames.EDIT_CONSOLES, event);
    }

    public void consoleDetailsToEdit(String id, String name, boolean inForRepair) {
        nameTextField.setText(name);
        inForRepairCheckBox.setSelected(inForRepair);
    }
}
