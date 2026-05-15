package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author jdani
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
