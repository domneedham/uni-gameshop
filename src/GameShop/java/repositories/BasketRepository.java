package GameShop.java.repositories;

import GameShop.java.models.Basket;
import GameShop.java.services.StateService;

public class BasketRepository {
    private final StateService stateService = StateService.getInstance();

    public Basket getBasket() {
        return stateService.getBasket();
    }
}
