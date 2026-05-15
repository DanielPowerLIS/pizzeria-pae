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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jdani
 */
public class UsuariosViewController implements Initializable {

    @FXML
    private TextField txtBuscarUsuario;
    @FXML
    private Button btnBuscarUsuario;
    @FXML
    private TableView<?> tblUsuarios;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colTelefono;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colDireccion;
    @FXML
    private TableColumn<?, ?> colTipo;
    @FXML
    private Button btnAgregarUsuario;
    @FXML
    private Button btnEditarUsuario;
    @FXML
    private Button btnEliminarUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
