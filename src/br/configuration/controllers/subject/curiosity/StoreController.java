package br.configuration.controllers.subject.curiosity;

import br.common.models.curiosity.Curiosity;
import br.common.models.curiosity.CuriosityDAO;
import br.common.validation.RuleNames;
import br.configuration.interactions.database.Action;
import br.configuration.interactions.database.InteractiveController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class StoreController extends InteractiveController<Curiosity, CuriosityView, CuriosityDAO> {
    @FXML
    protected TextArea curiosityText;

    @Override
    protected void fillModel(Curiosity model) {
        model.setDescription(this.curiosityText.getText());
    }

    @Override
    protected Curiosity getModel() {
        return new Curiosity();
    }

    @Override
    protected Action getAction() {
        return Action.ADD_TO_VIEW;
    }

    @Override
    protected Node getOwner() {
        return this.curiosityText;
    }

    @FXML
    public void initialize() {
        this.registerRules();
    }

    private void registerRules() {
        this.getValidator().createRule(this.curiosityText, "curiosidade")
                .add(RuleNames.REQUIRED);
    }
}
