package br.common.models.curiosity;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;
import br.common.database.TableMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuriosityDataBuilder implements DataBuilder<Curiosity> {
    @Override
    public Curiosity getModel() {
        return new Curiosity();
    }

    @Override
    public Curiosity build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        Curiosity curiosity = this.getModel();

        String description = compiler.alias(Curiosity.DESCRIPTION);
        curiosity.setDescription(resultSet.getString(description));

        String subjectId = compiler.alias(Curiosity.SUBJECT_ID);
        curiosity.setSubjectId(resultSet.getInt(subjectId));

        return curiosity;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, Curiosity curiosity) throws SQLException {
        preparedStatement.setString(1, curiosity.getDescription());
        preparedStatement.setInt(2, curiosity.getSubjectId());
    }
}
