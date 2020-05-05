package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.models.Game;
import GameShop.java.repositories.GameRepository;

import java.util.ArrayList;

public class GameService {
    protected static final GameRepository repo = new GameRepository();

    public static ArrayList<Game> getAllGames() { return repo.getAllGames(); }

    public static ArrayList<Game> getAvailableGames() { return repo.getAvailableGames(); }

    public static ArrayList<Game> getAllGamesForConsole(Console console) { return repo.getAllGamesForConsole(console); }

    public static ArrayList<Game> getAvailableGamesForConsole(Console console) { return repo.getAvailableGamesForConsole(console); }

    public static Game getById(String id) { return repo.getById(id); }

    public static boolean idExists(String id) { return repo.getById(id) != null; }

    public static void addGame(Game game) { repo.addGame(game); }

    public static void removeGame(Game game) { repo.removeGame(game); }

    public static void modifyGame(Game game, String newName, boolean inForRepair) {
        game.setName(newName);
        game.setInForRepair(inForRepair);
        repo.modifyGame(game);
    }

    public static void rentGame(Game game) throws Exception {
        repo.rentGame(game);
    }

    public static void returnGame(Game game) {
        repo.returnGame(game);
    }
}
