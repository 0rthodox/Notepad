package alert;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.nio.file.Path;

public class AlertWindow {
    private Stage saveStage = new Stage();
    private final int paddingAndSpacing = 20;

    public AlertWindow(Stage stage) {
        saveStage.initOwner(stage);
    }

    public void makeSaveStage(Path filePath, TextArea textArea) {
        AlertWindowModel alertWindowModel = new AlertWindowModel(filePath, saveStage, textArea);
        Label label = new Label(new StringBuilder().append("Сохранить изменения в файле ").append('\"')
                .append(processFileName(filePath)).append("\"?").toString());
        label.setPadding(new Insets(0, paddingAndSpacing, paddingAndSpacing, 0));

        HBox hBox = new HBox(alertWindowModel.makeSave(),
                alertWindowModel.makeNotSave(), alertWindowModel.makeDismiss());
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
