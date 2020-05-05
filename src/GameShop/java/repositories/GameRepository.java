package GameShop.java.repositories;

import GameShop.java.models.Console;
import GameShop.java.models.Game;
import GameShop.java.services.StateService;

import java.util.ArrayList;

public class GameRepository {
    private final StateService stateService = StateService.getInstance();

    public ArrayList<Game> getAllGames() { return stateService.getGames(); }

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

    public void rentGame(Game game) throws Exception {
        for (Game g: getAllGames()) {
            if (g.getId().equals(game.getId())) {
                g.rentItem();
            }
        }
    }

    public void returnGame(Game game) {
        for (Game g: getAllGames()) {
            if (g.getId().equals(game.getId())) {
                g.returnItem();
            }
        }
    }
}
