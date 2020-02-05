package br.common.models.teacher;

import br.common.database.DataModel;

import java.util.ArrayList;

public class Teacher extends DataModel {
    final public static String NAME = "name";

    @Override
    protected void allowedColumns(ArrayList<String> keys) {
        keys.add(NAME);
    }

    @Override
    public String getTableName() {
        return "teacher";
    }

    public void setName(String name) {
        this.setString(NAME, name);
    }

    public String getName() {
        return this.getString(NAME);
    }
}
