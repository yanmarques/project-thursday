package br.common.models.teacherClassSubject;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;
import br.common.models.classes.Class;
import br.common.models.classes.ClassDAO;
import br.common.models.subject.Subject;
import br.common.models.subject.SubjectDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherClassSubjectDataBuilder implements DataBuilder<TeacherClassSubject> {
    @Override
    public TeacherClassSubject build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        TeacherClassSubject model = this.getModel();

        String teacherId = compiler.alias(TeacherClassSubject.TEACHER_ID);
        model.setTeacherId(resultSet.getInt(teacherId));

        Subject subject = new SubjectDAO().buildModel(resultSet);
        model.setSubject(subject);

        Class classModel = new ClassDAO().buildModel(resultSet);
        model.setClassModel(classModel);

        return model;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, TeacherClassSubject model) throws SQLException {
        preparedStatement.setInt(1, model.getClassId());
        preparedStatement.setInt(2, model.getSubjectId());
    }

    @Override
    public TeacherClassSubject getModel() {
        return new TeacherClassSubject();
    }
}
