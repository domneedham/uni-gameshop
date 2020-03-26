package Tests;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.enums.ConsoleForm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestData {
    public Customer customer1 = new Customer("Test", "One", "testuser1@test.com", "0987");
    public Customer customer2 = new Customer("Test", "Two", "testuser2@test.com", "0987");
    public Customer customer3 = new Customer("Test", "Three", "testuser3@test.com", "0987");

    public Console standardConsole1 = new Console("Rentable Console 1", ConsoleForm.STANDARD, 15, 8, false);
    public Console standardConsole2 = new Console("Rentable Console 2", ConsoleForm.HANDHELD, 15, 8, false);
    public Console nonStandardConsole1 = new Console("Unrentable Console 1", ConsoleForm.STANDARD, 15, 8, true);
    public Console nonStandardConsole2 = new Console("Unrentable Console 2", ConsoleForm.HANDHELD, 15, 8, true);

    public Game consoleOneGame1 = new Game("Rentable Game 1", standardConsole1, 10, false);
    public Game consoleOneGame2 = new Game("Rentable Game 2", standardConsole1, 10, false);
    public Game consoleOneGame3 = new Game("Rentable Game 3", standardConsole1, 10, false);
    public Game consoleTwoGame1 = new Game("Rentable Game 3", standardConsole2, 10, false);
    public Game consoleTwoGame2 = new Game("Rentable Game 4", standardConsole2, 10, false);
    public Game consoleTwoGame3 = new Game("Rentable Game 4", standardConsole2, 10, false);
    public Game consoleOneRepairGame1 = new Game("Unrentable Game 2", standardConsole1, 10, true);
    public Game consoleTwoRepairGame1 = new Game("Unrentable Game 2", standardConsole2, 10, true);

    public ArrayList<Game> consoleOneGamesFullList = new ArrayList<>(List.of(consoleOneGame1, consoleOneGame2, consoleOneGame3));
    public ArrayList<Game> consoleOneGamesNotFullList = new ArrayList<>(List.of(consoleOneGame1, consoleOneGame2));
    public ArrayList<Game> consoleTwoGamesFullList = new ArrayList<>(List.of(consoleTwoGame1, consoleTwoGame2, consoleTwoGame3));
    public ArrayList<Game> consoleTwoGamesNotFullList = new ArrayList<>(List.of(consoleTwoGame1, consoleTwoGame2));

    public LocalDate date = LocalDate.of(2020, 03, 26);
}
