package pizzeria.pae.utilidades;

import javafx.scene.control.TextField;

/**
 *
 * @author jdani
 */
public class ConfigurarSoloNumeros {
    public static void configurarSoloNumeros(TextField tf) {
        tf.textProperty().addListener((observable, valorViejo, valorNuevo) -> {

        if(!valorNuevo.matches("\\d*")){
            tf.setText(
                    valorNuevo.replaceAll("[^\\d]", "")
                );
            }

        });        
    }
    
    public static void configurarPrecio(TextField tf) {
        tf.textProperty().addListener((observable, valorViejo, valorNuevo) -> {

            if (!valorNuevo.matches("\\d*(\\.\\d{0,2})?")) {
                tf.setText(valorViejo);
            }

        });
    }
}
