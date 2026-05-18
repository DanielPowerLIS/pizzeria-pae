package pizzeria.pae.modelo.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import pizzeria.pae.modelo.MySQLConnectionManager;
import pizzeria.pae.modelo.beans.Pedido;

/**
 *
 * @author adair
 */
public class PedidoDAO {
    public static List<Pedido> buscarPedidoPorFecha(Date fecha)throws SQLException{
        String consulta = "SELECT * " +
                    "FROM pedido " +
                    "WHERE fecha = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setDate(1, fecha);
        
        
        return null;
    }
}
