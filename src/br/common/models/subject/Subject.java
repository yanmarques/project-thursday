package br.common.models.subject;

import br.common.database.DataModel;

import java.util.ArrayList;

public class Subject extends DataModel {
    final public static String NAME = "name";
    final public static String CURIOSITIES_COUNT = "curiosities_count";

    private int curiositiesCount = 0;

    @Override
    public String getTableName() {
        return "subject";
    }

    @Override
    protected void allowedColumns(ArrayList<String> columns) {
        columns.add(NAME);
    }

    public void setName(String name) {
        this.setString(NAME, name);
    }

    public String getName() {
        return this.getString(NAME);
    }

    public int getCuriositiesCount() {
        return curiositiesCount;
    }

    public void setCuriositiesCount(int curiositiesCount) {
        this.curiositiesCount = curiositiesCount;
    }
}
