package GameShop.java.repositories;

import GameShop.App;
import GameShop.java.models.Customer;
import GameShop.java.models.Rental;

import java.util.ArrayList;

public class RentalRepository {
    public ArrayList<Rental> getAllRentals() { return App.state.getRentals(); }

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
        rental.setIsReturned(true);

    }
}
