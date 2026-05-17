package pizzeria.pae.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pizzeria.pae.modelo.MySQLConnectionManager;
import pizzeria.pae.modelo.beans.Usuario;

/**
 *
 * @author adair
 */
public class UsuarioDAO {
    public static Usuario buscarUsuarioPorNombre(String nombreCompleto )throws SQLException{
        String nombres[] = prepararNombre(nombreCompleto);
        
        String nombre = nombres[0];
        String apellidoPaterno = nombres[1];
        String apellidoMaterno = nombres[2];
        
        String consulta = "SELECT * FROM pizzeriapae.usuario "
                + "WHERE nombre = ? AND apellidoPaterno = ? AND apellidoMaterno = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setString(1, nombre);
        sentenciaBD.setString(2, apellidoPaterno);
        sentenciaBD.setString(3, apellidoMaterno);
        
        ResultSet resultado = sentenciaBD.executeQuery();
        
        
        
        
        
    }
    
    private static String[] prepararNombre(String nombreCompleto){
        
        String nombreSeparado[] = nombreCompleto.split(" ");
        return nombreSeparado;
    }
}
