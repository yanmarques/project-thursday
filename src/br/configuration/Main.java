package br.configuration;

import br.common.application.DatabaseAdapter;
import br.common.models.teacher.TeacherDAO;
import br.common.window.Carrier;
import br.configuration.window.ConfigFxml;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends DatabaseAdapter {
    @Override
    public void start(Stage primaryStage) {
        try {
            this.initialize(primaryStage);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void initialize(Stage primaryStage) throws IOException {
        URL fxml;
        String title;

        if (new TeacherDAO().all().isEmpty()) {
            fxml = ConfigFxml.get("welcome.fxml");
            title = "Bem vindo";
            Carrier.open(fxml, title);
        } else {
            fxml = ConfigFxml.get("login.fxml");
            title = "Projeto quinta-feira";
        }

        Carrier.openStaged(primaryStage, fxml, title);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
