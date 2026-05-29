package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pizzeria.pae.modelo.beans.Direccion;
import pizzeria.pae.modelo.beans.Usuario;
import pizzeria.pae.modelo.dao.UsuarioDAO;
import pizzeria.pae.utilidades.UtilidadesUI;
import pizzeria.pae.utilidades.seguridad.BCryptHasher;

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
    private TextField txtNombre;
    @FXML
    private TextField txtApellidoPaterno;
    @FXML
    private TextField txtApellidoMaterno;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDireccion;
    @FXML
    private ComboBox<String> cmbTipoUsuario;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    private boolean esEdicion = false;
    private Usuario usuarioActual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTipoUsuario.getItems().addAll("Administrador", "Cajero", "Cliente");

        btnGuardar.setOnAction(event -> guardarUsuario());
        btnCancelar.setOnAction(event -> cerrarVentana());

        cmbTipoUsuario.valueProperty().addListener((observable, oldValue, newValue) -> {
            boolean esCliente = "Cliente".equals(newValue);
            txtUsername.setDisable(esCliente);
            txtPassword.setDisable(esCliente);
            if (esCliente) {
                txtUsername.clear();
                txtPassword.clear();
            }
        });
    }

    private void guardarUsuario() {
        if (!validarCampos()) {
            return;
        }

        try {
            Usuario u = esEdicion ? usuarioActual : new Usuario();

            u.setNombre(txtNombre.getText().trim());
            u.setApellidoPaterno(txtApellidoPaterno.getText().trim());
            u.setApellidoMaterno(txtApellidoMaterno.getText().trim());
            u.setTelefono(txtTelefono.getText().trim());
            u.setEmail(txtEmail.getText().trim());

            Direccion direccion = new Direccion();
            direccion.setCalle(txtDireccion.getText().trim());
            direccion.setCiudad("");
            direccion.setNumero("");
            direccion.setCodigoPostal("");
            u.setDireccion(direccion);

            String tipo = cmbTipoUsuario.getValue();
            boolean esEmpleado = "Administrador".equals(tipo) || "Cajero".equals(tipo);
            u.setEsEmpleado(esEmpleado);

            if (!esEdicion) {
                u.setEsActivo(true);
                u.setHaPedido(false);
            }

            if (esEmpleado) {
                u.setNombreUsuario(txtUsername.getText().trim());
                if (!txtPassword.getText().trim().isEmpty()) {
                    String hash = BCryptHasher.generarContraseniaHash(txtPassword.getText().trim());
                    u.setContrasenia(hash);
                }
            } else {
                u.setNombreUsuario("");
                u.setContrasenia("");
            }

            boolean operacionExitosa;
            if (esEdicion) {
                operacionExitosa = UsuarioDAO.actualizarUsuario(u);
            } else {
                operacionExitosa = UsuarioDAO.agregarUsuario(u);
            }

            if (operacionExitosa) {
                UtilidadesUI.mostrarAlertaSimple("Éxito", "Usuario registrado correctamente en el sistema.", Alert.AlertType.INFORMATION);
                cerrarVentana();
            } else {
                UtilidadesUI.mostrarAlertaSimple("Error", "No se pudo registrar al usuario en la base de datos.", Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            UtilidadesUI.mostrarAlertaSimple("Error de Base de Datos", "Ocurrió un error al intentar guardar: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty()
                || txtApellidoPaterno.getText().trim().isEmpty()
                || txtTelefono.getText().trim().isEmpty()
                || txtEmail.getText().trim().isEmpty()
                || txtDireccion.getText().trim().isEmpty()
                || cmbTipoUsuario.getValue() == null) {

            UtilidadesUI.mostrarAlertaSimple("Campos incompletos", "Por favor, llene todos los campos obligatorios (*).", Alert.AlertType.WARNING);
            return false;
        }

        boolean esEmpleado = "Administrador".equals(cmbTipoUsuario.getValue()) || "Cajero".equals(cmbTipoUsuario.getValue());

        if (esEmpleado) {
            if (txtUsername.getText().trim().isEmpty()) {
                UtilidadesUI.mostrarAlertaSimple("Campos incompletos", "Debe asignar un nombre de usuario al empleado.", Alert.AlertType.WARNING);
                return false;
            }
            if (!esEdicion && txtPassword.getText().trim().isEmpty()) {
                UtilidadesUI.mostrarAlertaSimple("Contraseña vacía", "Debe asignar una contraseña al nuevo empleado.", Alert.AlertType.WARNING);
                return false;
            }
            if (!txtPassword.getText().trim().isEmpty() && txtPassword.getText().length() < 6) {
                UtilidadesUI.mostrarAlertaSimple("Contraseña débil", "La contraseña debe tener al menos 6 caracteres.", Alert.AlertType.WARNING);
                return false;
            }
        }

        return true;
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}
