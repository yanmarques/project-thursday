package br.configuration.interactions.table;

import br.common.database.TableMapping;
import br.common.database.connection.ModelDAO;
import br.common.window.Carrier;
import br.common.window.ChainedHandler;
import br.common.window.handlers.ModalHandler;
import br.configuration.controllers.ModelView;
import br.configuration.interactions.controller.DatabaseInteraction;
import br.configuration.interactions.controller.InteractsWithDatabase;
import br.configuration.window.handlers.controllers.Renderizable;
import br.configuration.window.handlers.controllers.RenderizableHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.function.Function;

abstract public class ClickInteractiveController<T extends TableMapping, M extends ModelView<T>, D extends ModelDAO<T>>
        implements InteractsWithDatabase<T, M, D>, Renderizable {
    protected int clickCount = 2;
    protected DatabaseInteraction<T, M, D> dbInteraction;

    @FXML
    private TableColumn<M, String> createdColumn;

    @FXML
    private TableColumn<M, String> updatedColumn;

    abstract protected D getNewlyDatabase();

    abstract protected TableView<M> getTable();

    abstract protected Function<T, M> getSyncerFunction();

    abstract protected InteractsWithDatabase<T, M, D> getRowUpdateController(M view);

    abstract protected InteractsWithDatabase<T, M, D> getStoreController();

    abstract protected URL getFXMLForm();

    abstract protected String getCreateTitle();

    abstract protected String getUpdateTitle();

    public void render() {
        this.getDbInteraction().syncAll();
    }

    @FXML
    public void initialize() {
        this.registerInterations();
        this.registerClick();
        this.registerTemporalColumns();
    }

    @FXML
    public void create() {
        this.openForm(this.getStoreController(), this.getCreateTitle());
    }

    @FXML
    public void remove() {
        M view = this.getTable().getSelectionModel().getSelectedItem();

        if (view != null) {
            this.getDbInteraction().delete(view.getModel(), true);
        }
    }

    @Override
    public DatabaseInteraction<T, M, D> getDbInteraction() throws NullPointerException {
        if (this.dbInteraction == null) {
            throw new NullPointerException("Database interaction must not be null.");
        }

        return dbInteraction;
    }

    @Override
    public void setDbInteraction(DatabaseInteraction<T, M, D> dbInteration) {
        this.dbInteraction = dbInteration;
    }

    private void registerClick() {
        this.getTable().setRowFactory(table -> {
            TableRow<M> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == this.clickCount && (! row.isEmpty()) ) {
                    InteractsWithDatabase<T, M, D> controller = this.getRowUpdateController(row.getItem());
                    this.openForm(controller, this.getUpdateTitle());
                }
            });

            return row;
        });
    }

    protected void registerTemporalColumns() {
        this.createdColumn.setCellValueFactory(cellData -> cellData.getValue().createdAtProperty());

        this.updatedColumn.setCellValueFactory(cellData -> cellData.getValue().updatedAtProperty());
    }

    private void registerInterations() {
        this.setDbInteraction(new DatabaseInteraction<>(this.getNewlyDatabase(), this.getTable(), this.getSyncerFunction()));
    }

    private void openForm(InteractsWithDatabase<T, M, D> controller, String title) {
        controller.setDbInteraction(this.getDbInteraction());

        try {
            Carrier.proxyOpenWithController(controller, this.getHandlers(), this.getFXMLForm(), title);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private ChainedHandler getHandlers() {
        ChainedHandler handler = new ChainedHandler();
        handler.add(new ModalHandler(this.getTable()));
        handler.add(new RenderizableHandler());
        return handler;
    }
}
