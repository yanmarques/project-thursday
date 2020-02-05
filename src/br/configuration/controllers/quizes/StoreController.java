package br.configuration.controllers.quizes;

import br.common.models.answer.Answer;
import br.common.models.answer.AnswerDAO;
import br.common.models.quiz.Quiz;
import br.common.models.quiz.QuizDAO;
import br.common.models.subject.SubjectDAO;
import br.common.validation.RuleNames;
import br.common.validation.RuleWrapper;
import br.common.validation.rules.GreaterEquals;
import br.common.validation.rules.LowerEquals;
import br.common.validation.textValues.ComboBoxValue;
import br.common.validation.textValues.CountTableItem;
import br.common.validation.textValues.SpinnerValue;
import br.common.validation.textValues.TextAreaValue;
import br.configuration.controllers.ActionsInterface;
import br.configuration.controllers.BoxActionsHelper;
import br.configuration.controllers.quizes.answers.AnswerView;
import br.configuration.controllers.quizes.answers.UpdateController;
import br.configuration.controllers.quizes.scoreAvaliationActions.Actions;
import br.configuration.controllers.quizes.scoreAvaliationActions.AverageScoreAvaliation;
import br.configuration.controllers.quizes.scoreAvaliationActions.ExactScoreAvaliation;
import br.configuration.controllers.subject.SubjectView;
import br.configuration.interactions.controller.DatabaseInteraction;
import br.configuration.interactions.controller.InteractsWithDatabase;
import br.configuration.interactions.database.Action;
import br.configuration.interactions.table.RelationalClickableController;
import br.configuration.window.ConfigFxml;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.util.function.Function;

public class StoreController extends RelationalClickableController<Quiz, QuizView, QuizDAO, Answer, AnswerView, AnswerDAO> {
    @FXML
    protected TextArea question;

    @FXML
    protected Spinner<Double> score;

    @FXML
    protected ComboBox<ActionsInterface<Actions>> scoreAvaliation;

    @FXML
    protected TableView<AnswerView> answersView;

    @FXML
    private TableColumn<AnswerView, String> answerColumn;

    @FXML
    private TableColumn<AnswerView, String> correctAnswerColumn;

    @FXML
    protected ComboBox<SubjectView> subjectsBox;

    private BoxActionsHelper<Actions> boxActions;
    protected double spinnerMaxValue = 10;

    public BoxActionsHelper<Actions> getBoxActions() {
        return boxActions;
    }

    @Override
    protected AnswerDAO getNewlyDatabase() {
        return new AnswerDAO();
    }

    @Override
    protected TableView<AnswerView> getTable() {
        return this.answersView;
    }

    @Override
    protected Function<Answer, AnswerView> getSyncerFunction() {
        return AnswerView::new;
    }

    @Override
    protected Function<AnswerView, InteractsWithDatabase<Answer, AnswerView, AnswerDAO>> getUpdateControllerFunction() {
        return UpdateController::new;
    }

    @Override
    protected Function<Void, InteractsWithDatabase<Answer, AnswerView, AnswerDAO>> getStoreControllerFunction() {
        return v -> new br.configuration.controllers.quizes.answers.StoreController();
    }

    @Override
    protected URL getFXMLForm() {
        return ConfigFxml.get("forms/answer.fxml");
    }

    @Override
    protected String getUpdateTitle() {
        return "Atualize a resposta";
    }

    @Override
    protected String getCreateTitle() {
        return "Crie uma nova resposta";
    }

    @Override
    protected void fillRelation(Quiz model, Answer relation) {
        relation.setQuiz(model);
    }

    @Override
    protected void fillModel(Quiz model) {
        model.setQuestion(this.question.getText());
        model.setScore(this.score.getValue());

        ActionsInterface<Actions> scoreAvaliation = this.scoreAvaliation.getSelectionModel().getSelectedItem();
        model.setScoreAvaliation(scoreAvaliation.getAction().toString());

        model.setSubject(this.subjectsBox.getSelectionModel().getSelectedItem().getModel());
    }

    @Override
    protected Quiz getModel() {
        return new Quiz();
    }

    @Override
    protected Action getAction() {
        return Action.STORE;
    }

    @Override
    protected Node getOwner() {
        return this.question;
    }

    protected double getSpinnerInitialValue() {
        return 1;
    }

    @Override
    public void initialize() {
        this.syncOnSave = false;
        this.registerColumns();
        this.registerRules();
        this.registerSpinner();
        this.registerScoreAvaliationsBox();
        this.registerSubjectsBox();
        super.initialize();
    }

    private void registerColumns() {
        this.answerColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        this.correctAnswerColumn.setCellValueFactory(cellData -> cellData.getValue().isCorrectAnswerProperty());
    }

    private void registerRules() {
        RuleWrapper requiredWrapper = this.getValidator().createWraper(RuleNames.REQUIRED);

        requiredWrapper.createRule(new TextAreaValue(this.question), "pergunta");

        requiredWrapper.createRule(new SpinnerValue(this.score), "número de pontos")
                .add(RuleNames.DOUBLE)
                .add(RuleNames.POSITIVE)
                .add(new LowerEquals(this.spinnerMaxValue));

        requiredWrapper.createRule(new ComboBoxValue(this.scoreAvaliation), "método de avaliação");

        requiredWrapper.createRule(new ComboBoxValue(this.subjectsBox), "matéria");

        CountTableItem<AnswerView> tableCount = new CountTableItem<>(this.getTable(), view -> view.getModel().isCorrectAnswer());
        requiredWrapper.createRule(tableCount, "resposta correta")
                .add(new GreaterEquals(1));
    }

    private void registerSpinner() {
        this.score.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.1, this.spinnerMaxValue,
                this.getSpinnerInitialValue()));
    }

    private void registerScoreAvaliationsBox() {
        this.boxActions = new BoxActionsHelper<>(this.scoreAvaliation);
        this.boxActions.add(new AverageScoreAvaliation());
        this.boxActions.add(new ExactScoreAvaliation());
        this.boxActions.render();
    }

    private void registerSubjectsBox() {
        new DatabaseInteraction<>(new SubjectDAO(), this.subjectsBox, SubjectView::new).syncAll();
    }
}
