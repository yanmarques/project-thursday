package br.common.models.subject;

import br.common.database.connection.LeftModelRelation;
import br.common.models.curiosity.Curiosity;
import br.common.models.curiosity.CuriosityDAO;

public class CountCuriositiesRelation extends LeftModelRelation {
    public CountCuriositiesRelation() {
        super(new CuriosityDAO(), Subject.ID_ATTRIBUTE, Curiosity.SUBJECT_ID);
    }

    @Override
    public StringBuilder compileColumns(StringBuilder select) {
        return this.getRelationDAO().getCompiler().compileCount(select, Curiosity.ID_ATTRIBUTE, Subject.CURIOSITIES_COUNT);
    }
}
