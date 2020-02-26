import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class Layout {

    private LayoutManager layoutManager;
    private TextArea textArea;
    private final String imagePath = "resources/np.png";
    private SubStagesHolder subStagesHolder;

    Layout(Stage stage, SubStagesHolder subStagesHolder) {
        this.subStagesHolder = subStagesHolder;
        textArea = new TextArea();
        layoutManager = new LayoutManager(stage, textArea);

        MenuBar menuBar = constructMenuBar();

        VBox vBox = new VBox(menuBar, textArea);

        VBox.setVgrow(textArea, Priority.ALWAYS);

        stage.setScene(new Scene(vBox));

        layoutManager.setTitle("Безымянный");
        layoutManager.setImage(imagePath);
        layoutManager.handleClosing(subStagesHolder);
    }

    private MenuBar constructMenuBar() {
        Menu file = new Menu("Файл");
        Menu edit = new Menu("Правка");

        file.getItems().addAll(layoutManager.makeCreate(subStagesHolder),
                layoutManager.makeOpen(subStagesHolder), layoutManager.makeSave(),
                layoutManager.makeSaveAs(), layoutManager.makeExit(subStagesHolder));
        edit.getItems().addAll(layoutManager.makeUndo(), layoutManager.makeCut(),
                layoutManager.makeCopy(), layoutManager.makePaste(),
                layoutManager.makeDelete(), layoutManager.makeHighlightAll());

        return new MenuBar(file, edit);
    }
}
