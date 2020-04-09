package GameShop.java.repositories;

import GameShop.App;
import GameShop.java.models.Customer;

import java.util.ArrayList;

public class CustomerRepository {
    public ArrayList<Customer> getAllCustomers() { return App.state.getCustomers(); }

    public Customer getById(String id) {
        for (Customer c: getAllCustomers()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        if (!getAllCustomers().contains(customer)) {
            getAllCustomers().add(customer);
        }
    }

    public void removeCustomer(Customer customer) {
        getAllCustomers().remove(customer);
    }

    public void modifyCustomer(Customer customer) {
        for (Customer c: getAllCustomers()) {
            if (c.getId().equals(customer.getId())) {
                c.setEmail(customer.getEmail());
                c.setTelNumber(customer.getTelNumber());
            }
        }
    }
}
