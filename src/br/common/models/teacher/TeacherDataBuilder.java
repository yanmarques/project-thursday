package br.common.models.teacher;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;
import br.common.database.TableMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDataBuilder implements DataBuilder<Teacher> {
    @Override
    public Teacher getModel() {
        return new Teacher();
    }

    @Override
    public Teacher build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        Teacher alumn = this.getModel();

        String name = compiler.alias(Teacher.NAME);
        alumn.setName(resultSet.getString(name));

        return alumn;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, Teacher teacher) throws SQLException {
        preparedStatement.setString(1, teacher.getName());
    }
}
