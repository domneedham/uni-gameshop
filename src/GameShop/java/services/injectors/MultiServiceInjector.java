package GameShop.java.services.injectors;

import GameShop.java.routers.RouteNames;
import GameShop.java.services.*;
import GameShop.java.services.interfaces.IService;

import java.util.ArrayList;
import java.util.List;

public class MultiServiceInjector {
    public static ArrayList<IService> getService(RouteNames route) {
        switch (route) {
            case CREATE_RENTAL:
                return new ArrayList<>(List.of(new GameService(), new ConsoleService(), new BasketService(), new CustomerService()));
            case VIEW_RENTALS:
                return new ArrayList<>(List.of(new RentalService(), new CustomerService()));
            case VIEW_GAMES:
                return new ArrayList<>(List.of(new ConsoleService(), new GameService()));
            default:
                return null;
        }
    }
}
