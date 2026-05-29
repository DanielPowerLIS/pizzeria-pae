package pizzeria.pae.utilidades;

import javafx.scene.control.Alert;

/**
 *
 * @author jdani
 */
public class Alerta {
    public static void mostrarAlertaError(String titulo, String contenido){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
    
    public static void mostrarAlertaInformacion(String titulo, String contenido){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }

    public static void mostrarAlertaAdvertencia(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
