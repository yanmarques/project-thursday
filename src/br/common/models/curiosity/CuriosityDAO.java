package br.common.models.curiosity;

import br.common.database.DataBuilder;
import br.common.database.connection.ModelDAO;
import br.common.models.subject.Subject;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CuriosityDAO extends ModelDAO<Curiosity> {
    @Override
    public DataBuilder<Curiosity> getDataBuilder() {
        return new CuriosityDataBuilder();
    }

    public ArrayList<Curiosity> allBySubject(Subject subject) {
        try {
            StringBuilder select = this.compileSelect();
            this.getCompiler().compileWhere(select, Curiosity.SUBJECT_ID, "=");

            PreparedStatement preparedStatement = this.getQuery().prepareStatement(select.toString());
            preparedStatement.setInt(1, subject.getPrimaryKey());

            return this.getQuery().getAll(preparedStatement);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
