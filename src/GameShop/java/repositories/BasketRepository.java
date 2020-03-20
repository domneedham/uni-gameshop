package GameShop.java.repositories;

import GameShop.App;
import GameShop.java.models.Basket;
import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;

import java.time.LocalDate;
import java.util.ArrayList;

public class BasketRepository {
    public Basket getBasket() { return App.state.getBasket(); }

    public boolean isBasketPopulated() { return getBasket().isBasketPopulated(); }

    public Customer getCustomer() { return getBasket().getCustomer(); }

    public boolean isConsoleRequired() {
        return getBasket().isConsoleRequired();
    }

    public Console getConsole() {
        return getBasket().getConsole();
    }

    public ArrayList<Game> getGames() {
        return getBasket().getGames();
    }

    public LocalDate getDate() { return getBasket().getDate(); }

    public void setCustomer(Customer customer) {
        getBasket().setCustomer(customer);
    }

    public void setConsoleRequired(boolean required) { getBasket().setConsoleRequired(required); }

    public void setConsole(Console console) {
        getBasket().setConsole(console);
    }

    public void setDate(LocalDate date) { getBasket().setDate(date); }

    public void addGame(Game game) { getBasket().addGame(game); }

    public void removeGame(Game game) {
        getBasket().removeGame(game);
    }

    public void removeGame(ArrayList<Game> games) { getBasket().removeGame(games); }

    public void clearBasket() { getBasket().clearBasket(); }

    public boolean gameInBasket(Game game) { return getBasket().gameInBasket(game); }

    public void clearGames() {
        getBasket().clearGames();
    }

    public void submitBasket() {
        getBasket().submitBasket();
        clearBasket();
    }

    public boolean maxGamesInBasket() { return getBasket().maxGamesInBasket(); }
}
