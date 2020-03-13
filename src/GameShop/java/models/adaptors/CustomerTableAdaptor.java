package GameShop.java.models.adaptors;

import GameShop.java.models.Customer;
import GameShop.java.services.CustomerFXMLTableService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;

public class CustomerTableAdaptor {
    public static void setIdValues(TableColumn<Customer, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CustomerFXMLTableService.getId(val.getValue())));
    }

    public static void setForenameValues(TableColumn<Customer, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CustomerFXMLTableService.getForename(val.getValue())));
    }

    public static void setSurnameValues(TableColumn<Customer, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CustomerFXMLTableService.getSurname(val.getValue())));
    }

    public static void setEmailValues(TableColumn<Customer, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CustomerFXMLTableService.getEmail(val.getValue())));
    }

    public static void setTelephoneValues(TableColumn<Customer, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(CustomerFXMLTableService.getTelephone(val.getValue())));
    }
}
