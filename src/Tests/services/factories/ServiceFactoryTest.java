package Tests.services.factories;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.routers.RouteNames;
import GameShop.java.services.BasketService;
import GameShop.java.services.factories.ServiceFactory;
import GameShop.java.services.interfaces.IService;
import Tests.TestData;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;

public class ServiceFactoryTest {

    @Test
    void factoryReturnsNullIfRouteIsNotValid() {
        IService service = ServiceFactory.getService(RouteNames.SHOP_KEEPER_HOME);
        Assertions.assertNull(service);
    }

    @Test
    void factoryReturnsCorrectServiceForInterface() {

    }
}
