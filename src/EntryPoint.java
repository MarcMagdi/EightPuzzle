import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * CS 482 : Artificial Intelligence.
 * Assignment 1 : Eight Puzzle Solver
 * Main Application Class
 * @author Marc Magdi
 * Friday 25 October 2018
 */
public class EntryPoint extends Application {

    /**
     * Starts the application window.
     */
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        String resourceName = "com/bmi/ai/views/MainLayout.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(resourceName));
        fullScreenApp(primaryStage);
        Scene scene = new Scene(root);
//        scene.get
//        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            System.out.println(key.getText());
//        });
        primaryStage.setScene(scene);
        primaryStage.setTitle("Eight Puzzle Solver");
        primaryStage.show();
    }

    /**
     * Main Method launches the application.
     * @param args the arguments of the program.
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**.
     * Start the application as full screen
     * @param primaryStage the Application stage to
     * apply the new dimensions
     */
    private void fullScreenApp(final Stage primaryStage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
    }
}
