package GameShop.java.repositories;

import GameShop.App;
import GameShop.java.models.Customer;

import java.util.ArrayList;

public class CustomerRepository {
    public ArrayList<Customer> getAllCustomers() { return App.state.getCustomers(); }

    public Customer getById(int id) {
        for (Customer c: getAllCustomers()) {
            if (c.getId() == id) {
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
        if (!getAllCustomers().contains(customer)) {
            getAllCustomers().remove(customer);
        }
    }

    public void modifyCustomer(Customer customer) {
        for (Customer c: getAllCustomers()) {
            if (c.getId() == customer.getId()) {
                c.setEmail(customer.getEmail());
                c.setTelNumber(customer.getTelNumber());
            }
        }
    }

    public int getId(Customer customer) { return customer.getId(); }
    public String getForename(Customer customer) { return customer.getForename(); }
    public String getSurname(Customer customer) { return customer.getSurname(); }
    public String getEmail(Customer customer) { return customer.getEmail(); }
    public String getTelNumber(Customer customer) { return customer.getTelNumber(); }
}
