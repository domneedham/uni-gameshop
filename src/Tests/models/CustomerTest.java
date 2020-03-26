package Tests.models;

import GameShop.java.models.Customer;
import Tests.TestData;
import org.junit.jupiter.api.*;

class CustomerTest {
    TestData testData = new TestData();
    Customer customer1 = testData.customer1;
    Customer customer2 = testData.customer2;

    @Test
    void idIsDifferentForEachCustomer() {
        Assertions.assertNotEquals(this.customer1.getId(), this.customer2.getId());
    }
}