package Tests.services.factories;

import GameShop.java.routers.RouteNames;
import GameShop.java.services.*;
import GameShop.java.services.factories.MultiServiceFactory;
import GameShop.java.services.interfaces.IService;
import org.junit.jupiter.api.*;

public class MultiServiceFactoryTest {

    @Test
    void factoryReturnsNullIfRoutePassedIsNull() {
        IService[] service = MultiServiceFactory.getServices(null);
        Assertions.assertNull(service);
    }

    @Test
    void factoryReturnsArrayOfServicesForCreateRentalController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.CREATE_RENTAL);
        Assertions.assertTrue(services[0] instanceof GameService);
        Assertions.assertTrue(services[1] instanceof ConsoleService);
        Assertions.assertTrue(services[2] instanceof BasketService);
        Assertions.assertTrue(services[3] instanceof CustomerService);
    }

    @Test
    void factoryReturnsNullForEditConsoleController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.EDIT_CONSOLE);
        Assertions.assertNull(services);
    }

    @Test
    void factoryReturnsCNullForEditConsolesController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.EDIT_CONSOLES);
        Assertions.assertNull(services);
    }

    @Test
    void factoryReturnsNullForEditGameController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.EDIT_GAME);
        Assertions.assertNull(services);
    }

    @Test
    void factoryReturnsNullForEditGamesController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.EDIT_GAMES);
        Assertions.assertNull(services);
    }

    @Test
    void factoryReturnsNullForHomeController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.HOME);
        Assertions.assertNull(services);
    }

    @Test
    void factoryReturnsNullForShopKeeperHomeController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.SHOP_KEEPER_HOME);
        Assertions.assertNull(services);
    }

    @Test
    void factoryReturnsNullForSignInController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.SIGN_IN);
        Assertions.assertNull(services);
    }

    @Test
    void factoryReturnsNullForViewBasketController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.VIEW_BASKET);
        Assertions.assertNull(services);
    }

    @Test
    void factoryReturnsNullForViewConsolesController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.VIEW_CONSOLES);
        Assertions.assertNull(services);
    }

    @Test
    void factoryReturnsNullForViewCustomerController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.VIEW_CUSTOMERS);
        Assertions.assertNull(services);
    }

    @Test
    void factoryReturnsNullForViewGamesController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.VIEW_GAMES);
        Assertions.assertTrue(services[0] instanceof ConsoleService);
        Assertions.assertTrue(services[1] instanceof GameService);
    }

    @Test
    void factoryReturnsNullForViewRentalsController() {
        IService[] services = MultiServiceFactory.getServices(RouteNames.VIEW_RENTALS);
        Assertions.assertTrue(services[0] instanceof RentalService);
        Assertions.assertTrue(services[1] instanceof CustomerService);
    }
}
