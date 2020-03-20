package GameShop.java.models.adaptors;

import GameShop.java.controllers.EditGameController;
import GameShop.java.models.Game;

public class EditGameAdaptor {
    public static void getGame(Game game, EditGameController controller) {
        controller.gameDetailsToEdit(game.getId(), game.getName(), game.isInForRepair());
    }
}
