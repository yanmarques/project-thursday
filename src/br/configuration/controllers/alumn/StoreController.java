package br.configuration.controllers.alumn;

import br.common.models.alumn.Alumn;
import br.common.models.alumn.AlumnDAO;
import br.common.models.classes.ClassDAO;
import br.common.validation.RuleNames;
import br.configuration.Authentication;
import br.configuration.interactions.controller.DatabaseInteraction;
import br.configuration.interactions.database.Action;
import br.configuration.interactions.database.InteractiveController;
import br.configuration.controllers.classes.ClassView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class StoreController extends InteractiveController<Alumn, AlumnView, AlumnDAO> {
    @FXML
    protected TextField name;

    @FXML
    protected ComboBox<ClassView> classesView;

    @Override
    protected Node getOwner() {
        return this.name;
    }

    @Override
    protected Alumn getModel() {
        return new Alumn();
    }

    @Override
    protected Action getAction() {
        return Action.STORE;
    }

    @Override
    protected void fillModel(Alumn alumn) {
        alumn.setName(this.name.getText());

        ClassView view = this.classesView.getSelectionModel().getSelectedItem();
        alumn.setClass(view.getModel());

        this.setCurrentTeacher(alumn);
    }

    @FXML
    public void initialize() {
        this.registerClasses();
        this.registerRules();
    }

    private void registerClasses() {
        new DatabaseInteraction<>(new ClassDAO(), this.classesView, ClassView::new).syncAll();
    }

    private void registerRules() {
        this.getValidator().createRule(this.name, "nome")
                .add(RuleNames.REQUIRED);

        this.getValidator().createRule(this.classesView, "turma")
                .add(RuleNames.REQUIRED);
    }

    protected void setCurrentTeacher(Alumn alumn) {
        alumn.setTeacher(Authentication.getTeacher());
    }
}
