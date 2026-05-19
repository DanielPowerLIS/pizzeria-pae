package pizzeria.pae.modelo.dao;

import com.sun.deploy.ui.DialogTemplate;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pizzeria.pae.modelo.MySQLConnectionManager;
import pizzeria.pae.modelo.beans.DetallePedido;
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
    
    private static List<DetallePedido> obtenerDetalles(Integer idPedido)throws SQLException{
        String consulta = "SELECT * " +
                    "FROM pizzeriapae.detallepedido " +
                    "WHERE idPedido = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setInt(1, idPedido);
        
        ResultSet resultado = sentenciaBD.executeQuery();
        
        List<DetallePedido> detalles = null;
        
        if(resultado != null){
            detalles = new ArrayList<>();
            while(resultado.next()){
                DetallePedido p = new DetallePedido();
                p.setIdPedido(resultado.getInt("idPedido"));
                p.setCantidad(resultado.getInt("cantidad"));
                p.setSubtotal(resultado.getBigDecimal("subTotal"));
                p.setProducto(ProductoDAO.buscarProducto(resultado.getInt("idProducto")));
                
                detalles.add(p);
                       
            }
        }
        
        resultado.close();
        conexion.close();
        
        return detalles;
    }
}
