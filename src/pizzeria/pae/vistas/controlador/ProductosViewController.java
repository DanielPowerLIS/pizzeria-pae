package pizzeria.pae.vistas.controlador;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pizzeria.pae.modelo.beans.Producto;
import pizzeria.pae.modelo.dao.ProductoDAO;
import pizzeria.pae.utilidades.Alerta;

/**
 * FXML Controller class
 *
 * @author jdani
 */
public class ProductosViewController implements Initializable {

    @FXML
    private TextField tfBuscador;
    @FXML
    private RadioButton rdPorNombre;
    @FXML
    private RadioButton rdConsumo;
    @FXML
    private ToggleGroup rdBuscador;
    @FXML
    private RadioButton rdPorCodigo;
    @FXML
    private ToggleGroup rdTipoProducto;
    @FXML
    private RadioButton rdInsumo;
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
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        iniciarVista();
        
    }    
    
    private void configurarTabla(){
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tcExistencia.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcRestricciones.setCellValueFactory(new PropertyValueFactory<>("restricciones"));
    }
    
    private void iniciarVista(){
        rdConsumo.setSelected(true);
        cargarInfomracionGeneral(false);
    }
    
    private void cargarInfomracionGeneral(Boolean insumo){
        try{
            List<Producto> productos = ProductoDAO.obtenerProductos(insumo);
            productosObservables = FXCollections.observableArrayList(productos);
            tvProductos.setItems(productosObservables);
        }catch(SQLException e){
            e.printStackTrace();
            Alerta.mostrarAlertaError("Error de conexión.", "Lo sentimos no se pudo cargar la información.");
        }
    }
    
    private void cargarInformacionNombre(String nombre, Boolean insumo){
        try{
            List<Producto> productos = ProductoDAO.buscarProductoPorNombre(nombre, insumo);
            if(productos != null){
                productosObservables = FXCollections.observableArrayList(productos);
                tvProductos.setItems(productosObservables);
            }else{
                Alerta.mostrarAlertaAdvertencia("No se encontró producto", "No se encontraron coincidencias.");
            }
        }catch(SQLException e){
            e.printStackTrace();
            Alerta.mostrarAlertaError("Error de conexión.", "Lo sentimos no se pudo cargar la información.");
        }
    }
    
     private void cargarInformacionCodigo(String codigo, Boolean insumo){
        try{
            List<Producto> productos = ProductoDAO.buscarProductoPorCodigo(codigo, insumo);
            if(productos != null){
                productosObservables = FXCollections.observableArrayList(productos);
                tvProductos.setItems(productosObservables);
            }else{
                Alerta.mostrarAlertaAdvertencia("No se encontró producto", "No se encontraron coincidencias.");
            }
        }catch(SQLException e){
            e.printStackTrace();
            Alerta.mostrarAlertaError("Error de conexión.", "Lo sentimos no se pudo cargar la información.");
        }
    }
    
    private Boolean estaVacio(String buscador){
        if(buscador.isEmpty() || buscador.length() < 1){
            return true;
        }
        return false;
    }

    @FXML
    private void clickBuscar(ActionEvent event) {
        String buscador = tfBuscador.getText();
        
        if(estaVacio(buscador)){
            if(rdConsumo.isSelected()){
                cargarInfomracionGeneral(false);
                return;
            }
            cargarInfomracionGeneral(true);
            return;
            
        }
        
        if(rdPorNombre.isSelected()){
           if(rdConsumo.isSelected()){
               cargarInformacionNombre(buscador, false);
               return;
           } 
            cargarInformacionNombre(buscador, true);
           return;
        }
        
        if(rdConsumo.isSelected()){
            cargarInformacionCodigo(buscador, false);
            return;
        }
        cargarInformacionCodigo(buscador, true);
        return;
        
    }

    @FXML
    private void clickAgregarProducto(ActionEvent event) {
        try{
            Parent vista = FXMLLoader.load(getClass().getResource("/pizzeria/pae/vistas/fxml/ProductoFormView.fxml"));
            Scene escena = new Scene(vista);
            
            Stage ventana = new Stage();
            ventana.setScene(escena);
            ventana.setTitle("Formulario de producto.");
            ventana.initModality(Modality.APPLICATION_MODAL);
            ventana.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
            Alerta.mostrarAlertaError("Error al cargar", "No se pudo cargar la ventana.");
        }
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
}
