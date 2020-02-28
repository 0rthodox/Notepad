import alert.AlertWindow;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Notepad extends Application {
    @Override
    public void start(Stage primaryStage) {
        Stage saveStage = new Stage();
        saveStage.initModality(Modality.APPLICATION_MODAL);
        AlertWindow alertWindow = new AlertWindow(saveStage);
        new NotepadView(primaryStage, alertWindow);
        primaryStage.show();
    }
}
