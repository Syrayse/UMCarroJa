package UMCarroJa;

import UMCarroJa.Model.UMCarroJaModel;

import java.io.Serializable;

public class UMCarroJaController implements Serializable {

    private UMCarroJaView view;
    private UMCarroJaModel model;

    public void startController() {
        while (true) {
            // Fazer merdas
        }
    }

    public void set(UMCarroJaView view) {
        this.view = view;
    }

    public void set(UMCarroJaModel model) {
        this.model = model;
    }
}
