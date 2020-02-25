import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;

public class FileManager {
    public static Path saveToNew(Stage stage, String text) {
        text = text.replaceAll("\n", lineSeparator());
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Txt files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Path currentPath = fileChooser.showSaveDialog(stage).toPath();
        saveToExisting(currentPath, text);
        return currentPath;
    }
    public static void saveToExisting(Path path, String text) {
        try (BufferedWriter ostream = new BufferedWriter(new FileWriter(path.toFile()))){
            ostream.write(text);
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }
    public static Path open(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        Path currentPath = fileChooser.showOpenDialog(stage).toPath();
        return currentPath;
    }

    public static List<String> readPath(Path currentPath) {
        List<String> readLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(currentPath.toFile()))){
            while (reader.ready()) {
                readLines.add(reader.readLine());
            }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
        return readLines;

    }
}