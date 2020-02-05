package br.configuration.controllers;

import br.common.database.TableMapping;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModelView<T> {
    final public static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/YY HH:mm");

    private T model;
    private SimpleStringProperty createdAt;
    private SimpleStringProperty updatedAt;

    public ModelView(T model) {
        this.model = model;
        TableMapping tableModel = (TableMapping) model;

        if (tableModel.exists()) {
            this.createdAt = new SimpleStringProperty(this.formatDate(tableModel.getCreatedTime()));
            this.updatedAt = new SimpleStringProperty(this.formatDate(tableModel.getUpdatedTime()));
        }
    }

    public String getCreatedAt() {
        return createdAt.get();
    }

    public SimpleStringProperty createdAtProperty() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt.get();
    }

    public SimpleStringProperty updatedAtProperty() {
        return updatedAt;
    }

    public T getModel() {
        return model;
    }

    protected String formatDate(LocalDateTime date) {
        return date.format(ModelView.DATE_TIME_FORMAT);
    }
}
