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
    public static List<Producto> buscarProductoPorNombre(String nombreProducto, Boolean esInsumo)throws SQLException{
        String consulta = "SELECT " +
                        "idProducto," +
                        "nombre, " +
                        "codigo, " +
                        "descripcion, " +
                        "precio, " +
                        "cantidad, " +
                        "restricciones, " +
                        "foto, " +
                        "esUtilizado, " +
                        "esInsumo" +
                        "FROM Producto " +
                        "WHERE nombre = ? " +
                        "  AND esInsumo = ?;";
        
        try( MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
                PreparedStatement sentenciaBD = conexion.prepareStatement(consulta) ){
            
            List<Producto> productos = new ArrayList<>();
            
            sentenciaBD.setString(1, nombreProducto);
            sentenciaBD.setBoolean(2, esInsumo);
            
            try(ResultSet resultado = sentenciaBD.executeQuery()){
               
                if(resultado.next()){
                    Producto p = new Producto();

                    p.setIdProducto(resultado.getInt("idProducto"));
                    p.setNombre(resultado.getString("nombre"));
                    p.setCodigo(resultado.getString("codigo"));
                    p.setEsInsumo(resultado.getBoolean("esInsumo"));
                    p.setEsUtilizado(resultado.getBoolean("esUtilizado"));
                    p.setCantidad(resultado.getInt("cantidad"));
                    p.setFoto(resultado.getBytes("foto"));
                    p.setRestricciones(resultado.getString("restricciones"));
                    p.setPrecio(resultado.getBigDecimal("precio"));
                    p.setDescripcion(resultado.getString("descripcion"));

                    productos.add(p);

                }
            }
            return productos;
        }
    }
    
    public static List<Producto> buscarProductoPorCodigo(String codigoProducto, Boolean esInsumo)throws SQLException{
        String consulta = "SELECT " +
                        "idProducto," +
                        "nombre, " +
                        "codigo, " +
                        "descripcion, " +
                        "precio, " +
                        "cantidad, " +
                        "restricciones, " +
                        "foto, " +
                        "esUtilizado, " +
                        "esInsumo" +
                        "FROM Producto " +
                        "WHERE codigo = ? " +
                        "  AND esInsumo = ?;";
        
        try( MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
                PreparedStatement sentenciaBD = conexion.prepareStatement(consulta) ){
            
            List<Producto> productos = new ArrayList<>();
            
            sentenciaBD.setString(1, codigoProducto);
            sentenciaBD.setBoolean(2, esInsumo);
            
            try(ResultSet resultado = sentenciaBD.executeQuery()){
               
                if(resultado.next()){
                    Producto p = new Producto();

                    p.setIdProducto(resultado.getInt("idProducto"));
                    p.setNombre(resultado.getString("nombre"));
                    p.setCodigo(resultado.getString("codigo"));
                    p.setEsInsumo(resultado.getBoolean("esInsumo"));
                    p.setEsUtilizado(resultado.getBoolean("esUtilizado"));
                    p.setCantidad(resultado.getInt("cantidad"));
                    p.setFoto(resultado.getBytes("foto"));
                    p.setRestricciones(resultado.getString("restricciones"));
                    p.setPrecio(resultado.getBigDecimal("precio"));
                    p.setDescripcion(resultado.getString("descripcion"));

                    productos.add(p);

                }
            }
            return productos;
        }
    }
    
    public static List<Producto> obtenerProductos(Boolean Insumo)throws SQLException{
        String consulta = "SELECT * " +
                "FROM pizzeriapae.producto " +
                "WHERE esInsumo = ?";
        
        try( MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta) ){
            
            List<Producto> productos = new ArrayList<>();
            
            sentenciaBD.setBoolean(1, Insumo);
            
            try( ResultSet resultado = sentenciaBD.executeQuery() ){
                while(resultado.next()){
                    Producto p = new Producto();

                    p.setIdProducto(resultado.getInt("idProducto"));
                    p.setNombre(resultado.getString("nombre"));
                    p.setCodigo(resultado.getString("codigo"));
                    p.setEsInsumo(resultado.getBoolean("esInsumo"));
                    p.setEsUtilizado(resultado.getBoolean("esUtilizado"));
                    p.setCantidad(resultado.getInt("cantidad"));
                    p.setFoto(resultado.getBytes("foto"));
                    p.setRestricciones(resultado.getString("restricciones"));
                    p.setPrecio(resultado.getBigDecimal("precio"));
                    p.setDescripcion(resultado.getString("descripcion"));

                    productos.add(p);
                } 
            }
            return productos;
        }
    }
    
    public static Boolean agregarProducto(Producto productoAgregar)throws SQLException{
        Boolean esUtilizado = false;
        String insercionProducto = "INSERT INTO producto (nombre, codigo, descripcion, " +
                            "precio, cantidad, restricciones, foto, esUtilizado, esInsumo) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try( MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
            PreparedStatement insercionProductoBD = conexion.prepareStatement(insercionProducto)){
            
            insercionProductoBD.setString(1, productoAgregar.getNombre());
            insercionProductoBD.setString(2, productoAgregar.getCodigo());
            insercionProductoBD.setString(3, productoAgregar.getDescripcion());
            insercionProductoBD.setBigDecimal(4, productoAgregar.getPrecio());
            insercionProductoBD.setInt(5, productoAgregar.getCantidad());
            insercionProductoBD.setString(6, productoAgregar.getRestricciones());
            insercionProductoBD.setBytes(7, productoAgregar.getFoto());
            insercionProductoBD.setBoolean(8, esUtilizado);
            insercionProductoBD.setBoolean(9, productoAgregar.getEsInsumo());

            Integer productoInsertado = insercionProductoBD.executeUpdate();

            return productoInsertado > 0;
        }      
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

        try( MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
            PreparedStatement actualizarProductoBD = conexion.prepareStatement(actualizarProducto) ){
            
            actualizarProductoBD.setString(1, producoActualizar.getNombre());
            actualizarProductoBD.setString(2, producoActualizar.getDescripcion());
            actualizarProductoBD.setBigDecimal(3, producoActualizar.getPrecio());
            actualizarProductoBD.setInt(4, producoActualizar.getCantidad());
            actualizarProductoBD.setString(5, producoActualizar.getRestricciones());
            actualizarProductoBD.setBytes(6, producoActualizar.getFoto());
            actualizarProductoBD.setBoolean(7, producoActualizar.getEsUtilizado());
            actualizarProductoBD.setBoolean(8, producoActualizar.getEsInsumo());
            actualizarProductoBD.setInt(9, producoActualizar.getIdProducto());

            Integer productoActualizado = actualizarProductoBD.executeUpdate();

            return productoActualizado > 0;
            
        }
    }
    
    public static Boolean eliminarProducto(Integer idProducto)throws SQLException{
        String eliminarProducto = "DELETE FROM producto WHERE idProducto = ?";
        
        try(MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
            PreparedStatement eliminarProductoBD = conexion.prepareStatement(eliminarProducto)){

            eliminarProductoBD.setInt(1, idProducto);
        
            Integer productoEliminado = eliminarProductoBD.executeUpdate();

            return productoEliminado > 0;
        }  
    }
    
    public static Producto buscarProducto(Integer idProducto)throws SQLException{
        String consulta = "SELECT * " +
                "FROM pizzeriapae.producto " +
                "WHERE idProducto = ?";
        
        try( MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
            PreparedStatement sentenciaBD = conexion.prepareStatement(consulta) ){
                
            Producto p = null;
            
            sentenciaBD.setInt(1, idProducto);
            
            try( ResultSet resultado = sentenciaBD.executeQuery() ){
                if(resultado.next()){
                    p = new Producto();

                    p.setIdProducto(resultado.getInt("idProducto"));
                    p.setNombre(resultado.getString("nombre"));
                    p.setCodigo(resultado.getString("codigo"));
                    p.setEsInsumo(resultado.getBoolean("esInsumo"));
                    p.setEsUtilizado(resultado.getBoolean("esUtilizado"));
                    p.setCantidad(resultado.getInt("cantidad"));
                    p.setFoto(resultado.getBytes("foto"));
                    p.setRestricciones(resultado.getString("restricciones"));
                    p.setPrecio(resultado.getBigDecimal("precio"));
                    p.setDescripcion(resultado.getString("descripcion"));
                } 
            }
            return p;
        }   
    }
}
