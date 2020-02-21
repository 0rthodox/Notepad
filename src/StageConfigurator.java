import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class StageConfigurator {
    static void configureStage(Stage stage) {
        try {
            FileInputStream inputImageStream = new FileInputStream("resources/np.png");
            Image iImage = new Image(inputImageStream);
            stage.getIcons().add(iImage);

        } catch (FileNotFoundException noImage) {
            throw new IllegalArgumentException("No such image");
        }

        MainMenu mainMenu = new MainMenu();

        stage.setScene(mainMenu.getPrimaryScene());

        stage.setTitle(mainMenu.getTitle() + " – Блокнот");

    }
}
