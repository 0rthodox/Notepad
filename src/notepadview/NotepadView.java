package notepadview;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NotepadView {

    private NotepadViewModel notepadViewModel;
    private TextArea textArea;
    private final String imagePath = "resources/np.png";
    private Stage stage;

    public NotepadView(Stage stage) {
        this.stage = stage;
        textArea = new TextArea();
        notepadViewModel = new NotepadViewModel(stage, textArea);

        MenuBar menuBar = createMenuBar();

        VBox vBox = new VBox(menuBar, textArea);

        VBox.setVgrow(textArea, Priority.ALWAYS);

        stage.setScene(new Scene(vBox));

        notepadViewModel.updateTitle();
        notepadViewModel.setImage(imagePath);
        handleClosing();
    }

    private MenuBar createMenuBar() {
        Menu file = new Menu("Файл");
        Menu edit = new Menu("Правка");

        file.getItems().addAll(createNewButton(),
                createOpenButton(), createSaveButton(),
                createSaveAsButton(), createExitButton());
        edit.getItems().addAll(createUndoButton(), createCutButton(),
                createCopyButton(), createPasteButton(),
                createDeleteButton(), createHighlightAllButton());

        return new MenuBar(file, edit);
    }
    private MenuItem createNewButton() {
        MenuItem create = new MenuItem("Создать");
        create.setOnAction(event -> notepadViewModel.create());
        create.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY));
        return create;
    }

    private MenuItem createOpenButton() {
        MenuItem open = new MenuItem("Открыть..");
        open.setOnAction(event -> notepadViewModel.open());
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY));
        return open;
    }

    private MenuItem createSaveButton() {
        MenuItem save = new MenuItem("Сохранить");
        save.setOnAction(event -> notepadViewModel.save());
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY));
        return save;
    }

    private MenuItem createSaveAsButton() {
        MenuItem saveAs = new MenuItem("Сохранить как..");
        saveAs.setOnAction(event -> notepadViewModel.saveAs());
        return saveAs;
    }
    private MenuItem createExitButton() {
        MenuItem exit = new MenuItem("Выход");
        exit.setOnAction(event -> notepadViewModel.exit());
        exit.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_ANY));
        return exit;
    }
    private MenuItem createUndoButton() {
        MenuItem undo = new MenuItem("Отменить");
        undo.setOnAction(event -> textArea.undo());
        undo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_ANY));
        return undo;
    }
    private MenuItem createCutButton() {
        MenuItem cut = new MenuItem("Вырезать");
        cut.setOnAction(event -> textArea.cut());
        cut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_ANY));
        return cut;
    }
    private MenuItem createCopyButton() {
        MenuItem copy = new MenuItem("Копировать");
        copy.setOnAction(event -> textArea.copy());
        copy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY));
        return copy;
    }
    private MenuItem createPasteButton() {
        MenuItem paste = new MenuItem("Вставить");
        paste.setOnAction(event -> textArea.paste());
        paste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_ANY));
        return paste;
    }
    private MenuItem createDeleteButton() {
        MenuItem delete = new MenuItem("Удалить");
        delete.setOnAction(event -> textArea.deleteText(textArea.getSelection()));
        delete.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        return delete;
    }
    private MenuItem createHighlightAllButton() {
        MenuItem highlightAll = new MenuItem("Выделить всё");
        highlightAll.setOnAction(event -> textArea.selectAll());
        highlightAll.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_ANY));
        return highlightAll;
    }

    private void handleClosing() {
        stage.setOnCloseRequest(event -> {
            notepadViewModel.exit();
            event.consume();
        });
    }
}
