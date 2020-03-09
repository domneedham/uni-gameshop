package GameShop.java.services;

import GameShop.java.models.Game;

public class GameFXMLTableService extends GameService {
    public static String getId(Game game) { return repo.getId(game); }
    public static String getName(Game game) { return repo.getName(game); }
    public static String getConsoleName(Game game) { return repo.getConsole(game).getName(); }
    public static String getCost(Game game) { return String.format("£%.2f", repo.getCost(game)); }
    public static String getAvailable(Game game) { return repo.isInForRepair(game) ? "No" : "Yes"; }
}