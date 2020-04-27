package Tests.services;

import GameShop.java.models.Customer;
import GameShop.java.services.CustomerService;
import Tests.TestData;
import org.junit.jupiter.api.*;

class CustomerServiceTest {
    final TestData testData = new TestData();
    final CustomerService customerService = new CustomerService();

    final Customer customer1 = testData.customer1;
    final Customer customer2 = testData.customer2;

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        customerService.getAllCustomers().clear();
        // test clear worked on customers before running other tests
        Assertions.assertEquals(0, customerService.getAllCustomers().size());
    }

    @Test
    void getAllCustomersReturnsCorrectSize() {
        customerService.addCustomer(this.customer1);
        Assertions.assertEquals(1, customerService.getAllCustomers().size());

        customerService.addCustomer(this.customer2);
        Assertions.assertEquals(2, customerService.getAllCustomers().size());
    }

    @Test
    void getByIdReturnsCorrectCustomer() {
        customerService.addCustomer(this.customer1);

        String expectedId = this.customer1.getId();
        Assertions.assertEquals(this.customer1, customerService.getById(expectedId));
    }

    @Test
    void idExistsReturnsTrueForCorrectCustomer() {
        customerService.addCustomer(this.customer1);

        String expectedId = this.customer1.getId();
        Assertions.assertTrue(customerService.idExists(expectedId));
    }

    @Test
    void addCustomerWorks() {
        customerService.addCustomer(this.customer1);
        Assertions.assertEquals(1, customerService.getAllCustomers().size());
    }

    @Test
    void removeCustomerRemovesCorrectCustomer() {
        customerService.addCustomer(this.customer1);
        // ensure customer got added correctly
        Assertions.assertEquals(this.customer1, customerService.getById(this.customer1.getId()));

        customerService.removeCustomer(this.customer1);
        Assertions.assertNull(customerService.getById(this.customer1.getId()));
    }

    @Test
    void changingACustomerEmailWorks() {
        customerService.addCustomer(this.customer1);
        this.customer1.setEmail("new test");

        customerService.modifyCustomer(this.customer1);
        Assertions.assertEquals("new test", customerService.getById(this.customer1.getId()).getEmail());
    }

    @Test
    void changingACustomerTelNumberWorks() {
        customerService.addCustomer(this.customer1);
        this.customer1.setTelNumber("099");

        customerService.modifyCustomer(this.customer1);
        Assertions.assertEquals("099", customerService.getById(this.customer1.getId()).getTelNumber());
    }
}