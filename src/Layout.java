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
    TextArea textArea;


    Layout(Stage stage) {
        textArea = new TextArea();
        layoutManager = new LayoutManager(stage, textArea);

        MenuBar menuBar = constructMenuBar();


        VBox vBox = new VBox(menuBar, textArea);

        VBox.setVgrow(textArea, Priority.ALWAYS);

        stage.setScene(new Scene(vBox));

        stage.setTitle("Безымянный");
    }

    private MenuBar constructMenuBar() {
        Menu file = new Menu("Файл");
        Menu edit = new Menu("Правка");

        file.getItems().addAll(layoutManager.makeCreate(),
                layoutManager.makeOpen(), layoutManager.makeSave(),
                layoutManager.makeSaveAs(), layoutManager.makeExit());
        edit.getItems().addAll(layoutManager.makeUndo(), layoutManager.makeCut(),
                layoutManager.makeCopy(), layoutManager.makePaste(),
                layoutManager.makeDelete(), layoutManager.makeHighlightAll());

        return new MenuBar(file, edit);
    }

    Layout() {
       /* create.setOnAction(event -> {
            if (!textArea.getText().isEmpty()) {
                NotepadPlus.showPopUpStage(PopUpStage.constructPopUpStage(currentFile, textArea));
            }
        });
        saveAs.setOnAction(event -> FileManager.saveToNew(textManager.getText()));
        save.setOnAction(event -> {
            if (currentFile == null) {
                currentFile = FileManager.saveToNew(textManager.getText());
                syncronizeStage();
            } else {
                FileManager.saveToExisting(currentFile, textManager.getText());
            }
        });
        open.setOnAction(event -> {
            currentFile = FileManager.open();
            List<String> fileContents = FileManager.readPath(currentFile);
            for (String line : fileContents) {
                textManager.getTextArea().appendText(line + lineSeparator());
            }
        });

        exit.setOnAction(event -> NotepadPlus.terminate());

        file.getItems().add(create);
        file.getItems().add(open);
        file.getItems().add(save);
        file.getItems().add(saveAs);
        //file.getItems().add(pageParams);
        //file.getItems().add(print);
        file.getItems().add(exit);

        //format.getItems().add(hyphenation);
        format.getItems().add(font);

        view.getItems().add(conditionString);

        help.getItems().add(viewHelp);
        help.getItems().add(aboutProgram);

        menuBar = new MenuBar(file, textManager.getMenu(), format, view, help);

        vBox = new VBox(menuBar, textArea);

        VBox.setVgrow(textManager.getTextArea(), Priority.ALWAYS);

        primaryScene = new Scene(vBox);*/
    }

}
