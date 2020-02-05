package br.configuration.controllers.classes;

import br.common.models.classes.Class;
import br.common.models.classes.ClassDAO;
import br.common.validation.RuleNames;
import br.configuration.interactions.database.Action;
import br.configuration.interactions.database.InteractiveController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class StoreController extends InteractiveController<Class, ClassView, ClassDAO> {
    @FXML
    protected TextField name;

    @Override
    protected Node getOwner() {
        return this.name;
    }

    @Override
    protected Action getAction() {
        return Action.STORE;
    }

    @Override
    protected Class getModel() {
        return new Class();
    }

    @Override
    protected void fillModel(Class model) {
        model.setName(this.name.getText());
    }

    @FXML
    public void initialize() {
        this.getValidator().createRule(this.name, "nome")
                .add(RuleNames.REQUIRED);
    }
}
