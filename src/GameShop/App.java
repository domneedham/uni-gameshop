package GameShop;

import GameShop.java.services.StateService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Creates a public object that holds current state of the application (mocking a DB)
        new StateService();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("resources/views/Home.fxml"));
            root.getStylesheets()
                    .add(getClass().getResource("resources/css/style.css").toExternalForm());
            primaryStage.setTitle("GameShop");
            primaryStage.setScene(new Scene(root, 1080, 720));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
