package br.configuration.controllers.alumn;

import br.common.models.alumn.Alumn;
import br.configuration.controllers.classes.ClassView;
import br.configuration.interactions.database.Action;

public class UpdateController extends StoreController {
    private AlumnView view;

    @Override
    protected Alumn getModel() {
        return this.view.getModel();
    }

    @Override
    protected Action getAction() {
        return Action.UPDATE;
    }

    @Override
    public void initialize() {
        super.initialize();
        this.name.setText(this.view.getName());

        ClassView classView = new ClassView(this.getModel().getClassModel());
        this.classesView.getSelectionModel().select(classView);
    }

    public UpdateController(AlumnView view) {
        this.view = view;
    }

    @Override
    protected void setCurrentTeacher(Alumn alumn) {
        // TODO do nothing or change the view current responsible teacher.
    }
}
