import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class NotepadPlus extends Application {

    static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void showPopUpStage(Stage popUpStage) {
        NotepadPlus.popUpStage = popUpStage;
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        NotepadPlus.popUpStage.show();
    }

    static void updateStageTitle(String name) {
        primaryStage.setTitle(name);
    }

    static void terminate() {
        primaryStage.close();
    }

    private static Stage primaryStage;
    private static Stage popUpStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        StageConfigurator.configureStage(stage);
        stage.show();
    }

}