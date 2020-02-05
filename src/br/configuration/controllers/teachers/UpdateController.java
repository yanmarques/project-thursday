package br.configuration.controllers.teachers;

import br.common.models.teacher.Teacher;
import br.configuration.interactions.database.Action;
import javafx.fxml.FXML;

public class UpdateController extends StoreController {
    private TeacherView view;

    public UpdateController(TeacherView view) {
        this.view = view;
    }

    @Override
    protected Teacher getModel() {
        return this.view.getModel();
    }

    @Override
    protected Action getAction() {
        return Action.UPDATE;
    }

    @FXML
    public void initialize() {
        super.initialize();
        this.name.setText(this.getModel().getName());
        this.getFakeRelationDb().selectWith(db -> db.allByTeacher(this.getModel()));
    }
}
