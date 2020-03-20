package GameShop.java.models.adaptors;

import GameShop.java.general.AlertBox;
import GameShop.java.models.Game;
import GameShop.java.routers.RouteNames;
import GameShop.java.routers.Router;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.io.IOException;

public class EditGamesAdaptor {
    public static void addButtonToTable(TableColumn<Game, Button> tableColumn, Router router) {
        Callback<TableColumn<Game, Button>, TableCell<Game, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Game, Button> call(final TableColumn<Game, Button> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Edit");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Game game = getTableView().getItems().get(getIndex());
                            try {
                                router.changeRouteActivated(RouteNames.EDIT_GAME, event, game.getId());
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
