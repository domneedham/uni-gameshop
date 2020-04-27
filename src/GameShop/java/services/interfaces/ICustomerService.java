package GameShop.java.services.interfaces;

import GameShop.java.models.Customer;

import java.util.ArrayList;

public interface ICustomerService extends IService {
    ArrayList<Customer> getAllCustomers();

    Customer getById(String id);

    boolean idExists(String id);

    void addCustomer(Customer customer);

    void removeCustomer(Customer customer);

    void modifyCustomer(Customer customer);
}
