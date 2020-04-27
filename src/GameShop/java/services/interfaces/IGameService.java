package GameShop.java.services.interfaces;

import GameShop.java.models.Console;
import GameShop.java.models.Game;

import java.util.ArrayList;

public interface IGameService extends IService {
    ArrayList<Game> getAllGames();

    ArrayList<Game> getAvailableGames();

    ArrayList<Game> getAllGamesForConsole(Console console);

    ArrayList<Game> getAvailableGamesForConsole(Console console);

    Game getById(String id);

    boolean idExists(String id);

    void addGame(Game game);

    void removeGame(Game game);

    void modifyGame(Game game, String newName, boolean inForRepair);

    void rentGame(Game game);

    void returnGame(Game game);
}
