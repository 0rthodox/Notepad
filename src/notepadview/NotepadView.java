package notepadview;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NotepadView {

    private NotepadViewModel notepadViewModel;
    private TextArea textArea;
    private final String imagePath = "resources/np.png";

    public NotepadView(Stage stage) {
        textArea = new TextArea();
        notepadViewModel = new NotepadViewModel(stage, textArea);

        MenuBar menuBar = constructMenuBar();

        VBox vBox = new VBox(menuBar, textArea);

        VBox.setVgrow(textArea, Priority.ALWAYS);

        stage.setScene(new Scene(vBox));

        notepadViewModel.setTitle("Безымянный");
        notepadViewModel.setImage(imagePath);
        notepadViewModel.handleClosing();
    }

    private MenuBar constructMenuBar() {
        Menu file = new Menu("Файл");
        Menu edit = new Menu("Правка");

        file.getItems().addAll(notepadViewModel.makeCreate(),
                notepadViewModel.makeOpen(), notepadViewModel.makeSave(),
                notepadViewModel.makeSaveAs(), notepadViewModel.makeExit());
        edit.getItems().addAll(notepadViewModel.makeUndo(), notepadViewModel.makeCut(),
                notepadViewModel.makeCopy(), notepadViewModel.makePaste(),
                notepadViewModel.makeDelete(), notepadViewModel.makeHighlightAll());

        return new MenuBar(file, edit);
    }
}
