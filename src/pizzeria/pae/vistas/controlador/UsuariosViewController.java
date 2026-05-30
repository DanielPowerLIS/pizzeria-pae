package pizzeria.pae.vistas.controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

        tgFiltroUsuarios.selectedToggleProperty().addListener((observable, oldValue, newValue) -> cargarDatosTabla());
        txtBuscarUsuario.textProperty().addListener((observable, oldValue, newValue) -> cargarDatosTabla());
    }

    private void configurarTabla() {
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreCompleto()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colDireccion.setCellValueFactory(cellData -> {
            if (cellData.getValue().getDireccion() != null) {
                return new SimpleStringProperty(cellData.getValue().getDireccion().getCalle() + " " + cellData.getValue().getDireccion().getNumero());
            } else {
                return new SimpleStringProperty("Sin dirección");
            }
        });
    }

    private void cargarDatosTabla() {
        try {
            List<Usuario> listaTotal = new ArrayList<>();

            if (rdTodos.isSelected()) {
                List<Usuario> empleados = UsuarioDAO.obtenerUsuarios(true);
                List<Usuario> clientes = UsuarioDAO.obtenerUsuarios(false);
                if (empleados != null) {
                    listaTotal.addAll(empleados);
                }
                if (clientes != null) {
                    listaTotal.addAll(clientes);
                }
            } else if (rdEmpleados.isSelected()) {
                List<Usuario> empleados = UsuarioDAO.obtenerUsuarios(true);
                if (empleados != null) {
                    listaTotal.addAll(empleados);
                }
            } else if (rdClientes.isSelected()) {
                List<Usuario> clientes = UsuarioDAO.obtenerUsuarios(false);
                if (clientes != null) {
                    listaTotal.addAll(clientes);
                }
            }

            String textoBusqueda = txtBuscarUsuario.getText() != null ? txtBuscarUsuario.getText().toLowerCase() : "";

            List<Usuario> usuariosFiltrados = listaTotal.stream()
                    .filter(usr -> {
                        if (textoBusqueda.isEmpty()) {
                            return true;
                        }
                        boolean coincideNombre = usr.getNombreCompleto() != null && usr.getNombreCompleto().toLowerCase().contains(textoBusqueda);
                        boolean coincideTel = usr.getTelefono() != null && usr.getTelefono().contains(textoBusqueda);
                        boolean coincideDir = usr.getDireccion() != null && usr.getDireccion().toString().toLowerCase().contains(textoBusqueda);
                        return coincideNombre || coincideTel || coincideDir;
                    })
                    .collect(Collectors.toList());

            tblUsuarios.setItems(FXCollections.observableArrayList(usuariosFiltrados));

        } catch (SQLException ex) {
            Alerta.mostrarAlertaError("Error de base de datos", "No se pudo recuperar la lista de usuarios.");
        }
    }

    private void abrirFormularioUsuario(boolean esEdicion) {
        Usuario usuarioSeleccionado = null;

        if (esEdicion) {
            usuarioSeleccionado = tblUsuarios.getSelectionModel().getSelectedItem();
            if (usuarioSeleccionado == null) {
                Alerta.mostrarAlertaAdvertencia("Selección requerida", "Por favor, seleccione un usuario.");
                return;
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/pae/vistas/fxml/UsuarioFormView.fxml"));
            Parent root = loader.load();

            UsuarioFormViewController controlador = loader.getController();
            if (esEdicion) {
                controlador.cargarUsuario(usuarioSeleccionado);
            }

            Stage stage = new Stage();
            stage.setTitle(esEdicion ? "Editar Usuario" : "Nuevo Usuario");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnAgregarUsuario.getScene().getWindow());
            stage.showAndWait();

            cargarDatosTabla();

        } catch (IOException e) {
            Alerta.mostrarAlertaError("Error", "No se pudo abrir el formulario.");
        }
    }

    private void eliminarUsuario() {
        Usuario usuarioSeleccionado = tblUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado == null) {
            Alerta.mostrarAlertaAdvertencia("Selección requerida", "Seleccione un usuario para eliminar.");
            return;
        }

        if (usuarioSeleccionado.getHaPedido()) {
            Alerta.mostrarAlertaError("Acción denegada", "No es posible eliminar a un cliente con historial de pedidos.");
            return;
        }

        if (usuarioSeleccionado.getEsActivo()) {
            Alerta.mostrarAlertaError("Acción denegada", "No es posible eliminar a este usuario porque tiene la sesión activa.");
            return;
        }

        try {
            if (UsuarioDAO.eliminarUsuario(usuarioSeleccionado.getIdUsuario())) {
                Alerta.mostrarAlertaInformacion("Usuario Eliminado", "El usuario fue dado de baja exitosamente.");
                cargarDatosTabla();
            } else {
                Alerta.mostrarAlertaError("Error", "No se pudo eliminar al usuario.");
            }
        } catch (SQLException ex) {
            Alerta.mostrarAlertaError("Error de Base de Datos", "Ocurrió un problema al intentar procesar la baja.");
        }
    }
}
