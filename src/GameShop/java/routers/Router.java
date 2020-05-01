package GameShop.java.routers;

import GameShop.java.controllers.interfaces.ControllerCommunication;
import GameShop.java.controllers.interfaces.MultiServiceDependency;
import GameShop.java.controllers.interfaces.ServiceDependency;
import GameShop.java.services.factories.MultiServiceFactory;
import GameShop.java.services.interfaces.IService;
import GameShop.java.services.factories.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Router {
    private FXMLLoader loader;

    private final Map<RouteNames, String> routes = new HashMap<>() {{
        String VIEW_PATH = "../../resources/views/";

        put(RouteNames.HOME, VIEW_PATH + "Home.fxml");
        put(RouteNames.SIGN_IN, VIEW_PATH + "SignIn.fxml");
        put(RouteNames.VIEW_GAMES, VIEW_PATH + "ViewGames.fxml");
        put(RouteNames.VIEW_CONSOLES, VIEW_PATH + "ViewConsoles.fxml");
        put(RouteNames.SHOP_KEEPER_HOME, VIEW_PATH + "ShopKeeperHome.fxml");
        put(RouteNames.CREATE_RENTAL, VIEW_PATH + "CreateRental.fxml");
        put(RouteNames.VIEW_BASKET, VIEW_PATH + "ViewBasket.fxml");
        put(RouteNames.VIEW_CUSTOMERS, VIEW_PATH + "ViewCustomers.fxml");
        put(RouteNames.VIEW_RENTALS, VIEW_PATH + "ViewRentals.fxml");
        put(RouteNames.EDIT_GAMES, VIEW_PATH + "EditGames.fxml");
        put(RouteNames.EDIT_GAME, VIEW_PATH + "EditGame.fxml");
        put(RouteNames.EDIT_CONSOLES, VIEW_PATH + "EditConsoles.fxml");
        put(RouteNames.EDIT_CONSOLE, VIEW_PATH + "EditConsole.fxml");
    }};

    public void changeRoute(RouteNames route, ActionEvent event) throws IOException {
        String sceneRoute = routes.get(route);

        loader = new FXMLLoader(getClass().getResource(sceneRoute));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1080, 720);
        String CSS_FILE = "../../resources/css/style.css";
        root.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        IService service = ServiceFactory.getService(route);
        if (service != null) {
            ServiceDependency control = loader.getController();
            control.assignService(service);
            // if service is found, no need to try to find multi service, so return to break
            return;
        }

        IService[] services = MultiServiceFactory.getServices(route);
        if (services != null) {
            MultiServiceDependency control = loader.getController();
            control.assignServices(services);
        }
    }

    public void changeRouteActivated(RouteNames route, ActionEvent event, String id) throws IOException {
        changeRoute(route, event);

        ControllerCommunication control = loader.getController();
        control.passId(id);
    }
}
