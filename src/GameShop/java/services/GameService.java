package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.models.Game;
import GameShop.java.repositories.GameRepository;
import GameShop.java.services.interfaces.IGameService;

import java.util.ArrayList;

public class GameService implements IGameService {
    protected static final GameRepository repo = new GameRepository();

    @Override
    public ArrayList<Game> getAllGames() { return repo.getAllGames(); }

    @Override
    public ArrayList<Game> getAvailableGames() { return repo.getAvailableGames(); }

    @Override
    public ArrayList<Game> getAllGamesForConsole(Console console) { return repo.getAllGamesForConsole(console); }

    @Override
    public ArrayList<Game> getAvailableGamesForConsole(Console console) { return repo.getAvailableGamesForConsole(console); }

    @Override
    public Game getById(String id) { return repo.getById(id); }

    @Override
    public boolean idExists(String id) { return repo.getById(id) != null; }

    @Override
    public void addGame(Game game) { repo.addGame(game); }

    @Override
    public void removeGame(Game game) { repo.removeGame(game); }

    @Override
    public void modifyGame(Game game, String newName, boolean inForRepair) {
        game.setName(newName);
        game.setInForRepair(inForRepair);
        repo.modifyGame(game);
    }

    @Override
    public void rentGame(Game game) throws Exception {
        repo.rentGame(game);
    }

    @Override
    public void returnGame(Game game) {
        repo.returnGame(game);
    }
}
