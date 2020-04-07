package GameShop.java.models.adaptors;

import GameShop.java.general.AlertBox;
import GameShop.java.models.helpers.FXMLTableFormat;
import GameShop.java.models.Customer;
import GameShop.java.models.Rental;
import GameShop.java.models.helpers.RentalFXMLTableFormat;
import GameShop.java.services.RentalService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Callback;

public class ViewRentalAdaptor {
    public static void setCustomerValues(TableColumn<Rental, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatString(val.getValue().getCustomer().getFullName())));
    }

    public static void setDateRentedValues(TableColumn<Rental, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatDate(val.getValue().getDateRented())));
    }

    public static void setDateDueValues(TableColumn<Rental, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatDate(val.getValue().getDateDue())));
    }

    public static void setConsoleValues(TableColumn<Rental, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(RentalFXMLTableFormat.formatConsoleName(val.getValue().getConsole())));
    }

    public static void setGameValues(TableColumn<Rental, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatArrayList(val.getValue().getGames())));
    }

    public static void setCostValues(TableColumn<Rental, String> tableColumn) {
        tableColumn.setCellValueFactory(val -> new ReadOnlyObjectWrapper<>(FXMLTableFormat.formatCost(val.getValue().calculateCost())));
    }

    public static void addButtonToTable(TableColumn<Rental, Button> tableColumn) {
        Callback<TableColumn<Rental, Button>, TableCell<Rental, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Rental, Button> call(final TableColumn<Rental, Button> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Return");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Rental rental = getTableView().getItems().get(getIndex());
                            RentalService.returnRental(rental);
                            btn.setText("Already Returned");
                            btn.setDisable(true);
                            AlertBox.showMessage(Alert.AlertType.CONFIRMATION, "Returned the product!");
                        });
                    }

                    @Override
                    public void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Rental rental = param.getTableView().getItems().get(getIndex());
                            if (rental.isReturned()) {
                                btn.setText("Already Returned");
                                btn.setDisable(true);
                            }
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        tableColumn.setCellFactory(cellFactory);
    }

    public static Customer getSelectedCustomer(ChoiceBox<Customer> customerChoiceBox) {
        return customerChoiceBox.getSelectionModel().getSelectedItem();
    }
}
