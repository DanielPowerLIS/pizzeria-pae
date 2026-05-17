package pizzeria.pae.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        
        String consulta = "SELECT * " +
                    "FROM usuario JOIN direccion ON usuario.idDireccion = direccion.idDireccion " +
                    "WHERE nombre = ? AND apellidoPaterno = ? AND apellidoMaterno = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setString(1, nombre);
        sentenciaBD.setString(2, apellidoPaterno);
        sentenciaBD.setString(3, apellidoMaterno);
        
        ResultSet resultado = sentenciaBD.executeQuery();
        /*
        if(resultado.next()){
            Usuario u = new Usuario();
            u.idUsuario = resultado.getInt("idUsuario");
            u.nombre = resultado.getString("nombre");
            u.apellidoPaterno = resultado.getString("apellidoPaterno");
            u.apellidoMaterno = resultado.getString("apellidoMaterno");
            u.telefono = resultado.getString("telefono");
            u.email = resultado.getString("email");
            u.haPedido = resultado.getBoolean("haPedido");
            u.esEmpleado = resultado.getBoolean("esEmpleado");
            u.esActivo = resultado.getBoolean("esActivo");
            u.nombreUsuario = resultado.getString("nombreUsuario");
            u.contrasenia = resultado.getString("contrasenia");
            u.direccion.idDireccion = resultado.getInt("idDireccion");
            u.direccion.calle = resultado.getString("calle");
            u.direccion.ciudad = resultado.getString("ciudad");
            u.direccion.numero = resultado.getString("numero");
            u.direccion.codigoPostal = resultado.getString("codigoPostal");
        }
        resultado.close();
        conexion.close();
        
        return u;
        */
        return null;
    }
    
    private static String[] prepararNombre(String nombreCompleto){ 
        String nombreSeparado[] = nombreCompleto.split(" ");
        return nombreSeparado;
    }
        
    public static Usuario buscarUsuarioPorTelefono(String telefonoBuscar )throws SQLException{
        String consulta = "SELECT * " +
                    "FROM usuario JOIN direccion ON usuario.idUsuario = direccion.idUsuario " +
                    "WHERE usuario.telefono = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setString(1, telefonoBuscar);
        
        ResultSet resultado = sentenciaBD.executeQuery();
        /*
        if(resultado.next()){
            Usuario u = new Usuario();
            u.idUsuario = resultado.getInt("idUsuario");
            u.nombre = resultado.getString("nombre");
            u.apellidoPaterno = resultado.getString("apellidoPaterno");
            u.apellidoMaterno = resultado.getString("apellidoMaterno");
            u.telefono = resultado.getString("telefono");
            u.email = resultado.getString("email");
            u.haPedido = resultado.getBoolean("haPedido");
            u.esEmpleado = resultado.getBoolean("esEmpleado");
            u.esActivo = resultado.getBoolean("esActivo");
            u.nombreUsuario = resultado.getString("nombreUsuario");
            u.contrasenia = resultado.getString("contrasenia");
            u.direccion.idDireccion = resultado.getInt("idDireccion");
            u.direccion.calle = resultado.getString("calle");
            u.direccion.ciudad = resultado.getString("ciudad");
            u.direccion.numero = resultado.getString("numero");
            u.direccion.codigoPostal = resultado.getString("codigoPostal");
        }
        resultado.close();
        conexion.close();
        
        return u;
        */
        return null;
    }
       
    public static Usuario buscarUsuarioPorDireccion(String calle, String numero )throws SQLException{
        String consulta = "SELECT * " +
                    "FROM usuario JOIN direccion ON usuario.idUsuario = direccion.idUsuario " +
                    "WHERE direccion.calle = ? AND direccion.numero = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setString(1, calle);
        sentenciaBD.setString(2, numero);
        
        ResultSet resultado = sentenciaBD.executeQuery();
        /*
        if(resultado.next()){
            Usuario u = new Usuario();
            u.idUsuario = resultado.getInt("idUsuario");
            u.nombre = resultado.getString("nombre");
            u.apellidoPaterno = resultado.getString("apellidoPaterno");
            u.apellidoMaterno = resultado.getString("apellidoMaterno");
            u.telefono = resultado.getString("telefono");
            u.email = resultado.getString("email");
            u.haPedido = resultado.getBoolean("haPedido");
            u.esEmpleado = resultado.getBoolean("esEmpleado");
            u.esActivo = resultado.getBoolean("esActivo");
            u.nombreUsuario = resultado.getString("nombreUsuario");
            u.contrasenia = resultado.getString("contrasenia");
            u.direccion.idDireccion = resultado.getInt("idDireccion");
            u.direccion.calle = resultado.getString("calle");
            u.direccion.ciudad = resultado.getString("ciudad");
            u.direccion.numero = resultado.getString("numero");
            u.direccion.codigoPostal = resultado.getString("codigoPostal");
        }
        resultado.close();
        conexion.close();
        
        return u;
        */
        return null;
    }
    
    public static List<Usuario> obtenerUsuarios(boolean esEmpleado)throws SQLException{
        if(esEmpleado){
            List<Usuario> empleados = obtenerEmpleados();
            return empleados;
        }
        List<Usuario> clientes = obtenerClientes();
        return clientes;
    }
    
    private static List<Usuario> obtenerEmpleados()throws SQLException{
        Boolean empleado = true;
        String consulta = "SELECT * " +
                    "FROM usuario JOIN direccion ON usuario.idUsuario = direccion.idUsuario " +
                    "WHERE usuario.esEmpleado = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setBoolean(1, empleado);
        ResultSet resultado = sentenciaBD.executeQuery();
        
        List<Usuario> empleados = new ArrayList<>();
        /*
        while(resultado.next()){
            Usuario u = new Usuario();
            u.idUsuario = resultado.getInt("idUsuario");
            u.nombre = resultado.getString("nombre");
            u.apellidoPaterno = resultado.getString("apellidoPaterno");
            u.apellidoMaterno = resultado.getString("apellidoMaterno");
            u.telefono = resultado.getString("telefono");
            u.email = resultado.getString("email");
            u.haPedido = resultado.getBoolean("haPedido");
            u.esEmpleado = resultado.getBoolean("esEmpleado");
            u.esActivo = resultado.getBoolean("esActivo");
            u.nombreUsuario = resultado.getString("nombreUsuario");
            u.contrasenia = resultado.getString("contrasenia");
            u.direccion.idDireccion = resultado.getInt("idDireccion");
            u.direccion.calle = resultado.getString("calle");
            u.direccion.ciudad = resultado.getString("ciudad");
            u.direccion.numero = resultado.getString("numero");
            u.direccion.codigoPostal = resultado.getString("codigoPostal");
            
            empleados.add(u);
        }
        */
        resultado.close();
        conexion.close();
        
        return empleados;
    }
    
    private static List<Usuario> obtenerClientes()throws SQLException{
        Boolean empleado = false;
        String consulta = "SELECT * " +
                    "FROM usuario JOIN direccion ON usuario.idUsuario = direccion.idUsuario " +
                    "WHERE usuario.esEmpleado = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setBoolean(1, empleado);
        ResultSet resultado = sentenciaBD.executeQuery();
        
        List<Usuario> clientes = new ArrayList<>();
        /*
        while(resultado.next()){
            Usuario u = new Usuario();
            u.idUsuario = resultado.getInt("idUsuario");
            u.nombre = resultado.getString("nombre");
            u.apellidoPaterno = resultado.getString("apellidoPaterno");
            u.apellidoMaterno = resultado.getString("apellidoMaterno");
            u.telefono = resultado.getString("telefono");
            u.email = resultado.getString("email");
            u.haPedido = resultado.getBoolean("haPedido");
            u.esEmpleado = resultado.getBoolean("esEmpleado");
            u.esActivo = resultado.getBoolean("esActivo");
            u.nombreUsuario = resultado.getString("nombreUsuario");
            u.contrasenia = resultado.getString("contrasenia");
            u.direccion.idDireccion = resultado.getInt("idDireccion");
            u.direccion.calle = resultado.getString("calle");
            u.direccion.ciudad = resultado.getString("ciudad");
            u.direccion.numero = resultado.getString("numero");
            u.direccion.codigoPostal = resultado.getString("codigoPostal");
            
            clientes.add(u);
        }
        */
        resultado.close();
        conexion.close();
        
        return clientes;
    }
}
