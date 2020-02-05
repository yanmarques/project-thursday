package br.common.models.selectionRules;

import br.common.database.connection.InnerModelRelation;
import br.common.models.correctAnswer.CorrectAnswer;
import br.common.models.correctAnswer.CorrectAnswerDAO;
import br.common.models.quiz.Quiz;
import br.common.models.selectionRuleSubjects.SelectionRuleSubject;
import br.common.models.selectionRuleSubjects.SelectionRuleSubjectDAO;

public class CountSubjectsRelation extends InnerModelRelation {
    public CountSubjectsRelation() {
        super(new SelectionRuleSubjectDAO(), SelectionRule.ID_ATTRIBUTE, SelectionRuleSubject.SELECTION_RULE_ID);
    }

    @Override
    public StringBuilder compileColumns(StringBuilder select) {
        return this.getRelationDAO().getCompiler().compileCount(select, SelectionRuleSubject.SELECTION_RULE_ID,
                SelectionRule.SUBJECTS_COUNT);
    }
}
