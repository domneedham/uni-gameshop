package Tests.models;

import GameShop.java.models.Customer;
import org.junit.jupiter.api.*;

class CustomerTest {
    Customer customer1;
    Customer customer2;

    @BeforeEach
    void setUp() {
        this.customer1 = new Customer("Test1", "Test1", "test1@test.com", "01773");
        this.customer2 = new Customer("Test2", "Test2", "test2@test.com", "01773");
    }

    @Test
    void idIsDifferentForEachCustomer() {
        Assertions.assertNotEquals(this.customer1.getId(), this.customer2.getId());
    }
}