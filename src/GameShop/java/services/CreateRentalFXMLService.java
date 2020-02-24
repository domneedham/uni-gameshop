package GameShop.java.services;

import GameShop.java.models.Game;

public class CreateRentalFXMLService extends CreateRentalService {

    public static String getId(Game game) {
        return GameFXMLTableService.getId(game);
    }

    public static String getName(Game game) {
        return GameFXMLTableService.getName(game);
    }
}
