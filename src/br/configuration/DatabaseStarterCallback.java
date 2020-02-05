package br.configuration;

import br.common.application.Callback;
import br.common.models.teacher.Teacher;
import br.common.models.teacher.TeacherDAO;
import javafx.scene.Node;

public class DatabaseStarterCallback implements Callback {
    private Node owner;

    public DatabaseStarterCallback(Node owner) {
        this.owner = owner;
    }

    @Override
    public void execute() {
        TeacherDAO database = new TeacherDAO();
        Teacher teacher = database.first();

        if (teacher == null) {
            System.out.println("Any teacher available.");
        } else {
            new StarterCallback(this.owner, teacher).execute();
        }
    }
}
