package br.common.window;

import br.common.window.interfaces.StageGather;
import javafx.stage.Stage;

public class SimpleStageGather implements StageGather {
    private Stage stage;

    @Override
    public void setCurrentStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Stage getCurrentStage() {
        return this.stage;
    }
}
