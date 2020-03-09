package GameShop.java.repositories;

import GameShop.App;
import GameShop.java.models.Rental;

import java.util.ArrayList;

public class RentalRepository {
    public ArrayList<Rental> getAllRentals() { return App.state.getRentals(); };

    public void addRental(Rental rental) {
        if (!getAllRentals().contains(rental)) {
            getAllRentals().add(rental);
        }
    }
}
