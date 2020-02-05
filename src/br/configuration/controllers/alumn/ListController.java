package br.configuration.controllers.alumn;

import br.common.models.alumn.Alumn;
import br.common.models.alumn.AlumnDAO;
import br.configuration.interactions.controller.ClassBoxesInteraction;
import br.configuration.interactions.database.InteractiveController;
import br.configuration.interactions.table.ClickInteractiveController;
import br.configuration.controllers.classes.ClassView;
import br.configuration.window.ConfigFxml;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.function.Function;

public class ListController extends ClickInteractiveController<Alumn, AlumnView, AlumnDAO> {
    @FXML
    private TableView<AlumnView> alumnsView;

    @FXML
    private TableColumn<AlumnView, String> nameColumn;

    @FXML
    private TableColumn<AlumnView, String> classColumn;

    @FXML
    private TableColumn<AlumnView, Number> scoreColumn;

    @FXML
    private TableColumn<AlumnView, String> teacherColumn;

    @FXML
    private TableColumn<AlumnView, String> createdColumn;

    @FXML
    private TableColumn<AlumnView, String> updatedColumn;

    @FXML
    private ComboBox<ClassView> classesBox;

    private ClassBoxesInteraction<Alumn> boxesInteraction;

    @Override
    protected AlumnDAO getNewlyDatabase() {
        return new AlumnDAO();
    }

    @Override
    protected TableView<AlumnView> getTable() {
        return this.alumnsView;
    }

    @Override
    protected Function<Alumn, AlumnView> getSyncerFunction() {
        return AlumnView::new;
    }

    @Override
    protected InteractiveController<Alumn, AlumnView, AlumnDAO> getRowUpdateController(AlumnView view) {
        return new UpdateController(view);
    }

    @Override
    protected InteractiveController<Alumn, AlumnView, AlumnDAO> getStoreController() {
        return new StoreController();
    }

    @Override
    protected URL getFXMLForm() {
        return ConfigFxml.get("forms/alumn.fxml");
    }

    @Override
    protected String getUpdateTitle() {
        return "Atualizar aluno";
    }

    @Override
    protected String getCreateTitle() {
        return "Criar novo aluno";
    }

    @Override
    public void render() {
        this.boxesInteraction.onClassSelected(null);
    }

    @FXML
    public void initialize() {
        super.initialize();
        this.registerBoxInteraction();
        this.registerColumns();
    }

    private void registerColumns() {
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        this.classColumn.setCellValueFactory(cellData -> cellData.getValue().getClassView().nameProperty());

        this.teacherColumn.setCellValueFactory(cellData -> cellData.getValue().getTeacherView().nameProperty());

        this.scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());

        this.createdColumn.setCellValueFactory(cellData -> cellData.getValue().createdAtProperty());

        this.updatedColumn.setCellValueFactory(cellData -> cellData.getValue().updatedAtProperty());
    }

    private void registerBoxInteraction() {
        this.boxesInteraction = new ClassBoxesInteraction<>(this.classesBox, this.getDbInteraction());
    }
}
