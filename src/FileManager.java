import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;

public class FileManager {
    public static Path save(Path currentPath, String text) {
        text = text.replaceAll("\n", lineSeparator());
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Txt files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        currentPath = fileChooser.showSaveDialog(NotepadPlus.getPrimaryStage()).toPath();
        try (BufferedWriter ostream = new BufferedWriter(new FileWriter(currentPath.toFile()))){
            ostream.write(text);
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
        NotepadPlus.getPrimaryStage().setTitle(currentPath.getFileName().toString() + " - Блокнот");
        return currentPath;
    }
    public static void open(Path currentPath) {
        FileChooser fileChooser = new FileChooser();
        currentPath = fileChooser.showOpenDialog(NotepadPlus.getPrimaryStage()).toPath();
        NotepadPlus.getPrimaryStage().setTitle(currentPath.getFileName().toString() + " - Блокнот");
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
