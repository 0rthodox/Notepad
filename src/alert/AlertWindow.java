package alert;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Answer;

public class AlertWindow {
    private static int PADDING_AND_SPACING = 20;
    private AlertWindowModel alertWindowModel;
    public AlertWindow(Stage stage, String fileName) {
        Stage alertWindow = new Stage();
        alertWindow.initOwner(stage);
        alertWindow.initModality(Modality.WINDOW_MODAL);

        alertWindowModel = new AlertWindowModel(alertWindow);

        Label label = new Label("Сохранить изменения в файле " + '\"' +
                fileName + "\"?");

        label.setPadding(new Insets(0, PADDING_AND_SPACING, PADDING_AND_SPACING, 0));

        HBox hBox = new HBox(makeSave(),
                makeNotSave(), makeDismiss());
        hBox.setSpacing(PADDING_AND_SPACING);
        VBox vBox = new VBox(label, hBox);
        vBox.setPadding(new Insets(PADDING_AND_SPACING));
        vBox.setSpacing(PADDING_AND_SPACING);

        Scene scene = new Scene(vBox);
        alertWindow.setScene(scene);
        alertWindow.setTitle("Блокнот");
        alertWindow.showAndWait();
    }

    public Answer getAnswer() {
        return alertWindowModel.getAnswer();
    }

    private Button makeSave() {
        Button save = new Button("Сохранить");
        save.setOnAction(event -> alertWindowModel.yes());
        return save;
    }
    private Button makeNotSave() {
        Button notSave = new Button("Не сохранять");
        notSave.setOnAction(event -> alertWindowModel.no());
        return notSave;
    }
    private Button makeDismiss() {
        Button dismiss = new Button("Отмена");
        dismiss.setOnAction(event -> alertWindowModel.dismiss());
        return dismiss;
    }

}
