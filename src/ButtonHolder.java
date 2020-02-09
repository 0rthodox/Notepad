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
}
