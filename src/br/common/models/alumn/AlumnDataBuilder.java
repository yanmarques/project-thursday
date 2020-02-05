package br.common.models.alumn;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;
import br.common.database.TableMapping;
import br.common.models.classes.Class;
import br.common.models.classes.ClassDAO;
import br.common.models.teacher.Teacher;
import br.common.models.teacher.TeacherDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnDataBuilder implements DataBuilder<Alumn> {
    @Override
    public Alumn getModel() {
        return new Alumn();
    }

    @Override
    public Alumn build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        Alumn alumn = this.getModel();

        String name = compiler.alias(Alumn.NAME);
        alumn.setName(resultSet.getString(name));

        String score = compiler.alias(Alumn.SCORE);
        alumn.setScore(resultSet.getDouble(score));

        Class classModel = new ClassDAO().buildModel(resultSet);
        alumn.setClass(classModel);

        Teacher teacher = new TeacherDAO().buildModel(resultSet);
        alumn.setTeacher(teacher);

        return alumn;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, Alumn alumn) throws SQLException {
        preparedStatement.setString(1, alumn.getName());
        preparedStatement.setDouble(2, alumn.getScore());
        preparedStatement.setInt(3, alumn.getTeacherId());
        preparedStatement.setInt(4, alumn.getClassId());
    }
}
