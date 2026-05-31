package pizzeria.pae.vistas.controlador;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pizzeria.pae.modelo.beans.Producto;
import pizzeria.pae.modelo.dao.ProductoDAO;
import pizzeria.pae.utilidades.Alerta;
import pizzeria.pae.utilidades.ConfigurarSoloNumeros;

/**
 * @author Adair Alejandro Martinez Alejo
 * @author Gabriel Hernández Martínez
 * @author Víctor Hugo Vásquez Martínez
 * @author Juan Daniel Pérez Santiago
 */
public class ProductoFormViewController implements Initializable {
    
    private Producto productoEditar = null;
    private Producto productoNuevo = null;
    
    @FXML
    private TextField tfCodigo;
    @FXML
    private TextField tfNombreProducto;
    @FXML
    private TextArea tfDescripcion;
    @FXML
    private TextField tfPrecio;
    @FXML
    private TextField tfCantidad;
    @FXML
    private TextField tfRestricciones;
    @FXML
    private ImageView ivFoto;
    
    private File archivoFoto;
    
    @FXML
    private RadioButton rdConsumo;
    @FXML
    private ToggleGroup rdTipo;
    @FXML
    private RadioButton rdInsumo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarFormulario();
    }
    
    private void configurarFormulario(){
        ConfigurarSoloNumeros.configurarSoloNumeros(tfCantidad);
        ConfigurarSoloNumeros.configurarPrecio(tfPrecio);
        tfRestricciones.setText("Ninguna");
    }
    
    public void asignarProducto(Producto producto){
        this.productoEditar = producto;
        cargarInformacionProducto();
        tfCodigo.setEditable(false);
        
    }
    
    private void cargarInformacionProducto(){
        tfCodigo.setText(productoEditar.getCodigo());
        tfNombreProducto.setText(productoEditar.getNombre());
        tfDescripcion.setText(productoEditar.getDescripcion());
        tfCantidad.setText(String.valueOf(productoEditar.getCantidad()));
        tfRestricciones.setText(productoEditar.getRestricciones());
        tfPrecio.setText(String.valueOf(productoEditar.getPrecio()));
        
        if(productoEditar.getEsInsumo()) {
            rdInsumo.setSelected(true);
        } else {
            rdConsumo.setSelected(true);
        }
        
        if(productoEditar.getFoto() != null){
            ByteArrayInputStream bais = new ByteArrayInputStream(productoEditar.getFoto());
            Image imagenBD = new Image(bais);
            ivFoto.setImage(imagenBD);
        }
    }
    
    private void modificarProducto(){
        if(datosValidos()){
            try{
                productoEditar.setNombre(tfNombreProducto.getText());
                productoEditar.setDescripcion(tfDescripcion.getText());
                productoEditar.setCantidad(Integer.parseInt(tfCantidad.getText()));
                productoEditar.setPrecio(new BigDecimal(tfPrecio.getText()));
                productoEditar.setRestricciones(tfRestricciones.getText());
                
                productoEditar.setEsInsumo(rdInsumo.isSelected());
                
                if(archivoFoto != null){
                    byte[] fotoBytes = Files.readAllBytes(archivoFoto.toPath());
                    productoEditar.setFoto(fotoBytes);
                }
                
                Boolean modificacionExitosa = ProductoDAO.actualizarProducto(productoEditar);
                
                if (modificacionExitosa) {
                    Alerta.mostrarAlertaInformacion("Modificación exitosa", "El producto se ha modificado correctamente.");
                    cerrarVentana();
                } else {
                    Alerta.mostrarAlertaError("Error", "No se pudo guardar el producto en la base de datos.");
                }
                
            }catch(IOException e){
                e.printStackTrace();
                Alerta.mostrarAlertaError("Error de base de datos", "Error al intentar guardar la información.");
            }catch(SQLException ex){
                ex.printStackTrace();
                Alerta.mostrarAlertaError("Error de base de datos", "Error al intentar guardar la información.");
            }
        }else{
            Alerta.mostrarAlertaAdvertencia("Campos incompletos", "Por favor, llena todos los campos marcados con asterisco (*).");
        }
        
    }

    @FXML
    private void clickSeleccionarImagen(ActionEvent event) {
        abrirSeleccionadorFoto();        
    }

    @FXML
    private void clickGuardar(ActionEvent event) {
        if(productoEditar != null){
            modificarProducto();
        }else{
           registrarProducto(); 
        }
    }
    
    private void registrarProducto(){
        if (datosValidos()) {
            try {
                Producto productoNuevo = new Producto();
                
                productoNuevo.setCodigo(tfCodigo.getText());
                productoNuevo.setNombre(tfNombreProducto.getText());
                productoNuevo.setDescripcion(tfDescripcion.getText());
                productoNuevo.setPrecio(new BigDecimal(tfPrecio.getText()));
                productoNuevo.setCantidad(Integer.parseInt(tfCantidad.getText()));
                productoNuevo.setRestricciones(tfRestricciones.getText());
                productoNuevo.setEsInsumo(rdInsumo.isSelected());
                
                byte[] fotoBytes = Files.readAllBytes(archivoFoto.toPath());
                productoNuevo.setFoto(fotoBytes);


                Boolean guardadoExitoso = ProductoDAO.agregarProducto(productoNuevo);
                this.productoNuevo = productoNuevo;
                
                if (guardadoExitoso) {
                    Alerta.mostrarAlertaInformacion("Guardado exitoso", "El producto se ha guardado correctamente.");
                    cerrarVentana();
                } else {
                    Alerta.mostrarAlertaError("Error", "No se pudo guardar el producto en la base de datos.");
                }

            } catch (IOException e) {
                e.printStackTrace();
                Alerta.mostrarAlertaError("Error de imagen", "Hubo un problema al leer el archivo de la foto.");
            } catch (SQLException e) {
                e.printStackTrace();
                Alerta.mostrarAlertaError("Error de base de datos", "Error al intentar guardar la información.");
            } 
        } else {
            Alerta.mostrarAlertaAdvertencia("Campos incompletos", "Por favor, llena todos los campos marcados con asterisco (*).");
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private void cerrarVentana() {
        Stage stage = (Stage) tfCodigo.getScene().getWindow();
        stage.close();
    }
        
    private Boolean datosValidos(){
        if(tfCodigo.getText().isEmpty() || tfCodigo.getText().length() != 5){
            Alerta.mostrarAlertaAdvertencia("Formato inválido", "El código debe tener 5 caracteres (Números o letras).");
            return false;
        }
        
        if(tfNombreProducto.getText().isEmpty() || tfNombreProducto.getText().length() < 1){
            return false;
        }
        
        if(tfDescripcion.getText().isEmpty() || tfDescripcion.getText().length() < 1){
            return false;
        }
        
        if(tfCantidad.getText().isEmpty() || tfCantidad.getText().length() < 1){
            return false;
        }
        
        if(tfPrecio.getText().isEmpty() || tfPrecio.getText().length() < 1){
            return false;
        }
        
        if(productoEditar == null){
            if(archivoFoto == null){
                return false;
            }
        }
            
        if(!rdConsumo.isSelected() && !rdInsumo.isSelected()){
            return false;
        }
        
        return true;
    }
    
    private void abrirSeleccionadorFoto(){
        FileChooser dialogoSeleccion = new FileChooser();
        dialogoSeleccion.setTitle("Selecciona la foto del producto.");
        String descripcionFormato = "Archivos de imagen (*.png, *.jpg, *jpeg)";
        List<String> formatos = new ArrayList<String>();
        formatos.add("*.png");
        formatos.add("*.jpg");
        formatos.add("*.jpeg");
        FileChooser.ExtensionFilter filtroSeleccion = new FileChooser.ExtensionFilter(descripcionFormato, formatos);
        dialogoSeleccion.getExtensionFilters().add(filtroSeleccion);
        archivoFoto = dialogoSeleccion.showOpenDialog(tfCantidad.getScene().getWindow());
        if(archivoFoto != null){
            mostrarImagen(archivoFoto);
        }
    }
    
    private void mostrarImagen(File foto){
        if( foto != null){
            try{
                BufferedImage buffer = ImageIO.read(foto);
                Image image   = SwingFXUtils.toFXImage(buffer, null);
                ivFoto.setImage(image);
            }catch(IOException e){
                Alerta.mostrarAlertaError("Error al cargar", "Lo sentimo no se pudo cargar la foto");
            }
        }
    }
}
