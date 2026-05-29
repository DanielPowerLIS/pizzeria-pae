package pizzeria.pae.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pizzeria.pae.modelo.MySQLConnectionManager;
import pizzeria.pae.modelo.beans.Direccion;
import pizzeria.pae.modelo.beans.Usuario;
import pizzeria.pae.utilidades.ConvertidorNombre;

/**
 *
 * @author adair
 */
public class UsuarioDAO {
    public static Usuario buscarUsuarioPorNombre(String nombreCompleto )throws SQLException{
        String nombres[] = ConvertidorNombre.prepararNombre(nombreCompleto);
        
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
     
    public static Usuario buscarUsuarioEmpleado(String contrasenia, String usuario) throws SQLException {
        Usuario usr = new Usuario();
        String consulta = "SELECT * FROM usuario WHERE contrasenia = ?"
                + " AND nombreUsuario = ? AND esEmpleado = ?; ";
        try(
            MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
        ){
            sentencia.setString(2, usuario);
            sentencia.setString(1, contrasenia);
            sentencia.setBoolean(3, true);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()) {
               
                usr.setIdUsuario(resultado.getInt("idUsuario"));
                usr.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                usr.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                usr.setNombre(resultado.getString("nombre"));
                usr.setTelefono(resultado.getString("telefono"));
                usr.setEmail(resultado.getString("email"));
                usr.setHaPedido(resultado.getBoolean("haPedido"));
                usr.setEsEmpleado(true);
                usr.setEsActivo(true);
                usr.setNombreUsuario(usuario);
                usr.setContrasenia("");
            }
            return usr;
        }
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
                        "haPedido = ?," +
                        "esEmpleado = ?," +
                        "esActivo = ?," +
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
        
        actualizarUsuarioBD.setString(1, usuarioActualizar.getNombre());
        actualizarUsuarioBD.setString(2, usuarioActualizar.getApellidoPaterno());
        actualizarUsuarioBD.setString(3, usuarioActualizar.getApellidoMaterno());
        actualizarUsuarioBD.setString(4, usuarioActualizar.getTelefono());
        actualizarUsuarioBD.setString(5, usuarioActualizar.getEmail());
        actualizarUsuarioBD.setBoolean(6, usuarioActualizar.getHaPedido());
        actualizarUsuarioBD.setBoolean(7, usuarioActualizar.getEsEmpleado());
        actualizarUsuarioBD.setBoolean(8, usuarioActualizar.getEsActivo());
        actualizarUsuarioBD.setString(9, usuarioActualizar.getNombreUsuario());
        actualizarUsuarioBD.setString(10, usuarioActualizar.getContrasenia());
        actualizarUsuarioBD.setInt(11, usuarioActualizar.getIdUsuario());
        
        actualizarDireccionBD.setString(1, usuarioActualizar.getDireccion().getCalle());
        actualizarDireccionBD.setString(2, usuarioActualizar.getDireccion().getCiudad());
        actualizarDireccionBD.setString(3, usuarioActualizar.getDireccion().getNumero());
        actualizarDireccionBD.setString(4, usuarioActualizar.getDireccion().getCodigoPostal());
        actualizarDireccionBD.setInt(5, usuarioActualizar.getIdUsuario());
        
        Integer usuarioActualizado = actualizarDireccionBD.executeUpdate();
        Integer direccionActualizada = actualizarDireccionBD.executeUpdate();
        
        conexion.close();
        
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
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        
        String insercionDireccion = "INSERT INTO direccion (calle, ciudad, numero, codigoPostal, idUsuario) " +
                         "VALUES (?, ?, ?, ?, ?) ";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement insercionUsuarioBD = conexion.prepareStatement(insercionUsuario);
        PreparedStatement insercionDireccionBD = conexion.prepareStatement(insercionDireccion);
        
        insercionUsuarioBD.setString(1, usuarioAgregar.getNombre());
        insercionUsuarioBD.setString(2, usuarioAgregar.getApellidoPaterno());
        insercionUsuarioBD.setString(3, usuarioAgregar.getApellidoMaterno());
        insercionUsuarioBD.setString(4, usuarioAgregar.getTelefono());
        insercionUsuarioBD.setString(5, usuarioAgregar.getEmail());
        insercionUsuarioBD.setBoolean(6, usuarioAgregar.getHaPedido());
        insercionUsuarioBD.setBoolean(7, usuarioAgregar.getEsEmpleado());
        insercionUsuarioBD.setBoolean(8, usuarioAgregar.getEsActivo());
        insercionUsuarioBD.setString(9, usuarioAgregar.getNombreUsuario());
        insercionUsuarioBD.setString(10, usuarioAgregar.getContrasenia());
        
        Integer usuarioInsertado = insercionUsuarioBD.executeUpdate();
        
        Usuario usuario = buscarUsuarioPorTelefono(usuarioAgregar.getTelefono());
        
        insercionDireccionBD.setString(1, usuarioAgregar.getDireccion().getCalle());
        insercionDireccionBD.setString(2, usuarioAgregar.getDireccion().getCiudad());
        insercionDireccionBD.setString(3, usuarioAgregar.getDireccion().getNumero());
        insercionDireccionBD.setString(4, usuarioAgregar.getDireccion().getCodigoPostal());
        insercionDireccionBD.setInt(5, usuario.getIdUsuario());
        
        Integer direccionInsertada = insercionDireccionBD.executeUpdate();
        
        conexion.close();
        
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
        
        conexion.close();
        
        if((usuarioEliminado > 0) && (direccionEliminada > 0)){
            return true;
        }
        
        return false;
    }
    
    public static Usuario buscarUsuario(Integer idUsuario)throws SQLException{
        String consulta = "SELECT * " +
                    "FROM usuario JOIN direccion ON usuario.idUsuario = direccion.idUsuario " +
                    "WHERE usuario.idUsuario = ?";
        
        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);
        
        sentenciaBD.setInt(1, idUsuario);
        
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
}