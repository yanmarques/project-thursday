package br.game;

import br.common.models.subject.Subject;
import br.game.common.PlayerGatherer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class BriefingController extends PlayerGatherer {
    private Subject subject;

    @FXML
    private Label subjectChoosed;

    @FXML
    private TextArea subjectCuriosities;

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void render() {

    }
}
