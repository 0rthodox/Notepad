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

public class Notepad extends Application {
    private String currentFileName = "Безымянный";
    private static final int x0 = 420;
    private static final int y0 = 230;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(currentFileName + " – Блокнот");
        primaryStage.setX(x0);
        primaryStage.setY(y0);
        try {
            FileInputStream inputImageStream = new FileInputStream("resources/np.png");
            Image iImage = new Image(inputImageStream);
            primaryStage.getIcons().add(iImage);

        } catch (FileNotFoundException noImage) {
            throw new IllegalArgumentException("No such image");
        }
        ButtonHolder mainButtons = new ButtonHolder();
        mainButtons.add(new Button("Файл"));
        mainButtons.add(new Button("Правка"));
        mainButtons.add(new Button("Формат"));
        mainButtons.add(new Button("Вид"));
        mainButtons.add(new Button("Справка"));
        mainButtons.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-background-insets: 0; -fx-padding: 1 7 1 7;");
        HBox mainPlank = new HBox(mainButtons.get(0), mainButtons.get(1),
                mainButtons.get(2), mainButtons.get(3), mainButtons.get(4));
        mainPlank.setStyle("-fx-background-color: #ffffff");
        mainButtons.checkMouseHolding();
        TextArea mainArea = new TextArea();
        mainArea.setPrefColumnCount(40);
        mainArea.setPrefRowCount(40);
        mainArea.setStyle("-fx-background-insets: 0; -fx-background-radius: 0;" +
                "-fx-faint-focus-color: -fx-control-inner-background; -fx-focus-color: -fx-control-inner-background;" +
                "-fx-box-border: none; -fx-text-box-border: none; -fx-border-width: -5; -fx-border-insets: 0; ");
        VBox almostScene = new VBox(mainPlank, mainArea);
        Scene primaryScene = new Scene(almostScene);

        primaryStage.setScene(primaryScene);

        primaryStage.show();
    }

}