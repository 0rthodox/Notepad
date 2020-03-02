package notepadview;

import alert.AlertWindow;
import alert.Answer;
import javafx.application.Platform;
import javafx.stage.FileChooser;
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

    private String fileName() {
        if (currentFile == null) {
            return "Безымянный";
        }
        return currentFile.getFileName().toString();
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

    private void resetCondition() {
        textArea.clear();
        currentFile = null;
        updateCondition();
    }

    void create() {
        if (modified()) {
            AlertWindow alertWindow = new AlertWindow(stage, fileName());
            Answer answer = alertWindow.getAnswer();
            if (!answer.equals(Answer.DISMISS)) {
                if (answer.equals(Answer.YES)) {
                    save();
                }
                resetCondition();
            }
        } else {
            resetCondition();
        }
    }

    private void refill(List<String> contents) {
        textArea.clear();
        for (String line : contents) {
            textArea.appendText(line + lineSeparator());
        }
    }

    void save() {
        if (currentFile != null) {
            FileManager.save(currentFile, textArea.getText());
            logText();
        } else {
            saveAs();
        }
    }
    void saveAs() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Txt files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Path openedFile = fileChooser.showSaveDialog(stage).toPath();
        if (openedFile != null) {
            currentFile = openedFile;
            FileManager.save(currentFile,
                    textArea.getText().replaceAll("\n", lineSeparator()));
        }
        updateTitle();
        updateCondition();
    }

    void updateTitle() {
        stage.setTitle(fileName() + " — Блокнот");
    }

    void open() {
        if (modified()) {
            AlertWindow alertWindow = new AlertWindow(stage, fileName());
            Answer answer = alertWindow.getAnswer();
            if (!answer.equals(Answer.DISMISS)) {
                if (answer.equals(Answer.YES)) {
                    save();
                }
                resetCondition();
                openWithoutChecking();
            }
        } else {
            openWithoutChecking();
        }
    }

    private void openWithoutChecking() {
        FileChooser fileChooser = new FileChooser();
        Path openedFile = fileChooser.showOpenDialog(stage).toPath();
        if (openedFile != null) {
            currentFile = openedFile;
            refill(FileManager.readPath(currentFile));
            updateCondition();
        }
    }

    void exit() {
        if (modified()) {
            AlertWindow alertWindow = new AlertWindow(stage, fileName());
            Answer answer = alertWindow.getAnswer();
            if (!answer.equals(Answer.DISMISS)) {
                if (answer.equals(Answer.YES)) {
                    save();
                }
                Platform.exit();
            }
        } else {
            Platform.exit();
        }
    }
}
