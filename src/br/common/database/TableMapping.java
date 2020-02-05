package br.common.database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public interface TableMapping {
    String ID_ATTRIBUTE = "id";
    String CREATED_ATTRIBUTE = "created_at";
    String UPDATED_ATTRIBUTE = "updated_at";
    DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

    String getTableName();

    String getPrimaryKeyAttribute();

    void setPrimaryKey(int id);

    Integer getPrimaryKey();

    ArrayList<String> getColumns();

    String qualifyColumn(String attribute);

    LocalDateTime getCreatedTime();

    LocalDateTime getUpdatedTime();

    String getFormattedCreatedTime();

    String getFormattedUpdatedTime();

    void setCreatedTime(String createdAt);

    void setUpdatedTime(String updatedAt);

    void setFreshTimes();

    void setNewUpdatedTime();

    boolean isDirty();

    boolean exists();

    void setExists(boolean status);

    boolean isAutoIncrement();
}
