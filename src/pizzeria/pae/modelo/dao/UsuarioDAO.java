package pizzeria.pae.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pizzeria.pae.modelo.MySQLConnectionManager;
import pizzeria.pae.modelo.beans.Direccion;
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
        
        Usuario u = null;
        
        if(resultado != null && resultado.next()){
            u = new Usuario();
            
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
            
            Direccion d =  new Direccion();
            d.setIdDireccion(resultado.getInt("idDireccion"));
            d.setCalle(resultado.getString("calle"));
            d.setCiudad(resultado.getString("ciudad"));
            d.setNumero(resultado.getString("numero"));
            d.setCodigoPostal(resultado.getString("codigoPostal"));
            
            u.setDireccion(d);
            
        }
        resultado.close();
        conexion.close();
        
        return u;
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
        Usuario u = null;
        
        if(resultado != null && resultado.next()){
            u = new Usuario();
            
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
            
            Direccion d =  new Direccion();
            d.setIdDireccion(resultado.getInt("idDireccion"));
            d.setCalle(resultado.getString("calle"));
            d.setCiudad(resultado.getString("ciudad"));
            d.setNumero(resultado.getString("numero"));
            d.setCodigoPostal(resultado.getString("codigoPostal"));
            
            u.setDireccion(d);
            
        }
        resultado.close();
        conexion.close();
        
        return u;
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
        Usuario u = null;
        
        if(resultado != null && resultado.next()){
            u = new Usuario();
            
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
            
            Direccion d =  new Direccion();
            d.setIdDireccion(resultado.getInt("idDireccion"));
            d.setCalle(resultado.getString("calle"));
            d.setCiudad(resultado.getString("ciudad"));
            d.setNumero(resultado.getString("numero"));
            d.setCodigoPostal(resultado.getString("codigoPostal"));
            
            u.setDireccion(d);
            
        }
        resultado.close();
        conexion.close();
        
        return u;
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
        
        List<Usuario> empleados = null;
        
        if(resultado != null){
            empleados = new ArrayList<>();
            while(resultado.next()){
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

                Direccion d =  new Direccion();
                d.setIdDireccion(resultado.getInt("idDireccion"));
                d.setCalle(resultado.getString("calle"));
                d.setCiudad(resultado.getString("ciudad"));
                d.setNumero(resultado.getString("numero"));
                d.setCodigoPostal(resultado.getString("codigoPostal"));

                u.setDireccion(d);

                empleados.add(u);
            }
        }
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
        
        List<Usuario> clientes = null;
        if(resultado != null){
            clientes = new ArrayList<>();
            while(resultado.next()){
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

                Direccion d =  new Direccion();
                d.setIdDireccion(resultado.getInt("idDireccion"));
                d.setCalle(resultado.getString("calle"));
                d.setCiudad(resultado.getString("ciudad"));
                d.setNumero(resultado.getString("numero"));
                d.setCodigoPostal(resultado.getString("codigoPostal"));

                u.setDireccion(d);

                clientes.add(u);
            }
        }
        resultado.close();
        conexion.close();
        
        return clientes;
    }
    
    public static boolean actualizarUsuario(Usuario usuarioActualizar)throws SQLException{
        String actualizarUsuario = "UPDATE usuario " +
                        "SET nombre = ?," +
                        "apellidoPaterno = ?," +
                        "apellidoMaterno = ?," +
                        "telefono = ?," +
                        "email = ?," +
                        "nombreUsuario = ?," +
                        "contrasenia = ? " +
                        "WHERE idUsuario = ?";
        
        String actualizarDireccion = "UPDATE direccion " +
                            "SET calle = ?," +
                            "ciudad = ?," +
                            "numero = ?," +
                            "codigoPostal = ? " +
                            "WHERE idUsuario = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement actualizarUsuarioBD = conexion.prepareStatement(actualizarUsuario);
        PreparedStatement actualizarDireccionBD = conexion.prepareStatement(actualizarDireccion);
        /*
        actualizarUsuarioBD.setString(1, usuarioActualizar.nombre);
        actualizarUsuarioBD.setString(2, usuarioActualizar.apellidoPaterno);
        actualizarUsuarioBD.setString(3, usuarioActualizar.apellidoMaterno);
        actualizarUsuarioBD.setString(4, usuarioActualizar.telefono);
        actualizarUsuarioBD.setString(5, usuarioActualizar.email);
        actualizarUsuarioBD.setString(6, usuarioActualizar.nombreUsuario);
        actualizarUsuarioBD.setString(7, usuarioActualizar.contrasenia);
        actualizarUsuarioBD.setString(8, usuarioActualizar.idUsuario);
        
        actualizarDireccionBD.setString(1, usuarioActualizar.direccion.calle);
        actualizarDireccionBD.setString(2, usuarioActualizar.direccion.ciudad);
        actualizarDireccionBD.setString(3, usuarioActualizar.direccion.numero);
        actualizarDireccionBD.setString(4, usuarioActualizar.direccion.codigoPostal);
        actualizarDireccionBD.setString(5, usuarioActualizar.idUsuario);
        */
        Integer usuarioActualizado = actualizarDireccionBD.executeUpdate();
        Integer direccionActualizada = actualizarDireccionBD.executeUpdate();
        
        if((usuarioActualizado > 0) && (direccionActualizada > 0)){
            return true;
        }
        return false;
    }
    
    public static boolean agregarUsuario(Usuario usuarioAgregar)throws SQLException{
        Boolean haPedido = false;
        Boolean esActivo = false;
        
        String insercionUsuario = "INSERT INTO usuario (nombre, apellidoPaterno, apellidoMaterno, telefono, " +
                        "email, haPedido, esEmpleado, esActivo, nombreUsuario, contrasenia) " +
                        "VALUES ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
        
        String insercionDireccion = "INSERT INTO direccion (calle, ciudad, numero, codigoPostal, idUsuario) " +
                         "VALUES ?, ?, ?, ?, ? ";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement insercionUsuarioBD = conexion.prepareStatement(insercionUsuario);
        PreparedStatement insercionDireccionBD = conexion.prepareStatement(insercionDireccion);
        /*
        insercionUsuarioBD.setString(1, usuarioAgregar.nombre);
        insercionUsuarioBD.setString(2, usuarioAgregar.apellidoPaterno);
        insercionUsuarioBD.setString(3, usuarioAgregar.apellidoMaterno);
        insercionUsuarioBD.setString(4, usuarioAgregar.telefono);
        insercionUsuarioBD.setString(5, usuarioAgregar.email);
        insercionUsuarioBD.setBoolean(6,haPedido);
        insercionUsuarioBD.setString(7, usuarioAgregar.esEmpleado);
        insercionUsuarioBD.setBoolean(8,esActivo);
        insercionUsuarioBD.setString(9, usuarioAgregar.nombreUsuario);
        insercionUsuarioBD.setString(10, usuarioAgregar.contrasenia);
        */
        Integer usuarioInsertado = insercionUsuarioBD.executeUpdate();
        /*
        Usuario usuario = buscarUsuarioPorTelefono(usuarioAgregar.telefono);
        
        insercionDireccionBD.setString(1, usuarioAgregar.direccion.calle);
        insercionDireccionBD.setString(2, usuarioAgregar.direccion.ciudad);
        insercionDireccionBD.setString(3, usuarioAgregar.direccion.numero);
        insercionDireccionBD.setString(4, usuarioAgregar.direccion.codigoPostal);
        insercionDireccionBD.setInt(5, usuario.idUsuario);
        */
        Integer direccionInsertada = insercionDireccionBD.executeUpdate();
        
        if((usuarioInsertado > 0) && (direccionInsertada > 0)){
            return true;
        }

        return false;
    }
    
    public static boolean eliminarUsuario(Integer idUsuario)throws SQLException{
        String eliminarUsuario = "DELETE FROM usuario WHERE idUsuario = ?";
        String eliminarDireccion = "DELETE FROM direccion WHERE idUsuario = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        
        PreparedStatement eliminarUsuarioBD = conexion.prepareStatement(eliminarUsuario);
        PreparedStatement eliminarDireccionBD = conexion.prepareStatement(eliminarDireccion);
        
        eliminarUsuarioBD.setInt(1, idUsuario);
        eliminarDireccionBD.setInt(1, idUsuario);
        
        Integer usuarioEliminado = eliminarUsuarioBD.executeUpdate();
        Integer  direccionEliminada = eliminarDireccionBD.executeUpdate();
        
        if((usuarioEliminado > 0) && (direccionEliminada > 0)){
            return true;
        }
        
        return false;
    }
}