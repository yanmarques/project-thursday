package br.configuration.controllers.classes;

import br.common.models.classes.Class;
import br.configuration.interactions.database.Action;
import javafx.fxml.FXML;

public class UpdateController extends StoreController {
    private ClassView view;

    public UpdateController(ClassView view) {
        this.view = view;
    }

    @FXML
    public void initialize() {
        super.initialize();
        this.name.setText(this.view.getName());
    }

    @Override
    protected Action getAction() {
        return Action.UPDATE;
    }

    @Override
    protected Class getModel() {
        return this.view.getModel();
    }
}
