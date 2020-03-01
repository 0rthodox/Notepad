package utils;

import javafx.scene.image.Image;
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
        return fileChooser.showOpenDialog(stage).toPath();
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
    public static Image readImage(String imagePath) {
        try {
            FileInputStream inputImageStream = new FileInputStream(imagePath);
            return new Image(inputImageStream);

        } catch (FileNotFoundException noImage) {
            throw new IllegalArgumentException("No such image");
        }
    }
}