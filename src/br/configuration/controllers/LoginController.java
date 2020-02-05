package br.configuration.controllers;

import br.common.models.teacher.Teacher;
import br.common.models.teacher.TeacherDAO;
import br.common.validation.AttributesValidator;
import br.common.validation.RuleNames;
import br.common.window.validation.ModalValidationHandler;
import br.configuration.StarterCallback;
import br.configuration.controllers.teachers.TeacherView;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class LoginController {
    @FXML
    private ComboBox<TeacherView> teachersView;

    private ObservableListWrapper<TeacherView> teachers = new ObservableListWrapper<>(new ArrayList<>());
    private AttributesValidator validator;

    @FXML
    public void initialize() {
        this.registerRules();
        this.synchronizeTable();
    }

    @FXML
    public void login() {
        if (this.validator.isValid()) {
            int index = this.teachersView.getSelectionModel().getSelectedIndex();
            TeacherView view = this.teachers.get(index);
            new StarterCallback(this.teachersView, view.getModel()).execute();
        }
    }

    private void registerRules() {
        this.validator = new AttributesValidator(new ModalValidationHandler());
        this.validator.createRule(this.teachersView, "professor")
                .add(RuleNames.REQUIRED);
    }

    private void synchronizeTable() {
        ArrayList<Teacher> teachers = new TeacherDAO().all();

        if (teachers == null) {
            System.out.println("Unable to retrieve teachers");
        } else {
            this.teachers.clear();
            teachers.forEach(teacher -> this.teachers.add(new TeacherView(teacher)));
            this.teachersView.setItems(this.teachers);
        }
    }
}
