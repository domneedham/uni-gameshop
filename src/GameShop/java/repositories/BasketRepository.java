package GameShop.java.repositories;

import GameShop.java.models.Basket;
import GameShop.java.services.StateService;

public class BasketRepository {
    public Basket getBasket() {
        return StateService.getBasket();
    }
}
