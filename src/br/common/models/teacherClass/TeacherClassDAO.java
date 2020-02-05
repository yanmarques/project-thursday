package br.common.models.teacherClass;

import br.common.database.DataBuilder;
import br.common.database.connection.ModelDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherClassDAO extends ModelDAO<TeacherClass> {
    @Override
    public DataBuilder<TeacherClass> getDataBuilder() {
        return new TeacherClassDataBuilder();
    }

    public TeacherClass firstBy(int teacherId, int classId) {
        try {
            StringBuilder select = this.compileSelect();

            TeacherClass teacherClass = new TeacherClass();
            teacherClass.setTeacherId(teacherId);
            teacherClass.setClassId(classId);

            this.getCompiler().compileWhere(select, TeacherClass.CLASS_ID, "=");
            this.getCompiler().compileAnd(select, TeacherClass.TEACHER_ID, "=");

            PreparedStatement preparedStatement = this.getQuery().prepareStatement(select.toString());
            this.getDataBuilder().buildStatement(preparedStatement, teacherClass);
            this.getQuery().bindPrimaryKey(teacherClass, preparedStatement);

            return this.getQuery().getOne(preparedStatement);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
