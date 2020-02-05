package br.configuration.synchronism;

import com.sun.javafx.collections.ObservableListWrapper;

import java.util.ArrayList;
import java.util.function.Function;

public class ViewSyncer<T, K> {
    private ItemsWrapper<T> items;
    private Function<K, T> function;

    public ViewSyncer(ItemsWrapper<T> items, Function<K, T> function) {
        this.items = items;
        this.function = function;
    }

    public void sync(ArrayList<K> models) {
        ObservableListWrapper<T> views = new ObservableListWrapper<>(new ArrayList<>());

        if (models == null) {
            System.out.println("Falha ao sincronizar tabela");
        } else {
            models.forEach(model -> views.add(this.function.apply(model)));
        }

        this.items.setItems(views);
    }

    public ArrayList<T> toArray() {
        return new ArrayList<>(this.items.getItems());
    }

    public boolean add(K model) {
        return this.items.getItems().add(this.function.apply(model));
    }

    public boolean remove(T view) {
        return this.items.getItems().remove(view);
    }
}
