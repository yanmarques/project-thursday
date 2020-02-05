package br.configuration.controllers.subject;

import br.common.models.subject.Subject;
import br.configuration.controllers.ModelView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SubjectView extends ModelView<Subject> {
    private SimpleStringProperty name;
    private SimpleIntegerProperty curiositiesCount;

    public SubjectView(Subject subject) {
        super(subject);
        this.name = new SimpleStringProperty(subject.getName());
        this.curiositiesCount = new SimpleIntegerProperty(subject.getCuriositiesCount());
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getCuriositiesCount() {
        return curiositiesCount.get();
    }

    public SimpleIntegerProperty curiositiesCountProperty() {
        return curiositiesCount;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
