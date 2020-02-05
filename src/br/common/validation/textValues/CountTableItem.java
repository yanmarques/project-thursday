package br.common.validation.textValues;

import br.common.validation.InputTextValue;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.function.Function;

public class CountTableItem<T> implements InputTextValue {
    private TableView<T> tableView;
    private Function<T, Boolean> validItems;

    public CountTableItem(TableView<T> tableView) {
        this.tableView = tableView;
    }

    public CountTableItem(TableView<T> tableView, Function<T, Boolean> validItems) {
        this.tableView = tableView;
        this.validItems = validItems;
    }

    @Override
    public String getText() {
        ArrayList<T> validItems;

        if (this.validItems == null) {
            validItems = new ArrayList<>(this.tableView.getItems());
        } else {
            validItems = new ArrayList<>();

            for (T item: this.tableView.getItems()) {
                if (this.validItems.apply(item)) {
                    validItems.add(item);
                }
            }
        }

        Integer size = validItems.size();
        return size.toString();
    }
}
