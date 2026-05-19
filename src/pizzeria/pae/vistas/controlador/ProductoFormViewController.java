package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pizzeria.pae.utilidades.UtilidadesUI;

/**
 * @author Adair Alejandro Martinez Alejo
 * @author Gabriel Hernández Martínez
 * @author Víctor Hugo Vásquez Martínez
 * @author Juan Daniel Pérez Santiago
 */
public class ProductoFormViewController implements Initializable {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombreProducto;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtRestricciones;
    @FXML
    private Button btnSeleccionarFoto;
    @FXML
    private Label lblRutaFoto;
    @FXML
    private Button btnGuardarProd;
    @FXML
    private Button btnCancelarProd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnGuardarProd.setOnAction(event -> guardarProducto());
        btnCancelarProd.setOnAction(event -> cerrarVentana());
    }

    private void guardarProducto() {
        if (txtCodigo.getText().trim().isEmpty()
                || txtNombreProducto.getText().trim().isEmpty()
                || txtDescripcion.getText().trim().isEmpty()
                || txtPrecio.getText().trim().isEmpty()
                || txtCantidad.getText().trim().isEmpty()) {

            UtilidadesUI.mostrarAlertaSimple("Campos incompletos", "Por favor, llene todos los campos obligatorios (*).", Alert.AlertType.WARNING);
            return;
        }

        try {
            Double.parseDouble(txtPrecio.getText().trim());
            Integer.parseInt(txtCantidad.getText().trim());
        } catch (NumberFormatException e) {
            UtilidadesUI.mostrarAlertaSimple("Datos inválidos", "El precio y la cantidad deben ser valores numéricos.", Alert.AlertType.WARNING);
            return;
        }

        UtilidadesUI.mostrarAlertaSimple("Éxito", "El producto está listo para ser guardado.", Alert.AlertType.INFORMATION);
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelarProd.getScene().getWindow();
        stage.close();
    }
}
