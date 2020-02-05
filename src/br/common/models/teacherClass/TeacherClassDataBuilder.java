package br.common.models.teacherClass;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherClassDataBuilder implements DataBuilder<TeacherClass> {
    @Override
    public TeacherClass build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        TeacherClass teacherClass = this.getModel();

        String classId = compiler.alias(TeacherClass.CLASS_ID);
        teacherClass.setClassId(resultSet.getInt(classId));

        return teacherClass;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, TeacherClass model) throws SQLException {
        preparedStatement.setInt(1, model.getClassId());
    }

    @Override
    public TeacherClass getModel() {
        return new TeacherClass();
    }
}
