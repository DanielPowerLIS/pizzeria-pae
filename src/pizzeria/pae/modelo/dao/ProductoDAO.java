package pizzeria.pae.modelo.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pizzeria.pae.modelo.MySQLConnectionManager;
import pizzeria.pae.modelo.beans.Producto;

/**
 *
 * @author adair
 */
public class ProductoDAO {
    public static Producto buscarProductoPorNombre(String nombreProducto)throws SQLException{
        String consulta = "SELECT * " +
                "FROM pizzeriapae.producto " +
                "WHERE nombre = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setString(1, nombreProducto);
        ResultSet resultado = sentenciaBD.executeQuery();
        
        Producto p = null;
        
        if(resultado != null && resultado.next()){
            p = new Producto();
            
            p.setIdProducto(resultado.getInt("idProducto"));
            p.setNombre(resultado.getString("nombre"));
            p.setCodigo(resultado.getString("codigo"));
            p.setEsInsumo(resultado.getBoolean("esInsumo"));
            p.setEsUtilizado(resultado.getBoolean("esUtilizado"));
            p.setCantidad(resultado.getInt("cantidad"));
            p.setFoto(resultado.getBlob("foto"));
            p.setRestricciones(resultado.getString("restricciones"));
            p.setPrecio(resultado.getBigDecimal("precio"));
            p.setDescripcion(resultado.getString("descripcion"));
            
        }
        
        resultado.close();
        conexion.close();
        
        return p;
    }
    
    public static Producto buscarProductoPorCodigo(String codigoProducto)throws SQLException{
        String consulta = "SELECT * " +
                "FROM pizzeriapae.producto " +
                "WHERE codigo = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setString(1, codigoProducto);
        ResultSet resultado = sentenciaBD.executeQuery();
        
        Producto p = null;
        
        if(resultado != null && resultado.next()){
            p = new Producto();
            
            p.setIdProducto(resultado.getInt("idProducto"));
            p.setNombre(resultado.getString("nombre"));
            p.setCodigo(resultado.getString("codigo"));
            p.setEsInsumo(resultado.getBoolean("esInsumo"));
            p.setEsUtilizado(resultado.getBoolean("esUtilizado"));
            p.setCantidad(resultado.getInt("cantidad"));
            p.setFoto(resultado.getBlob("foto"));
            p.setRestricciones(resultado.getString("restricciones"));
            p.setPrecio(resultado.getBigDecimal("precio"));
            p.setDescripcion(resultado.getString("descripcion"));
            
        }
        
        resultado.close();
        conexion.close();
        
        return p;
    }
    
    public static List<Producto> obtenerProductos(Boolean esInsumo)throws SQLException{
        if(esInsumo){
            List<Producto> productosInsumo = obtenerProductosInsumo();
            return productosInsumo;
        }
        List<Producto> productosConsumo = obetenerProductosConsumo();
        return productosConsumo;
    }
    
    private static List<Producto> obtenerProductosInsumo()throws SQLException{
        Boolean Insumo = true;
        String consulta = "SELECT * " +
                "FROM pizzeriapae.producto " +
                "WHERE esInsumo = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setBoolean(1, Insumo);
        ResultSet resultado = sentenciaBD.executeQuery();
        
        List<Producto> productos = null;
        
        if(resultado != null){
            productos = new ArrayList<>();
            while(resultado.next()){
                Producto p = new Producto();
                
                p.setIdProducto(resultado.getInt("idProducto"));
                p.setNombre(resultado.getString("nombre"));
                p.setCodigo(resultado.getString("codigo"));
                p.setEsInsumo(resultado.getBoolean("esInsumo"));
                p.setEsUtilizado(resultado.getBoolean("esUtilizado"));
                p.setCantidad(resultado.getInt("cantidad"));
                p.setFoto(resultado.getBlob("foto"));
                p.setRestricciones(resultado.getString("restricciones"));
                p.setPrecio(resultado.getBigDecimal("precio"));
                p.setDescripcion(resultado.getString("descripcion"));
                
                productos.add(p);
            }
        }
        resultado.close();
        conexion.close();
        
        return productos;
    }
    
    private static List<Producto> obetenerProductosConsumo()throws SQLException{
        Boolean Insumo = false;
        String consulta = "SELECT * " +
                "FROM pizzeriapae.producto " +
                "WHERE esInsumo = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setBoolean(1, Insumo);
        ResultSet resultado = sentenciaBD.executeQuery();
        
        List<Producto> productos = null;
        
        if(resultado != null){
            productos = new ArrayList<>();
            while(resultado.next()){
                Producto p = new Producto();
                
                p.setIdProducto(resultado.getInt("idProducto"));
                p.setNombre(resultado.getString("nombre"));
                p.setCodigo(resultado.getString("codigo"));
                p.setEsInsumo(resultado.getBoolean("esInsumo"));
                p.setEsUtilizado(resultado.getBoolean("esUtilizado"));
                p.setCantidad(resultado.getInt("cantidad"));
                p.setFoto(resultado.getBlob("foto"));
                p.setRestricciones(resultado.getString("restricciones"));
                p.setPrecio(resultado.getBigDecimal("precio"));
                p.setDescripcion(resultado.getString("descripcion"));
                
                productos.add(p);
            }
        }
        resultado.close();
        conexion.close();
        
        return productos;
    }
    
    public static Boolean agregarProducto(Producto productoAgregar)throws SQLException{
        Boolean esUtilizado = false;
        String insercionProducto = "INSERT INTO producto (nombre, codigo, descripcion, " +
                            "precio, cantidad, restricciones, foto, esUtilizado, esInsumo) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement insercionProductoBD = conexion.prepareStatement(insercionProducto);
       
        insercionProductoBD.setString(1, productoAgregar.getNombre());
        insercionProductoBD.setString(2, productoAgregar.getCodigo());
        insercionProductoBD.setString(3, productoAgregar.getDescripcion());
        insercionProductoBD.setBigDecimal(4, productoAgregar.getPrecio());
        insercionProductoBD.setInt(5, productoAgregar.getCantidad());
        insercionProductoBD.setString(6, productoAgregar.getRestricciones());
        insercionProductoBD.setBlob(7, productoAgregar.getFoto());
        insercionProductoBD.setBoolean(8, esUtilizado);
        insercionProductoBD.setBoolean(9, productoAgregar.getEsInsumo());
        
        Integer productoInsertado = insercionProductoBD.executeUpdate();
        
        conexion.close();
        
        return productoInsertado > 0;
          
    }
    
    public static Boolean actualizarProducto(Producto producoActualizar)throws SQLException{
        String actualizarProducto = "UPDATE producto SET " +
                        "nombre = ?, " +
                        "descripcion = ?, " +
                        "precio = ?, " +
                        "cantidad = ?, " +
                        "restricciones = ?, " +
                        "foto = ?, " +
                        "esUtilizado = ?, " +
                        "esInsumo = ? " +
                        "WHERE idProducto = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement actualizarProductoBD = conexion.prepareStatement(actualizarProducto);
        
        actualizarProductoBD.setString(1, producoActualizar.getNombre());
        actualizarProductoBD.setString(2, producoActualizar.getDescripcion());
        actualizarProductoBD.setBigDecimal(3, producoActualizar.getPrecio());
        actualizarProductoBD.setInt(4, producoActualizar.getCantidad());
        actualizarProductoBD.setString(5, producoActualizar.getRestricciones());
        actualizarProductoBD.setBlob(6, producoActualizar.getFoto());
        actualizarProductoBD.setBoolean(7, producoActualizar.getEsUtilizado());
        actualizarProductoBD.setBoolean(8, producoActualizar.getEsInsumo());
        actualizarProductoBD.setInt(9, producoActualizar.getIdProducto());
        
        Integer productoActualizado = actualizarProductoBD.executeUpdate();
        
        conexion.close();
        
        return productoActualizado > 0;
    }
    
    public static Boolean eliminarProducto(Integer idProducto)throws SQLException{
        String eliminarProducto = "DELETE FROM producto WHERE idProducto = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement eliminarProductoBD = conexion.prepareStatement(eliminarProducto);
        
        eliminarProductoBD.setInt(1, idProducto);
        
        Integer productoEliminado = eliminarProductoBD.executeUpdate();
        
        conexion.close();
        
        return productoEliminado > 0;
    }
    
    public static Producto buscarProducto(Integer idProducto)throws SQLException{
        String consulta = "SELECT * " +
                "FROM pizzeriapae.producto " +
                "WHERE idProducto = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setInt(1, idProducto);
        ResultSet resultado = sentenciaBD.executeQuery();
        
        Producto p = null;
        
        if(resultado != null && resultado.next()){
            p = new Producto();
            
            p.setIdProducto(resultado.getInt("idProducto"));
            p.setNombre(resultado.getString("nombre"));
            p.setCodigo(resultado.getString("codigo"));
            p.setEsInsumo(resultado.getBoolean("esInsumo"));
            p.setEsUtilizado(resultado.getBoolean("esUtilizado"));
            p.setCantidad(resultado.getInt("cantidad"));
            p.setFoto(resultado.getBlob("foto"));
            p.setRestricciones(resultado.getString("restricciones"));
            p.setPrecio(resultado.getBigDecimal("precio"));
            p.setDescripcion(resultado.getString("descripcion"));
            
        }
        
        resultado.close();
        conexion.close();
        
        return p;
    }
}
