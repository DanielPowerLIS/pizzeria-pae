package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pizzeria.pae.modelo.beans.Direccion;
import pizzeria.pae.modelo.beans.Usuario;
import pizzeria.pae.modelo.dao.UsuarioDAO;
import pizzeria.pae.utilidades.Alerta;
import pizzeria.pae.utilidades.seguridad.BCryptHasher;

/**
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
    private TextField txtCalle;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtCodigoPostal;
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
            boolean requiereCredenciales = "Administrador".equals(newValue) || "Cajero".equals(newValue);

            txtUsername.setDisable(!requiereCredenciales);
            txtPassword.setDisable(!requiereCredenciales);
            if (!requiereCredenciales) {
                txtUsername.clear();
                txtPassword.clear();
            }
        });
        pizzeria.pae.utilidades.ConfigurarSoloNumeros.configurarSoloNumeros(txtTelefono);
        pizzeria.pae.utilidades.ConfigurarSoloNumeros.configurarSoloNumeros(txtCodigoPostal);
    }

    public void cargarUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        this.esEdicion = true;

        txtNombre.setText(usuario.getNombre());
        txtApellidoPaterno.setText(usuario.getApellidoPaterno());
        if (usuario.getApellidoMaterno() != null) {
            txtApellidoMaterno.setText(usuario.getApellidoMaterno());
        }
        txtTelefono.setText(usuario.getTelefono());
        txtEmail.setText(usuario.getEmail());

        if (usuario.getDireccion() != null) {
            txtCalle.setText(usuario.getDireccion().getCalle());
            txtNumero.setText(usuario.getDireccion().getNumero());
            txtCiudad.setText(usuario.getDireccion().getCiudad());
            txtCodigoPostal.setText(usuario.getDireccion().getCodigoPostal());
        }

        if (!usuario.getEsEmpleado()) {
            cmbTipoUsuario.setValue("Cliente");
        } else {
            if (usuario.getNombreUsuario() != null && !usuario.getNombreUsuario().trim().isEmpty()) {
                cmbTipoUsuario.setValue("Administrador");
                txtUsername.setText(usuario.getNombreUsuario());
            } else {
                cmbTipoUsuario.setValue("Cajero");
            }
        }
    }

    private void guardarUsuario() {
        if (!validarCampos()) {
            return;
        }

        try {
            Usuario usuario = esEdicion ? usuarioActual : new Usuario();

            usuario.setNombre(txtNombre.getText().trim());
            usuario.setApellidoPaterno(txtApellidoPaterno.getText().trim());
            usuario.setApellidoMaterno(txtApellidoMaterno.getText().trim());
            usuario.setTelefono(txtTelefono.getText().trim());
            usuario.setEmail(txtEmail.getText().trim());

            Direccion direccion = new Direccion();
            direccion.setCalle(txtCalle.getText().trim());
            direccion.setNumero(txtNumero.getText().trim());
            direccion.setCiudad(txtCiudad.getText().trim());
            direccion.setCodigoPostal(txtCodigoPostal.getText().trim());
            usuario.setDireccion(direccion);

            String tipo = cmbTipoUsuario.getValue();
            usuario.setRol(tipo);
            boolean esEmpleado = "Administrador".equals(tipo) || "Cajero".equals(tipo);
            usuario.setEsEmpleado(esEmpleado);

            if (!esEdicion) {
                usuario.setEsActivo(true);
                usuario.setHaPedido(false);
            }

            boolean requiereCredenciales = "Administrador".equals(tipo) || "Cajero".equals(tipo);
            if (requiereCredenciales) {
                usuario.setNombreUsuario(txtUsername.getText().trim());
                if (!txtPassword.getText().trim().isEmpty()) {
                    String hash = BCryptHasher.generarContraseniaHash(txtPassword.getText().trim());
                    usuario.setContrasenia(hash);
                }
            } else {
                usuario.setNombreUsuario("");
                usuario.setContrasenia("");
            }

            boolean operacionExitosa;
            if (esEdicion) {
                operacionExitosa = UsuarioDAO.actualizarUsuario(usuario);
            } else {
                operacionExitosa = UsuarioDAO.agregarUsuario(usuario);
            }

            if (operacionExitosa) {
                Alerta.mostrarAlertaInformacion("Éxito", "Usuario registrado correctamente en el sistema.");
                cerrarVentana();
            } else {
                Alerta.mostrarAlertaError("Error", "No se pudo registrar al usuario en la base de datos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alerta.mostrarAlertaError("Error de Base de Datos", "Ocurrió un error al intentar guardar: " + e.getMessage());
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty()
                || txtApellidoPaterno.getText().trim().isEmpty()
                || txtTelefono.getText().trim().isEmpty()
                || txtEmail.getText().trim().isEmpty()
                || txtCalle.getText().trim().isEmpty()
                || txtNumero.getText().trim().isEmpty()
                || txtCiudad.getText().trim().isEmpty()
                || txtCodigoPostal.getText().trim().isEmpty()
                || cmbTipoUsuario.getValue() == null) {

            Alerta.mostrarAlertaAdvertencia("Campos incompletos", "Por favor, llene todos los campos obligatorios.");
            return false;
        }

        boolean requiereCredenciales = "Administrador".equals(cmbTipoUsuario.getValue());
        if (!txtEmail.getText().contains("@") || !txtEmail.getText().contains(".")) {
            Alerta.mostrarAlertaAdvertencia("Email inválido", "Por favor, ingrese un correo electrónico válido.");
            return false;
        }

        if (requiereCredenciales) {
            if (txtUsername.getText().trim().isEmpty()) {
                Alerta.mostrarAlertaAdvertencia("Campos incompletos", "Debe asignar un nombre de usuario al administrador.");
                return false;
            }
            if (!esEdicion && txtPassword.getText().trim().isEmpty()) {
                Alerta.mostrarAlertaAdvertencia("Contraseña vacía", "Debe asignar una contraseña al nuevo administrador.");
                return false;
            }
            if (!txtPassword.getText().trim().isEmpty() && txtPassword.getText().length() < 6) {
                Alerta.mostrarAlertaAdvertencia("Contraseña débil", "La contraseña debe tener al menos 6 caracteres.");
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
