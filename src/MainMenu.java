import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.nio.file.Path;
import java.util.List;

import static java.lang.System.lineSeparator;

class MainMenu {

    private TextArea textArea = new TextArea();

    private Menu file = new Menu("Файл");
    private Menu edit = new Menu("Правка");
    private Menu format = new Menu("Формат");
    private Menu view = new Menu("Вид");
    private Menu help = new Menu("Справка");

    private MenuItem create = new MenuItem("Создать");
    private MenuItem open = new MenuItem("Открыть..");
    private MenuItem save = new MenuItem("Сохранить");
    private MenuItem saveAs = new MenuItem("Сохранить как..");
    private MenuItem pageParams = new MenuItem("Параметры страницы..");
    private MenuItem print = new MenuItem("Печать..");
    private MenuItem exit = new MenuItem("Выход");

    private MenuItem undo = new MenuItem("Отменить");
    private MenuItem cut = new MenuItem("Вырезать");
    private MenuItem copy = new MenuItem("Копировать");
    private MenuItem paste = new MenuItem("Вставить");
    private MenuItem delete = new MenuItem("Удалить");
    private MenuItem find = new MenuItem("Найти");
    private MenuItem findFurther = new MenuItem("Найти далее");
    private MenuItem replace = new MenuItem("Заменить");
    private MenuItem move = new MenuItem("Перейти");
    private MenuItem highlightAll = new MenuItem("Выделить всё");
    private MenuItem timeStamp = new MenuItem("Время и дата");

    private MenuItem hyphenation = new CheckMenuItem("Перенос по словам");
    private MenuItem font = new MenuItem("Шрифт..");

    private MenuItem conditionString = new MenuItem("Строка состояния");

    private MenuItem viewHelp = new MenuItem("Просмотреть справку");
    private MenuItem aboutProgram = new MenuItem("О программе");



    private MenuBar menuBar;

    private VBox vBox;

    private Scene primaryScene;

    private Path currentFile = null;
    private Path currentDirectory = null;
    private String title = "Безымянный";


    MainMenu() {
        create.setOnAction(event -> {
            if (!textArea.getText().isEmpty()) {
                SubsidiaryStage subStage = new SubsidiaryStage(currentFile, textArea);
                if(textArea.getText().isEmpty())
                    currentFile = null;
                    title = "Безымянный";
            }
        });
        saveAs.setOnAction(event -> {
            FileManager.save(currentFile, textArea.getText());
        });
        open.setOnAction(event -> {
            FileManager.open(currentFile);
            List<String> fileContents = FileManager.readPath(currentFile);
            for (String line : fileContents) {
                textArea.appendText(line + lineSeparator());
            }
        });

        exit.setOnAction(event -> {
            NotepadPlus.getPrimaryStage().close();
        });

        file.getItems().add(create);
        file.getItems().add(open);
        file.getItems().add(save);
        file.getItems().add(saveAs);
        file.getItems().add(pageParams);
        file.getItems().add(print);
        file.getItems().add(exit);

        edit.getItems().add(undo);
        edit.getItems().add(cut);
        edit.getItems().add(copy);
        edit.getItems().add(paste);
        edit.getItems().add(delete);
        edit.getItems().add(find);
        edit.getItems().add(findFurther);
        edit.getItems().add(replace);
        edit.getItems().add(move);
        edit.getItems().add(highlightAll);
        edit.getItems().add(timeStamp);

        format.getItems().add(hyphenation);
        format.getItems().add(font);

        view.getItems().add(conditionString);

        help.getItems().add(viewHelp);
        help.getItems().add(aboutProgram);

        menuBar = new MenuBar(file, edit, format, view, help);

        vBox = new VBox(menuBar, textArea);

        VBox.setVgrow(textArea, Priority.ALWAYS);

        primaryScene = new Scene(vBox);
    }

    public Path getCurrentFile() {
        return currentFile;
    }

    public String getTitle() {
        return title;
    }

    public Scene getPrimaryScene() {
        return primaryScene;
    }
}
