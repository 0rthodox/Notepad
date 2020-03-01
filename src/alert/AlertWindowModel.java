package alert;

import javafx.stage.Stage;
import notepadview.NotepadViewModel;


class AlertWindowModel {
    private NotepadViewModel notepadViewModel;
    private Stage alertWindow;

    AlertWindowModel(NotepadViewModel notepadViewModel, Stage alertWindow) {
        this.notepadViewModel = notepadViewModel;
        this.alertWindow = alertWindow;
    }

    void save() {
        notepadViewModel.save();
        resetAndClose();
    }
    void resetAndClose() {
        notepadViewModel.resetCondition();
        alertWindow.close();
    }

}
