package br.configuration.interactions.controller;

import br.common.database.TableMapping;
import br.common.database.connection.ModelDAO;
import br.configuration.controllers.ModelView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.function.Function;

public class FakeDatabaseInteraction<T extends TableMapping, M extends ModelView<T>, D extends ModelDAO<T>> extends DatabaseInteraction<T, M, D> {

    public FakeDatabaseInteraction(DatabaseInteraction<T, M, D> databaseInteraction) {
        super(databaseInteraction.getDatabase(), databaseInteraction.getViewSyncer());
    }

    public FakeDatabaseInteraction(D database, TableView<M> tableView, Function<T, M> function) {
        super(database, tableView, function);
    }

    public FakeDatabaseInteraction(D database, ComboBox<M> comboBox, Function<T, M> function) {
        super(database, comboBox, function);
    }

    public boolean delete(T model, boolean sync) {
        M view = this.findView(model);

        if (view == null) {
            return false;
        }

        this.getViewSyncer().remove(view);
        return true;
    }

    public int update(T model, boolean sync) {
        return 1;
    }

    public boolean create(T model, boolean sync) {
        return this.addToView(model);
    }

    private M findView(T model) {
        ArrayList<M> views = this.getViewSyncer().toArray();
        for (M view: views) {
            if (view.getModel() == model) {
                return view;
            }
        }

        return null;
    }
}
