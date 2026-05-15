package pizzeria.pae.vistas.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author jdani
 */
public class ValidacionInventarioViewController implements Initializable {

    @FXML
    private TableView<?> tblValidacion;
    @FXML
    private TableColumn<?, ?> colCodVal;
    @FXML
    private TableColumn<?, ?> colNomVal;
    @FXML
    private TableColumn<?, ?> colCantSistema;
    @FXML
    private TableColumn<?, ?> colCantFisica;
    @FXML
    private TableColumn<?, ?> colDiferencia;
    @FXML
    private Button btnGuardarValidacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
