import javafx.scene.Scene;
import javafx.scene.image.Image;
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

        VBox vBox = new VBox(mainMenu.getMenuBar(), mainMenu.getTextArea());

        Scene primaryScene = new Scene(vBox);

        stage.setScene(primaryScene);


        stage.setTitle((mainMenu.getCurrentFile() == null ?
                "Безымянный" : mainMenu.getCurrentFile().toString()) + " – Блокнот");

    }
}
