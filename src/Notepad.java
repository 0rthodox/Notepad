import javafx.application.Application;
import javafx.stage.Stage;
import notepadview.NotepadView;

public class Notepad extends Application {
    @Override
    public void start(Stage primaryStage) {
        new NotepadView(primaryStage);
        primaryStage.show();
    }
}
