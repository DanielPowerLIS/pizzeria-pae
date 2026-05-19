package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controlador para el formulario de registro y edición de Usuarios del sistema.
 *
 * @author Adair Alejandro Martinez Alejo
 * @author Gabriel Hernández Martínez
 * @author Víctor Hugo Vásquez Martínez
 * @author Juan Daniel Pérez Santiago
 */
public class UsuarioFormViewController implements Initializable {

    @FXML
    private TextField txtNombreCompleto;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwdContrasena;
    @FXML
    private ComboBox<String> cmbRol;

    @FXML
    private Button btnGuardarUsuario;
    @FXML
    private Button btnCancelarUsuario;

    private boolean esEdicion = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbRol.getItems().addAll("Administrador", "Cajero");
        btnGuardarUsuario.setOnAction(event -> guardarUsuario());
        btnCancelarUsuario.setOnAction(event -> cerrarVentana());
    }

    private void guardarUsuario() {
        if (validarCampos()) {
            System.out.println("Validación exitosa. Listo para enviar a MySQL.");
            mostrarAlertaInfo("Éxito", "El usuario está listo para ser guardado.");
            cerrarVentana();
        }
    }

    private boolean validarCampos() {
        if (txtNombreCompleto.getText().trim().isEmpty()
                || txtUsername.getText().trim().isEmpty()
                || cmbRol.getValue() == null) {

            mostrarAlertaError("Campos incompletos", "Por favor, llene todos los campos obligatorios.");
            return false;
        }

        if (!esEdicion && pwdContrasena.getText().trim().isEmpty()) {
            mostrarAlertaError("Contraseña vacía", "Debe asignar una contraseña al nuevo usuario.");
            return false;
        }

        if (!pwdContrasena.getText().trim().isEmpty() && pwdContrasena.getText().length() < 6) {
            mostrarAlertaError("Contraseña débil", "La contraseña debe tener al menos 6 caracteres.");
            return false;
        }

        return true;
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarAlertaInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelarUsuario.getScene().getWindow();
        stage.close();
    }
}
