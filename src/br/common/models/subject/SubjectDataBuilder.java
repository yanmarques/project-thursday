package br.common.models.subject;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDataBuilder implements DataBuilder<Subject> {
    @Override
    public Subject getModel() {
        return new Subject();
    }

    @Override
    public Subject build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        Subject subject = this.getModel();

        String name = compiler.alias(Subject.NAME);
        subject.setName(resultSet.getString(name));

        try {;
            subject.setCuriositiesCount(resultSet.getInt(Subject.CURIOSITIES_COUNT));
        } catch (Exception exception) {
            // Ignore exception.
        }

        return subject;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, Subject subject) throws SQLException {
        preparedStatement.setString(1, subject.getName());
    }
}
