package GameShop.java.controllers;

import GameShop.java.controllers.interfaces.ControllerCommunication;
import GameShop.java.controllers.interfaces.ServiceDependency;
import GameShop.java.models.adaptors.EditGameAdaptor;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import GameShop.java.services.interfaces.IGameService;
import GameShop.java.services.interfaces.IService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditGameController implements Initializable, ControllerCommunication, ServiceDependency {
    private final Router router = new Router();
    private IGameService gameService;
    private String gameId;

    @FXML TextField nameTextField;
    @FXML CheckBox inForRepairCheckBox;

    @Override
    public void passId(String id) {
        gameId = id;
        EditGameAdaptor.getGame(gameService.getById(id), this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @Override
    public void assignService(IService service) {
        this.gameService = (IGameService) service;
    }

    @FXML
    public void handleGoBack(ActionEvent event) throws IOException {
        router.changeRoute(RouteNames.EDIT_GAMES, event);
    }

    @FXML
    public void handleSaveChanges(ActionEvent event) throws IOException {
        gameService.modifyGame(gameService.getById(gameId), nameTextField.getText(), inForRepairCheckBox.isSelected());
        router.changeRoute(RouteNames.EDIT_GAMES, event);
    }

    public void gameDetailsToEdit(String id, String name, boolean inForRepair) {
        nameTextField.setText(name);
        inForRepairCheckBox.setSelected(inForRepair);
    }
}
