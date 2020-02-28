import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.nio.file.Path;

public class SaveStageManager {
    private Path filePath;
    private Stage stage;
    private TextArea textArea;

    SaveStageManager(Path filePath, Stage stage, TextArea textArea) {
        this.filePath = filePath;
        this.stage = stage;
        this.textArea = textArea;
    }

    Button makeSave() {
        Button save = new Button("Сохранить");
        save.setOnAction(event -> {
            if (filePath == null) {
                FileManager.saveToNew(stage, textArea.getText());
            } else {
                FileManager.saveToExisting(filePath, textArea.getText());
            }
            textArea.clear();
            filePath = null;
            stage.close();
        });
        return save;
    }
    Button makeNotSave() {
        Button notSave = new Button("Не сохранять");
        notSave.setOnAction(event -> {
            textArea.clear();
            filePath = null;
            stage.close();
        });
        return notSave;
    }
    Button makeDismiss() {
        Button dismiss = new Button("Отмена");
        dismiss.setOnAction(event -> stage.close());
        return dismiss;
    }

}
