package GameShop.java.repositories;

import GameShop.App;
import GameShop.java.models.Console;
import GameShop.java.models.Game;

import java.util.ArrayList;

public class GameRepository {
    public ArrayList<Game> getAllGames() { return App.state.getGames(); }

    public ArrayList<Game> getAvailableGames() {
        ArrayList<Game> availableGames = new ArrayList<>();
        for (Game g: getAllGames()) {
            if (g.isAvailable()) {
                availableGames.add(g);
            }
        }
        return availableGames;
    }

    public ArrayList<Game> getAllGamesForConsole(Console console) {
        ArrayList<Game> gamesForConsole = new ArrayList<>();
        for (Game g: getAllGames()) {
            if (console == g.getConsole()) {
                gamesForConsole.add(g);
            }
        }
        return gamesForConsole;
    }

    public ArrayList<Game> getAvailableGamesForConsole(Console console) {
        ArrayList<Game> gamesForConsole = new ArrayList<>();
        for (Game g: getAvailableGames()) {
            if (console == g.getConsole()) {
                gamesForConsole.add(g);
            }
        }
        return gamesForConsole;
    }

    public Game getById(String id) {
        for (Game g: getAllGames()) {
            if (g.getId().equals(id)) {
                return g;
            }
        }
        return null;
    }

    public void addGame(Game game) {
        if (!getAllGames().contains(game)) {
            getAllGames().add(game);
        }
    }

    public void removeGame(Game game) {
        getAllGames().remove(game);
    }

    public void modifyGame(Game game) {
        for (Game g: getAllGames()) {
            if (g.getId().equals(game.getId())) {
                g.setName(game.getName());
                g.setInForRepair(game.isInForRepair());
            }
        }
    }
}
