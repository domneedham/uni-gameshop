package GameShop.java.models.adaptors;

import GameShop.java.models.Console;
import GameShop.java.services.ConsoleFXMLTableService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;

public class ConsoleTableAdaptor {
    public static void setIdValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(ConsoleFXMLTableService.getId(val.getValue())));
    }

    public static void setNameValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(ConsoleFXMLTableService.getName(val.getValue())));
    }

    public static void setFormValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(ConsoleFXMLTableService.getForm(val.getValue())));
    }

    public static void setBitValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(ConsoleFXMLTableService.getBit(val.getValue())));
    }

    public static void setAvailableValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(ConsoleFXMLTableService.getAvailable(val.getValue())));
    }

    public static void setCostValues(TableColumn<Console, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper(ConsoleFXMLTableService.getFormattedCost(val.getValue())));
    }
}
