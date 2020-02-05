package br.common.models.selectionRuleSubjects;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;
import br.common.models.subject.Subject;
import br.common.models.subject.SubjectDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectionRuleSubjectDataBuilder implements DataBuilder<SelectionRuleSubject> {
    @Override
    public SelectionRuleSubject getModel() {
        return new SelectionRuleSubject();
    }

    @Override
    public SelectionRuleSubject build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        SelectionRuleSubject selectionRuleSubject = this.getModel();

        Subject subject = new SubjectDAO().buildModel(resultSet);
        selectionRuleSubject.setSubject(subject);

        return selectionRuleSubject;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, SelectionRuleSubject model) throws SQLException {
        preparedStatement.setInt(1, model.getSubjectId());
    }
}
