package br.common.models.teacher;

import br.common.database.DataBuilder;
import br.common.database.RelationCompiler;
import br.common.database.connection.ModelDAO;
import br.common.models.classes.Class;
import br.common.models.classes.ClassDAO;
import br.common.models.teacherClass.TeacherClass;
import br.common.models.teacherClass.TeacherClassDAO;
import br.configuration.interactions.controller.ClassFilterDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDAO extends ModelDAO<Teacher> implements ClassFilterDAO<Teacher> {
    @Override
    public DataBuilder<Teacher> getDataBuilder() {
        return new TeacherDataBuilder();
    }

    @Override
    public ArrayList<Teacher> allByClass(Class classModel) {
        try {
            StringBuilder select = this.compileSelect();
            TeacherClassDAO teacherClassDao = new TeacherClassDAO();

            RelationCompiler teacherClassRelation = this.getRelationCompiler(teacherClassDao);
            teacherClassRelation.compileInnerJoin(select, Teacher.ID_ATTRIBUTE, "=", TeacherClass.TEACHER_ID);

            teacherClassDao.getCompiler().compileWhere(select, TeacherClass.CLASS_ID, "=");
            this.getCompiler().compileGroupped(select, Teacher.ID_ATTRIBUTE);

            PreparedStatement ps = this.getQuery().prepareStatement(select.toString());
            ps.setInt(1, classModel.getPrimaryKey());

            return this.getQuery().getAll(ps);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
