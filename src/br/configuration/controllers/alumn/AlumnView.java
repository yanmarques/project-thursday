package br.configuration.controllers.alumn;

import br.common.models.alumn.Alumn;
import br.configuration.controllers.ModelView;
import br.configuration.controllers.classes.ClassView;
import br.configuration.synchronism.TableItemsWrapper;
import br.configuration.synchronism.ViewSyncer;
import br.configuration.controllers.teachers.TeacherView;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableView;

public class AlumnView extends ModelView<Alumn> {
    private SimpleStringProperty name;
    private SimpleDoubleProperty score;
    private TeacherView teacherView;
    private ClassView classView;

    public AlumnView(Alumn alumn) {
        super(alumn);
        this.name = new SimpleStringProperty(alumn.getName());
        this.score = new SimpleDoubleProperty(alumn.getScore());
        this.teacherView = new TeacherView(alumn.getTeacher());
        this.classView = new ClassView(alumn.getClassModel());
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public double getScore() {
        return score.get();
    }

    public SimpleDoubleProperty scoreProperty() {
        return score;
    }

    public ClassView getClassView() {
        return classView;
    }

    public TeacherView getTeacherView() {
        return teacherView;
    }

    public static ViewSyncer<AlumnView, Alumn> getSyncer(TableView<AlumnView> table) {
        return new ViewSyncer<>(new TableItemsWrapper<>(table), AlumnView::new);
    }
}
