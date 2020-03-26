package Tests;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.enums.ConsoleForm;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static Customer customer1 = new Customer("Test", "One", "testuser1@test.com", "0987");
    public static Customer customer2 = new Customer("Test", "Two", "testuser2@test.com", "0987");
    public static Customer customer3 = new Customer("Test", "Three", "testuser3@test.com", "0987");

    public static Console standardConsole1 = new Console("Rentable Console 1", ConsoleForm.STANDARD, 15, 8, false);
    public static Console standardConsole2 = new Console("Rentable Console 2", ConsoleForm.HANDHELD, 15, 8, false);
    public static Console nonStandardConsole1 = new Console("Unrentable Console 1", ConsoleForm.STANDARD, 15, 8, true);
    public static Console nonStandardConsole2 = new Console("Unrentable Console 2", ConsoleForm.HANDHELD, 15, 8, true);

    public static Game standardGame1 = new Game("Rentable Game 1", standardConsole1, 10, false);
    public static Game standardGame2 = new Game("Rentable Game 2", standardConsole1, 10, false);
    public static Game standardGame3 = new Game("Rentable Game 3", standardConsole1, 10, false);
    public static Game standardGame4 = new Game("Rentable Game 4", standardConsole1, 10, false);
    public static Game nonStandardGame1 = new Game("Unrentable Game 2", standardConsole1, 10, true);
    public static Game nonStandardGame2 = new Game("Unrentable Game 2", standardConsole1, 10, true);

    public static ArrayList<Game> games1 = new ArrayList<>(List.of(standardGame1, standardGame2));
    public static ArrayList<Game> games2 = new ArrayList<>(List.of(standardGame1, nonStandardGame2));
}
