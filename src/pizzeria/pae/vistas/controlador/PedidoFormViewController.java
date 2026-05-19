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

/**
 * Controlador para el formulario de registro y edición de Pedidos del sistema.
 *
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
    private Spinner<Integer> spnCantidad;
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

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 1);
        spnCantidad.setValueFactory(valueFactory);

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
            mostrarAlertaError("Selección requerida", "Por favor, seleccione un producto de la lista.");
            return;
        }

        int cantidad = spnCantidad.getValue();
        System.out.println("Producto agregado a la lista temporal. Cantidad: " + cantidad);

        spnCantidad.getValueFactory().setValue(1);
        calcularTotal();
    }

    private void quitarProductoSeleccionado() {
        int indiceSeleccionado = tblDetallePedido.getSelectionModel().getSelectedIndex();

        if (indiceSeleccionado >= 0) {
            listaDetalles.remove(indiceSeleccionado);
            calcularTotal();
        } else {
            mostrarAlertaError("Sin selección", "Debe seleccionar un producto de la tabla para eliminarlo.");
        }
    }

    private void calcularTotal() {
        totalAcumulado = 0.0;
        lblTotalPago.setText(String.format("$%.2f", totalAcumulado));
    }

    private void guardarPedido() {
        if (cmbCliente.getValue() == null) {
            mostrarAlertaError("Cliente no seleccionado", "Debe asignar el pedido a un cliente.");
            return;
        }

        if (listaDetalles.isEmpty()) {
            mostrarAlertaError("Pedido vacío", "Debe agregar al menos un producto al pedido.");
            return;
        }

        System.out.println("Pedido listo para guardar en BD. Total a cobrar: $" + totalAcumulado);
        cerrarVentana();
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelarPed.getScene().getWindow();
        stage.close();
    }
}
