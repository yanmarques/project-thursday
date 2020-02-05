package br.common.models.selectionRules;

import br.common.database.DataBuilder;
import br.common.database.connection.InnerModelRelation;
import br.common.database.connection.RelationModelDAO;

import java.util.ArrayList;

public class SelectionRuleDAO extends RelationModelDAO<SelectionRule> {
    public SelectionRuleDAO() {
        this(true);
    }

    public SelectionRuleDAO(boolean withRelations) {
        super(withRelations);
    }

    @Override
    public DataBuilder<SelectionRule> getDataBuilder() {
        return new SelectionRuleDataBuilder();
    }

    @Override
    protected void addRelations(ArrayList<InnerModelRelation> relations) {
        relations.add(new CountSubjectsRelation());
    }

    @Override
    protected StringBuilder compileSelect() {
        StringBuilder select = super.compileSelect();
        this.getCompiler().compileGroupped(select, SelectionRule.ID_ATTRIBUTE);
        return select;
    }
}
