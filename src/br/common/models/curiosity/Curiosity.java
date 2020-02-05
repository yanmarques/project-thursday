package br.common.models.curiosity;

import br.common.database.DataModel;
import br.common.models.subject.Subject;

import java.util.ArrayList;

public class Curiosity extends DataModel {
    final public static String DESCRIPTION = "description";
    final public static String SUBJECT_ID = "subject_id";

    private Subject subject;

    @Override
    public String getTableName() {
        return "curiosity";
    }

    @Override
    protected void allowedColumns(ArrayList<String> columns) {
        columns.add(DESCRIPTION);
        columns.add(SUBJECT_ID);
    }

    public void setDescription(String description) {
        this.setString(DESCRIPTION, description);
    }

    public String getDescription() {
        return this.getString(DESCRIPTION);
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
