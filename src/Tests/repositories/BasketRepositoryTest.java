package Tests.repositories;

import GameShop.java.models.Basket;
import GameShop.java.repositories.BasketRepository;
import org.junit.jupiter.api.*;

class BasketRepositoryTest {
    final BasketRepository repo = new BasketRepository();

    @Test
    void getBasketReturnsABasketType() {
        Assertions.assertTrue(repo.getBasket() instanceof Basket);
    }
}