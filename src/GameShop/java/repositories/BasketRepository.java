package GameShop.java.repositories;

import GameShop.App;
import GameShop.java.models.Basket;

public class BasketRepository {
    public Basket getBasket() {
        return App.state.getBasket();
    }
}
