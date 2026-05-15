package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jdani
 */
public class ProductosViewController implements Initializable {

    @FXML
    private TextField txtBuscarProducto;
    @FXML
    private Button btnBuscarProducto;
    @FXML
    private Button btnGenerarReportePDF;
    @FXML
    private TableView<?> tblProductos;
    @FXML
    private TableColumn<?, ?> colCodigo;
    @FXML
    private TableColumn<?, ?> colNombreProd;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private TableColumn<?, ?> colCantidad;
    @FXML
    private TableColumn<?, ?> colRestricciones;
    @FXML
    private Button btnAgregarProducto;
    @FXML
    private Button btnEditarProducto;
    @FXML
    private Button btnEliminarProducto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
