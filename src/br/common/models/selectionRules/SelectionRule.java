package br.common.models.selectionRules;

import br.common.database.DataModel;

import java.util.ArrayList;

public class SelectionRule extends DataModel {
    final public static String IMAGE_PATH = "image_path";
    final public static String SUBJECTS_COUNT = "subjects_count";

    private int subjectsCount;

    @Override
    public String getTableName() {
        return "selection_rule";
    }

    @Override
    protected void allowedColumns(ArrayList<String> columns) {
        columns.add(IMAGE_PATH);
    }

    public void setImagePath(String imagePath) {
        this.setString(IMAGE_PATH, imagePath);
    }

    public String getImagePath() {
        return this.getString(IMAGE_PATH);
    }

    public int getSubjectsCount() {
        return subjectsCount;
    }

    public void setSubjectsCount(int subjectsCount) {
        this.subjectsCount = subjectsCount;
    }
}
