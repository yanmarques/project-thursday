package br.configuration.controllers.subject.curiosity;

import br.common.models.curiosity.Curiosity;
import br.configuration.interactions.database.Action;
import javafx.fxml.FXML;

public class UpdateController extends StoreController {
    private CuriosityView view;

    public UpdateController(CuriosityView view) {
        this.view = view;
    }

    @Override
    protected Curiosity getModel() {
        return this.view.getModel();
    }

    @Override
    protected Action getAction() {
        return Action.UPDATE_FROM_VIEW;
    }

    @FXML
    public void initialize() {
        super.initialize();
        this.curiosityText.setText(this.getModel().getDescription());
    }

    @Override
    protected CuriosityView getViewToRemove() {
        return this.view;
    }
}
