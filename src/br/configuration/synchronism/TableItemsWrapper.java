package br.configuration.synchronism;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class TableItemsWrapper<T> implements ItemsWrapper<T> {
    private TableView<T> tableView;

    public TableItemsWrapper(TableView<T> tableView) {
        this.tableView = tableView;
    }

    @Override
    public void setItems(ObservableListWrapper<T> items) {
        this.tableView.setItems(items);
    }

    @Override
    public ObservableList<T> getItems() {
        return this.tableView.getItems();
    }
}
