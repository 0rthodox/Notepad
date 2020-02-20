import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class NotepadPlus extends Application {
    private String currentFileName = "Безымянный";

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(currentFileName + " – Блокнот");
        try {
            FileInputStream inputImageStream = new FileInputStream("resources/np.png");
            Image iImage = new Image(inputImageStream);
            primaryStage.getIcons().add(iImage);

        } catch (FileNotFoundException noImage) {
            throw new IllegalArgumentException("No such image");
        }

        MainField mainField = new MainField();
        MainMenu mainMenu = new MainMenu();

        VBox vBox = new VBox(mainMenu.getMenuBar(), mainField.getTextArea());

        Scene primaryScene = new Scene(vBox);

        primaryStage.setScene(primaryScene);

        primaryStage.show();
    }

}