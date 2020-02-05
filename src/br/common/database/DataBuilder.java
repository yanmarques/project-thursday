package br.common.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataBuilder<T extends TableMapping> {
	T build(SQLCompiler compiler, ResultSet resultSet) throws SQLException;

	void buildStatement(PreparedStatement preparedStatement, T model) throws SQLException;

	T getModel();
}
