package br.configuration.controllers.quizes;

import br.common.models.quiz.Quiz;
import br.common.models.quiz.QuizDAO;
import br.configuration.interactions.controller.InteractsWithDatabase;
import br.configuration.interactions.table.ClickInteractiveController;
import br.configuration.window.ConfigFxml;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.function.Function;

public class ListController extends ClickInteractiveController<Quiz, QuizView, QuizDAO> {
    @FXML
    private TableView<QuizView> quizesView;

    @FXML
    private TableColumn<QuizView, String> questionColumn;

    @FXML
    private TableColumn<QuizView, Number> correctAnswersCountColumn;

    @FXML
    private TableColumn<QuizView, String> subjectColumn;

    @FXML
    private TableColumn<QuizView, Number> scoreColumn;

    @FXML
    private TableColumn<QuizView, String> scoreAvaliationColumn;

    @Override
    protected QuizDAO getNewlyDatabase() {
        return new QuizDAO();
    }

    @Override
    protected TableView<QuizView> getTable() {
        return this.quizesView;
    }

    @Override
    protected Function<Quiz, QuizView> getSyncerFunction() {
        return QuizView::new;
    }

    @Override
    protected InteractsWithDatabase<Quiz, QuizView, QuizDAO> getRowUpdateController(QuizView view) {
        return new UpdateController(view);
    }

    @Override
    protected InteractsWithDatabase<Quiz, QuizView, QuizDAO> getStoreController() {
        return new StoreController();
    }

    @Override
    protected URL getFXMLForm() {
        return ConfigFxml.get("forms/quiz.fxml");
    }

    @Override
    protected String getUpdateTitle() {
        return "Atualize o questionário";
    }

    @Override
    protected String getCreateTitle() {
        return "Crie um novo questionário";
    }

    @Override
    public void initialize() {
        this.registerColumns();
        super.initialize();
    }

    private void registerColumns() {
        this.questionColumn.setCellValueFactory(cellData -> cellData.getValue().questionProperty());

        this.correctAnswersCountColumn.setCellValueFactory(cellData -> cellData.getValue().correctAnswersCountProperty());

        this.subjectColumn.setCellValueFactory(cellData -> cellData.getValue().getSubjectView().nameProperty());

        this.scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());

        this.scoreAvaliationColumn.setCellValueFactory(cellData -> cellData.getValue().scoreAvaliationProperty());
    }
}
