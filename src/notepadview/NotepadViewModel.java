package notepadview;

import alert.AlertWindow;
import utils.FileManager;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.util.List;

import static java.lang.System.lineSeparator;

public class NotepadViewModel {

    private Stage stage;
    private TextArea textArea;
    private Path currentFile;
    private String loggedText = "";

    public Stage getStage() {
        return stage;
    }

    NotepadViewModel(Stage stage, TextArea textArea) {
        this.stage = stage;
        this.textArea = textArea;
    }

    void setImage(String imagePath) {
        stage.getIcons().add(FileManager.readImage(imagePath));
    }

    public String fileName() {
        if (currentFile == null) {
            return "Безымянный";
        }
        return currentFile.getFileName().toString();
    }

    boolean modified() {
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
    void saveAs() {
        currentFile = FileManager.saveToNew(stage, textArea.getText()).getFileName();
        updateTitle();
        updateCondition();
    }

    void updateTitle() {
        stage.setTitle(fileName() + " — Блокнот");
    }

    void open() {
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

    void exit() {
        if (modified()) {
            new AlertWindow(this);
        } else {
            stage.close();
        }
    }
}
