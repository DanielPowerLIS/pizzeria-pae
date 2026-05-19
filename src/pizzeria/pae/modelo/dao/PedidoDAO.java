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
import pizzeria.pae.modelo.beans.Direccion;
import pizzeria.pae.modelo.beans.Pedido;
import pizzeria.pae.modelo.beans.Producto;
import pizzeria.pae.modelo.beans.Usuario;
import pizzeria.pae.utilidades.ConvertidorNombre;

/**
 *
 * @author adair
 */
public class PedidoDAO {
    public static List<Pedido> buscarPedidoPorFecha(Date fecha)throws SQLException{
        String consulta = "SELECT " +
                        "p.idPedido, p.codigo AS codigoPedido, p.fecha, p.estado, p.totalAPagar, " +
                        "u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.telefono, " +
                        "u.email, u.haPedido, u.esEmpleado, u.esActivo, u.nombreUsuario, u.contrasenia, " +
                        "d.idDireccion, d.calle, d.ciudad, d.numero, d.codigoPostal " +
                        "FROM pedido p " +
                        "INNER JOIN usuario u ON p.idUsuario = u.idUsuario " +
                        "INNER JOIN direccion d ON u.idUsuario = d.idUsuario " +
                        "WHERE p.fecha = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setDate(1, fecha);
        
        ResultSet resultado = sentenciaBD.executeQuery();
        
        List<Pedido> pedidos= null;
        
        if(resultado != null){
            pedidos = new ArrayList<>();
            while(resultado.next()){
                Pedido p = new Pedido();
                p.setIdPedido(resultado.getInt("idPedido"));
                p.setFecha(resultado.getDate("fecha").toLocalDate());
                p.setEstado(resultado.getString("estado"));
                p.setTotal(resultado.getBigDecimal("totalAPagar"));

                Direccion d = new Direccion();
                d.setIdDireccion(resultado.getInt("idDireccion"));
                d.setCalle(resultado.getString("calle"));
                d.setCiudad(resultado.getString("ciudad"));
                d.setNumero(resultado.getString("numero"));
                d.setCodigoPostal(resultado.getString("codigoPostal"));

                Usuario u = new Usuario();
                u.setIdUsuario(resultado.getInt("idUsuario"));
                u.setNombre(resultado.getString("nombre"));
                u.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                u.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                u.setTelefono(resultado.getString("telefono"));
                u.setEmail(resultado.getString("email"));
                u.setHaPedido(resultado.getBoolean("haPedido"));
                u.setEsEmpleado(resultado.getBoolean("esEmpleado"));
                u.setEsActivo(resultado.getBoolean("esActivo"));
                u.setNombreUsuario(resultado.getString("nombreUsuario"));
                u.setContrasenia(resultado.getString("contrasenia"));
                
                u.setDireccion(d);
                p.setCliente(u);
                
                p.setDetallePedido(obtenerDetalles(p.getIdPedido()));
                
                pedidos.add(p);
                    
            }
        }
        
        resultado.close();
        conexion.close();
        
        return pedidos;
    }
    
    private static List<DetallePedido> obtenerDetalles(Integer idPedido)throws SQLException{
        String consulta = "SELECT dp.idPedido, dp.idProducto, dp.cantidad AS cantidadPedida, dp.subTotal, " +
                  "p.nombre, p.codigo, p.esInsumo, p.esUtilizado, p.cantidad AS cantidadInventario, " +
                  "p.foto, p.restricciones, p.precio, p.descripcion " +
                  "FROM detallepedido dp JOIN producto p ON dp.idProducto = p.idProducto " +
                  "WHERE dp.idPedido = ?";
        
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
                p.setCantidad(resultado.getInt("cantidadPedida"));
                p.setSubtotal(resultado.getBigDecimal("subTotal"));
                
                Producto pro = new Producto();
                pro.setIdProducto(resultado.getInt("idProducto"));
                pro.setNombre(resultado.getString("nombre"));
                pro.setCodigo(resultado.getString("codigo"));
                pro.setEsInsumo(resultado.getBoolean("esInsumo"));
                pro.setEsUtilizado(resultado.getBoolean("esUtilizado"));
                pro.setCantidad(resultado.getInt("cantidadInventario")); 
                pro.setFoto(resultado.getBlob("foto"));
                pro.setRestricciones(resultado.getString("restricciones"));
                pro.setPrecio(resultado.getBigDecimal("precio"));
                pro.setDescripcion(resultado.getString("descripcion"));
                
                p.setProducto(pro);
                
                detalles.add(p);
                       
            }
        }
        
        resultado.close();
        conexion.close();
        
        return detalles;
    }
    
    public static List<Pedido> buscarPedidoPorEstado(String estado)throws SQLException{
        String consulta = "SELECT " +
                        "p.idPedido, p.codigo AS codigoPedido, p.fecha, p.estado, p.totalAPagar, " +
                        "u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.telefono, " +
                        "u.email, u.haPedido, u.esEmpleado, u.esActivo, u.nombreUsuario, u.contrasenia, " +
                        "d.idDireccion, d.calle, d.ciudad, d.numero, d.codigoPostal " +
                        "FROM pedido p " +
                        "INNER JOIN usuario u ON p.idUsuario = u.idUsuario " +
                        "INNER JOIN direccion d ON u.idUsuario = d.idUsuario " +
                        "WHERE p.estado = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setString(1, estado);
        
        ResultSet resultado = sentenciaBD.executeQuery();
        
        List<Pedido> pedidos = null;
        
        if(resultado != null){
            pedidos = new ArrayList<>();
            while(resultado.next()){
                Pedido p = new Pedido();
                p.setIdPedido(resultado.getInt("idPedido"));
                p.setFecha(resultado.getDate("fecha").toLocalDate());
                p.setEstado(resultado.getString("estado"));
                p.setTotal(resultado.getBigDecimal("totalAPagar"));

                Direccion d = new Direccion();
                d.setIdDireccion(resultado.getInt("idDireccion"));
                d.setCalle(resultado.getString("calle"));
                d.setCiudad(resultado.getString("ciudad"));
                d.setNumero(resultado.getString("numero"));
                d.setCodigoPostal(resultado.getString("codigoPostal"));

                Usuario u = new Usuario();
                u.setIdUsuario(resultado.getInt("idUsuario"));
                u.setNombre(resultado.getString("nombre"));
                u.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                u.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                u.setTelefono(resultado.getString("telefono"));
                u.setEmail(resultado.getString("email"));
                u.setHaPedido(resultado.getBoolean("haPedido"));
                u.setEsEmpleado(resultado.getBoolean("esEmpleado"));
                u.setEsActivo(resultado.getBoolean("esActivo"));
                u.setNombreUsuario(resultado.getString("nombreUsuario"));
                u.setContrasenia(resultado.getString("contrasenia"));
                
                u.setDireccion(d);
                p.setCliente(u);
                
                p.setDetallePedido(obtenerDetalles(p.getIdPedido()));
                
                pedidos.add(p);
                    
            }
        }
        
        resultado.close();
        conexion.close();
        
        return pedidos;
    }
    
    public static List<Pedido> buscarPedidosPorUsuario(String nombreCompletoBuscar)throws SQLException{
        String consulta = "SELECT " +
                        "p.idPedido, p.codigo AS codigoPedido, p.fecha, p.estado, p.totalAPagar, " +
                        "u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.telefono, " +
                        "u.email, u.haPedido, u.esEmpleado, u.esActivo, u.nombreUsuario, u.contrasenia, " +
                        "d.idDireccion, d.calle, d.ciudad, d.numero, d.codigoPostal " +
                        "FROM pedido p " +
                        "INNER JOIN usuario u ON p.idUsuario = u.idUsuario " +
                        "INNER JOIN direccion d ON u.idUsuario = d.idUsuario " +
                        "WHERE u.nombre = ? AND u.apellidoPaterno = ? AND u.apellidoMaterno = ?";
        
        String[] nombreCompleto = ConvertidorNombre.prepararNombre(nombreCompletoBuscar);
        
        String nombre = nombreCompleto[0];
        String apellidoPaterno = nombreCompleto[1];
        String apellidoMaterno = nombreCompleto[2];
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setString(1, nombre);
        sentenciaBD.setString(2, apellidoPaterno);
        sentenciaBD.setString(3, apellidoMaterno);
        
        ResultSet resultado = sentenciaBD.executeQuery();
        
        List<Pedido> pedidos = null;
        
        if(resultado != null){
            pedidos = new ArrayList<>();
            while(resultado.next()){
                Pedido p = new Pedido();
                p.setIdPedido(resultado.getInt("idPedido"));
                p.setFecha(resultado.getDate("fecha").toLocalDate());
                p.setEstado(resultado.getString("estado"));
                p.setTotal(resultado.getBigDecimal("totalAPagar"));

                Direccion d = new Direccion();
                d.setIdDireccion(resultado.getInt("idDireccion"));
                d.setCalle(resultado.getString("calle"));
                d.setCiudad(resultado.getString("ciudad"));
                d.setNumero(resultado.getString("numero"));
                d.setCodigoPostal(resultado.getString("codigoPostal"));

                Usuario u = new Usuario();
                u.setIdUsuario(resultado.getInt("idUsuario"));
                u.setNombre(resultado.getString("nombre"));
                u.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                u.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                u.setTelefono(resultado.getString("telefono"));
                u.setEmail(resultado.getString("email"));
                u.setHaPedido(resultado.getBoolean("haPedido"));
                u.setEsEmpleado(resultado.getBoolean("esEmpleado"));
                u.setEsActivo(resultado.getBoolean("esActivo"));
                u.setNombreUsuario(resultado.getString("nombreUsuario"));
                u.setContrasenia(resultado.getString("contrasenia"));
                
                u.setDireccion(d);
                p.setCliente(u);
                
                p.setDetallePedido(obtenerDetalles(p.getIdPedido()));
                
                pedidos.add(p);
                    
            }
        }
        
        resultado.close();
        conexion.close();
        
        return pedidos;
    }
    
    public static List<Pedido> obtenerPedidos()throws SQLException{
        String consulta = "SELECT " +
                        "p.idPedido, p.codigo AS codigoPedido, p.fecha, p.estado, p.totalAPagar, " +
                        "u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.telefono, " +
                        "u.email, u.haPedido, u.esEmpleado, u.esActivo, u.nombreUsuario, u.contrasenia, " +
                        "d.idDireccion, d.calle, d.ciudad, d.numero, d.codigoPostal " +
                        "FROM pedido p " +
                        "INNER JOIN usuario u ON p.idUsuario = u.idUsuario " +
                        "INNER JOIN direccion d ON u.idUsuario = d.idUsuario ";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        ResultSet resultado = sentenciaBD.executeQuery();
        
        List<Pedido> pedidos = null;
        
        if(resultado != null){
            pedidos = new ArrayList<>();
            while(resultado.next()){
                Pedido p = new Pedido();
                p.setIdPedido(resultado.getInt("idPedido"));
                p.setFecha(resultado.getDate("fecha").toLocalDate());
                p.setEstado(resultado.getString("estado"));
                p.setTotal(resultado.getBigDecimal("totalAPagar"));

                Direccion d = new Direccion();
                d.setIdDireccion(resultado.getInt("idDireccion"));
                d.setCalle(resultado.getString("calle"));
                d.setCiudad(resultado.getString("ciudad"));
                d.setNumero(resultado.getString("numero"));
                d.setCodigoPostal(resultado.getString("codigoPostal"));

                Usuario u = new Usuario();
                u.setIdUsuario(resultado.getInt("idUsuario"));
                u.setNombre(resultado.getString("nombre"));
                u.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                u.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                u.setTelefono(resultado.getString("telefono"));
                u.setEmail(resultado.getString("email"));
                u.setHaPedido(resultado.getBoolean("haPedido"));
                u.setEsEmpleado(resultado.getBoolean("esEmpleado"));
                u.setEsActivo(resultado.getBoolean("esActivo"));
                u.setNombreUsuario(resultado.getString("nombreUsuario"));
                u.setContrasenia(resultado.getString("contrasenia"));
                
                u.setDireccion(d);
                p.setCliente(u);
                
                p.setDetallePedido(obtenerDetalles(p.getIdPedido()));
                
                pedidos.add(p);
                    
            }
        }
        
        resultado.close();
        conexion.close();
        
        return pedidos;
    }
    
    
}
