package GameShop.java.repositories;

import GameShop.App;
import GameShop.java.models.Basket;
import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;

import java.time.LocalDate;
import java.util.ArrayList;

public class BasketRepository {
    public Basket getBasket() {
        return App.state.getBasket();
    }
}
