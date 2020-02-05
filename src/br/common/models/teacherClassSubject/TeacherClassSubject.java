package br.common.models.teacherClassSubject;

import br.common.models.subject.Subject;
import br.common.models.teacherClass.TeacherClass;

import java.util.ArrayList;

public class TeacherClassSubject extends TeacherClass {
    final public static String SUBJECT_ID = "subject_id";

    private Subject subject;

    @Override
    public String getTableName() {
        return "teacher_class_subject";
    }

    @Override
    protected void allowedColumns(ArrayList<String> columns) {
        super.allowedColumns(columns);
        columns.add(SUBJECT_ID);
    }

    public void setSubjectId(int subjectId) {
        this.setInt(SUBJECT_ID, subjectId);
    }

    public Integer getSubjectId() {
        return this.getInt(SUBJECT_ID);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.setSubjectId(subject.getPrimaryKey());
        this.subject = subject;
    }
}
