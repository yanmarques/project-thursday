package br.common.models.config;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfigurationDataBuilder implements DataBuilder<Configuration> {
    @Override
    public Configuration getModel() {
        return new Configuration();
    }

    @Override
    public Configuration build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        Configuration configuration = this.getModel();

        String ruleAction = compiler.alias(Configuration.RULE_ACTION);
        configuration.setRuleAction(resultSet.getString(ruleAction));

        String hideTeachers = compiler.alias(Configuration.HIDE_TEACHERS);
        configuration.setHideTeachers(resultSet.getBoolean(hideTeachers));

        String displayRulesbyClass = compiler.alias(Configuration.DISPLAY_RULES_BY_CLASS);
        configuration.setDisplayRulesByClass(resultSet.getBoolean(displayRulesbyClass));

        return configuration;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, Configuration configuration) throws SQLException {
        preparedStatement.setString(1, configuration.getRuleAction());
        preparedStatement.setBoolean(2, configuration.getHideTeachers());
        preparedStatement.setBoolean(3, configuration.getDisplayRulesByClass());
    }
}
