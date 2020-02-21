import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.nio.file.Path;
import java.util.List;

import static java.lang.System.lineSeparator;

class MainMenu {

    TextManager textManager = new TextManager();

    private TextArea textArea = textManager.getTextArea();

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

    private static Path currentFile = null;
    private static String title = "Безымянный";

    MainMenu() {
        create.setOnAction(event -> {
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

    static void resetPath() {
        currentFile = null;
        title = "Безымянный";
        syncronizeStage();
    }
    static void syncronizeStage() {
        NotepadPlus.updateStageTitle(title + " — Блокнот");
    }
}
