package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author jdani
 */
public class PedidoFormViewController implements Initializable {

    @FXML
    private ComboBox<?> cmbCliente;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private ComboBox<?> cmbProducto;
    @FXML
    private Spinner<?> spnCantidad;
    @FXML
    private Button btnAgregarAlPedido;
    @FXML
    private Button btnQuitarDelPedido;
    @FXML
    private TableView<?> tblDetallePedido;
    @FXML
    private TableColumn<?, ?> colDetalleCod;
    @FXML
    private TableColumn<?, ?> colDetalleProd;
    @FXML
    private TableColumn<?, ?> colDetalleCant;
    @FXML
    private TableColumn<?, ?> colDetallePrecio;
    @FXML
    private TableColumn<?, ?> colDetalleSubtotal;
    @FXML
    private Label lblTotalPago;
    @FXML
    private Button btnCancelarPed;
    @FXML
    private Button btnGuardarPed;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
