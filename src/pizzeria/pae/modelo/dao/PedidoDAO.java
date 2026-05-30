package pizzeria.pae.modelo.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pizzeria.pae.modelo.MySQLConnectionManager;
import pizzeria.pae.modelo.beans.DetallePedido;
import pizzeria.pae.modelo.beans.Direccion;
import pizzeria.pae.modelo.beans.Pedido;
import pizzeria.pae.modelo.beans.Producto;
import pizzeria.pae.modelo.beans.Usuario;

/**
 *
 * @author adair
 */
public class PedidoDAO {

    public static List<Pedido> buscarPedidoPorFecha(Date fecha) throws SQLException {
        String consulta = "SELECT "
                + "p.idPedido, p.fecha, p.estado, p.totalAPagar, "
                + "u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.telefono, "
                + "u.email, u.haPedido, u.esEmpleado, u.esActivo, u.eliminado, u.nombreUsuario, u.contrasenia, u.rol, "
                + "d.idDireccion, d.calle, d.ciudad, d.numero, d.codigoPostal "
                + "FROM pedido p "
                + "INNER JOIN usuario u ON p.idUsuario = u.idUsuario "
                + "INNER JOIN direccion d ON u.idUsuario = d.idUsuario "
                + "WHERE p.fecha = ?";

        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);

        sentenciaBD.setDate(1, fecha);

        ResultSet resultado = sentenciaBD.executeQuery();

        List<Pedido> pedidos = null;

        if (resultado != null) {
            pedidos = new ArrayList<>();
            while (resultado.next()) {
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
                u.setEliminado(resultado.getBoolean("eliminado"));
                u.setNombreUsuario(resultado.getString("nombreUsuario"));
                u.setContrasenia(resultado.getString("contrasenia"));
                u.setRol(resultado.getString("rol"));

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

    private static List<DetallePedido> obtenerDetalles(Integer idPedido) throws SQLException {
        String consulta = "SELECT dp.idPedido, dp.idProducto, dp.cantidad AS cantidadPedida, dp.subTotal, "
                + "p.nombre, p.codigo, p.esInsumo, p.esUtilizado, p.cantidad AS cantidadInventario, "
                + "p.foto, p.restricciones, p.precio, p.descripcion "
                + "FROM detallepedido dp JOIN producto p ON dp.idProducto = p.idProducto "
                + "WHERE dp.idPedido = ?";

        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);

        sentenciaBD.setInt(1, idPedido);

        ResultSet resultado = sentenciaBD.executeQuery();

        List<DetallePedido> detalles = null;

        if (resultado != null) {
            detalles = new ArrayList<>();
            while (resultado.next()) {
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
                pro.setFoto(resultado.getBytes("foto"));
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

    public static List<Usuario> obtenerClientes() throws SQLException {

        String consulta =
                "SELECT idUsuario, nombre, apellidoPaterno, apellidoMaterno "
                + "FROM usuario "
                + "WHERE esEmpleado = FALSE";

        List<Usuario> clientes = new ArrayList<>();

        try (
            MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery()
        ) {

            while (resultado.next()) {

                Usuario cliente = new Usuario();

                cliente.setIdUsuario(resultado.getInt("idUsuario"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                cliente.setApellidoMaterno(resultado.getString("apellidoMaterno"));

                clientes.add(cliente);
            }
        }

        return clientes;
    }
    
    public static List<Pedido> buscarPedidosPorUsuario(int idUsuario) throws SQLException {

        String consulta =
                "SELECT p.idPedido, p.fecha, p.estado, p.totalAPagar, "
              + "u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, "
              + "d.idDireccion, d.calle, d.ciudad, d.numero, d.codigoPostal "
              + "FROM pedido p "
              + "INNER JOIN usuario u ON p.idUsuario = u.idUsuario "
              + "INNER JOIN direccion d ON u.idUsuario = d.idUsuario "
              + "WHERE p.idUsuario = ?";

        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentencia = conexion.prepareStatement(consulta);

        sentencia.setInt(1, idUsuario);

        ResultSet resultado = sentencia.executeQuery();

        List<Pedido> pedidos = new ArrayList<>();

        while (resultado.next()) {

            Pedido p = new Pedido();
            p.setIdPedido(resultado.getInt("idPedido"));
            p.setFecha(resultado.getDate("fecha").toLocalDate());
            p.setEstado(resultado.getString("estado"));
            p.setTotal(resultado.getBigDecimal("totalAPagar"));

            Usuario u = new Usuario();
            u.setIdUsuario(resultado.getInt("idUsuario"));
            u.setNombre(resultado.getString("nombre"));
            u.setApellidoPaterno(resultado.getString("apellidoPaterno"));
            u.setApellidoMaterno(resultado.getString("apellidoMaterno"));

            p.setCliente(u);

            pedidos.add(p);
        }

        resultado.close();
        sentencia.close();
        conexion.close();

        return pedidos;
    }
    public static List<Pedido> buscarPedidoPorEstado(String estado) throws SQLException {
        String consulta = "SELECT "
                + "p.idPedido, p.fecha, p.estado, p.totalAPagar, "
                + "u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.telefono, "
                + "u.email, u.haPedido, u.esEmpleado, u.esActivo, u.eliminado, u.nombreUsuario, u.contrasenia, u.rol, "
                + "d.idDireccion, d.calle, d.ciudad, d.numero, d.codigoPostal "
                + "FROM pedido p "
                + "INNER JOIN usuario u ON p.idUsuario = u.idUsuario "
                + "INNER JOIN direccion d ON u.idUsuario = d.idUsuario "
                + "WHERE p.estado = ?";

        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);

        sentenciaBD.setString(1, estado);

        ResultSet resultado = sentenciaBD.executeQuery();

        List<Pedido> pedidos = null;

        if (resultado != null) {
            pedidos = new ArrayList<>();
            while (resultado.next()) {
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
                u.setEliminado(resultado.getBoolean("eliminado"));
                u.setNombreUsuario(resultado.getString("nombreUsuario"));
                u.setContrasenia(resultado.getString("contrasenia"));
                u.setRol(resultado.getString("rol"));

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

    public static List<Pedido> buscarPedidosPorUsuario(String textoBusqueda)
            throws SQLException {

        String consulta
                = "SELECT "
                + "p.idPedido, p.fecha, p.estado, p.totalAPagar, "
                + "u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.telefono, "
                + "u.email, u.haPedido, u.esEmpleado, u.esActivo, u.eliminado, u.nombreUsuario, u.contrasenia, u.rol, "
                + "d.idDireccion, d.calle, d.ciudad, d.numero, d.codigoPostal "
                + "FROM pedido p "
                + "INNER JOIN usuario u ON p.idUsuario = u.idUsuario "
                + "INNER JOIN direccion d ON u.idUsuario = d.idUsuario "
                + "WHERE CONCAT(u.nombre, ' ', u.apellidoPaterno, ' ', u.apellidoMaterno) LIKE ?";

        MySQLConnectionManager conexion
                = MySQLConnectionManager.buildConnection();

        PreparedStatement sentenciaBD
                = conexion.prepareStatement(consulta);

        sentenciaBD.setString(
                1,
                "%" + textoBusqueda + "%"
        );

        ResultSet resultado = sentenciaBD.executeQuery();

        List<Pedido> pedidos = new ArrayList<>();

        while (resultado.next()) {

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
            u.setEliminado(resultado.getBoolean("eliminado"));
            u.setNombreUsuario(resultado.getString("nombreUsuario"));
            u.setContrasenia(resultado.getString("contrasenia"));
            u.setRol(resultado.getString("rol"));

            u.setDireccion(d);
            p.setCliente(u);

            p.setDetallePedido(
                    obtenerDetalles(p.getIdPedido())
            );

            pedidos.add(p);
        }

        resultado.close();
        sentenciaBD.close();
        conexion.close();

        return pedidos;
    }

    public static List<Pedido> obtenerPedidos() throws SQLException {
        String consulta = "SELECT "
                + "p.idPedido, p.fecha, p.estado, p.totalAPagar, "
                + "u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.telefono, "
                + "u.email, u.haPedido, u.esEmpleado, u.esActivo, u.eliminado, u.nombreUsuario, u.contrasenia, u.rol, "
                + "d.idDireccion, d.calle, d.ciudad, d.numero, d.codigoPostal "
                + "FROM pedido p "
                + "INNER JOIN usuario u ON p.idUsuario = u.idUsuario "
                + "INNER JOIN direccion d ON u.idUsuario = d.idUsuario";

        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement sentenciaBD = conexion.prepareStatement(consulta);

        ResultSet resultado = sentenciaBD.executeQuery();

        List<Pedido> pedidos = null;

        if (resultado != null) {
            pedidos = new ArrayList<>();
            while (resultado.next()) {
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
                u.setEliminado(resultado.getBoolean("eliminado"));
                u.setNombreUsuario(resultado.getString("nombreUsuario"));
                u.setContrasenia(resultado.getString("contrasenia"));
                u.setRol(resultado.getString("rol"));

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

    public static Boolean agregarPedido(Pedido pedidoAgregar) throws SQLException {

        String sqlPedido = "INSERT INTO pedido(fecha, estado, totalAPagar, idUsuario) VALUES (?, ?, ?, ?)";

        String sqlDetalle = "INSERT INTO detallepedido(idPedido, idProducto, cantidad, subTotal) VALUES (?, ?, ?, ?)";

        try (
                MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection()) {

            conexion.setAutoCommit(false);
            try (
                    PreparedStatement psPedido
                    = conexion.prepareStatement(
                            sqlPedido,
                            Statement.RETURN_GENERATED_KEYS
                    )) {

                        psPedido.setDate(1, Date.valueOf(pedidoAgregar.getFecha()));
                        psPedido.setString(2, pedidoAgregar.getEstado());
                        psPedido.setBigDecimal(3, pedidoAgregar.getTotal());
                        psPedido.setInt(4, pedidoAgregar.getCliente().getIdUsuario());
                        int filasPedido = psPedido.executeUpdate();

                        if (filasPedido == 0) {
                            conexion.rollback();
                            return false;
                        }

                        ResultSet keys = psPedido.getGeneratedKeys();

                        if (!keys.next()) {
                            conexion.rollback();
                            return false;
                        }

                        int idPedido = keys.getInt(1);

                        pedidoAgregar.setIdPedido(idPedido);

                        try (
                                PreparedStatement psDetalle
                                = conexion.prepareStatement(sqlDetalle)) {

                            for (DetallePedido detalle
                                    : pedidoAgregar.getDetallePedido()) {

                                psDetalle.setInt(1, idPedido);
                                psDetalle.setInt(2, detalle.getProducto().getIdProducto());
                                psDetalle.setInt(3, detalle.getCantidad());
                                psDetalle.setBigDecimal(4, detalle.getSubtotal());

                                psDetalle.addBatch();
                            }

                            psDetalle.executeBatch();
                            
                            conexion.commit();
                            
                        }

                        UsuarioDAO.marcarComoHaPedido(pedidoAgregar.getCliente().getIdUsuario());

                        return true;

                    } catch (SQLException ex) {

                        conexion.rollback();

                        throw ex;
                    }
        }
    }

    public static Boolean actualizarPedido(Pedido pedidoActualizar) throws SQLException {
        String actualizar = "UPDATE pedido "
                + "SET estado = ?, totalAPagar = ? "
                + "WHERE idPedido = ?";

        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement actualizarBD = conexion.prepareStatement(actualizar);

        actualizarBD.setString(1, pedidoActualizar.getEstado());
        actualizarBD.setBigDecimal(2, pedidoActualizar.getTotal());
        actualizarBD.setInt(3, pedidoActualizar.getIdPedido());

        Integer pedidoActualizado = actualizarBD.executeUpdate();

        actualizarBD.close();
        conexion.close();

        return pedidoActualizado > 0;
    }

    public static Boolean agregarDetalles(List<DetallePedido> agregarDetalles) throws SQLException {
        String insercionDetalles = "INSERT INTO detallepedido (idPedido, idProducto, cantidad, subTotal) "
                + "VALUES (?, ?, ?, ?)";

        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement insercionDetallesBD = conexion.prepareStatement(insercionDetalles);

        Integer numDetalles = agregarDetalles.size();
        Integer detallesInsertados = 0;

        for (DetallePedido dp : agregarDetalles) {
            insercionDetallesBD.setInt(1, dp.getIdPedido());
            insercionDetallesBD.setInt(2, dp.getProducto().getIdProducto());
            insercionDetallesBD.setInt(3, dp.getCantidad());
            insercionDetallesBD.setBigDecimal(4, dp.getSubtotal());

            detallesInsertados += insercionDetallesBD.executeUpdate();
        }

        insercionDetallesBD.close();
        conexion.close();

        return detallesInsertados == numDetalles;
    }

    public static Boolean quitarDetalles(List<DetallePedido> eliminarDetalles) throws SQLException {
        String quitarDetalles = "DELETE FROM detallepedido WHERE idPedido = ? AND idProducto = ?";

        MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection();
        PreparedStatement quitarDetallesBD = conexion.prepareStatement(quitarDetalles);

        Integer numDetalles = eliminarDetalles.size();
        Integer detallesEliminados = 0;

        for (DetallePedido dp : eliminarDetalles) {
            quitarDetallesBD.setInt(1, dp.getIdPedido());
            quitarDetallesBD.setInt(2, dp.getProducto().getIdProducto());

            detallesEliminados += quitarDetallesBD.executeUpdate();
        }

        quitarDetallesBD.close();
        conexion.close();

        return detallesEliminados == numDetalles;
    }

    public static Boolean actualizarEstatusPedido(Integer idPedido, String estadoNuevo) throws SQLException {
        int pedidoEliminado = 0;
        String eliminarPedido = "UPDATE pedido SET estado = ? WHERE idPedido = ?";

        try (
                MySQLConnectionManager conexion = MySQLConnectionManager.buildConnection(); PreparedStatement eliminarPedidoBD = conexion.prepareStatement(eliminarPedido);) {
            eliminarPedidoBD.setInt(2, idPedido);
            eliminarPedidoBD.setString(1, estadoNuevo);

            pedidoEliminado = eliminarPedidoBD.executeUpdate();
        }

        return pedidoEliminado > 0;
    }
}
