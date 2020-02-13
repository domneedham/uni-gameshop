package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.models.ConsoleForm;
import GameShop.java.models.Game;

import java.util.ArrayList;

public class StateService {
    // Create ArrayLists to hold some fake data for application
    private final static ArrayList<Game> games = new ArrayList<>();
    private final static ArrayList<Console> consoles = new ArrayList<>();

    // On initialisation, populate lists with dummy data
    // Create consoles first, so games can use consoles
    public StateService(){
        populateConsoles();
        populateGames();
    }

    public ArrayList<Game> getGames(){
        return games;
    }

    public ArrayList<Console> getConsoles(){
        return consoles;
    }

    // create dummy objects for consoles, like reading from a DB
    private void populateConsoles(){
        String[] names = {"Xbox", "Nintendo DS", "Playstation 1"};
        ConsoleForm[] forms = {ConsoleForm.STANDARD, ConsoleForm.HANDHELD, ConsoleForm.STANDARD};
        double[] costs = { 15, 10, 15};
        int[] bits = {256, 128, 128};
        boolean[] repair = {false, false, true};

        for(int i = 0; i < names.length; i++){
            Console con = new Console(names[i], forms[i], costs[i], bits[i], repair[i]);
            consoles.add(con);
        }
    }

    // create dummy objects for games, like reading from a DB
    private void populateGames(){
        String[] names = {"Just Dance", "Animal Crossing", "Italian Job"};
        double[] costs = {5, 2.5, 4};
        boolean[] repair = {false, false, true};

        for(int i = 0; i < names.length; i++){
            Game g = new Game(names[i], consoles.get(i), costs[i], repair[i]);
            games.add(g);
        }
    }

    @Override
    public String toString() {
        return "Games: " + games +
                "\nConsoles: " + consoles;
    }

}

