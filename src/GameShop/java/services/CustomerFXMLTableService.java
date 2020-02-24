package GameShop.java.services;

import GameShop.java.models.Customer;

public class CustomerFXMLTableService extends CustomerService {
    public static String getId(Customer customer) { return String.format("%d", repo.getId(customer)); }
    public static String getForename(Customer customer) { return repo.getForename(customer); }
    public static String getSurname(Customer customer) { return repo.getSurname(customer); }
    public static String getEmail(Customer customer) { return repo.getEmail(customer); }
    public static String getTelephone(Customer customer) { return repo.getTelNumber(customer); }

}
