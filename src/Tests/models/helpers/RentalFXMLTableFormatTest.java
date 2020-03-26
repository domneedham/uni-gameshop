package Tests.models.helpers;

import GameShop.java.models.Console;
import GameShop.java.models.helpers.RentalFXMLTableFormat;
import Tests.TestData;
import org.junit.jupiter.api.*;

class RentalFXMLTableFormatTest {
    final TestData testData = new TestData();

    final Console console = testData.standardConsole1;

    @Test
    void formatConsoleNameReturnsConsoleName() {
        String result = RentalFXMLTableFormat.formatConsoleName(console);
        String expected = console.getName();
        Assertions.assertEquals(expected, result);
    }

    @Test
    void formatConsoleNameReturnsConsoleNotRented() {
        String result = RentalFXMLTableFormat.formatConsoleName(null);
        Assertions.assertEquals("Console Not Rented", result);
    }
}