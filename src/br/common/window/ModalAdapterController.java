package br.common.window;

import br.common.application.Callback;
import javafx.scene.Node;
import javafx.stage.Stage;

abstract public class ModalAdapterController {
    protected Callback onClose;

    abstract protected Node getOwner();

    protected void close() {
        Carrier.close(this.getOwner());

        if (this.hasOnClose()) {
            this.getOnClose().execute();
        }
    }

    public Callback getOnClose() {
        return onClose;
    }

    public void setOnClose(Callback onClose) {
        this.onClose = onClose;
    }

    public boolean hasOnClose() {
        return this.getOnClose() != null;
    }
}
