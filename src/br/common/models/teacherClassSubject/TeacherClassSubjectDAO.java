package br.common.models.teacherClassSubject;

import br.common.database.DataBuilder;
import br.common.database.RelationCompiler;
import br.common.database.connection.ModelDAO;
import br.common.models.classes.Class;
import br.common.models.classes.ClassDAO;
import br.common.models.subject.Subject;
import br.common.models.subject.SubjectDAO;
import br.common.models.teacher.Teacher;
import br.common.models.teacherClass.TeacherClass;
import br.common.models.teacherClass.TeacherClassDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherClassSubjectDAO extends ModelDAO<TeacherClassSubject> {
    @Override
    public DataBuilder<TeacherClassSubject> getDataBuilder() {
        return new TeacherClassSubjectDataBuilder();
    }

    public boolean create(TeacherClassSubject model) {
        TeacherClassDAO teacherClassDAO = new TeacherClassDAO();
        TeacherClass teacherClass = teacherClassDAO.firstBy(model.getTeacherId(), model.getClassId());

        if (teacherClass == null) {
            teacherClass = new TeacherClass();
            teacherClass.setTeacherId(model.getTeacherId());
            teacherClass.setClassId(model.getClassId());

            if (teacherClassDAO.create(teacherClass)) {
                return super.create(model);
            }

            return false;
        }

        return super.create(model);
    }

    @Override
    public boolean delete(TeacherClassSubject model) {
        try {
            StringBuilder delete = this.getCompiler().compileDelete();
            this.getCompiler().compileAnd(delete, TeacherClassSubject.CLASS_ID, "=");
            this.getCompiler().compileAnd(delete, TeacherClassSubject.SUBJECT_ID, "=");

            PreparedStatement preparedStatement = this.getQuery().prepareStatement(delete.toString());
            this.getQuery().bindPrimaryKey(1, model, preparedStatement);
            preparedStatement.setInt(2, model.getClassId());
            preparedStatement.setInt(3, model.getSubjectId());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public ArrayList<TeacherClassSubject> allByTeacher(Teacher teacher) {
        try {
            SubjectDAO subjectDAO = new SubjectDAO();
            ClassDAO classDAO = new ClassDAO();

            RelationCompiler subjectClassRelation = subjectDAO.getRelationCompiler(classDAO);

            StringBuilder select = subjectClassRelation.compileSelect();

            ArrayList<String> except = new ArrayList<>();
            except.add(TeacherClassSubject.SUBJECT_ID);
            except.add(TeacherClassSubject.CLASS_ID);

            select.append(", ");
            this.getCompiler().compileColumns(select, except);
            subjectClassRelation.compileFrom(select);

            subjectDAO.getRelationCompiler(this).compileInnerJoin(select, Subject.ID_ATTRIBUTE, "=",
                    TeacherClassSubject.SUBJECT_ID);

            this.getRelationCompiler(classDAO).compileInnerJoin(select, TeacherClassSubject.CLASS_ID, "=",
                    Class.ID_ATTRIBUTE);

            this.getCompiler().compileWhere(select, TeacherClassSubject.TEACHER_ID, "=");

            PreparedStatement preparedStatement = this.getQuery().prepareStatement(select.toString());
            preparedStatement.setInt(1, teacher.getPrimaryKey());

            return this.getQuery().getAll(preparedStatement);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
