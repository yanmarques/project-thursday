package br.common.database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract public class DataModel implements TableMapping {
    protected Map<String, String> data;
    protected boolean isDirty = false;
    protected ArrayList<String> columns;
    private boolean exists = false;

    public DataModel() {
        this.data = new HashMap<>();
        this.columns = new ArrayList<>();
        this.allowedColumns(this.columns);
        this.columns.add(this.getPrimaryKeyAttribute());
        this.columns.add(this.CREATED_ATTRIBUTE);
        this.columns.add(this.UPDATED_ATTRIBUTE);
    }

    abstract protected void allowedColumns(ArrayList<String> columns);

    abstract public String getTableName();

    @Override
    public boolean isAutoIncrement() {
        return true;
    }

    public String getPrimaryKeyAttribute() {
        return DataModel.ID_ATTRIBUTE;
    }

    public void setPrimaryKey(int id) {
        this.setInt(this.getPrimaryKeyAttribute(), id);
    }

    public Integer getPrimaryKey() {
        return this.getInt(this.getPrimaryKeyAttribute());
    }

    public String qualifyColumn(String attribute) {
        return this.getTableName() + "." + attribute;
    }

    public ArrayList<String> getColumns() {
        return new ArrayList<>(this.columns);
    }

    public Map<String, String> toMap() {
        Map<String, String> newData = new HashMap<>();
        data.forEach(newData::put);
        return newData;
    }

    public boolean isDirty() {
        return this.isDirty;
    }

    @Override
    public LocalDateTime getCreatedTime() {
        String creationTime = this.getString(DataModel.CREATED_ATTRIBUTE);

        if (creationTime == null) {
            return null;
        }

        return this.parseDate(creationTime);
    }

    @Override
    public LocalDateTime getUpdatedTime() {
        String updateTime = this.getString(DataModel.UPDATED_ATTRIBUTE);

        if (updateTime == null) {
            return null;
        }

        return this.parseDate(updateTime);
    }

    @Override
    public String getFormattedCreatedTime() {
        return this.getCreatedTime().format(DataModel.DATE_TIME_FORMAT);
    }

    @Override
    public String getFormattedUpdatedTime() {
        return this.getUpdatedTime().format(DataModel.DATE_TIME_FORMAT);
    }

    @Override
    public void setFreshTimes() {
        this.setString(DataModel.CREATED_ATTRIBUTE, this.freshTime());
        this.setNewUpdatedTime();
    }

    @Override
    public void setNewUpdatedTime() {
        this.setString(DataModel.UPDATED_ATTRIBUTE, this.freshTime());
    }

    @Override
    public void setCreatedTime(String createdAt) {
        this.setString(DataModel.CREATED_ATTRIBUTE, this.formatDate(createdAt));
    }

    @Override
    public void setUpdatedTime(String updatedAt) {
        this.setString(DataModel.UPDATED_ATTRIBUTE, this.formatDate(updatedAt));
    }

    @Override
    public boolean exists() {
        return this.exists;
    }

    @Override
    public void setExists(boolean status) {
        if (status) {
            this.isDirty = false;
        }

        this.exists = status;
    }

    protected void setString(String column, String value) {
        if (this.columns.contains(column)) {
            if (! this.isDirty && ! value.equals(this.getString(column))) {
                this.isDirty = true;
            }

            this.data.put(column, value);
        }
    }

    protected void setInt(String column, int value) {
        this.setString(column, String.valueOf(value));
    }

    protected void setBoolean(String column, boolean value) {
        this.setString(column, String.valueOf(value));
    }

    protected void setDouble(String column, double value) {
        this.setString(column, String.valueOf(value));
    }

    protected String getString(String column) {
        return this.data.getOrDefault(column, null);
    }

    protected Integer getInt(String column) {
        String value = this.getString(column);
        if (value == null) {
            return null;
        }

        return Integer.parseInt(value);
    }

    protected Double getDouble(String column) {
        String value = this.getString(column);
        if (value == null) {
            return null;
        }

        return Double.parseDouble(value);
    }

    protected Boolean getBoolean(String column) {
        String value = this.getString(column);
        if (value == null) {
            return null;
        }

        return Boolean.parseBoolean(value);
    }

    protected LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date, DataModel.DATE_TIME_FORMAT);
    }

    protected String formatDate(String date) {
        return this.parseDate(date).format(DataModel.DATE_TIME_FORMAT);
    }

    protected String freshTime() {
        return LocalDateTime.now().format(DataModel.DATE_TIME_FORMAT);
    }
}
