package GameShop.java.services;

import GameShop.java.models.Game;

public class GameFXMLTableService {
    public static String getId(Game game) { return game.getId(); }
    public static String getName(Game game) { return game.getName(); }
    public static String getConsoleName(Game game) { return game.getConsole().getName(); }
    public static String getCost(Game game) { return String.format("£%.2f", game.getCost()); }
    public static String getAvailable(Game game) { return game.isAvailable() ? "Yes" : "No"; }
}