package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pizzeria.pae.utilidades.UtilidadesUI;

/**
 * @author Adair Alejandro Martinez Alejo
 * @author Gabriel Hernández Martínez
 * @author Víctor Hugo Vásquez Martínez
 * @author Juan Daniel Pérez Santiago
 */
public class PedidoFormViewController implements Initializable {

    @FXML
    private ComboBox<String> cmbCliente;
    @FXML
    private TextField txtFecha;
    @FXML
    private ComboBox<String> cmbProducto;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button btnAgregarAlPedido;
    @FXML
    private Button btnQuitarDelPedido;
    @FXML
    private TableView<Object> tblDetallePedido;
    @FXML
    private TableColumn<Object, String> colDetalleCod;
    @FXML
    private TableColumn<Object, String> colDetalleProd;
    @FXML
    private TableColumn<Object, Integer> colDetalleCant;
    @FXML
    private TableColumn<Object, Double> colDetallePrecio;
    @FXML
    private TableColumn<Object, Double> colDetalleSubtotal;
    @FXML
    private Label lblTotalPago;
    @FXML
    private Button btnCancelarPed;
    @FXML
    private Button btnGuardarPed;

    private ObservableList<Object> listaDetalles;
    private double totalAcumulado = 0.0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaDetalles = FXCollections.observableArrayList();
        tblDetallePedido.setItems(listaDetalles);

        txtFecha.setText(LocalDate.now().toString());
        txtFecha.setEditable(false);
        txtCantidad.setText("1");

        colDetalleCod.setCellValueFactory(new PropertyValueFactory<>("codigoProducto"));
        colDetalleProd.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        colDetalleCant.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colDetallePrecio.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        colDetalleSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        btnAgregarAlPedido.setOnAction(event -> agregarProductoALista());
        btnQuitarDelPedido.setOnAction(event -> quitarProductoSeleccionado());
        btnGuardarPed.setOnAction(event -> guardarPedido());
        btnCancelarPed.setOnAction(event -> cerrarVentana());
    }

    private void agregarProductoALista() {
        if (cmbProducto.getValue() == null) {
            UtilidadesUI.mostrarAlertaSimple("Selección requerida", "Por favor, seleccione un producto de la lista.", Alert.AlertType.WARNING);
            return;
        }

        try {
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (cantidad <= 0) {
                throw new NumberFormatException();
            }

            System.out.println("Producto agregado a la lista temporal. Cantidad: " + cantidad);
            txtCantidad.setText("1");
            calcularTotal();

        } catch (NumberFormatException e) {
            UtilidadesUI.mostrarAlertaSimple("Cantidad inválida", "Ingrese un número entero mayor a cero.", Alert.AlertType.WARNING);
        }
    }

    private void quitarProductoSeleccionado() {
        int indiceSeleccionado = tblDetallePedido.getSelectionModel().getSelectedIndex();

        if (indiceSeleccionado >= 0) {
            listaDetalles.remove(indiceSeleccionado);
            calcularTotal();
        } else {
            UtilidadesUI.mostrarAlertaSimple("Sin selección", "Debe seleccionar un producto de la tabla para eliminarlo.", Alert.AlertType.WARNING);
        }
    }

    private void calcularTotal() {
        totalAcumulado = 0.0;
        lblTotalPago.setText(String.format("$%.2f", totalAcumulado));
    }

    private void guardarPedido() {
        if (cmbCliente.getValue() == null) {
            UtilidadesUI.mostrarAlertaSimple("Cliente no seleccionado", "Debe asignar el pedido a un cliente.", Alert.AlertType.WARNING);
            return;
        }

        if (listaDetalles.isEmpty()) {
            UtilidadesUI.mostrarAlertaSimple("Pedido vacío", "Debe agregar al menos un producto al pedido.", Alert.AlertType.WARNING);
            return;
        }

        System.out.println("Pedido listo para guardar en BD. Total a cobrar: $" + totalAcumulado);
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelarPed.getScene().getWindow();
        stage.close();
    }
}
