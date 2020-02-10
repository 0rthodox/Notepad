import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class ButtonHolder {
    private List<Button> buttons;
    public ButtonHolder() {
        buttons = new ArrayList<>();
    }
    void add(Button button) {
        buttons.add(button);
    }
    public void setStyle(String style) {
        for(int i = 0; i < buttons.size(); ++i) {
            buttons.get(i).setStyle(style);
        }
    }
    Button get(int i) {
        return buttons.get(i);
    }
    public void checkMouseHolding() {
        for(int i = 0; i < buttons.size(); ++i) {
            Button currentButton = buttons.get(i);
            currentButton.setOnMouseEntered(event -> {
                currentButton.setStyle("-fx-background-color: #e5f3ff; -fx-background-radius: 0; -fx-background-insets: 0; -fx-padding: 1 7 1 7;");
            });
            currentButton.setOnMouseExited(event -> {
                currentButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-background-insets: 0; -fx-padding: 1 7 1 7;");
            });

        }
    }
}
