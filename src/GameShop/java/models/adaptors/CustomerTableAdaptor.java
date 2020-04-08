package GameShop.java.models.adaptors;

import GameShop.java.models.helpers.FXMLTableFormat;
import GameShop.java.models.Customer;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;

public class CustomerTableAdaptor {
    public static void setIdValues(TableColumn<Customer, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatString(val.getValue().getId())));
    }

    public static void setForenameValues(TableColumn<Customer, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatString(val.getValue().getForename())));
    }

    public static void setSurnameValues(TableColumn<Customer, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatString(val.getValue().getSurname())));
    }

    public static void setEmailValues(TableColumn<Customer, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatString(val.getValue().getEmail())));
    }

    public static void setTelephoneValues(TableColumn<Customer, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatString(val.getValue().getTelNumber())));
    }
}
