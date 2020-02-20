import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.nio.file.Path;

public class MainMenu {
    private Menu file;
    private Menu edit;
    private Menu format;
    private Menu view;
    private Menu help;

    private MenuItem create;
    private MenuItem open;
    private MenuItem save;
    private MenuItem saveAs;
    private MenuItem pageParams;
    private MenuItem print;
    private MenuItem exit;

    private MenuBar menuBar;

    private Path currentFile;

    MainMenu() {
        file = new Menu("Файл");
        edit = new Menu("Правка");
        format = new Menu("Формат");
        view = new Menu("Вид");
        help = new Menu("Справка");

        create = new MenuItem("Создать");
        open = new MenuItem("Открыть..");
        save = new MenuItem("Сохранить");
        saveAs = new MenuItem("Сохранить как..");
        pageParams = new MenuItem("Параметры страницы..");
        print = new MenuItem("Печать..");
        exit = new MenuItem("Выход");

        currentFile = null;

        create.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            //currentFile = fileChooser.showOpenDialog()

        });


        file.getItems().add(create);
        file.getItems().add(open);
        file.getItems().add(save);
        file.getItems().add(saveAs);
        file.getItems().add(pageParams);
        file.getItems().add(print);
        file.getItems().add(exit);

        menuBar = new MenuBar(file, edit, format, view, help);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
