package br.common.models.alumn;

import br.common.database.DataBuilder;
import br.common.database.connection.BelongsModelRelation;
import br.common.database.connection.InnerModelRelation;
import br.common.database.connection.RelationModelDAO;
import br.common.models.classes.Class;
import br.common.models.classes.ClassDAO;
import br.common.models.teacher.TeacherDAO;
import br.configuration.interactions.controller.ClassFilterDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlumnDAO extends RelationModelDAO<Alumn> implements ClassFilterDAO<Alumn> {
    @Override
    public DataBuilder<Alumn> getDataBuilder() {
        return new AlumnDataBuilder();
    }

    public ArrayList<Alumn> allOrderedByScore() {
        try {
            StringBuilder select = this.compileSelect();
            this.getCompiler().compileOrderBy(select, Alumn.SCORE, "DESC");
            return this.getAll(select);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public ArrayList<Alumn> allByClass(Class classModel) {
        try {
            StringBuilder select = this.compileSelect();
            this.getCompiler().compileWhere(select, Alumn.CLASS_ID, "=");

            PreparedStatement preparedStatement = this.getQuery().prepareStatement(select.toString());
            preparedStatement.setInt(1, classModel.getPrimaryKey());

            return this.getQuery().getAll(preparedStatement);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    protected void addRelations(ArrayList<InnerModelRelation> modelRelations) {
        modelRelations.add(new BelongsModelRelation(new TeacherDAO(), Alumn.TEACHER_ID));
        modelRelations.add(new BelongsModelRelation(new ClassDAO(), Alumn.CLASS_ID));
    }
}
