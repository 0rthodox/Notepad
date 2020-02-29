package notepadview;

import alert.AlertWindow;
import fileIO.FileManager;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.util.List;

import static java.lang.System.lineSeparator;

public class NotepadViewModel {

    private Stage stage;
    private TextArea textArea;
    private Path currentFile = null;
    private String loggedText = "";

    public Stage getStage() {
        return stage;
    }

    NotepadViewModel(Stage stage, TextArea textArea) {
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
            if (modified()) {
                new AlertWindow(this);
            } else {
                resetCondition();
            }
        });
        create.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY));
        return create;
    }

    MenuItem makeOpen() {
        MenuItem open = new MenuItem("Открыть..");
        open.setOnAction(event -> open());
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY));
        return open;
    }

    MenuItem makeSave() {
        MenuItem save = new MenuItem("Сохранить");
        save.setOnAction(event -> save());
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY));
        return save;
    }

    MenuItem makeSaveAs() {
        MenuItem saveAs = new MenuItem("Сохранить как..");
        saveAs.setOnAction(event -> saveAs());
        return saveAs;
    }
    MenuItem makeExit() {
        MenuItem exit = new MenuItem("Выход");
        exit.setOnAction(event -> exit());
        exit.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_ANY));
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

    void handleClosing() {
        stage.setOnCloseRequest(event -> {
            exit();
            event.consume();
        });
    }

    public String fileName() {
        if (currentFile == null) {
            return "Безымянный";
        }
        return currentFile.getFileName().toString();
    }
    private void updateTitle() {
        stage.setTitle(fileName() + " — Блокнот");
    }

    private boolean modified() {
        return !loggedText.equals(textArea.getText());
    }

    private void logText() {
        loggedText = textArea.getText();
    }
    private void updateCondition() {
        logText();
        updateTitle();
    }

    public void resetCondition() {
        textArea.clear();
        currentFile = null;
        updateCondition();
    }

    private void refill(List<String> contents) {
        textArea.clear();
        for (String line : contents) {
            textArea.appendText(line + lineSeparator());
        }
    }

    public void save() {
        if (currentFile != null) {
            FileManager.saveToExisting(currentFile, textArea.getText());
            logText();
        } else {
            saveAs();
        }
    }
    private void saveAs() {
        currentFile = FileManager.saveToNew(stage, textArea.getText()).getFileName();
        setTitle(currentFile.getFileName().toString());
        updateCondition();
    }

    private void open() {
        if (modified()) {
            new AlertWindow(this);
        } else {
            Path openedFile = FileManager.open(stage);
            if (openedFile != null) {
                currentFile = openedFile;
                refill(FileManager.readPath(currentFile));
                updateCondition();
            }
        }

    }

    private void exit() {
        if (modified()) {
            new AlertWindow(this);
        } else {
            stage.close();
        }
    }
}
