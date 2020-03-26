package Tests.repositories;

import GameShop.java.models.Customer;
import GameShop.java.repositories.CustomerRepository;
import Tests.TestData;
import org.junit.jupiter.api.*;

class CustomerRepositoryTest {
    TestData testData = new TestData();

    CustomerRepository repo = new CustomerRepository();

    Customer customer1 = testData.customer1;
    Customer customer2 = testData.customer2;

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        repo.getAllCustomers().clear();
        // test clear worked on customers before running other tests
        Assertions.assertEquals(0, repo.getAllCustomers().size());
    }

    @Test
    void getAllCustomersReturnsCorrectNumberOfCustomers() {
        Assertions.assertEquals(0, repo.getAllCustomers().size());

        this.repo.addCustomer(this.customer1);
        Assertions.assertEquals(1, repo.getAllCustomers().size());
    }

    @Test
    void getByIdReturnsCorrectCustomer() {
        this.repo.addCustomer(this.customer1);

        int customerId = this.customer1.getId();
        Assertions.assertEquals(this.repo.getById(customerId), this.customer1);
    }

    @Test
    void getByIdReturnsNullIfWrongId() {
        this.repo.addCustomer(this.customer1);

        int wrongId = this.customer2.getId();

        Assertions.assertEquals(null, this.repo.getById(wrongId));
    }

    @Test
    void addCustomerWorks() {
        this.repo.addCustomer(this.customer1);
        Assertions.assertEquals(1, this.repo.getAllCustomers().size());
    }

    @Test
    void removeCustomerWorks() {
        this.repo.addCustomer(this.customer1);
        this.repo.addCustomer(this.customer2);
        // ensure both customers got added
        Assertions.assertEquals(2, this.repo.getAllCustomers().size());

        this.repo.removeCustomer(this.customer1);
        Assertions.assertNull(this.repo.getById(this.customer1.getId()));
        Assertions.assertEquals(this.customer2, this.repo.getById(this.customer2.getId()));
    }

    @Test
    void changingACustomerEmailWorks() {
        this.repo.addCustomer(this.customer1);
        this.customer1.setEmail("new test");

        this.repo.modifyCustomer(this.customer1);
        Assertions.assertEquals("new test", this.repo.getById(this.customer1.getId()).getEmail());
    }

    @Test
    void changingACustomerTelNumberWorks() {
        this.repo.addCustomer(this.customer1);
        this.customer1.setTelNumber("099");

        this.repo.modifyCustomer(this.customer1);
        Assertions.assertEquals("099", this.repo.getById(this.customer1.getId()).getTelNumber());
    }
}