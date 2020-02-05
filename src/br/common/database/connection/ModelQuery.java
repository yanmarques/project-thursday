package br.common.database.connection;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;
import br.common.database.TableMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelQuery<T extends TableMapping> {
    protected DataBuilder<T> builder;
    protected SQLCompiler compiler;

    public ModelQuery(DataBuilder<T> builder) {
        this.setBuilder(builder);
    }

    public void setBuilder(DataBuilder<T> builder) {
        this.builder = builder;
        this.compiler = new SQLCompiler(builder.getModel());
    }

    public SQLCompiler getCompiler() {
        return this.compiler;
    }

    public T getOne(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            T model = this.buildModel(resultSet);
            resultSet.close();
            return model;
        }

        return null;
    }

    public Integer getLastInsertId(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return null;
    }

    public ArrayList<T> getAll(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<T> results = new ArrayList<>();

        while(resultSet.next()) {
            T model = this.buildModel(resultSet);
            results.add(model);
        }

        return results;
    }


    public int update(T model) throws SQLException {
        model.setNewUpdatedTime();

        ArrayList<String> except = this.getColumnsExcepted(model);
        except.add(T.CREATED_ATTRIBUTE);

        StringBuilder sql = this.getCompiler().compileUpdate(except);

        PreparedStatement preparedStatement = this.prepareStatement(sql.toString());

        this.builder.buildStatement(preparedStatement, model);
        this.bindUpdatedDate(model, preparedStatement, this.getParameterCount(preparedStatement) - 1);
        this.bindPrimaryKey(model, preparedStatement);

        return preparedStatement.executeUpdate();
    }

    public boolean delete(T model) throws SQLException {
        StringBuilder delete = this.getCompiler().compileDelete();

        PreparedStatement preparedStatement = this.prepareStatement(delete.toString());
        this.bindPrimaryKey(model, preparedStatement);

        return preparedStatement.executeUpdate() == 1;
    }

    public boolean create(T model) throws SQLException {
        StringBuilder insert = this.getCompiler().compileInsert(this.getColumnsExcepted(model));

        PreparedStatement preparedStatement = this.prepareStatement(insert.toString());

        this.builder.buildStatement(preparedStatement, model);

        if (! model.isAutoIncrement()) {
            this.bindPrimaryKey(model.getColumns().size() - 2, model, preparedStatement);
        }

        this.bindFreshDates(model, preparedStatement);
        boolean result = preparedStatement.executeUpdate() == 1;

        if (result) {
            if (model.isAutoIncrement()) {
                model.setPrimaryKey(this.getLastInsertId(preparedStatement));
            }

            model.setExists(true);
        }

        return result;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        try {
            Connector.checkConnection();
        } catch (Exception exception) {
            throw new SQLException(exception.getMessage());
        }
        System.out.println(sql);
        return Connector.getConnection().prepareStatement(sql);
    }

    public T buildModel(ResultSet resultSet) throws SQLException {
        T model = this.builder.build(this.getCompiler(), resultSet);

        String primaryKeyAttribute = this.getCompiler().alias(model.getPrimaryKeyAttribute());
        model.setPrimaryKey(resultSet.getInt(primaryKeyAttribute));

        String createdAt = this.getCompiler().alias(T.CREATED_ATTRIBUTE);
        model.setCreatedTime(resultSet.getString(createdAt));

        String updatedAt = this.getCompiler().alias(T.UPDATED_ATTRIBUTE);
        model.setUpdatedTime(resultSet.getString(updatedAt));

        model.setExists(true);

        return model;
    }

    public void bindPrimaryKey(T model, PreparedStatement preparedStatement) throws SQLException {
        int primaryKeyParameter = preparedStatement.getParameterMetaData().getParameterCount();

        if (primaryKeyParameter > 0) {
            this.bindPrimaryKey(primaryKeyParameter, model, preparedStatement);
        }
    }

    public void bindPrimaryKey(int position, T model, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(position, model.getPrimaryKey());
    }

    protected void bindFreshDates(T model, PreparedStatement preparedStatement) throws SQLException {
        model.setFreshTimes();

        int dateTimeParameter = this.getParameterCount(preparedStatement) - 1;
        preparedStatement.setString(dateTimeParameter, model.getFormattedCreatedTime());

        this.bindUpdatedDate(model, preparedStatement, dateTimeParameter + 1);
    }

    protected void bindUpdatedDate(T model, PreparedStatement preparedStatement, int offset) throws SQLException {
        preparedStatement.setString(offset, model.getFormattedUpdatedTime());
    }

    protected int getParameterCount(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.getParameterMetaData().getParameterCount();
    }

    protected ArrayList<String> getColumnsExcepted(T model) {
        ArrayList<String> except = new ArrayList<>();

        if (model.isAutoIncrement()) {
            except.add(model.getPrimaryKeyAttribute());
        }

        return except;
    }
}
