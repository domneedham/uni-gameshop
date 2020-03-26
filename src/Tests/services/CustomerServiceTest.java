package Tests.services;

import GameShop.java.models.Customer;
import GameShop.java.services.CustomerService;
import org.junit.jupiter.api.*;

class CustomerServiceTest {
    Customer customer1 = new Customer("Test", "One", "testuser1@test.com", "0177");
    Customer customer2 = new Customer("Test", "Two", "testuser2@test.com", "0177");

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        CustomerService.getAllCustomers().clear();
        // test clear worked on customers before running other tests
        Assertions.assertEquals(0, CustomerService.getAllCustomers().size());
    }

    @Test
    void getAllCustomersReturnsCorrectSize() {
        CustomerService.addCustomer(this.customer1);
        Assertions.assertEquals(1, CustomerService.getAllCustomers().size());

        CustomerService.addCustomer(this.customer2);
        Assertions.assertEquals(2, CustomerService.getAllCustomers().size());
    }

    @Test
    void getByIdReturnsCorrectCustomer() {
        CustomerService.addCustomer(this.customer1);

        int expectedId = this.customer1.getId();
        Assertions.assertEquals(this.customer1, CustomerService.getById(expectedId));
    }

    @Test
    void idExistsReturnsTrueForCorrectCustomer() {
        CustomerService.addCustomer(this.customer1);

        int expectedId = this.customer1.getId();
        Assertions.assertTrue(CustomerService.idExists(expectedId));
    }

    @Test
    void addCustomerWorks() {
        CustomerService.addCustomer(this.customer1);
        Assertions.assertEquals(1, CustomerService.getAllCustomers().size());
    }

    @Test
    void removeCustomerRemovesCorrectCustomer() {
        CustomerService.addCustomer(this.customer1);
        // ensure customer got added correctly
        Assertions.assertEquals(this.customer1, CustomerService.getById(this.customer1.getId()));

        CustomerService.removeCustomer(this.customer1);
        Assertions.assertNull(CustomerService.getById(this.customer1.getId()));
    }

    @Test
    void changingACustomerEmailWorks() {
        CustomerService.addCustomer(this.customer1);
        this.customer1.setEmail("new test");

        CustomerService.modifyCustomer(this.customer1);
        Assertions.assertEquals("new test", CustomerService.getById(this.customer1.getId()).getEmail());
    }

    @Test
    void changingACustomerTelNumberWorks() {
        CustomerService.addCustomer(this.customer1);
        this.customer1.setTelNumber("099");

        CustomerService.modifyCustomer(this.customer1);
        Assertions.assertEquals("099", CustomerService.getById(this.customer1.getId()).getTelNumber());
    }
}