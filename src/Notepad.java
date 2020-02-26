import javafx.application.Application;
import javafx.stage.Stage;

public class Notepad extends Application {
    @Override
    public void start(Stage primaryStage) {
        Stage saveStage = new Stage();
        SubStagesHolder subStagesHolder = new SubStagesHolder(saveStage);
        new Layout(primaryStage, subStagesHolder);
        primaryStage.show();
    }
}
