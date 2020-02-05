package br.configuration;

import br.common.application.Callback;
import br.common.models.teacher.Teacher;
import br.common.window.Carrier;
import br.common.window.handlers.MaximazedHandler;
import br.configuration.window.ConfigFxml;
import javafx.scene.Node;

import java.net.URL;

public class StarterCallback implements Callback {
    private Node owner;
    private Teacher teacher;

    public StarterCallback(Node owner, Teacher teacher) {
        this.owner = owner;
        this.teacher = teacher;
    }

    @Override
    public void execute() {
        Carrier.close(this.owner);

        Authentication.setTeacher(this.teacher);

        URL fxml = ConfigFxml.get("main.fxml");
        String title = "Projeto teste";
        MaximazedHandler handler = new MaximazedHandler(true);

        try {
            Carrier.proxyOpen(handler, fxml, title);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
