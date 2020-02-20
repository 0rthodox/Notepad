import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Path;

public class SubsidiaryStage {

    private static final int paddingAndSpacing = 10;

    Stage stage;

    public SubsidiaryStage(Path filePath, TextArea textArea) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        String fileName;
        if (filePath == null) {
            fileName = "Безымянный";
        } else {
            fileName = filePath.getFileName().toString();
        }
        Label label = new Label("Сохранить изменения в файле " + "\"" + fileName + "\"?" );
        label.setPadding(new Insets(0, 2 * paddingAndSpacing, 2 * paddingAndSpacing, 0));

        Button save = new Button("Сохранить");
        Button notSave = new Button("Не сохранять");
        Button dismiss = new Button("Отмена");

        save.setOnAction(event -> {
            FileManager.save(filePath, textArea.getText());
            textArea.clear();
            stage.close();
        });
        notSave.setOnAction(event -> {
            textArea.clear();
            stage.close();
        });
        dismiss.setOnAction(event -> {
            stage.close();
        });

        HBox hBox = new HBox(save, notSave, dismiss);
        hBox.setSpacing(paddingAndSpacing);
        VBox vBox = new VBox(label, hBox);
        vBox.setPadding(new Insets(paddingAndSpacing));
        vBox.setSpacing(paddingAndSpacing);
        Scene scene = new Scene(vBox);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Блокнот");
        stage.show();
    }
}
