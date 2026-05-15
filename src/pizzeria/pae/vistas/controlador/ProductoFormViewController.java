package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jdani
 */
public class ProductoFormViewController implements Initializable {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombreProducto;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtRestricciones;
    @FXML
    private Button btnSeleccionarFoto;
    @FXML
    private Label lblRutaFoto;
    @FXML
    private Button btnCancelarProd;
    @FXML
    private Button btnGuardarProd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
