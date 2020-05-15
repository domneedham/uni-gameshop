package Tests.services.factories;

import GameShop.java.routers.RouteNames;
import GameShop.java.services.BasketService;
import GameShop.java.services.ConsoleService;
import GameShop.java.services.CustomerService;
import GameShop.java.services.GameService;
import GameShop.java.services.factories.ServiceFactory;
import GameShop.java.services.interfaces.IService;
import org.junit.jupiter.api.*;

public class ServiceFactoryTest {

    @Test
    void factoryReturnsNullIfRoutePassedIsNull() {
        IService service = ServiceFactory.getService(null);
        Assertions.assertNull(service);
    }

    @Test
    void factoryReturnsNullForCreateRentalController() {
        IService service = ServiceFactory.getService(RouteNames.CREATE_RENTAL);
        Assertions.assertNull(service);
    }

    @Test
    void factoryReturnsCorrectServiceForEditConsoleController() {
        IService service = ServiceFactory.getService(RouteNames.EDIT_CONSOLE);
        Assertions.assertTrue(service instanceof ConsoleService);
    }

    @Test
    void factoryReturnsCorrectServiceForEditConsolesController() {
        IService service = ServiceFactory.getService(RouteNames.EDIT_CONSOLES);
        Assertions.assertTrue(service instanceof ConsoleService);
    }

    @Test
    void factoryReturnsCorrectServiceForEditGameController() {
        IService service = ServiceFactory.getService(RouteNames.EDIT_GAME);
        Assertions.assertTrue(service instanceof GameService);
    }

    @Test
    void factoryReturnsCorrectServiceForEditGamesController() {
        IService service = ServiceFactory.getService(RouteNames.EDIT_GAMES);
        Assertions.assertTrue(service instanceof GameService);
    }

    @Test
    void factoryReturnsNullForHomeController() {
        IService service = ServiceFactory.getService(RouteNames.HOME);
        Assertions.assertNull(service);
    }

    @Test
    void factoryReturnsNullForShopKeeperHomeController() {
        IService service = ServiceFactory.getService(RouteNames.SHOP_KEEPER_HOME);
        Assertions.assertNull(service);
    }

    @Test
    void factoryReturnsNullForSignInController() {
        IService service = ServiceFactory.getService(RouteNames.SIGN_IN);
        Assertions.assertNull(service);
    }

    @Test
    void factoryReturnsCorrectServiceForViewBasketController() {
        IService service = ServiceFactory.getService(RouteNames.VIEW_BASKET);
        Assertions.assertTrue(service instanceof BasketService);
    }

    @Test
    void factoryReturnsCorrectServiceForViewConsolesController() {
        IService service = ServiceFactory.getService(RouteNames.VIEW_CONSOLES);
        Assertions.assertTrue(service instanceof ConsoleService);
    }

    @Test
    void factoryReturnsCorrectServiceForViewCustomerController() {
        IService service = ServiceFactory.getService(RouteNames.VIEW_CUSTOMERS);
        Assertions.assertTrue(service instanceof CustomerService);
    }

    @Test
    void factoryReturnsNullForViewGamesController() {
        IService service = ServiceFactory.getService(RouteNames.VIEW_GAMES);
        Assertions.assertNull(service);
    }

    @Test
    void factoryReturnsNullForViewRentalsController() {
        IService service = ServiceFactory.getService(RouteNames.VIEW_RENTALS);
        Assertions.assertNull(service);
    }
}
