package pizzeria.pae.utilidades;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class UtilidadesUI {

    // Constructor privado
    private UtilidadesUI() {
    }

    // 1. Tu método de alertas
    public static void mostrarAlertaSimple(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }

    // 2. Método para cerrar la ventana actual (muy útil en botones de "Cancelar" o "Regresar")
    public static void cerrarVentana(ActionEvent evento) {
        Node source = (Node) evento.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    // 3. Método para centrar ventanas en la pantalla
    public static void centrarVentana(Stage stage) {
        stage.centerOnScreen();
    }
}
