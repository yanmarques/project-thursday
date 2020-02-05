package br.configuration.controllers.selectionRules;

import br.common.models.selectionRules.SelectionRule;
import br.configuration.interactions.database.Action;

import java.io.File;

public class UpdateController extends StoreController {
    private SelectionRuleView view;

    public UpdateController(SelectionRuleView view) {
        this.view = view;
    }

    @Override
    protected SelectionRule getModel() {
        return this.view.getModel();
    }

    @Override
    protected Action getAction() {
        return Action.UPDATE;
    }

    @Override
    public void initialize() {
        super.initialize();
        this.registerImage();
        this.getFakeRelationDb().selectWith(db -> db.allBySelectionRule(this.getModel()));
    }

    private void registerImage() {
        String imagePath = this.getModel().getImagePath();
        File file = new File(imagePath);
        this.renderImage(file);
    }
}
