package UMCarroJa;

import UMCarroJa.Model.UMCarroJaModel;

import java.io.Serializable;

public class UMCarroJaController implements Serializable {

    private UMCarroJaView view;
    private UMCarroJaModel model;

    public UMCarroJaController() {
        // Faz nada
    }

    public void startController() {
        while (true) {
            view.menuPrincipal();
        }
    }

    public void set(UMCarroJaView view) {
        this.view = view;
    }

    public void set(UMCarroJaModel model) {
        this.model = model;
    }
}
