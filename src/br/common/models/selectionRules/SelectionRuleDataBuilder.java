package br.common.models.selectionRules;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectionRuleDataBuilder implements DataBuilder<SelectionRule> {
    @Override
    public SelectionRule getModel() {
        return new SelectionRule();
    }

    @Override
    public SelectionRule build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        SelectionRule selectionRule = this.getModel();

        String imagePath = compiler.alias(SelectionRule.IMAGE_PATH);
        selectionRule.setImagePath(resultSet.getString(imagePath));

        selectionRule.setSubjectsCount(resultSet.getInt(SelectionRule.SUBJECTS_COUNT));

        return selectionRule;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, SelectionRule model) throws SQLException {
        preparedStatement.setString(1, model.getImagePath());
    }
}
