package br.common.models.classes;

import br.common.database.connection.InnerModelRelation;
import br.common.models.alumn.Alumn;
import br.common.models.alumn.AlumnDAO;

public class SumAlumScoreRelation extends InnerModelRelation {
    public SumAlumScoreRelation() {
        super(new AlumnDAO(), Class.ID_ATTRIBUTE, Alumn.CLASS_ID);
    }

    @Override
    public StringBuilder compileColumns(StringBuilder select) {
        return this.getRelationDAO().getCompiler().compileSum(select, Alumn.SCORE, Class.TOTAL_SCORE);
    }
}
