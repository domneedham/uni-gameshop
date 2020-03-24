package GameShop.java.models.adaptors;

import GameShop.java.models.Console;
import javafx.scene.control.ChoiceBox;

public class ViewGamesAdaptor {
    public static Console getSelectedConsole(ChoiceBox<Console> consoleChoiceBox) {
        return consoleChoiceBox.getSelectionModel().getSelectedItem();
    }
}
