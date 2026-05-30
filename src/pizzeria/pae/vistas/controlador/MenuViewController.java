package pizzeria.pae.vistas.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pizzeria.pae.modelo.beans.Usuario;
import pizzeria.pae.utilidades.SesionUsuario;

public class MenuViewController implements Initializable {

    @FXML
    private Button btnModuloUsuarios;
    @FXML
    private Button btnProductos;
    @FXML
    private Button btnValidacion;
    @FXML
    private Button btnPedidos;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnAcercaDe;

    @FXML
    private Label lbUsuario;
    @FXML
    private Label lblRol;
    @FXML
    private StackPane panelCentral;

    // --- NUEVAS VARIABLES PARA OCULTAR ---
    @FXML
    private Separator sepAdmin;
    @FXML
    private Label lblAdmin;
    @FXML
    private Separator sepInventario;
    @FXML
    private Label lblInventario;
    @FXML
    private Separator sepPedidos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnModuloUsuarios.setOnAction(event -> cargarVista("/pizzeria/pae/vistas/fxml/UsuariosView.fxml"));
        btnProductos.setOnAction(event -> cargarVista("/pizzeria/pae/vistas/fxml/ProductosView.fxml"));
        btnValidacion.setOnAction(event -> cargarVista("/pizzeria/pae/vistas/fxml/ValidacionInventarioView.fxml"));
        btnPedidos.setOnAction(event -> cargarVista("/pizzeria/pae/vistas/fxml/PedidosView.fxml"));
        btnAcercaDe.setOnAction(event -> mostrarAcercaDe());
        btnCerrarSesion.setOnAction(event -> cerrarSesion());

        Usuario usr = SesionUsuario.getUsuarioActual();
        if (usr != null) {
            String identificador = (usr.getNombreUsuario() != null && !usr.getNombreUsuario().isEmpty())
                    ? usr.getNombreUsuario() : usr.getNombre();

            lbUsuario.setText("Sesión: " + identificador);
            lblRol.setText("Rol: " + usr.getRol());

            // Restricción para todos los que NO son Administradores (Cajeros)
            if (!"Administrador".equals(usr.getRol())) {

                // Ocultar Módulo Usuarios y su etiqueta/separador
                sepAdmin.setVisible(false);
                sepAdmin.setManaged(false);
                lblAdmin.setVisible(false);
                lblAdmin.setManaged(false);
                btnModuloUsuarios.setVisible(false);
                btnModuloUsuarios.setManaged(false);

                // Ocultar Módulo Inventarios y su etiqueta/separador
                sepInventario.setVisible(false);
                sepInventario.setManaged(false);
                lblInventario.setVisible(false);
                lblInventario.setManaged(false);
                btnProductos.setVisible(false);
                btnProductos.setManaged(false);
                btnValidacion.setVisible(false);
                btnValidacion.setManaged(false);

                // Ocultar el separador superior de Pedidos para que quede pegado arriba
                sepPedidos.setVisible(false);
                sepPedidos.setManaged(false);
            }
        }

        cargarVista("/pizzeria/pae/vistas/fxml/PedidosView.fxml");
    }

    private void cargarVista(String rutaFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
            Parent nuevaVista = loader.load();
            panelCentral.getChildren().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAcercaDe() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/pae/vistas/fxml/AcercaDeView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Acerca de este proyecto");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initOwner(btnAcercaDe.getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cerrarSesion() {
        try {
            SesionUsuario.limpiarSesion();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/pae/vistas/fxml/LoginView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Italia Pizza");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
