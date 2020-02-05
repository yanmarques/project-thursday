package br.common.database.connection;

import br.common.database.DataBuilder;
import br.common.database.RelationCompiler;
import br.common.database.SQLCompiler;
import br.common.database.TableMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

abstract public class ModelDAO<T extends TableMapping> {
    private ModelQuery<T> query;

    public ModelDAO() {
        this.query = new ModelQuery<>(this.getDataBuilder());
    }

    abstract public DataBuilder<T> getDataBuilder();

    protected ModelQuery<T> getQuery() {
        return this.query;
    }

    public SQLCompiler getCompiler() {
        return this.getQuery().getCompiler();
    }

    public T first() {
        try {
            return this.getOne(this.compileSelect());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public ArrayList<T> all() {
        try {
            return this.getAll(this.compileSelect());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public boolean create(T model) {
        try {
            return this.getQuery().create(model);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public int update(T model) {
        try {
            return this.getQuery().update(model);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    public boolean delete(T model) {
        try {
            return this.getQuery().delete(model);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public RelationCompiler getRelationCompiler(ModelDAO referencedTable) {
        return new RelationCompiler(this.getCompiler(), referencedTable.getCompiler());
    }

    public T buildModel(ResultSet resultSet) throws SQLException {
        return this.getQuery().buildModel(resultSet);
    }

    protected T getOne(StringBuilder select) throws SQLException {
        PreparedStatement preparedStatement = this.getQuery().prepareStatement(select.toString());
        return this.getQuery().getOne(preparedStatement);
    }

    protected ArrayList<T> getAll(StringBuilder select) throws SQLException {
        PreparedStatement preparedStatement = this.getQuery().prepareStatement(select.toString());
        return this.getQuery().getAll(preparedStatement);
    }

    protected StringBuilder compileSelect() {
        StringBuilder select = this.getCompiler().compileSelect();
        return this.getCompiler().compileFrom(select);
    }
}
