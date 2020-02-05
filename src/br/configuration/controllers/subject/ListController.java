package br.configuration.controllers.subject;

import br.common.models.subject.Subject;
import br.common.models.subject.SubjectDAO;
import br.configuration.interactions.controller.InteractsWithDatabase;
import br.configuration.interactions.table.ClickInteractiveController;
import br.configuration.window.ConfigFxml;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.function.Function;

public class ListController extends ClickInteractiveController<Subject, SubjectView, SubjectDAO> {
    @FXML
    private TableView<SubjectView> subjectsView;

    @FXML
    private TableColumn<SubjectView, String> nameColumn;

    @FXML
    private TableColumn<SubjectView, Number> curiositiesCountColumn;

    @Override
    protected SubjectDAO getNewlyDatabase() {
        return new SubjectDAO();
    }

    @Override
    protected TableView<SubjectView> getTable() {
        return this.subjectsView;
    }

    @Override
    protected Function<Subject, SubjectView> getSyncerFunction() {
        return SubjectView::new;
    }

    @Override
    protected InteractsWithDatabase<Subject, SubjectView, SubjectDAO> getRowUpdateController(SubjectView model) {
        return new UpdateController(model);
    }

    @Override
    protected InteractsWithDatabase<Subject, SubjectView,SubjectDAO> getStoreController() {
        return new StoreController();
    }

    @Override
    protected URL getFXMLForm() {
        return ConfigFxml.get("forms/subject.fxml");
    }

    @Override
    protected String getUpdateTitle() {
        return "Atualize a disciplina";
    }

    @Override
    protected String getCreateTitle() {
        return "Cria um nova disciplina";
    }

    @FXML
    public void initialize() {
        this.registerColumns();
        super.initialize();
    }

    private void registerColumns() {
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        this.curiositiesCountColumn.setCellValueFactory(cellData -> cellData.getValue().curiositiesCountProperty());
    }
}
