package pizzeria.pae.utilidades;

import javafx.scene.control.Alert;

/**
 *
 * @author Gabriel Hernández Martínez
 */
public class UtilidadesUI {

    public static void mostrarAlertaSimple(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}
