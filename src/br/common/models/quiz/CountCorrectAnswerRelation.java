package br.common.models.quiz;

import br.common.database.connection.InnerModelRelation;
import br.common.models.correctAnswer.CorrectAnswer;
import br.common.models.correctAnswer.CorrectAnswerDAO;

public class CountCorrectAnswerRelation extends InnerModelRelation {
    public CountCorrectAnswerRelation() {
        super(new CorrectAnswerDAO(), Quiz.ID_ATTRIBUTE, CorrectAnswer.QUIZ_ID);
    }

    @Override
    public StringBuilder compileColumns(StringBuilder select) {
        return this.getRelationDAO().getCompiler().compileCount(select, CorrectAnswer.QUIZ_ID, Quiz.CORRECT_ANSWERS_COUNT);
    }
}
