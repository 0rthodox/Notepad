import alert.AlertWindow;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Notepad extends Application {
    @Override
    public void start(Stage primaryStage) {
        new NotepadView(primaryStage);
        primaryStage.show();
    }
}
