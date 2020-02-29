package alert;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import notepadview.NotepadViewModel;


public class AlertWindowModel {
    private NotepadViewModel notepadViewModel;
    private Stage stage;

    AlertWindowModel(NotepadViewModel notepadViewModel, Stage stage) {
        this.notepadViewModel = notepadViewModel;
        this.stage = stage;
    }

    Button makeSave() {
        Button save = new Button("Сохранить");
        save.setOnAction(event -> {
            notepadViewModel.save();
            notepadViewModel.resetCondition();
            stage.close();
        });
        return save;
    }
    Button makeNotSave() {
        Button notSave = new Button("Не сохранять");
        notSave.setOnAction(event -> {
            notepadViewModel.resetCondition();
            stage.close();
        });
        return notSave;
    }
    Button makeDismiss() {
        Button dismiss = new Button("Отмена");
        dismiss.setOnAction(event -> {
            stage.close();
        });
        return dismiss;
    }
}
