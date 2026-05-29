package pizzeria.pae.vistas.controlador;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import pizzeria.pae.modelo.beans.Pedido;
import pizzeria.pae.utilidades.UtilidadesUI;

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
    private TableView<Pedido> tblPedidos;
    @FXML
    private TableColumn<Pedido, Integer> colIdPedido;
    @FXML
    private TableColumn<Pedido, Date> colFechaPedido;
    @FXML
    private TableColumn<Pedido, String> colClientePedido;
    @FXML
    private TableColumn<Pedido, BigDecimal> colTotalPedido;
    @FXML
    private TableColumn<Pedido, String> colEstatusPedido;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnNuevoPedido.setOnAction(event -> abrirFormularioPedido(false));
        btnEditarPedido.setOnAction(event -> abrirFormularioPedido(true));
    }

    private void abrirFormularioPedido(boolean esEdicion) {
        if (esEdicion) {
            int indiceSeleccionado = tblPedidos.getSelectionModel().getSelectedIndex();
            if (indiceSeleccionado < 0) {
                UtilidadesUI.mostrarAlertaSimple("Selección requerida", "Por favor, seleccione un pedido de la tabla para poder editarlo.", Alert.AlertType.WARNING);
                return;
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/pae/vistas/fxml/PedidoFormView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(esEdicion ? "Editar Pedido" : "Nuevo Pedido");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnNuevoPedido.getScene().getWindow());

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            UtilidadesUI.mostrarAlertaSimple("Error de carga", "No se pudo abrir la ventana del formulario de pedido.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clickNuevoPedido(ActionEvent event) {
    }

    @FXML
    private void clickEditarPedido(ActionEvent event) {
    }

    @FXML
    private void clickCambiarEstatus(ActionEvent event) {
    }
}
