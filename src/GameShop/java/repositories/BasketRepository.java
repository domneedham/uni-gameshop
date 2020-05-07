package GameShop.java.repositories;

import GameShop.java.models.Basket;
import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.services.StateService;

import java.time.LocalDate;
import java.util.ArrayList;

public class BasketRepository {
    public Basket getBasket() {
        return StateService.getBasket();
    }

    public void setCustomer(Customer customer) {
        getBasket().setCustomer(customer);
    }

    public void requireConsole(Console console) {
        var basket = getBasket();
        basket.setConsoleRequired(true);
        basket.setConsole(console);
    }

    public void unrequireConsole() {
        var basket = getBasket();
        basket.setConsoleRequired(false);
        // clear console from basket
        basket.setConsole(null);
    }

    public void setDate(LocalDate date) {
        getBasket().setDateRented(date);
    }

    public void addGame(Game game) {
        getBasket().addGame(game);
    }

    public void removeGame(Game game) {
        getBasket().removeGame(game);
    }

    public void removeGame(ArrayList<Game> games) {
        getBasket().removeGame(games);
    }

    public void clearBasket() {
        getBasket().clearBasket();
    }

    public void clearGames() {
        getBasket().clearGames();
    }
}
