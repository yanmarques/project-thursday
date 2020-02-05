package br.configuration.controllers.subject;

import br.common.database.connection.ModelDAO;
import br.common.models.curiosity.Curiosity;
import br.common.models.curiosity.CuriosityDAO;
import br.common.models.subject.Subject;
import br.common.models.subject.SubjectDAO;
import br.common.validation.RuleNames;
import br.configuration.interactions.controller.InteractsWithDatabase;
import br.configuration.interactions.database.Action;
import br.configuration.interactions.table.RelationalClickableController;
import br.configuration.controllers.subject.curiosity.CuriosityView;
import br.configuration.controllers.subject.curiosity.UpdateController;
import br.configuration.window.ConfigFxml;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.function.Function;


public class StoreController extends RelationalClickableController<Subject, SubjectView, SubjectDAO, Curiosity, CuriosityView,
        CuriosityDAO> {
    @FXML
    protected TextField name;

    @FXML
    private TableView<CuriosityView> curiositiesView;

    @FXML
    private TableColumn<CuriosityView, String> textColumn;

    @Override
    protected Action getAction() {
        return Action.STORE;
    }

    @Override
    protected Subject getModel() {
        return new Subject();
    }

    @Override
    protected Node getOwner() {
        return this.name;
    }

    @Override
    protected void fillModel(Subject model) {
        model.setName(this.name.getText());
    }

    @Override
    protected ArrayList<CuriosityView> getCurrentRelations() {
        return this.getClickableController().getDbInteraction().getViewSyncer().toArray();
    }

    @Override
    protected void fillRelation(Subject model, Curiosity relation) {
        relation.setSubject(model);
    }

    @Override
    protected CuriosityDAO getNewlyDatabase() {
        return new CuriosityDAO();
    }

    @Override
    protected TableView<CuriosityView> getTable() {
        return this.curiositiesView;
    }

    @Override
    protected Function<Curiosity, CuriosityView> getSyncerFunction() {
        return CuriosityView::new;
    }

    @Override
    protected Function<CuriosityView, InteractsWithDatabase<Curiosity, CuriosityView, CuriosityDAO>> getUpdateControllerFunction() {
        return UpdateController::new;
    }

    @Override
    protected Function<Void, InteractsWithDatabase<Curiosity, CuriosityView, CuriosityDAO>> getStoreControllerFunction() {
        return v -> new br.configuration.controllers.subject.curiosity.StoreController();
    }

    @Override
    protected URL getFXMLForm() {
        return ConfigFxml.get("forms/curiosity.fxml");
    }

    @Override
    protected String getCreateTitle() {
        return "Criar nova curiosidade";
    }

    @Override
    protected String getUpdateTitle() {
        return "Atualizar curiosidade";
    }

    @FXML
    public void initialize() {
        this.syncOnSave = false;
        this.registerRules();
        this.registerColumns();
        super.initialize();
    }

    private void registerRules() {
        this.getValidator().createRule(this.name, "nome")
                .add(RuleNames.REQUIRED);
    }

    private void registerColumns() {
        this.textColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
    }
}
