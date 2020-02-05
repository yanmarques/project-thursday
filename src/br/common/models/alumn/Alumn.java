package br.common.models.alumn;

import br.common.database.DataModel;
import br.common.models.classes.Class;
import br.common.models.teacher.Teacher;

import java.util.ArrayList;

public class Alumn extends DataModel {
    final public static String NAME = "name";
    final public static String SCORE = "score";
    final public static String TEACHER_ID = "teacher_id";
    final public static String CLASS_ID = "class_id";

    protected Teacher teacher;
    protected Class classModel;

    @Override
    protected void allowedColumns(ArrayList<String> keys) {
        keys.add(NAME);
        keys.add(SCORE);
        keys.add(TEACHER_ID);
        keys.add(CLASS_ID);
    }

    @Override
    public String getTableName() {
        return "alumn";
    }

    public void setName(String name) {
        this.setString(NAME, name);
    }

    public void setScore(double score) {
        this.setDouble(SCORE, score);
    }

    public void setTeacherId(int teacherId) {
        this.setInt(TEACHER_ID, teacherId);
    }

    public void setClassId(int classId) {
        this.setInt(CLASS_ID, classId);
    }

    public String getName() {
        return this.getString(NAME);
    }

    public Double getScore() {
        Double score = this.getDouble(SCORE);

        if (score == null) {
            return 0.0;
        }

        return score;
    }

    public void setClass(Class classModel) {
        this.classModel = classModel;
        this.setClassId(classModel.getPrimaryKey());
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        this.setTeacherId(teacher.getPrimaryKey());
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Class getClassModel() {
        return classModel;
    }

    public Integer getTeacherId() {
        return this.getInt(TEACHER_ID);
    }

    public Integer getClassId() {
        return this.getInt(CLASS_ID);
    }
}
