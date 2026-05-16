package pizzeria.pae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal.
 *
 * @author Adair Alejandro Martinez Alejo
 * @author Gabriel Hernández Martínez
 * @author Víctor Hugo Vásquez Martínez
 * @author Juan Daniel Pérez Santiago
 */
public class PizzeriaPae extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/pizzeria/pae/vistas/fxml/MenuView.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Italia Pizza");

        stage.setResizable(false);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
