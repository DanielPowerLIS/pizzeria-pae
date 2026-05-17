package pizzeria.pae.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            p.setRutaFoto(resultado.getString("foto"));
            p.setRestricciones(resultado.getString("restricciones"));
            p.setPrecio(resultado.getBigDecimal("precio"));
            p.setDescripcion(resultado.getString("descripcion"));
            
        }
        
        resultado.close();
        conexion.close();
        
        return p;
    }
}
