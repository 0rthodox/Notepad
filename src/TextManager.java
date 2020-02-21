import javafx.event.Event;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class TextManager {
    private TextArea textArea = new TextArea();

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

    private Menu edit = new Menu("Правка");


    public TextManager() {
        undo.setOnAction(event -> textArea.undo());
        cut.setOnAction(event -> textArea.cut());
        copy.setOnAction(event -> textArea.copy());
        paste.setOnAction(event -> textArea.paste());
        delete.setOnAction(event -> {
            if (textArea.getSelection().getLength() != 0) {
                textArea.deleteText(textArea.getSelection());
            }
        });
        highlightAll.setOnAction(event -> textArea.selectAll());

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

    }

    public Menu getMenu() {
        return edit;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public String getText() {
        return textArea.getText();
    }

    public void appendText(String text) {
        textArea.appendText(text);
    }

}
