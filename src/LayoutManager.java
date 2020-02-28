import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.util.List;

import static java.lang.System.lineSeparator;

class LayoutManager {
    private Stage stage;
    private TextArea textArea;
    private Path currentFile = null;
    private String textCondition = "";

    LayoutManager(Stage stage, TextArea textArea) {
        this.stage = stage;
        this.textArea = textArea;
    }

    void setTitle(String title) {
        stage.setTitle(title + " — Блокнот");
    }

    void setImage(String imagePath) {
        stage.getIcons().add(FileManager.readImage(imagePath));
    }

    MenuItem makeCreate(SubStagesHolder subStagesHolder) {
        MenuItem create = new MenuItem("Создать");
        create.setOnAction(event -> {
            ensureRightCondition(subStagesHolder);
            setTitle("Безымянный");
        });
        create.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY));
        return create;
    }

    MenuItem makeOpen(SubStagesHolder subStagesHolder) {
        MenuItem open = new MenuItem("Открыть..");
        open.setOnAction(event -> {
            ensureRightCondition(subStagesHolder);
            currentFile = FileManager.open(stage);
            List<String> fileContents = FileManager.readPath(currentFile);
            for (String line : fileContents) {
                textArea.appendText(line + lineSeparator());
            }
            setTitle(currentFile.getFileName().toString());
            updateTextCondition();
            setTitle("Безымянный");
        });
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY));
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
            updateTextCondition();
        });
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY));
        return save;
    }

    MenuItem makeSaveAs() {
        MenuItem saveAs = new MenuItem("Сохранить как..");
        saveAs.setOnAction(event -> {
            currentFile = FileManager.saveToNew(stage, textArea.getText()).getFileName();
            setTitle(currentFile.getFileName().toString());
            updateTextCondition();
        });
        return saveAs;
    }
    MenuItem makeExit(SubStagesHolder subStagesHolder) {
        MenuItem exit = new MenuItem("Выход");
        exit.setOnAction(event -> {
            ensureRightCondition(subStagesHolder);
            stage.close();
        });
        exit.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.CONTROL_ANY));
        return exit;
    }
    MenuItem makeUndo() {
        MenuItem undo = new MenuItem("Отменить");
        undo.setOnAction(event -> textArea.undo());
        undo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_ANY));
        return undo;
    }
    MenuItem makeCut() {
        MenuItem cut = new MenuItem("Вырезать");
        cut.setOnAction(event -> textArea.cut());
        cut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_ANY));
        return cut;
    }
    MenuItem makeCopy() {
        MenuItem copy = new MenuItem("Копировать");
        copy.setOnAction(event -> textArea.copy());
        copy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY));
        return copy;
    }
    MenuItem makePaste() {
        MenuItem paste = new MenuItem("Вставить");
        paste.setOnAction(event -> textArea.paste());
        paste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_ANY));
        return paste;
    }
    MenuItem makeDelete() {
        MenuItem delete = new MenuItem("Удалить");
        delete.setOnAction(event -> textArea.deleteText(textArea.getSelection()));
        delete.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        return delete;
    }
    MenuItem makeHighlightAll() {
        MenuItem highlightAll = new MenuItem("Выделить всё");
        highlightAll.setOnAction(event -> textArea.selectAll());
        highlightAll.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_ANY));
        return highlightAll;
    }

    private void ensureRightCondition(SubStagesHolder subStagesHolder) {
        if (!textArea.getText().equals(textCondition)) {
            subStagesHolder.makeSaveStage(currentFile, textArea);
        } else {
            currentFile = null;
        }
    }

    void handleClosing(SubStagesHolder subStagesHolder) {
        stage.setOnCloseRequest(event -> ensureRightCondition(subStagesHolder));
    }
    private void updateTextCondition() {
        textCondition = textArea.getText();
    }
}
