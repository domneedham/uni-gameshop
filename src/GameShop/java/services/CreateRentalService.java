package GameShop.java.services;

import GameShop.java.repositories.CreateRentalRepository;

public class CreateRentalService {
    protected static CreateRentalRepository repo = new CreateRentalRepository();

    public static void createRental() { repo.createRental(); }
}
