package GameShop.java.repositories;

import GameShop.App;
import GameShop.java.models.Console;
import GameShop.java.models.Game;

import java.util.ArrayList;

public class GameRepository {
    public ArrayList<Game> getAllGames() { return App.state.getGames(); };

    public ArrayList<Game> getAvailableGames() {
        ArrayList<Game> availableGames = new ArrayList<>();
        for (Game g: getAllGames()) {
            if (!g.isInForRepair()) {
                availableGames.add(g);
            }
        }
        return availableGames;
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
        if (getAllGames().contains(game)) {
            getAllGames().remove(game);
        }
    }

    public void modifyGame(Game game) {
        for (Game g: getAllGames()) {
            if (g.getId().equals(game.getId())) {
                g.setName(game.getName());
                g.setInForRepair(game.isInForRepair());
            }
        }
    }

    public String getId(Game game) { return game.getId(); }
    public String getName(Game game) { return game.getName(); }
    public Console getConsole(Game game) { return game.getConsole(); }
    public boolean isInForRepair (Game game) { return game.isInForRepair();}
    public double getCost(Game game) { return game.getCost(); }

}
