package br.configuration.synchronism;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class EmptyItemWrapper<T> implements ItemsWrapper<T> {
    @Override
    public ObservableList<T> getItems() {
        return new ObservableListWrapper<>(new ArrayList<>());
    }

    @Override
    public void setItems(ObservableListWrapper items) {
        //
    }
}
