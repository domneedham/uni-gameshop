package GameShop;

import GameShop.java.services.StateService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    // Creates a public object that holds current state of the application (mocking a DB)
    // can be accessed by any class in the application via App.state
    public static StateService state = new StateService();

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(state);
        Parent root = FXMLLoader.load(getClass().getResource("resources/views/Home.fxml"));
        root.getStylesheets()
                .add(getClass().getResource("resources/css/style.css").toExternalForm());
        primaryStage.setTitle("GameShop");
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
