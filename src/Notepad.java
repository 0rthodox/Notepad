import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Notepad extends Application {
    @Override
    public void start(Stage primaryStage) {
        Stage saveStage = new Stage();
        saveStage.initModality(Modality.APPLICATION_MODAL);
        SubStagesHolder subStagesHolder = new SubStagesHolder(saveStage);
        new Layout(primaryStage, subStagesHolder);
        primaryStage.show();
    }
}
