package br.configuration.controllers.subject;

import br.common.models.subject.Subject;
import br.configuration.interactions.database.Action;
import javafx.fxml.FXML;


public class UpdateController extends StoreController {
    private SubjectView subject;

    public UpdateController(SubjectView subject) {
        this.subject = subject;
    }

    @Override
    public Subject getModel() {
        return this.subject.getModel();
    }

    @Override
    public Action getAction() {
        return Action.UPDATE;
    }

    @FXML
    public void initialize() {
        super.initialize();
        this.name.setText(this.subject.getName());
        this.getClickableController().getDbInteraction().selectWith(db -> db.allBySubject(this.getModel()));
    }
}
