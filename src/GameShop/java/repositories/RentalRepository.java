package GameShop.java.repositories;

import GameShop.java.models.Rental;

import java.util.ArrayList;

public class RentalRepository {
    private ArrayList<Rental> rentals = new ArrayList<>();

    public void addRental(Rental rental) {
        rentals.add(rental);
        System.out.println(rentals);
    }
}
