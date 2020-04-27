package GameShop.java.controllers.interfaces;

import GameShop.java.services.interfaces.IService;

import java.util.ArrayList;

public interface MultiServiceDependency {
    void assignServices(ArrayList<IService> services);
}
