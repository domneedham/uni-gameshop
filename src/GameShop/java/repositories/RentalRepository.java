package GameShop.java.repositories;

import GameShop.java.models.Customer;
import GameShop.java.models.Rental;
import GameShop.java.services.StateService;

import java.util.ArrayList;

public class RentalRepository {
    public ArrayList<Rental> getAllRentals() { return StateService.getRentals(); }

    public Rental getById(String id) {
        for (Rental rental : getAllRentals()) {
            if (id.equals(rental.getId())) {
                return rental;
            }
        }
        return null;
    }

    public ArrayList<Rental> getRentalsForCustomer(Customer customer) {
        ArrayList<Rental> customerRentals = new ArrayList<>();
        for (Rental rental : getAllRentals()) {
            if (customer == rental.getCustomer()) {
                customerRentals.add(rental);
            }
        }
        return customerRentals;
    }

    public void addRental(Rental rental) {
        if (!getAllRentals().contains(rental)) {
            getAllRentals().add(rental);
        }
    }

    public void returnRental(Rental rental) {
        for (Rental r: getAllRentals()) {
            if (r.getId().equals(rental.getId())) {
                r.setIsReturned(true);
            }
        }
    }
}
