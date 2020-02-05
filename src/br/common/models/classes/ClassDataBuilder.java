package br.common.models.classes;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassDataBuilder implements DataBuilder<Class> {
    @Override
    public Class getModel() {
        return new Class();
    }

    @Override
    public Class build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        Class classModel = this.getModel();

        String name = compiler.alias(Class.NAME);
        classModel.setName(resultSet.getString(name));

        try {
            double totalScore = resultSet.getDouble(Class.TOTAL_SCORE);
            classModel.setTotalScore(totalScore);
        } catch (Exception exception) {
            // Ignore this exception.
        }

        return classModel;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, Class classModel) throws SQLException {
        preparedStatement.setString(1, classModel.getName());
    }
}
