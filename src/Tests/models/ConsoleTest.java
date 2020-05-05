package Tests.models;

import GameShop.java.models.Console;
import Tests.TestData;
import org.junit.jupiter.api.*;

class ConsoleTest {
    final TestData testData = new TestData();
    final Console console1 = testData.standardConsole1;
    final Console console2 = testData.standardConsole2;
    final Console console3 = testData.nonStandardConsole1;

    @Test
    void idIsDifferentOnEachConsole() {
        Assertions.assertNotEquals(this.console1.getId(), this.console2.getId());
    }

    @Test
    void consoleIsNotAvailableIfInRepair() {
        Assertions.assertTrue(this.console3.isInForRepair());
        Assertions.assertFalse(this.console3.isAvailable());
    }

    @Test
    void consoleIsNotAvailableIfItIsRented() throws Exception {
        this.console1.rentItem();
        Assertions.assertTrue(this.console1.isCurrentlyRented());
        Assertions.assertFalse(this.console1.isAvailable());
    }

    @Test
    void consoleCanNotBeRentedIfItIsInForRepair() {
        this.console1.setInForRepair(true);
        try {
            this.console1.rentItem();
        } catch (Exception e) {
            // ignore error
        }
        Assertions.assertFalse(this.console1.isCurrentlyRented());
    }
}