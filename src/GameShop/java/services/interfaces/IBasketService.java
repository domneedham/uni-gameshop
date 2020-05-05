package GameShop.java.services.interfaces;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IBasketService extends IService {
    boolean isBasketPopulated();

    Customer getCustomer();

    boolean isConsoleRequired();

    Console getConsole();

    ArrayList<Game> getGames();

    LocalDate getDate();

    void setCustomer(Customer customer);

    void requireConsole(Console console) throws Exception;

    void unrequireConsole();

    void setDate(LocalDate date);

    void addGame(Game game);

    void removeGame(Game game);

    void removeGame(ArrayList<Game> games);

    void clearBasket();

    boolean gameInBasket(Game game);

    void clearGames();

    boolean isMaxGamesInBasket();

    void submitBasket() throws Error, Exception;

    double calculateCost();
}
