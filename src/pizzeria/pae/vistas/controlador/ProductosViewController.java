package pizzeria.pae.vistas.controlador;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import pizzeria.pae.modelo.beans.Producto;

/**
 * FXML Controller class
 *
 * @author jdani
 */
public class ProductosViewController implements Initializable {

    @FXML
    private Button btnBuscarProducto;
    @FXML
    private TextField tfBuscador;
    @FXML
    private Button s;
    @FXML
    private RadioButton rdPorNombre;
    @FXML
    private ToggleGroup rdBuscador;
    @FXML
    private RadioButton rdPorCodigo;
    @FXML
    private TableView<Producto> tvProductos;
    @FXML
    private TableColumn<Producto, String> tcCodigo;
    @FXML
    private TableColumn<Producto, String> tcNombre;
    @FXML
    private TableColumn<Producto, BigDecimal> tcPrecio;
    @FXML
    private TableColumn<Producto, Integer> tcExistencia;
    @FXML
    private TableColumn<Producto, String> tcRestricciones;

    private ObservableList<Producto> productosObservables;
    @FXML
    private RadioButton rdConsumo;
    @FXML
    private ToggleGroup rdTipoProducto;
    @FXML
    private RadioButton rdInsumo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
    }    
    
    private void configurarTabla(){
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tcExistencia.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcRestricciones.setCellValueFactory(new PropertyValueFactory<>("restricciones"));
    }

    @FXML
    private void clickBuscar(ActionEvent event) {
        if(tfBuscador.getText().isEmpty()){
            if(rdConsumo.isSelected()){
                return;
            }
            
            return;
            
        }
        
        
        if(rdConsumo.isSelected()){
            return;
        }
        
        return;
        
    }

    @FXML
    private void clickAgregarProducto(ActionEvent event) {
    }

    @FXML
    private void clickEditarProuducto(ActionEvent event) {
    }

    @FXML
    private void clickEliminarProducto(ActionEvent event) {
    }

    @FXML
    private void clickGenerarPDF(ActionEvent event) {
    }
    
    private void buscarConsumos(){
        
    }
    
    private void buscarInsumos(){
        
    }
}
