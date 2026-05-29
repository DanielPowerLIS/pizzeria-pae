package pizzeria.pae.vistas.controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pizzeria.pae.modelo.beans.Usuario;
import pizzeria.pae.modelo.dao.UsuarioDAO;
import pizzeria.pae.utilidades.Alerta;

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
    private TableView<Usuario> tblUsuarios;
    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, String> colTelefono;
    @FXML
    private TableColumn<Usuario, String> colEmail;
    @FXML
    private TableColumn<Usuario, String> colDireccion;
    @FXML
    private Button btnAgregarUsuario;
    @FXML
    private Button btnEditarUsuario;
    @FXML
    private Button btnEliminarUsuario;
    @FXML
    private RadioButton rdTodos;
    @FXML
    private ToggleGroup tgFiltroUsuarios;
    @FXML
    private RadioButton rdEmpleados;
    @FXML
    private RadioButton rdClientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarDatosTabla();

        btnAgregarUsuario.setOnAction(event -> abrirFormularioUsuario(false));
        btnEditarUsuario.setOnAction(event -> abrirFormularioUsuario(true));
        btnEliminarUsuario.setOnAction(event -> eliminarUsuario());
    }

    private void configurarTabla() {
        colNombre.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getNombreCompleto())
        );
        colTelefono.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getTelefono())
        );
        colEmail.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getEmail())
        );
        colDireccion.setCellValueFactory(cellData -> {
            if (cellData.getValue().getDireccion() != null) {
                return new SimpleStringProperty(cellData.getValue().getDireccion().toString());
            } else {
                return new SimpleStringProperty("Sin dirección");
            }
        });
    }

    private void cargarDatosTabla() {
        try {
            List<Usuario> listaTotal = UsuarioDAO.obtenerUsuarios(true);
            List<Usuario> clientes = UsuarioDAO.obtenerUsuarios(false);

            if (clientes != null && listaTotal != null) {
                listaTotal.addAll(clientes);
            }

            List<Usuario> usuariosActivos = listaTotal.stream()
                    .filter(Usuario::getEsActivo)
                    .collect(Collectors.toList());

            tblUsuarios.setItems(FXCollections.observableArrayList(usuariosActivos));

        } catch (SQLException ex) {
            Alerta.mostrarAlertaError(
                    "Ocurrió un  con la base de datos",
                    "No se pudo recuperar la lista de usuarios. Inténtalo de nuevo más tarde."
            );
            ex.printStackTrace();
        }
    }

    private void abrirFormularioUsuario(boolean esEdicion) {
        if (esEdicion) {
            int indiceSeleccionado = tblUsuarios.getSelectionModel().getSelectedIndex();
            if (indiceSeleccionado < 0) {
                Alerta.mostrarAlertaAdvertencia("Selección requerida", "Por favor, seleccione un usuario de la tabla para poder editarlo.");
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
            Alerta.mostrarAlertaError(
                    "Ocurrió un error al cargar la ventana de formulario",
                    "No se pudo recuperar la lista de usuarios. Inténtalo de nuevo más tarde."
            );
        }
    }

    private void editarUsuario() {
        abrirFormularioUsuario(true);
    }

    private void eliminarUsuario() {

    }
}
