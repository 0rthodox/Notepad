import alert.AlertWindow;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class NotepadView {

    private NotepadViewModel notepadViewModel;
    private TextArea textArea;
    private final String imagePath = "resources/np.png";
    private AlertWindow alertWindow;

    NotepadView(Stage stage) {
        alertWindow = new AlertWindow(stage);
        textArea = new TextArea();
        notepadViewModel = new NotepadViewModel(stage, textArea);

        MenuBar menuBar = constructMenuBar();

        VBox vBox = new VBox(menuBar, textArea);

        VBox.setVgrow(textArea, Priority.ALWAYS);

        stage.setScene(new Scene(vBox));

        notepadViewModel.setTitle("Безымянный");
        notepadViewModel.setImage(imagePath);
        notepadViewModel.handleClosing(alertWindow);
    }

    private MenuBar constructMenuBar() {
        Menu file = new Menu("Файл");
        Menu edit = new Menu("Правка");

        file.getItems().addAll(notepadViewModel.makeCreate(alertWindow),
                notepadViewModel.makeOpen(alertWindow), notepadViewModel.makeSave(),
                notepadViewModel.makeSaveAs(), notepadViewModel.makeExit(alertWindow));
        edit.getItems().addAll(notepadViewModel.makeUndo(), notepadViewModel.makeCut(),
                notepadViewModel.makeCopy(), notepadViewModel.makePaste(),
                notepadViewModel.makeDelete(), notepadViewModel.makeHighlightAll());

        return new MenuBar(file, edit);
    }
}
