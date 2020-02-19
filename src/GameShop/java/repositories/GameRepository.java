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

    // used for JAVA FXML tables to show formatted data
    public String getFXMLId(Game game) { return game.getId(); }
    public String getFXMLName(Game game) { return game.getName(); }
    public String getFXMLConsoleName(Game game) { return game.getConsole().getName(); }
    public String getFXMLAvailable(Game game) { return game.isInForRepair() ? "No" : "Yes" ;}
    public String getFXMLFormattedCost(Game game) { return String.format("£%.2f", game.getCost()); }

}
