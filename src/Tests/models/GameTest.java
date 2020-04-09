package Tests.models;

import GameShop.java.models.Game;
import Tests.TestData;
import org.junit.jupiter.api.*;

class GameTest {
    final TestData testData = new TestData();

    final Game game1 = testData.consoleOneGame1;
    final Game game2 = testData.consoleOneGame2;
    final Game game3 = testData.consoleOneRepairGame1;

    @Test
    void idIsDifferentOnEachGame() {
        Assertions.assertNotEquals(this.game1.getId(), this.game2.getId());
    }

    @Test
    void gameIsNotAvailableIfInRepair() {
        Assertions.assertTrue(this.game3.isInForRepair());
        Assertions.assertFalse(this.game3.isAvailable());
    }

    @Test
    void gameIsNotAvailableIfItIsRented() {
        this.game1.rentItem();
        Assertions.assertTrue(this.game1.isCurrentlyRented());
        Assertions.assertFalse(this.game1.isAvailable());
    }

    @Test
    void gameCanNotBeRentedIfItIsInForRepair() {
        this.game1.setInForRepair(true);
        try {
            this.game1.rentItem();
        } catch (Error e) {
            // ignore error
        }
        Assertions.assertFalse(this.game1.isCurrentlyRented());
    }
}