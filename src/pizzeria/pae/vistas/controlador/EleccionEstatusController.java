package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pizzeria.pae.modelo.beans.Pedido;
import pizzeria.pae.modelo.dao.PedidoDAO;
import pizzeria.pae.modelo.dao.ProductoDAO;
import pizzeria.pae.utilidades.Alerta;

/**
 * FXML Controller class
 *
 * @author jdani
 */
public class EleccionEstatusController implements Initializable {
    private Pedido pedido;
    @FXML
    private Label lblFolio;
    @FXML
    private ComboBox<String> cmbEstatus;
    @FXML
    private Button btnGuardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void cargarInformacion() {
        cmbEstatus.getItems().addAll(
            "CANCELADO",
            "APROBADO"
        );
    }

    public void inicializarInformacion(Pedido pedidoSeleccionado) {
        this.pedido = pedidoSeleccionado;
        cargarInformacion();
        lblFolio.setText(String.valueOf(pedido.getIdPedido()));
    }

    
    @FXML
    private void clickGuardarEstatus(ActionEvent event) {
        String estatusSeleccionado = cmbEstatus.getSelectionModel().getSelectedItem();
        if(estatusSeleccionado == null){
            Alerta.mostrarAlertaAdvertencia("Estatus no seleccionado",
                    "Por favor seleccione un estatus");
            return;
        }
        
        try{
        
            if(PedidoDAO.actualizarEstatusPedido(pedido.getIdPedido(), estatusSeleccionado)){
                
                if (estatusSeleccionado.equals("APROBADO")) {
                    ProductoDAO.utilizarProducto(pedido.getDetallePedido());
                }
                
                Alerta.mostrarAlertaInformacion("Pedido actualizado",
                        "Su pedido ha cambiado de estado exitosamente");
                
                Stage stage = (Stage) btnGuardar.getScene().getWindow();
                stage.close();
            }else{
                Alerta.mostrarAlertaAdvertencia(
                        "Sin cambios",
                        "No fue posible actualizar el estatus."
                );                
            }
            
        }catch(SQLException ex){
            Alerta.mostrarAlertaError(
                "Error de base de datos",
                ex.getMessage()
            );
        }
        
        
    }
}
