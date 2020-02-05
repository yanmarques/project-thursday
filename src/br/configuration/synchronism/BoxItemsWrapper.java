package br.configuration.synchronism;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class BoxItemsWrapper<T> implements ItemsWrapper<T> {
    private ComboBox<T> comboBox;

    public BoxItemsWrapper(ComboBox<T> comboBox) {
        this.comboBox = comboBox;
    }

    @Override
    public void setItems(ObservableListWrapper<T> items) {
        this.comboBox.setItems(items);
    }

    @Override
    public ObservableList<T> getItems() {
        return this.comboBox.getItems();
    }
}
