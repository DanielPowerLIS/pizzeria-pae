package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import pizzeria.pae.modelo.beans.Producto;
import pizzeria.pae.modelo.dao.ProductoDAO;

/**
 * FXML Controller class
 *
 * @author jdani
 */
public class ValidacionInventarioViewController implements Initializable {
    
    
    @FXML
    private Button btnGuardarValidacion;
    @FXML
    private TableView<Producto> tvValidacion;
    @FXML
    private TableColumn<Producto, String> tcCodigo;
    @FXML
    private TableColumn<Producto, String> tcProducto;
    @FXML
    private TableColumn<Producto, Integer> tcCantidadSistema;
    @FXML
    private TableColumn<Producto, Integer> tcCantidadFisica;
    @FXML
    private TableColumn<Producto, String> tcDiferencia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacion();
    }    
    
    
    public void iniciarVista() {
        
    }
    private void configurarTabla() {
        tvValidacion.setEditable(true);
        
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tcProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcCantidadSistema.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcCantidadFisica.setCellValueFactory(new PropertyValueFactory<>("cantidadFisica"));
        tcDiferencia.setCellValueFactory(cellData -> {

            Producto producto = cellData.getValue();

            Integer cantidadFisica = producto.getCantidadFisica();

            if (cantidadFisica == null) {
                return new SimpleStringProperty("");
            }

            int diferencia = cantidadFisica - producto.getCantidad();

            String mensaje;

            if (diferencia > 0) {

                mensaje = "Sobran " + diferencia;

            } else if (diferencia < 0) {

                mensaje = "Faltan " + Math.abs(diferencia);

            } else {

                mensaje = "Sin diferencia";
            }

            return new SimpleStringProperty(mensaje);
        });
        
        tcCantidadFisica.setCellFactory(
            TextFieldTableCell.forTableColumn(
                    new IntegerStringConverter()));
        
        tcCantidadFisica.setOnEditCommit(event -> {

            Producto producto = event.getRowValue();

            Integer cantidadFisica = event.getNewValue();

            producto.setCantidadFisica(cantidadFisica);

            tvValidacion.refresh();
        });
        
    }
    
    private void cargarInformacion() {
        try{
            List<Producto> productosBDInsumo = ProductoDAO.obtenerProductos(true);
            List<Producto> productosBDConsumo = ProductoDAO.obtenerProductos(false);
            productosBDInsumo.addAll(productosBDConsumo);
            
            tvValidacion.getItems().setAll(productosBDInsumo);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    
    }
}
