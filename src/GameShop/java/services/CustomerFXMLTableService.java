package GameShop.java.services;

import GameShop.java.models.Customer;

public class CustomerFXMLTableService {
    public static String getId(Customer customer) { return String.format("%d", customer.getId()); }
    public static String getForename(Customer customer) { return customer.getForename(); }
    public static String getSurname(Customer customer) { return customer.getSurname(); }
    public static String getEmail(Customer customer) { return customer.getEmail(); }
    public static String getTelephone(Customer customer) { return customer.getTelNumber(); }

}
