package GameShop.java.services.factories;

import GameShop.java.routers.RouteNames;
import GameShop.java.services.BasketService;
import GameShop.java.services.ConsoleService;
import GameShop.java.services.CustomerService;
import GameShop.java.services.GameService;
import GameShop.java.services.interfaces.IService;

public abstract class ServiceFactory {
    public static IService getService(RouteNames route) {
        switch (route) {
            case EDIT_GAME:
            case EDIT_GAMES:
                return new GameService();
            case EDIT_CONSOLE:
            case EDIT_CONSOLES:
            case VIEW_CONSOLES:
                return new ConsoleService();
            case VIEW_BASKET:
                return new BasketService();
            case VIEW_CUSTOMERS:
                return new CustomerService();
            default:
                return null;
        }
    }
}

