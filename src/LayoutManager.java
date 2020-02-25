import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.util.List;

import static java.lang.System.lineSeparator;

public class LayoutManager {
    Stage stage;
    TextArea textArea;
    Path currentFile = null;

    public LayoutManager(Stage stage, TextArea textArea) {
        this.stage = stage;
        this.textArea = textArea;
    }

    void setTitle(String title) {
        stage.setTitle(title + " — Блокнот");
    }

    void setImage(String imagePath) {
        stage.getIcons().add(FileManager.readImage(imagePath));
    }

    MenuItem makeCreate() {
        MenuItem create = new MenuItem("Создать");
        create.setOnAction(event -> {
            ensureEmptiness();
            textArea.clear();
            currentFile = null;
        });
        return create;
    }

    MenuItem makeOpen() {
        MenuItem open = new MenuItem("Открыть..");
        open.setOnAction(event -> {
            ensureEmptiness();
            currentFile = FileManager.open(stage);
            List<String> fileContents = FileManager.readPath(currentFile);
            for (String line : fileContents) {
                textArea.appendText(line + lineSeparator());
            }
            setTitle(currentFile.getFileName().toString());
        });
        return open;
    }

    MenuItem makeSave() {
        MenuItem save = new MenuItem("Сохранить");
        save.setOnAction(event -> {
            if (currentFile != null) {
                FileManager.saveToExisting(currentFile, textArea.getText());
            } else {
                currentFile = FileManager.saveToNew(stage, textArea.getText()).getFileName();
                setTitle(currentFile.getFileName().toString());
            }
        });
        return save;
    }

    MenuItem makeSaveAs() {
        MenuItem saveAs = new MenuItem("Сохранить как..");
        saveAs.setOnAction(event -> {
            currentFile = FileManager.saveToNew(stage, textArea.getText()).getFileName();
            setTitle(currentFile.getFileName().toString());
        });
        return saveAs;
    }
    MenuItem makeExit() {
        MenuItem exit = new MenuItem("Выход");
        exit.setOnAction(event -> {
            ensureEmptiness();
            stage.close();
        });
        return exit;
    }
    MenuItem makeUndo() {
        MenuItem undo = new MenuItem("Отменить");
        undo.setOnAction(event -> textArea.undo());
        return undo;
    }
    MenuItem makeCut() {
        MenuItem cut = new MenuItem("Вырезать");
        cut.setOnAction(event -> textArea.cut());
        return cut;
    }
    MenuItem makeCopy() {
        MenuItem copy = new MenuItem("Копировать");
        copy.setOnAction(event -> textArea.copy());
        return copy;
    }
    MenuItem makePaste() {
        MenuItem paste = new MenuItem("Вставить");
        paste.setOnAction(event -> textArea.paste());
        return paste;
    }
    MenuItem makeDelete() {
        MenuItem delete = new MenuItem("Удалить");
        delete.setOnAction(event -> textArea.deleteText(textArea.getSelection()));
        return delete;
    }
    MenuItem makeHighlightAll() {
        MenuItem highlightAll = new MenuItem("Выделить всё");
        highlightAll.setOnAction(event -> textArea.selectAll());
        return highlightAll;
    }

    void ensureEmptiness() {
        if (!textArea.getText().isEmpty()) {
            //TODO::initialize sub window
        }
    }
}
