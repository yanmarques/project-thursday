package br.common.models.choices;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChoiceDataBuilder implements DataBuilder<Choice> {
    @Override
    public Choice getModel() {
        return new Choice();
    }

    @Override
    public Choice build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        Choice choice = this.getModel();

        String selectionRuleId = compiler.alias(Choice.SELECTION_RULE_ID);
        choice.setSelectionRuleId(resultSet.getInt(selectionRuleId));

        return choice;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, Choice model) throws SQLException {
        preparedStatement.setInt(1, model.getSelectionRuleId());
    }
}
