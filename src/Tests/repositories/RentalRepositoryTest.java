package Tests.repositories;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.Rental;
import GameShop.java.repositories.RentalRepository;
import Tests.TestData;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

class RentalRepositoryTest {
    final TestData testData = new TestData();

    final RentalRepository repo = new RentalRepository();

    private Rental rental1;
    private Rental rental2;
    private final LocalDate date = testData.date;
    private final Customer customer1 = testData.customer1;
    private final Customer customer2 = testData.customer2;
    private final ArrayList<Game> gamesForRental1 = testData.consoleOneGamesFullList;
    private final ArrayList<Game> gamesForRental2 = testData.consoleTwoGamesNotFullList;
    private final Console console1 = testData.standardConsole1;
    private final Console console2 = testData.standardConsole2;

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        this.repo.getAllRentals().clear();
        // test clear worked on rentals before running other tests
        Assertions.assertEquals(0, repo.getAllRentals().size());

        this.rental1 = new Rental(this.date, this.customer1, this.console1, this.gamesForRental1);
        this.rental2 = new Rental(this.date, this.customer2, this.console2, this.gamesForRental2);
    }

    @Test
    void getAllRentalsReturnsCorrectSize() {
        Assertions.assertEquals(0, repo.getAllRentals().size());
        repo.addRental(this.rental1);

        Assertions.assertEquals(1, repo.getAllRentals().size());
    }

    @Test
    void getRentalsForCustomerReturnsCorrectSize() {
        this.repo.addRental(this.rental1);
        this.repo.addRental(this.rental2);

        Assertions.assertEquals(1, this.repo.getRentalsForCustomer(this.customer1).size());
        Assertions.assertEquals(1, this.repo.getRentalsForCustomer(this.customer2).size());
    }

    @Test
    void addRentalWorks() {
        Assertions.assertEquals(0, this.repo.getAllRentals().size());

        repo.addRental(this.rental1);

        Assertions.assertEquals(1, this.repo.getAllRentals().size());
    }
}