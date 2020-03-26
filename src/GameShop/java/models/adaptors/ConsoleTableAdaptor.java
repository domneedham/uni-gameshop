package GameShop.java.models.adaptors;

import GameShop.java.models.helpers.FXMLTableFormat;
import GameShop.java.models.Console;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;

public class ConsoleTableAdaptor {
    public static void setIdValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatString(val.getValue().getId())));
    }

    public static void setNameValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatString(val.getValue().getName())));
    }

    public static void setFormValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatEnum(val.getValue().getForm())));
    }

    public static void setBitValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatInt(val.getValue().getBit())));
    }

    public static void setAvailableValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatBoolean(val.getValue().isAvailable())));
    }

    public static void setCostValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatCost(val.getValue().getCost())));
    }
}
