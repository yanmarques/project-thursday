package br.configuration.controllers.classes;

import br.common.database.connection.ModelDAO;
import br.common.models.classes.Class;
import br.common.models.classes.ClassDAO;
import br.configuration.interactions.database.InteractiveController;
import br.configuration.interactions.table.ClickInteractiveController;
import br.configuration.window.ConfigFxml;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.function.Function;

public class ListController extends ClickInteractiveController<Class, ClassView, ClassDAO> {
    @FXML
    private TableView<ClassView> classesView;

    @FXML
    private TableColumn<ClassView, String> nameColumn;

    @FXML
    private TableColumn<ClassView, Number> totalScoreColumn;

    @Override
    protected ClassDAO getNewlyDatabase() {
        return new ClassDAO();
    }

    @Override
    protected TableView<ClassView> getTable() {
        return this.classesView;
    }

    @Override
    protected Function<Class, ClassView> getSyncerFunction() {
        return ClassView::new;
    }

    @Override
    protected InteractiveController<Class, ClassView, ClassDAO> getStoreController() {
        return new StoreController();
    }

    @Override
    protected InteractiveController<Class, ClassView, ClassDAO> getRowUpdateController(ClassView model) {
        return new UpdateController(model);
    }

    @Override
    protected URL getFXMLForm() {
        return ConfigFxml.get("forms/class.fxml");
    }

    @Override
    protected String getCreateTitle() {
        return "Criar nova turma";
    }

    @Override
    protected String getUpdateTitle() {
        return "Atualize a turma";
    }

    @FXML
    public void initialize() {
        this.registerColumns();
        super.initialize();
    }

    private void registerColumns() {
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        this.totalScoreColumn.setCellValueFactory(cellData -> cellData.getValue().totalScoreProperty());
    }
}
