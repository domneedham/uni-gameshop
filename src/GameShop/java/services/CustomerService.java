package GameShop.java.services;

import GameShop.java.models.Customer;
import GameShop.java.repositories.CustomerRepository;

import java.util.ArrayList;

public class CustomerService implements GameShop.java.services.interfaces.ICustomerService {
    protected static final CustomerRepository repo = new CustomerRepository();

    @Override
    public ArrayList<Customer> getAllCustomers() { return  repo.getAllCustomers(); }

    @Override
    public Customer getById(String id) { return repo.getById(id); }

    @Override
    public boolean idExists(String id) { return repo.getById(id) != null; }

    @Override
    public void addCustomer(Customer customer) { repo.addCustomer(customer); }

    @Override
    public void removeCustomer(Customer customer) { repo.removeCustomer(customer); }

    @Override
    public void modifyCustomer(Customer customer) { repo.modifyCustomer(customer); }
}
