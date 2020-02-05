package br.configuration.controllers;

import br.configuration.window.handlers.controllers.Renderizable;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;

import java.util.ArrayList;

public class BoxActionsHelper<T extends Enum> implements Renderizable {
    private ArrayList<ActionsInterface<T>> items = new ArrayList<>();
    private ComboBox<ActionsInterface<T>> comboxBox;

    public BoxActionsHelper(ComboBox<ActionsInterface<T>> comboBox) {
        this.comboxBox = comboBox;
    }

    public void add(ActionsInterface<T> item) {
        this.items.add(item);
    }

    public void render() {
        this.comboxBox.setItems(FXCollections.observableArrayList(items));

        this.comboxBox.setCellFactory(listView -> new ListCell<ActionsInterface<T>>() {
                    @Override
                    protected void updateItem(ActionsInterface<T> item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            setText(item.toString());
                            setTooltip(new Tooltip(item.getDescription()));
                        }
                    }
                }
        );
    }

    public ActionsInterface<T> findAction(String action) {
        for (ActionsInterface<T> actionsInterface: this.items) {
            if (actionsInterface.getAction().toString().equals(action)) {
                return actionsInterface;
            }
        }

        return null;
    }
}
