package GameShop.java.controllers.interfaces;

import GameShop.java.services.interfaces.IService;

public interface ServiceDependency {
    void assignService(IService service);
}
