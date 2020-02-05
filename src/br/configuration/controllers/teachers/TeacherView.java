package br.configuration.controllers.teachers;

import br.common.models.teacher.Teacher;
import br.configuration.controllers.ModelView;
import javafx.beans.property.SimpleStringProperty;

public class TeacherView extends ModelView<Teacher> {
    private SimpleStringProperty name;

    public TeacherView(Teacher teacher) {
        super(teacher);
        this.name = new SimpleStringProperty(teacher.getName());
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String toString() {
        return this.getName();
    }
}
