package pizzeria.pae.modelo.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        
        ResultSet resultadoPedido = sentenciaBD.executeQuery();
        
        List<Pedido> pedidos= null;
        
        if(resultadoPedido != null){
            pedidos = new ArrayList<>();
            while(resultadoPedido.next()){
                Pedido p = new Pedido();
                 p.setIdPedido(resultadoPedido.getInt("idPedido"));
                 p.setFecha(resultadoPedido.getDate("fecha").toLocalDate());
                 p.setTotal(resultadoPedido.getBigDecimal("totalAPagar"));
                 p.setEstado(resultadoPedido.getString("estado"));
                 p.setCliente(UsuarioDAO.buscarUsuario(resultadoPedido.getInt("idUsuario")));
                 
                 
                 
                 
                 
                
            }
        }
        
        
        return pedidos;
    }
}
