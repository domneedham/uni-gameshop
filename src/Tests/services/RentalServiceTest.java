package Tests.services;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.Rental;
import GameShop.java.services.RentalService;
import Tests.TestData;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

class RentalServiceTest {
    TestData testData = new TestData();

    private Rental rentalWithConsole;
    private Rental rentalWithoutConsole;
    private LocalDate date = testData.date;
    private Customer customer1 = testData.customer1;
    private Customer customer2 = testData.customer2;
    private ArrayList<Game> gamesForRental1 = testData.consoleOneGamesFullList;
    private ArrayList<Game> gamesForRental2 = testData.consoleTwoGamesNotFullList;
    private Console console1 = testData.standardConsole1;
    private Console console2 = testData.standardConsole2;

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        RentalService.getRentals().clear();
        // test clear worked on rentals before running other tests
        Assertions.assertEquals(0, RentalService.getRentals().size());
    }

    @Test
    void getRentalsReturnsCorrectSize() {
        Assertions.assertEquals(0, RentalService.getRentals().size());
        RentalService.getRentals().add(this.rentalWithConsole);

        Assertions.assertEquals(1, RentalService.getRentals().size());
    }

    @Test
    void getRentalsForCustomerReturnsCorrectSize() {
        this.rentalWithConsole = new Rental(this.date, this.customer1, this.console1, this.gamesForRental1);
        this.rentalWithoutConsole = new Rental(this.date, this.customer2, this.gamesForRental2);
        RentalService.getRentals().add(this.rentalWithConsole);
        RentalService.getRentals().add(this.rentalWithoutConsole);

        Assertions.assertEquals(1, RentalService.getRentalsForCustomer(this.customer1).size());
        Assertions.assertEquals(1, RentalService.getRentalsForCustomer(this.customer2).size());
    }

    @Test
    void createARentalWithConsoleWorks() {
        Assertions.assertEquals(0, RentalService.getRentals().size());

        RentalService.createRentalWithConsole(this.date, this.customer1, this.console1, this.gamesForRental1);

        Assertions.assertEquals(1, RentalService.getRentals().size());
        // make sure rental has a console
        Assertions.assertNotNull(RentalService.getRentals().get(0).getConsole());
    }

    @Test
    void createARentalWithoutConsoleWorks() {
        Assertions.assertEquals(0, RentalService.getRentals().size());

        RentalService.createRental(this.date, this.customer1, this.gamesForRental1);

        Assertions.assertEquals(1, RentalService.getRentals().size());
        // make sure rental does not have a console
        Assertions.assertNull(RentalService.getRentals().get(0).getConsole());
    }

}