package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pizzeria.pae.utilidades.UtilidadesUI;

/**
 * Controlador para el formulario de usuarios.
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
            System.out.println("Validación exitosa");
            UtilidadesUI.mostrarAlertaSimple("Éxito", "Ussuario guardado exitosamente.", Alert.AlertType.INFORMATION);
            cerrarVentana();
        }
    }

    private boolean validarCampos() {
        if (txtNombreCompleto.getText().trim().isEmpty()
                || txtUsername.getText().trim().isEmpty()
                || cmbRol.getValue() == null) {

            UtilidadesUI.mostrarAlertaSimple("Campos incompletos", "Por favor, llene todos los campos obligatorios.", Alert.AlertType.WARNING);
            return false;
        }

        if (!esEdicion && pwdContrasena.getText().trim().isEmpty()) {
            UtilidadesUI.mostrarAlertaSimple("Contraseña vacía", "Debe asignar una contraseña al nuevo usuario.", Alert.AlertType.WARNING);
            return false;
        }

        if (!pwdContrasena.getText().trim().isEmpty() && pwdContrasena.getText().length() < 6) {
            UtilidadesUI.mostrarAlertaSimple("Contraseña débil", "La contraseña debe tener al menos 6 caracteres.", Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelarUsuario.getScene().getWindow();
        stage.close();
    }
}
