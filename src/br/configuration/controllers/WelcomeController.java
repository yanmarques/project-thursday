package br.configuration.controllers;

import br.common.models.teacher.Teacher;
import br.common.models.teacher.TeacherDAO;
import br.common.window.Carrier;
import br.configuration.controllers.teachers.StoreController;
import br.configuration.controllers.teachers.TeacherView;
import br.configuration.interactions.controller.DatabaseInteraction;
import br.configuration.synchronism.EmptyItemWrapper;
import br.configuration.synchronism.ViewSyncer;
import br.configuration.window.ConfigFxml;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;

public class WelcomeController {
    @FXML
    private Button start;

    @FXML
    public void start() {
        URL fxml = ConfigFxml.get("forms/teacher.fxml");
        String title = "Crie seu usuário";

        WelcomeHandler handler = new WelcomeHandler(this.start);
        StoreController controller = new StoreController();
        ViewSyncer<TeacherView, Teacher> emptySyncer = new ViewSyncer<>(new EmptyItemWrapper<>(), TeacherView::new);
        controller.setDbInteraction(new DatabaseInteraction<>(new TeacherDAO(), emptySyncer));

        try {
            Carrier.proxyOpenWithController(controller, handler, fxml, title);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
