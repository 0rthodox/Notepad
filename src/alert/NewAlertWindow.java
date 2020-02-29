package alert;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import notepadview.NotepadViewModel;

public class NewAlertWindow {
    Stage alertWindow = new Stage();
    private static int PADDING_AND_SPACING = 20;
    public NewAlertWindow(NotepadViewModel notepadViewModel) {
        alertWindow.initOwner(notepadViewModel.getStage());
        alertWindow.initModality(Modality.WINDOW_MODAL);
        NewAlertWindowModel newAlertWindowModel = new NewAlertWindowModel(notepadViewModel, alertWindow);

        Label label = new Label("Сохранить изменения в файле " + '\"' +
                notepadViewModel.fileName() + "\"?");

        label.setPadding(new Insets(0, PADDING_AND_SPACING, PADDING_AND_SPACING, 0));

        HBox hBox = new HBox(newAlertWindowModel.makeSave(),
                newAlertWindowModel.makeNotSave(), newAlertWindowModel.makeDismiss());
        hBox.setSpacing(PADDING_AND_SPACING);
        VBox vBox = new VBox(label, hBox);
        vBox.setPadding(new Insets(PADDING_AND_SPACING));
        vBox.setSpacing(PADDING_AND_SPACING);

        Scene scene = new Scene(vBox);
        alertWindow.setScene(scene);
        alertWindow.setTitle("Блокнот");
        alertWindow.show();
    }
}
