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

class SubStagesHolder {
    private Stage saveStage;
    private final int paddingAndSpacing = 20;

    SubStagesHolder(Stage saveStage) {
        this.saveStage = saveStage;
    }

    void makeSaveStage(Path filePath, TextArea textArea) {
        SaveStageManager saveStageManager = new SaveStageManager(filePath, saveStage, textArea);
        Label label = new Label("Сохранить изменения в файле " + "\"" + processFileName(filePath) + "\"?" );
        label.setPadding(new Insets(0, paddingAndSpacing, paddingAndSpacing, 0));

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

    private String processFileName(Path filePath) {
        if (filePath == null) {
            return "Безымянный";
        } else {
            return filePath.getFileName().toString();
        }
    }
}
