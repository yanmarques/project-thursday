package br.configuration.interactions.controller;

import br.common.database.TableMapping;
import br.common.database.connection.ModelDAO;
import br.configuration.controllers.ModelView;
import br.configuration.synchronism.BoxItemsWrapper;
import br.configuration.synchronism.TableItemsWrapper;
import br.configuration.synchronism.ViewSyncer;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.function.Function;

public class DatabaseInteraction<T extends TableMapping, M extends ModelView<T>, D extends ModelDAO<T>> {
    private D database;
    private ViewSyncer<M, T> viewSyncer;

    public DatabaseInteraction(D database, ViewSyncer<M, T> viewSyncer) {
        this.database = database;
        this.viewSyncer = viewSyncer;
    }

    public DatabaseInteraction(D database, TableView<M> tableView, Function<T, M> function) {
        this(database, new ViewSyncer<>(new TableItemsWrapper<>(tableView), function));
    }

    public DatabaseInteraction(D database, ComboBox<M> boxView, Function<T, M> function) {
        this(database, new ViewSyncer<>(new BoxItemsWrapper<>(boxView), function));
    }

    public void syncView(ArrayList<T> models) {
        this.getViewSyncer().sync(models);
    }

    public boolean addToView(T model) {
        return this.getViewSyncer().add(model);
    }

    public void syncAll() {
        ArrayList<T> models = this.getDatabase().all();

        if (models != null) {
            this.syncView(models);
        }
    }

    public boolean delete(T model, boolean sync) {
        boolean result = this.getDatabase().delete(model);

        if (sync) {
            this.syncAll();
        }

        return result;
    }

    public int update(T model, boolean sync) {
        if (model.isDirty()) {
            int result = this.getDatabase().update(model);

            if (sync) {
                this.syncAll();
            }

            return result;
        }

        return -1;
    }

    public boolean create(T model, boolean sync) {
        boolean result = this.getDatabase().create(model);

        if (sync) {
            this.syncAll();
        }

        return result;
    }

    public void selectWith(Function<D, ArrayList<T>> dbExecutor) {
        ArrayList<T> models = dbExecutor.apply(this.getDatabase());

        if (models == null) {
            System.out.println("Falha ao buscar items.");
        } else {
            this.syncView(models);
        }
    }

    public D getDatabase() {
        return database;
    }

    public ViewSyncer<M, T> getViewSyncer() {
        return viewSyncer;
    }
}
