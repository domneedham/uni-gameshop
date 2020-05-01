package GameShop.java.controllers.interfaces;

import GameShop.java.services.interfaces.IService;

public interface MultiServiceDependency {
    void assignServices(IService[] services);
}
