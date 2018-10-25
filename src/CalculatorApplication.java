import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 1 : Simple Calculator with GUI
 * Calculator Application Class
 * @author Bishoy Nader
 * Friday 14 October 2016
 */
public class CalculatorApplication extends Application {

    /**
     * Starts the application window.
     */
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        String resourceName = "CalculatorLayout.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(resourceName));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Sample Calculator with GUI");
        primaryStage.show();
    }

    /**
     * Main Method launches the application.
     * @param args the arguments of the program.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
