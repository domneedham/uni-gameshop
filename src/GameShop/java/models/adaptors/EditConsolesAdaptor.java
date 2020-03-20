package GameShop.java.models.adaptors;

import GameShop.java.general.AlertBox;
import GameShop.java.models.Console;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.io.IOException;

public class EditConsolesAdaptor {
    public static void addButtonToTable(TableColumn<Console, Button> tableColumn, Router router) {
        Callback<TableColumn<Console, Button>, TableCell<Console, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Console, Button> call(final TableColumn<Console, Button> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Edit");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Console console = getTableView().getItems().get(getIndex());
                            try {
                                router.changeRouteActivated(RouteNames.EDIT_CONSOLE, event, console.getId());
                            } catch (IOException e) {
                                AlertBox.showMessage(Alert.AlertType.ERROR, e.toString());
                            }
                        });
                    }

                    @Override
                    public void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        tableColumn.setCellFactory(cellFactory);
    }
}
