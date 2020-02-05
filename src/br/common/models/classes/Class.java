package br.common.models.classes;

import br.common.database.DataModel;

import java.util.ArrayList;

public class Class extends DataModel {
    final public static String NAME = "name";
    final public static String TOTAL_SCORE = "total_score";

    private double totalScore = 0.0;

    @Override
    protected void allowedColumns(ArrayList<String> keys) {
        keys.add(NAME);
    }

    @Override
    public String getTableName() {
        return "class";
    }

    public void setName(String name) {
        this.setString(NAME, name);
    }

    public String getName() {
        return this.getString(NAME);
    }

    public void setTotalScore(double score) {
        this.totalScore = score;
    }

    public double getTotalScore() {
        return totalScore;
    }
}
