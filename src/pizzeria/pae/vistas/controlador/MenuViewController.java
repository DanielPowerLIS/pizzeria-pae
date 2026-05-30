package pizzeria.pae.vistas.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Controlador del meú principal que gestiona la navegación entre las diferentes
 * vistas
 *
 */
public class MenuViewController implements Initializable {

    @FXML
    private Button btnModuloUsuarios;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnProductos;
    @FXML
    private Button btnValidacion;
    @FXML
    private Button btnPedidos;
    @FXML
    private Button btnAcercaDe;
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblRol;

    @FXML
    private StackPane panelCentral;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnModuloUsuarios.setOnAction(event -> cargarVista("/pizzeria/pae/vistas/fxml/UsuariosView.fxml"));
        btnProductos.setOnAction(event -> cargarVista("/pizzeria/pae/vistas/fxml/ProductosView.fxml"));
        btnValidacion.setOnAction(event -> cargarVista("/pizzeria/pae/vistas/fxml/ValidacionInventarioView.fxml"));
        btnPedidos.setOnAction(event -> cargarVista("/pizzeria/pae/vistas/fxml/PedidosView.fxml"));

        btnAcercaDe.setOnAction(event -> mostrarAcercaDe());
        btnCerrarSesion.setOnAction(event -> cerrarSesion());

        cargarVista("/pizzeria/pae/vistas/fxml/PedidosView.fxml");
    }

    /**
     * Carga un archivo FXML y lo coloca en el panel central.
     *
     * @param rutaFxml La ruta relativa al archivo FXML que se desea mostrar.
     */
    private void cargarVista(String rutaFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
            Parent nuevaVista = loader.load();

            panelCentral.getChildren().setAll(nuevaVista);

        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (NullPointerException e) {
            System.err.println("Archivo FXML no encontrado en la ruta: " + rutaFxml);
        }
    }

    /**
     * Muestra la ventana de "Acerca de".
     */
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
            e.getMessage();
        }
    }

    /**
     * Cierra la sesión actual y regresa a la pantalla de Login.
     */
    private void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/pae/vistas/fxml/LoginView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Italia Pizza");
            stage.centerOnScreen();

        } catch (IOException e) {
            e.getMessage();
        }
    }

}
