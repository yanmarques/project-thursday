package br.configuration.controllers.classes;

import br.common.models.classes.Class;
import br.configuration.controllers.ModelView;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ClassView extends ModelView<Class> {
    private SimpleStringProperty name;
    private SimpleDoubleProperty totalScore;

    public ClassView(Class classModel) {
        super(classModel);
        this.name = new SimpleStringProperty(classModel.getName());
        this.totalScore = new SimpleDoubleProperty(classModel.getTotalScore());
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public double getTotalScore() {
        return totalScore.get();
    }

    public SimpleDoubleProperty totalScoreProperty() {
        return totalScore;
    }

    public String toString() {
        return this.getName();
    }
}
