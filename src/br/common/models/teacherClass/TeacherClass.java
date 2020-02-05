package br.common.models.teacherClass;

import br.common.database.DataModel;
import br.common.models.classes.Class;
import br.common.models.teacher.Teacher;

import java.util.ArrayList;

public class TeacherClass extends DataModel {
    final public static String TEACHER_ID = "teacher_id";
    final public static String CLASS_ID = "class_id";

    private Teacher teacher;
    private Class classModel;

    @Override
    public String getPrimaryKeyAttribute() {
        return TEACHER_ID;
    }

    @Override
    public boolean isAutoIncrement() {
        return false;
    }

    @Override
    public String getTableName() {
        return "teacher_class";
    }

    @Override
    protected void allowedColumns(ArrayList<String> columns) {
        columns.add(CLASS_ID);
    }

    public void setTeacherId(int teacherId) {
        this.setInt(TEACHER_ID, teacherId);
    }

    public Integer getTeacherId() {
        return this.getInt(TEACHER_ID);
    }

    public void setClassId(int classId) {
        this.setInt(CLASS_ID, classId);
    }

    public Integer getClassId() {
        return this.getInt(CLASS_ID);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.setTeacherId(teacher.getPrimaryKey());
        this.teacher = teacher;
    }

    public Class getClassModel() {
        return classModel;
    }

    public void setClassModel(Class classModel) {
        this.setClassId(classModel.getPrimaryKey());
        this.classModel = classModel;
    }
}
