package pizzeria.pae.vistas.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pizzeria.pae.utilidades.UtilidadesUI;

/**
 * @author Adair Alejandro Martinez Alejo
 * @author Gabriel Hernández Martínez
 * @author Víctor Hugo Vásquez Martínez
 * @author Juan Daniel Pérez Santiago
 */
public class UsuariosViewController implements Initializable {

    @FXML
    private TextField txtBuscarUsuario;
    @FXML
    private Button btnBuscarUsuario;
    @FXML
    private TableView<?> tblUsuarios;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colTelefono;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colDireccion;
    @FXML
    private TableColumn<?, ?> colTipo;
    @FXML
    private Button btnAgregarUsuario;
    @FXML
    private Button btnEditarUsuario;
    @FXML
    private Button btnEliminarUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAgregarUsuario.setOnAction(event -> abrirFormularioUsuario(false));
        btnEditarUsuario.setOnAction(event -> abrirFormularioUsuario(true));
    }

    private void abrirFormularioUsuario(boolean esEdicion) {
        if (esEdicion) {
            int indiceSeleccionado = tblUsuarios.getSelectionModel().getSelectedIndex();
            if (indiceSeleccionado < 0) {
                UtilidadesUI.mostrarAlertaSimple("Selección requerida", "Por favor, seleccione un usuario de la tabla para poder editarlo.", Alert.AlertType.WARNING);
                return;
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/pae/vistas/fxml/UsuarioFormView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(esEdicion ? "Editar Usuario" : "Nuevo Usuario");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnAgregarUsuario.getScene().getWindow());

            stage.showAndWait();

        } catch (IOException e) {
            UtilidadesUI.mostrarAlertaSimple("Error de carga", "No se pudo abrir la ventana del formulario de usuario.", Alert.AlertType.ERROR);
        }
    }
}
