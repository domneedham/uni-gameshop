package Tests.repositories;

import GameShop.java.repositories.BasketRepository;
import org.junit.jupiter.api.*;

class BasketRepositoryTest {
    final BasketRepository repo = new BasketRepository();

    @Test
    void getBasketReturnsABasket() {
        Assertions.assertNotNull(repo.getBasket());
    }
}