package br.configuration.controllers.teachers;

import br.common.models.classes.ClassDAO;
import br.common.models.subject.SubjectDAO;
import br.common.models.teacher.Teacher;
import br.common.models.teacher.TeacherDAO;
import br.common.models.teacherClassSubject.TeacherClassSubject;
import br.common.models.teacherClassSubject.TeacherClassSubjectDAO;
import br.common.validation.RuleNames;
import br.configuration.controllers.classes.ClassView;
import br.configuration.controllers.subject.SubjectView;
import br.configuration.interactions.controller.DatabaseInteraction;
import br.configuration.interactions.controller.FakeDatabaseInteraction;
import br.configuration.interactions.database.Action;
import br.configuration.interactions.database.RelationsInteractiveController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.function.Function;

public class StoreController extends RelationsInteractiveController<Teacher, TeacherView, TeacherDAO, TeacherClassSubject,
        ClassSubjectView, TeacherClassSubjectDAO> {
    @FXML
    protected TextField name;

    @FXML
    private TableView<ClassSubjectView> classSubjectView;

    @FXML
    private TableColumn<ClassSubjectView, String> classColumn;

    @FXML
    private TableColumn<ClassSubjectView, String> subjectColumn;

    @FXML
    private ComboBox<SubjectView> subjectsBox;

    @FXML
    private ComboBox<ClassView> classesBox;

    private FakeDatabaseInteraction<TeacherClassSubject, ClassSubjectView, TeacherClassSubjectDAO> classSubjectDb;

    @Override
    protected Node getOwner() {
        return this.classSubjectView;
    }

    @Override
    protected Teacher getModel() {
        return new Teacher();
    }

    @Override
    protected Function<TeacherClassSubject, ClassSubjectView> getSyncerFunction() {
        return ClassSubjectView::new;
    }

    @Override
    protected TeacherClassSubject getRelationModel() {
        return new TeacherClassSubject();
    }

    @Override
    protected void fillModel(Teacher model) {
        model.setName(this.name.getText());
    }

    @Override
    protected Action getAction() {
        return Action.STORE;
    }

    @Override
    protected TableView<ClassSubjectView> getTable() {
        return this.classSubjectView;
    }

    @Override
    protected boolean isCreateValid() {
        return this.subjectsBox.getSelectionModel().getSelectedItem() != null &&
                this.classesBox.getSelectionModel().getSelectedItem() != null;
    }

    @Override
    protected void fillRelationModel(TeacherClassSubject model) {
        model.setSubject(this.subjectsBox.getSelectionModel().getSelectedItem().getModel());
        model.setClassModel(this.classesBox.getSelectionModel().getSelectedItem().getModel());
    }

    @Override
    protected TeacherClassSubjectDAO getNewlyDatabase() {
        return new TeacherClassSubjectDAO();
    }

    @Override
    protected void fillRelation(Teacher model, TeacherClassSubject relation) {
        relation.setTeacher(model);
    }

    @FXML
    public void initialize() {
        this.registerColumns();
        this.registerRules();
        this.registerSubjectsBox();
        this.registerClassesBox();
        super.initialize();
    }

    private void registerColumns() {
        this.classColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());

        this.subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectNameProperty());
    }

    private void registerRules() {
        this.getValidator().createRule(this.name, "nome")
                .add(RuleNames.REQUIRED);
    }

    private void registerSubjectsBox() {
        new DatabaseInteraction<>(new SubjectDAO(), this.subjectsBox, SubjectView::new).syncAll();
    }

    private void registerClassesBox() {
        new DatabaseInteraction<>(new ClassDAO(), this.classesBox, ClassView::new).syncAll();
    }
}
