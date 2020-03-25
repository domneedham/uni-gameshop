package Tests.repositories;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.Rental;
import GameShop.java.models.enums.ConsoleForm;
import GameShop.java.repositories.RentalRepository;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

class RentalRepositoryTest {
    RentalRepository repo = new RentalRepository();

    private Rental rental1;
    private Rental rental2;
    private LocalDate date = LocalDate.now();
    private Customer customer1 = new Customer("Test", "1", "test1@test.com", "01773");
    private Customer customer2 = new Customer("Test", "2", "test2@test.com", "01773");
    private ArrayList<Game> gamesForRental1 = new ArrayList<>();
    private ArrayList<Game> gamesForRental2 = new ArrayList<>();
    private Console console1 = new Console("Test Console 1", ConsoleForm.STANDARD, 15.0, 8, false);
    private Console console2 = new Console("Test Console 2", ConsoleForm.STANDARD, 15.0, 8, false);

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        this.repo.getAllRentals().clear();
        // test clear worked on rentals before running other tests
        Assertions.assertEquals(0, repo.getAllRentals().size());

        Game game1 = new Game("Test game 1", this.console1, 10.0, false);
        this.gamesForRental1.add(game1);
        Game game2 = new Game("Test game 2", this.console2, 10.0, false);
        this.gamesForRental2.add(game2);

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