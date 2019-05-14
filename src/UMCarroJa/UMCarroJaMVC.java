package UMCarroJa;

import UMCarroJa.Model.UMCarroJaModel;

import java.io.Serializable;

import static java.lang.System.out;

public class UMCarroJaMVC implements Serializable {

    public static void main(String[] args) {
        UMCarroJaModel model = new UMCarroJaModel();

        if (model.createData() == null) {
            out.println("Error... ");
            System.exit(-1);
        }

        // O "menu"
        UMCarroJaView view = new UMCarroJaView();

        // O controlador
        UMCarroJaController control = new UMCarroJaController();

        control.set(model);
        control.set(view);
        control.startController();
        System.exit(0);
    }

}
