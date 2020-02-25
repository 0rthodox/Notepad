import javafx.application.Application;
import javafx.stage.Stage;

public class Notepad extends Application {
    @Override
    public void start(Stage primaryStage) {
        new Layout(primaryStage);
        primaryStage.show();
    }
}
