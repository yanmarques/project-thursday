package br.configuration.controllers.teachers;

import br.common.models.teacherClassSubject.TeacherClassSubject;
import br.configuration.controllers.ModelView;
import javafx.beans.property.SimpleStringProperty;

public class ClassSubjectView extends ModelView<TeacherClassSubject> {
    private SimpleStringProperty className;
    private SimpleStringProperty subjectName;

    public ClassSubjectView(TeacherClassSubject model) {
        super(model);
        this.className = new SimpleStringProperty(model.getClassModel().getName());
        this.subjectName = new SimpleStringProperty(model.getSubject().getName());
    }

    public String getClassName() {
        return className.get();
    }

    public SimpleStringProperty classNameProperty() {
        return className;
    }

    public String getSubjectName() {
        return subjectName.get();
    }

    public SimpleStringProperty subjectNameProperty() {
        return subjectName;
    }
}
