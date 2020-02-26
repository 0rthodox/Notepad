import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Path;

public class SubStagesHolder {
    Stage saveStage;
    private final int paddingAndSpacing = 20;

    public SubStagesHolder(Stage saveStage) {
        this.saveStage = saveStage;
    }

    public void makeSaveStage(Path filePath, TextArea textArea) {
        SaveStageManager saveStageManager = new SaveStageManager(filePath, saveStage, textArea);
        saveStage.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Сохранить изменения в файле " + "\"" + processFileName(filePath) + "\"?" );
        label.setPadding(new Insets(0, 2 * paddingAndSpacing, 2 * paddingAndSpacing, 0));

        HBox hBox = new HBox(saveStageManager.makeSave(),
                saveStageManager.makeNotSave(), saveStageManager.makeDismiss());
        hBox.setSpacing(paddingAndSpacing);
        VBox vBox = new VBox(label, hBox);
        vBox.setPadding(new Insets(paddingAndSpacing));
        vBox.setSpacing(paddingAndSpacing);
        Scene scene = new Scene(vBox);
        saveStage.setScene(scene);
        saveStage.setTitle("Блокнот");
        saveStage.show();
    }

    String processFileName(Path filePath) {
        if (filePath == null) {
            return "Безымянный";
        } else {
            return filePath.getFileName().toString();
        }
    }
}
