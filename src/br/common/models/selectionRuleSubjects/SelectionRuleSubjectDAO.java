package br.common.models.selectionRuleSubjects;

import br.common.database.DataBuilder;
import br.common.database.connection.BelongsModelRelation;
import br.common.database.connection.InnerModelRelation;
import br.common.database.connection.RelationModelDAO;
import br.common.models.selectionRules.SelectionRule;
import br.common.models.subject.SubjectDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectionRuleSubjectDAO extends RelationModelDAO<SelectionRuleSubject> {
    @Override
    public DataBuilder<SelectionRuleSubject> getDataBuilder() {
        return new SelectionRuleSubjectDataBuilder();
    }

    public ArrayList<SelectionRuleSubject> allBySelectionRule(SelectionRule selectionRule) {
        try {
            StringBuilder select = this.compileSelect();
            this.getCompiler().compileWhere(select, SelectionRuleSubject.SELECTION_RULE_ID, "=");

            PreparedStatement preparedStatement = this.getQuery().prepareStatement(select.toString());
            preparedStatement.setInt(1, selectionRule.getPrimaryKey());

            return this.getQuery().getAll(preparedStatement);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    protected void addRelations(ArrayList<InnerModelRelation> relations) {
        relations.add(new BelongsModelRelation(new SubjectDAO(false), SelectionRuleSubject.SUBJECT_ID));
    }
}
