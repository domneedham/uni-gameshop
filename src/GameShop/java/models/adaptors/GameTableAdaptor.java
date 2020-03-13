package GameShop.java.models.adaptors;

import GameShop.java.models.Game;
import GameShop.java.services.GameFXMLTableService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;

public class GameTableAdaptor {
    public static void setIdVales(TableColumn<Game, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(GameFXMLTableService.getId(val.getValue())));
    }

    public static void setNameValues(TableColumn<Game, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(GameFXMLTableService.getName(val.getValue())));
    }

    public static void setConsoleValues(TableColumn<Game, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(GameFXMLTableService.getConsoleName(val.getValue())));
    }

    public static void setCostValues(TableColumn<Game, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(GameFXMLTableService.getCost(val.getValue())));
    }

    public static void setAvailableValues(TableColumn<Game, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(GameFXMLTableService.getAvailable(val.getValue())));
    }





}
