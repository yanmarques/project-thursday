package br.common.models.choices;

import br.common.database.DataBuilder;
import br.common.database.connection.ModelDAO;

public class ChoiceDAO extends ModelDAO<Choice> {
    @Override
    public DataBuilder<Choice> getDataBuilder() {
        return new ChoiceDataBuilder();
    }
}
