package alert;

import javafx.stage.Stage;
import utils.Answer;


class AlertWindowModel {
    private Stage alertWindow;
    private Answer answer;

    AlertWindowModel(Stage alertWindow) {
        this.alertWindow = alertWindow;
    }

    void yes() {
        answer = Answer.YES;
        alertWindow.close();
    }
    void no() {
        answer = Answer.NO;
        alertWindow.close();
    }
    void dismiss() {
        answer = Answer.DISMISS;
        alertWindow.close();
    }

    Answer getAnswer() {
        return answer;
    }
}
