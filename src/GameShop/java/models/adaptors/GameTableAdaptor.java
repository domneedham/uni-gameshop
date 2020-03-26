package GameShop.java.models.adaptors;

import GameShop.java.models.helpers.FXMLTableFormat;
import GameShop.java.models.Game;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;

public class GameTableAdaptor {
    public static void setIdVales(TableColumn<Game, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatString(val.getValue().getId())));
    }

    public static void setNameValues(TableColumn<Game, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatString(val.getValue().getName())));
    }

    public static void setConsoleValues(TableColumn<Game, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatString(val.getValue().getConsole().getName())));
    }

    public static void setCostValues(TableColumn<Game, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatCost(val.getValue().getCost())));
    }

    public static void setAvailableValues(TableColumn<Game, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatBoolean(val.getValue().isAvailable())));
    }
}
