package br.configuration.controllers.teachers;

import br.common.models.teacher.Teacher;
import br.common.models.teacher.TeacherDAO;
import br.configuration.controllers.classes.ClassView;
import br.configuration.interactions.controller.ClassBoxesInteraction;
import br.configuration.interactions.controller.InteractsWithDatabase;
import br.configuration.interactions.table.ClickInteractiveController;
import br.configuration.window.ConfigFxml;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.function.Function;

public class ListController extends ClickInteractiveController<Teacher, TeacherView, TeacherDAO> {
    @FXML
    private TableView<TeacherView> teachersView;

    @FXML
    private TableColumn<TeacherView, String> nameColumn;

    @FXML
    private ComboBox<ClassView> classesBox;

    private ClassBoxesInteraction<Teacher> boxesInteraction;

    @Override
    protected TeacherDAO getNewlyDatabase() {
        return new TeacherDAO();
    }

    @Override
    protected TableView<TeacherView> getTable() {
        return this.teachersView;
    }

    @Override
    protected Function<Teacher, TeacherView> getSyncerFunction() {
        return TeacherView::new;
    }

    @Override
    protected InteractsWithDatabase<Teacher, TeacherView, TeacherDAO> getRowUpdateController(TeacherView view) {
        return new UpdateController(view);
    }

    @Override
    protected InteractsWithDatabase<Teacher, TeacherView, TeacherDAO> getStoreController() {
        return new StoreController();
    }

    @Override
    protected URL getFXMLForm() {
        return ConfigFxml.get("forms/teacher.fxml");
    }

    @Override
    protected String getUpdateTitle() {
        return "Atualize o professor";
    }

    @Override
    protected String getCreateTitle() {
        return "Crie o novo professor";
    }

    @Override
    public void render() {
        this.boxesInteraction.onClassSelected(null);
    }

    @FXML
    public void initialize() {
        super.initialize();
        this.registerColumns();
        this.registerBoxInteraction();
    }

    private void registerColumns() {
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }

    private void registerBoxInteraction() {
        this.boxesInteraction = new ClassBoxesInteraction<>(this.classesBox, this.getDbInteraction());
    }
}
