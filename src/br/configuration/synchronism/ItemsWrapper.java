package br.configuration.synchronism;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;

public interface ItemsWrapper<T> {
    void setItems(ObservableListWrapper<T> items);

    ObservableList<T> getItems();
}
