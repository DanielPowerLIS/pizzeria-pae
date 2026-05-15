/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author jdani
 */
public class PedidosViewController implements Initializable {

    @FXML
    private ComboBox<?> cmbBuscarUsuario;
    @FXML
    private DatePicker dpBuscarFecha;
    @FXML
    private ComboBox<?> cmbBuscarEstatus;
    @FXML
    private Button btnBuscarPedido;
    @FXML
    private Button btnExportarCSV;
    @FXML
    private Button btnExportarPDF;
    @FXML
    private TableView<?> tblPedidos;
    @FXML
    private TableColumn<?, ?> colIdPedido;
    @FXML
    private TableColumn<?, ?> colFechaPedido;
    @FXML
    private TableColumn<?, ?> colClientePedido;
    @FXML
    private TableColumn<?, ?> colTotalPedido;
    @FXML
    private TableColumn<?, ?> colEstatusPedido;
    @FXML
    private Button btnCambiarEstatus;
    @FXML
    private Button btnEditarPedido;
    @FXML
    private Button btnNuevoPedido;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
