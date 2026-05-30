package pizzeria.pae.vistas.controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pizzeria.pae.excepciones.UsuarioNoEncontradoException;
import pizzeria.pae.modelo.beans.Usuario;
import pizzeria.pae.modelo.dao.UsuarioDAO;
import pizzeria.pae.utilidades.Alerta;
import pizzeria.pae.utilidades.SesionUsuario;
import pizzeria.pae.utilidades.seguridad.BCryptHasher;

/**
 */
public class LoginViewController implements Initializable {

    @FXML
    private Button btnIniciarSesion;
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void clickIniciarSesion(ActionEvent event) {
        String usuarioBuscar = tfUsuario.getText().trim();
        String contraseniaBuscar = tfPassword.getText().trim();

        if (usuarioBuscar.isEmpty() || contraseniaBuscar.isEmpty()) {
            Alerta.mostrarAlertaAdvertencia("Campos vacíos", "Tiene que ingresar un usuario y contraseña");
            return;
        }

        try {
            Usuario usuario = UsuarioDAO.buscarUsuarioEmpleado(usuarioBuscar);

            if (usuario == null || !BCryptHasher.verificarContraseniaHash(contraseniaBuscar, usuario.getContrasenia())) {
                throw new UsuarioNoEncontradoException("El usuario no ha sido encontrado o la contraseña es incorrecta.");
            } else {
                SesionUsuario.setUsuarioActual(usuario);
                abrirMenuView();
            }

        } catch (SQLException ex) {
            Alerta.mostrarAlertaError("Error de Base de Datos", "Error de conexión.");
        } catch (UsuarioNoEncontradoException une) {
            Alerta.mostrarAlertaError("Credenciales inválidas", une.getMessage());
        }
    }

    private void abrirMenuView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/pae/vistas/fxml/MenuView.fxml"));
            Parent vista = loader.load();
            Scene escena = new Scene(vista);

            Stage ventana = (Stage) tfUsuario.getScene().getWindow();
            ventana.setScene(escena);
            ventana.setTitle("Italia Pizza - Menú Principal");
            ventana.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
