package pizzeria.pae.vistas.controlador;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pizzeria.pae.modelo.beans.Pedido;
import pizzeria.pae.modelo.dao.PedidoDAO;
import pizzeria.pae.utilidades.Alerta;
import pizzeria.pae.utilidades.ExportadorCSV;
import pizzeria.pae.utilidades.ExportadorPDF;

/**
 * @author Adair Alejandro Martinez Alejo
 * @author Gabriel Hernández Martínez
 * @author Víctor Hugo Vásquez Martínez
 * @author Juan Daniel Pérez Santiago
 */
public class PedidosViewController implements Initializable {

    @FXML
    private DatePicker dpBuscarFecha;
    @FXML
    private ComboBox<?> cmbBuscarEstatus;
    @FXML
    private Button btnCambiarEstatus;
    @FXML
    private Button btnEditarPedido;
    @FXML
    private Button btnNuevoPedido;
    @FXML
    private TextField txtBuscarUsuario;
    @FXML
    private Button btnBuscarUsuario;
    @FXML
    private MenuButton btnMenuExportar;
    @FXML
    private MenuItem menuItemExportarCSV;
    @FXML
    private MenuItem menuItemExportarPDF;
    @FXML
    private TableView<Pedido> tvPedidos;
    @FXML
    private TableColumn<Pedido, String> tcFolio;
    @FXML
    private TableColumn<Pedido, Date> tcFecha;
    @FXML
    private TableColumn<Pedido, String> tcCliente;
    @FXML
    private TableColumn<Pedido, BigDecimal> tcTotalPedido;
    @FXML
    private TableColumn<Pedido, String> tcEstatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacion();
    }

    
    @FXML
    private void clickNuevoPedido(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/pae/vistas/fxml/PedidoFormView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Registrar Pedido");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnNuevoPedido.getScene().getWindow());

            stage.showAndWait();
            
            cargarInformacion();

        } catch (IOException e) {
            e.printStackTrace();
            Alerta.mostrarAlertaError("Error de carga", "No se pudo abrir la ventana del formulario de pedido.");
        }
    }

    @FXML
    private void clickEditarPedido(ActionEvent event) {
        Pedido pedidoSeleccionado = tvPedidos.getSelectionModel().getSelectedItem();
        
        if (pedidoSeleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/pae/vistas/fxml/PedidoFormView.fxml"));
                Parent root = loader.load();

                PedidoFormViewController controlador = loader.getController();
                controlador.asignarPedido(pedidoSeleccionado);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.setTitle("Editar pedido");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(btnNuevoPedido.getScene().getWindow());

                stage.showAndWait();
                
                cargarInformacion();
                
            } catch (IOException e) {
                e.printStackTrace();
                Alerta.mostrarAlertaError("Error de carga", "No se pudo abrir la ventana del formulario de pedido.");
            }
        } else {
            Alerta.mostrarAlertaAdvertencia("Selección requerida", "Por favor, seleccione un pedido de la tabla para poder editarlo.");
        }
    }

    @FXML
    private void clickCambiarEstatus(ActionEvent event) {
        Pedido pedidoSeleccionado = tvPedidos.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado == null) {
            Alerta.mostrarAlertaAdvertencia("Selección requerida", "Por favor, seleccione un pedido de la "
                                                                    + "tabla para poder cambiar el estatus.");

            return;
        }
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/pizzeria/pae/vistas/fxml/EleccionEstatus.fxml"
                    )
            );

            Parent root = loader.load();
            EleccionEstatusController controlador = loader.getController();
            controlador.inicializarInformacion(pedidoSeleccionado);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cambiar Estatus");
      
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
            cargarInformacion();
        } catch (IOException ex) {

            ex.printStackTrace();

            Alerta.mostrarAlertaError("Error de conexion", "poner algo aqui alv");
        }
        
    }

    private void configurarTabla() {
        tcFolio.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tcCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        tcTotalPedido.setCellValueFactory(new PropertyValueFactory<>("total"));
        tcEstatus.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }
    
    
    private void cargarInformacion() {

        try {

            tvPedidos.getItems().clear();

            tvPedidos.getItems().addAll(
                    PedidoDAO.obtenerPedidos()
            );

        } catch (SQLException ex) {

            ex.printStackTrace();

            Alerta.mostrarAlertaError(
                    "Error de conexión",
                    "No se pudo cargar la información."
            );
        }
    }

    @FXML
    private void clickExportarCSV(ActionEvent event) {

        FileChooser chooser = new FileChooser();

        chooser.setTitle("Guardar reporte CSV");

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Archivos CSV (*.csv)",
                        "*.csv"
                )
        );

        chooser.setInitialFileName("ReportePedidos.csv");

        File archivo = chooser.showSaveDialog(
                btnMenuExportar.getScene().getWindow()
        );

        if (archivo == null) {
            return;
        }

        try {

            ExportadorCSV exportador = new ExportadorCSV();

            exportador.exportar(
                    PedidoDAO.obtenerPedidos(),
                    archivo.getAbsolutePath()
            );

            Alerta.mostrarAlertaInformacion(
                    "Exportación exitosa",
                    "El CSV se generó correctamente."
            );

        } catch (SQLException ex) {

            ex.printStackTrace();

            Alerta.mostrarAlertaError(
                    "Error",
                    "No fue posible generar el CSV."
            );
        }   
    }

    @FXML
    private void clickExportarPDF(ActionEvent event) {
        FileChooser chooser = new FileChooser();

        chooser.setTitle("Guardar reporte PDF");

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Archivos PDF (*.pdf)",
                        "*.pdf"
                )
        );

        chooser.setInitialFileName("ReportePedidos.pdf");

        File archivo = chooser.showSaveDialog(
                btnMenuExportar.getScene().getWindow()
        );

        if (archivo == null) {
            return;
        }

        try {

            ExportadorPDF exportador = new ExportadorPDF();

            exportador.exportar(
                    PedidoDAO.obtenerPedidos(),
                    archivo.getAbsolutePath()
            );

            Alerta.mostrarAlertaInformacion(
                    "Exportación exitosa",
                    "El PDF se generó correctamente."
            );

        } catch (SQLException ex) {

            ex.printStackTrace();

            Alerta.mostrarAlertaError(
                    "Error",
                    "No fue posible generar el PDF."
            );
        }

    }
            
}
