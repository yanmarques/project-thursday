package br.configuration.interactions.table;

import br.common.database.TableMapping;
import br.common.database.connection.ModelDAO;
import br.configuration.controllers.ModelView;
import br.configuration.interactions.controller.InteractsWithDatabase;
import br.configuration.interactions.database.RelationsInteractiveController;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.function.Function;

abstract public class RelationalClickableController<T extends TableMapping, M extends ModelView<T>, D extends ModelDAO<T>,
        R extends TableMapping, K extends ModelView<R>, L extends ModelDAO<R>> extends RelationsInteractiveController<T, M,
        D, R, K, L> {
    private SimpleClickableController<R, K, L> clickableController;

    abstract protected L getNewlyDatabase();

    abstract protected TableView<K> getTable();

    abstract protected Function<R, K> getSyncerFunction();

    abstract protected Function<K, InteractsWithDatabase<R, K, L>> getUpdateControllerFunction();

    abstract protected Function<Void, InteractsWithDatabase<R, K, L>> getStoreControllerFunction();

    abstract protected URL getFXMLForm();

    abstract protected String getCreateTitle();

    abstract protected String getUpdateTitle();

    public SimpleClickableController<R, K, L> getClickableController() {
        return clickableController;
    }

    @Override
    public void initialize() {
        super.initialize();
        this.clickableController = new SimpleClickableController<>(this.getNewlyDatabase(), this.getTable(), this.getSyncerFunction(),
                this.getUpdateControllerFunction(), this.getStoreControllerFunction(), this.getFXMLForm(), this.getCreateTitle(),
                this.getUpdateTitle());
    }

    @Override
    protected R getRelationModel() {
        return null;
    }

    @Override
    protected void fillRelationModel(R model) {
        //
    }

    @Override
    protected boolean isCreateValid() {
        return false;
    }

    @FXML
    public void create() {
        this.getClickableController().create();
    }

    @FXML
    public void remove() {
        this.getClickableController().remove();
    }
}
