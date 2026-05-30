package pizzeria.pae.vistas.controlador;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import pizzeria.pae.modelo.beans.DetallePedido;
import pizzeria.pae.modelo.beans.Pedido;
import pizzeria.pae.modelo.beans.Producto;
import pizzeria.pae.modelo.beans.Usuario;
import pizzeria.pae.modelo.dao.PedidoDAO;
import pizzeria.pae.modelo.dao.ProductoDAO;
import pizzeria.pae.modelo.dao.UsuarioDAO;
import pizzeria.pae.utilidades.Alerta;
import pizzeria.pae.utilidades.ConfigurarSoloNumeros;
import pizzeria.pae.utilidades.UtilidadesUI;

/**
 * @author Adair Alejandro Martinez Alejo
 * @author Gabriel Hernández Martínez
 * @author Víctor Hugo Vásquez Martínez
 * @author Juan Daniel Pérez Santiago
 */
public class PedidoFormViewController implements Initializable {

    @FXML
    private ComboBox<Usuario> cmbCliente;
    @FXML
    private TextField txtFecha;
    @FXML
    private ComboBox<Producto> cmbProducto;
    @FXML
    private Button btnAgregarAlPedido;
    @FXML
    private Button btnQuitarDelPedido;
    @FXML
    private TableView<DetallePedido> tblDetallePedido;
    @FXML
    private TableColumn<DetallePedido, String> colDetalleCod;
    @FXML
    private TableColumn<DetallePedido, String> colDetalleProd;
    @FXML
    private TableColumn<DetallePedido, Integer> colDetalleCant;
    @FXML
    private TableColumn<DetallePedido, BigDecimal> colDetallePrecio;
    @FXML
    private TableColumn<DetallePedido, BigDecimal> colDetalleSubtotal;
    @FXML
    private Label lblTotalPago;
    @FXML
    private Button btnCancelarPed;
    @FXML
    private Button btnGuardarPed;

    private ObservableList<DetallePedido> listaDetalles;
    private ObservableList<Producto> listaProductos;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        inicializarControles();
        cargarInformacionProductos();
        cargarClientes();
    }

    private void inicializarControles() {
        txtFecha.setText(LocalDate.now().toString());
        txtFecha.setEditable(false);

        listaDetalles = FXCollections.observableArrayList();
        tblDetallePedido.setItems(listaDetalles);
    }
    
    private void cargarInformacionProductos() {
        try{
            cmbProducto.setItems(
                FXCollections.observableArrayList(
                    ProductoDAO.obtenerProductos(false)
                )
            );
        }catch(SQLException ex){
             Alerta.mostrarAlertaError("Problema de conexión", "Ocurrió un problema al cargar la información.");       
        }
    
    }

    private void configurarTabla() {
        tblDetallePedido.setEditable(true);

        colDetalleCod.setCellValueFactory(new PropertyValueFactory<>("codigoProducto"));
        colDetalleProd.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        colDetalleCant.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colDetallePrecio.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        colDetalleSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        colDetalleCant.setCellFactory(columna ->
            new TextFieldTableCell<DetallePedido, Integer>(
                    new IntegerStringConverter()) {

                @Override
                public void startEdit() {
                    super.startEdit();

                    if (isEditing() && getGraphic() instanceof TextField) {
                        TextField tfCelda = (TextField) getGraphic();
                        ConfigurarSoloNumeros.configurarSoloNumeros(tfCelda);
                    }
                }
            });

        colDetalleCant.setOnEditCommit(event -> {

            if (event.getNewValue() <= 0) {

                tblDetallePedido.refresh();

                Alerta.mostrarAlertaAdvertencia(
                        "Cantidad inválida",
                        "La cantidad debe ser mayor que cero."
                );

                return;
            }

            DetallePedido detalle = event.getRowValue();

            detalle.setCantidad(event.getNewValue());

            detalle.setSubtotal(
                    detalle.getProducto().getPrecio()
                            .multiply(
                                    BigDecimal.valueOf(event.getNewValue())
                            )
            );

            calcularTotal();

            tblDetallePedido.refresh();
        });
    }
    
    private Pedido crearPedido() {

        Pedido pedido = new Pedido();

        pedido.setFecha(LocalDate.now());
        pedido.setEstado("PENDIENTE");
        pedido.setCliente(cmbCliente.getValue());

        pedido.setDetallePedido(listaDetalles);

        pedido.setTotal(calcularTotalPedido());

        return pedido;
    }
    
    private BigDecimal calcularTotalPedido() {

        BigDecimal total = BigDecimal.ZERO;

        for (DetallePedido detalle : listaDetalles) {
            total = total.add(detalle.getSubtotal());
        }

        return total;
    }
    private void cargarClientes() {
        try {
            cmbCliente.setItems(
                FXCollections.observableArrayList(
                    UsuarioDAO.obtenerUsuarios(false)
                )
            );
        } catch (SQLException ex) {
            Alerta.mostrarAlertaError(
                "Problema de conexión",
                "No se pudieron cargar los clientes."
            );
        }
    }
    
    private void calcularTotal() {
        lblTotalPago.setText("$" + calcularTotalPedido());
    }
    
    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelarPed.getScene().getWindow();
        stage.close();
    }
    
    private DetallePedido buscarDetalleProducto(Producto producto) {

        for (DetallePedido detalle : listaDetalles) {

            if (detalle.getProducto().getIdProducto()
                    == producto.getIdProducto()) {

                return detalle;
            }
        }

        return null;
    }
    
    private DetallePedido crearDetallePedido(Producto producto) {

        DetallePedido detalle = new DetallePedido();

        detalle.setProducto(producto);
        detalle.setCantidad(1);
        detalle.setSubtotal(producto.getPrecio());

        return detalle;
    }

    @FXML
    private void clickAgregarProducto(ActionEvent event) {

        Producto producto = cmbProducto.getValue();

        if (producto == null) {
            Alerta.mostrarAlertaAdvertencia(
                    "Producto no seleccionado",
                    "Por favor, seleccione un producto de la lista."
            );
            return;
        }

        if (buscarDetalleProducto(producto) != null) {
            Alerta.mostrarAlertaAdvertencia(
                    "Producto duplicado",
                    "Este producto ya fue agregado al pedido."
            );
            return;
        }

        listaDetalles.add(
                crearDetallePedido(producto)
        );

        calcularTotal();
    }

    @FXML
    private void clickGuardarPedido(ActionEvent event) {

        if (cmbCliente.getValue() == null) {
            UtilidadesUI.mostrarAlertaSimple(
                    "Cliente no seleccionado",
                    "Debe asignar el pedido a un cliente.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        if (listaDetalles.isEmpty()) {
            UtilidadesUI.mostrarAlertaSimple(
                    "Pedido vacío",
                    "Debe agregar al menos un producto al pedido.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        for (DetallePedido detalle : listaDetalles) {

            if (detalle.getCantidad() <= 0) {

                Alerta.mostrarAlertaAdvertencia(
                        "Cantidad inválida",
                        "Todos los productos deben tener una cantidad mayor a cero."
                );

                return;
            }
        }

        try {

            Pedido pedido = new Pedido();

            pedido.setCliente(cmbCliente.getValue());
            pedido.setFecha(LocalDate.now());
            pedido.setEstado("PENDIENTE");

            pedido.setDetallePedido(listaDetalles);

            pedido.setTotal(
                    listaDetalles.stream()
                            .map(DetallePedido::getSubtotal)
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
            );

            if (PedidoDAO.agregarPedido(pedido)) {

                Alerta.mostrarAlertaInformacion(
                        "Pedido registrado",
                        "El pedido se registró correctamente."
                );

                cerrarVentana();

            } else {

                Alerta.mostrarAlertaAdvertencia(
                        "Operación no realizada",
                        "No fue posible registrar el pedido."
                );
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            Alerta.mostrarAlertaError(
                    "Error de base de datos",
                    ex.getMessage()
            );
        }
    }

    @FXML
    private void clickQuitarProducto(ActionEvent event) {
        int indiceSeleccionado = tblDetallePedido.getSelectionModel().getSelectedIndex();

        if (indiceSeleccionado >= 0) {
            listaDetalles.remove(indiceSeleccionado);
            calcularTotal();
        } else {
            UtilidadesUI.mostrarAlertaSimple("Sin selección", "Debe seleccionar un producto de la tabla para eliminarlo.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
        cerrarVentana();
    }


}
