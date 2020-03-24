package GameShop.java.services;

import GameShop.java.models.Customer;
import GameShop.java.repositories.CustomerRepository;

import java.util.ArrayList;

public class CustomerService {
    protected static final CustomerRepository repo = new CustomerRepository();

    public static ArrayList<Customer> getAllCustomers() { return  repo.getAllCustomers(); }

    public static Customer getById(int id) { return repo.getById(id); }

    public static boolean idExists(int id) { return repo.getById(id) != null; }

    public static void addCustomer(Customer customer) { repo.addCustomer(customer); }

    public static void removeCustomer(Customer customer) { repo.removeCustomer(customer); }

    public static void modifyCustomer(Customer customer) { repo.modifyCustomer(customer); }
}
