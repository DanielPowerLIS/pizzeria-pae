package pizzeria.pae.vistas.controlador;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 *
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
    private Button btnCancelarProd;
    @FXML
    private Button btnGuardarProd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSeleccionarFoto.setOnAction(event -> seleccionarImagen());
        btnCancelarProd.setOnAction(event -> cerrarVentana());
        btnGuardarProd.setOnAction(event -> guardarProducto());
    }

    public void prepararEdicion() {
        // PENDIENTE
    }

    private void seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Foto del Producto");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(btnSeleccionarFoto.getScene().getWindow());
        if (selectedFile != null) {
            lblRutaFoto.setText(selectedFile.getName());
            // PENDIENTE
        }
    }

    private void guardarProducto() {
        if (validarDatos()) {
            System.out.println("guardando...");
            cerrarVentana();
        }
    }

    private boolean validarDatos() {
        if (txtCodigo.getText().isEmpty() || txtNombreProducto.getText().isEmpty()
                || txtPrecio.getText().isEmpty() || txtCantidad.getText().isEmpty()) {
            mostrarAlerta("Error de validación", "Faltan campos obligatorios de llenar.");
            return false;
        }

        try {
            new BigDecimal(txtPrecio.getText());
            Integer.parseInt(txtCantidad.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Formato inválido", "El precio y la cantidad deben ser números válidos.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelarProd.getScene().getWindow();
        stage.close();
    }
}
