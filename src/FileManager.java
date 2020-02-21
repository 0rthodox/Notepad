import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;

public class FileManager {
    public static Path saveToNew(String text) {
        text = text.replaceAll("\n", lineSeparator());
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Txt files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Path currentPath = fileChooser.showSaveDialog(NotepadPlus.getPrimaryStage()).toPath();
        saveToExisting(currentPath, text);
        NotepadPlus.updateStageTitle(currentPath.getFileName().toString() + " - Блокнот");
        return currentPath;
    }
    public static void saveToExisting(Path path, String text) {
        try (BufferedWriter ostream = new BufferedWriter(new FileWriter(path.toFile()))){
            ostream.write(text);
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }
    public static Path open() {
        FileChooser fileChooser = new FileChooser();
        Path currentPath = fileChooser.showOpenDialog(NotepadPlus.getPrimaryStage()).toPath();
        NotepadPlus.updateStageTitle(currentPath.getFileName().toString() + " - Блокнот");
        return currentPath;
    }

    public static List<String> readPath(Path currentPath) {
        List<String> readLines = new ArrayList<>();
        try (BufferedReader istream = new BufferedReader(new FileReader(currentPath.toFile()))){
            while (istream.ready()) {
                readLines.add(istream.readLine());
            }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
        return readLines;
    
    }
}
