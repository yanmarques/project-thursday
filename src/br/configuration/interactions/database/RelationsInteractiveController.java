package br.configuration.interactions.database;

import br.common.database.TableMapping;
import br.common.database.connection.ModelDAO;
import br.configuration.controllers.ModelView;
import br.configuration.interactions.controller.FakeDatabaseInteraction;
import br.configuration.window.handlers.controllers.Renderizable;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

abstract public class RelationsInteractiveController<T extends TableMapping, M extends ModelView<T>, D extends ModelDAO<T>,
        R extends TableMapping, K extends ModelView<R>, L extends ModelDAO<R>> extends InteractiveController<T, M, D>
        implements Renderizable {
    private MovementRegisterWrapper<R, L> relationDb;
    private ArrayList<K> originalRelations;
    private FakeDatabaseInteraction<R, K, L> fakeRelationDb;

    abstract protected TableView<K> getTable();

    abstract protected L getNewlyDatabase();

    abstract protected Function<R, K> getSyncerFunction();

    abstract protected R getRelationModel();

    abstract protected void fillRelationModel(R model);

    abstract protected void fillRelation(T model, R relation);

    abstract protected boolean isCreateValid();

    @Override
    public void render() {
        if (this.getAction() == Action.UPDATE) {
            this.originalRelations = this.getCurrentRelations();
        }
    }

    public FakeDatabaseInteraction<R, K, L> getFakeRelationDb() {
        return fakeRelationDb;
    }

    @FXML
    public void initialize() {
        this.relationDb = new MovementRegisterWrapper<>(this.getNewlyDatabase());
        this.fakeRelationDb = new FakeDatabaseInteraction<>(this.getNewlyDatabase(), this.getTable(), this.getSyncerFunction());
    }

    @FXML
    public void create() {
        if (this.isCreateValid()) {
            R relationModel = this.getRelationModel();
            this.fillRelationModel(relationModel);

            this.getFakeRelationDb().addToView(relationModel);
        }
    }

    @FXML
    public void remove() {
        K view = this.getTable().getSelectionModel().getSelectedItem();

        if (view != null) {
            this.getFakeRelationDb().delete(view.getModel(), true);
        }
    }

    @Override
    protected boolean handleSave(T model) {
        if (this.getAction() == Action.UPDATE) {
            int rowsUpdated = this.updateModel(model);
            this.getCurrentRelations().forEach(this.getRelationsConsumer(model));

            if (rowsUpdated < 1 && this.relationDb.hasMovements()) {
                model.setNewUpdatedTime();
                this.updateModel(model);
            }

            this.getDbInteraction().syncAll();

            return true;
        }

        if (super.handleSave(model)) {
            this.getCurrentRelations().forEach(this.getRelationsConsumer(model));
            this.getDbInteraction().syncAll();
            return true;
        }

        return false;
    }

    protected ArrayList<K> getCurrentRelations() {
        return this.getFakeRelationDb().getViewSyncer().toArray();
    }

    protected Consumer<K> getStoreConsumer(T model) {
        return view -> {
            R relation = view.getModel();
            this.fillRelation(model, relation);
            this.relationDb.create(relation);
        };
    }

    protected Consumer<K> getUpdateConsumer(T model) {
        return view -> {
            R relation = view.getModel();

            if (relation.exists()) {
                if (relation.isDirty()) {
                    this.relationDb.update(relation);
                }
            } else {
                this.getStoreConsumer(model).accept(view);
            }
        };
    }

    protected void removeDeletedRelations() {
        ArrayList<K> newRelations = this.getCurrentRelations();
        this.originalRelations.forEach(original -> {
            if (this.notContains(newRelations, original)) {
                this.relationDb.delete(original.getModel());
            }
        });
    }

    private Consumer<K> getRelationsConsumer(T model) {
        Action action = this.getAction();

        if (action == Action.STORE) {
            return this.getStoreConsumer(model);
        }

        if (action == Action.UPDATE) {
            this.removeDeletedRelations();
            return this.getUpdateConsumer(model);
        }

        return null;
    }

    private boolean notContains(ArrayList<K> newRelations, K relationView) {
        R model = relationView.getModel();

        for(K newRelation: newRelations) {
            if (newRelation.getModel() == model) {
                return false;
            }
        }

        return true;
    }
}
