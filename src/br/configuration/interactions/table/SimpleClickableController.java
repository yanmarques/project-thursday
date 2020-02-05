package br.configuration.interactions.table;

import br.common.database.TableMapping;
import br.common.database.connection.ModelDAO;
import br.configuration.controllers.ModelView;
import br.configuration.interactions.controller.DatabaseInteraction;
import br.configuration.interactions.controller.FakeDatabaseInteraction;
import br.configuration.interactions.controller.InteractsWithDatabase;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.function.Function;

public class SimpleClickableController<T extends TableMapping, M extends ModelView<T>, D extends ModelDAO<T>>
        extends ClickInteractiveController<T, M, D> {
    private D database;
    private TableView<M> tableView;
    private Function<T, M> syncerFunction;
    private Function<M, InteractsWithDatabase<T, M, D>> updateControllerFunction;
    private Function<Void, InteractsWithDatabase<T, M, D>> storeControllerFunction;
    private URL FXMLForm;
    private String createTitle;
    private String updateTitle;

    protected D getNewlyDatabase() {
        return this.database;
    }

    protected TableView<M> getTable() {
        return this.tableView;
    }

    protected Function<T, M> getSyncerFunction() {
        return this.syncerFunction;
    }

    protected InteractsWithDatabase<T, M, D> getRowUpdateController(M model) {
        return this.updateControllerFunction.apply(model);
    }

    protected InteractsWithDatabase<T, M, D> getStoreController() {
        return this.storeControllerFunction.apply(null);
    }

    protected URL getFXMLForm() {
        return this.FXMLForm;
    }

    protected String getCreateTitle() {
        return this.createTitle;
    }

    protected String getUpdateTitle() {
        return this.updateTitle;
    }

    public SimpleClickableController(D database, TableView<M> tableView, Function<T, M> syncerFunction,
                                     Function<M, InteractsWithDatabase<T, M, D>> updateControllerFunction,
                                     Function<Void, InteractsWithDatabase<T, M, D>> storeControllerFunction,
                                     URL FXMLForm, String createTitle, String updateTitle) {
        this.database = database;
        this.tableView = tableView;
        this.syncerFunction = syncerFunction;
        this.updateControllerFunction = updateControllerFunction;
        this.storeControllerFunction = storeControllerFunction;
        this.FXMLForm = FXMLForm;
        this.createTitle = createTitle;
        this.updateTitle = updateTitle;
        this.initialize();
    }

    @Override
    public void setDbInteraction(DatabaseInteraction<T, M, D> dbInteration) {
        super.setDbInteraction(new FakeDatabaseInteraction<>(dbInteration));
    }

    @Override
    protected void registerTemporalColumns() {
        //
    }

    @Override
    public void render() {
        //
    }
}
