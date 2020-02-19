package GameShop.java.services;

import GameShop.java.models.Game;
import GameShop.java.repositories.GameRepository;

import java.util.ArrayList;

public class GameService {
    private static GameRepository repo = new GameRepository();

    public static ArrayList<Game> getAllGames() { return  repo.getAllGames(); }

    public static ArrayList<Game> getAvailableGames() { return repo.getAvailableGames(); }

    public static Game getById(String id) { return repo.getById(id); }

    public static boolean idExists(String id) { return repo.getById(id) != null; }

    public static void addGame(Game game) { repo.addGame(game); }

    public static void removeGame(Game game) { repo.removeGame(game); }

    public static void modifyGame(Game game) { repo.modifyGame(game); }

    public static String getId(Game game) { return repo.getFXMLId(game); }
    public static String getName(Game game) { return repo.getFXMLName(game); }
    public static String getConsoleName(Game game) { return repo.getFXMLConsoleName(game); }
    public static String getCost(Game game) { return repo.getFXMLFormattedCost(game); }
    public static String getAvailable(Game game) { return repo.getFXMLAvailable(game); }
}
