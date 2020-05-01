package GameShop.java.services.factories;

import GameShop.java.routers.RouteNames;
import GameShop.java.services.*;
import GameShop.java.services.interfaces.IService;

public class MultiServiceFactory {
    public static IService[] getService(RouteNames route) {
        switch (route) {
            case CREATE_RENTAL:
                return new IService[] { new GameService(), new ConsoleService(), new BasketService(), new CustomerService()};
            case VIEW_RENTALS:
                return new IService[] { new RentalService(), new CustomerService() };
            case VIEW_GAMES:
                return new IService[] { new ConsoleService(), new GameService() };
            default:
                return null;
        }
    }
}
