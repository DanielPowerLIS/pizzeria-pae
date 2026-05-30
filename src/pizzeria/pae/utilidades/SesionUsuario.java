package pizzeria.pae.utilidades;

import pizzeria.pae.modelo.beans.Usuario;

/**
 *
 * @author Gabriel Hernández Martínez
 */
public class SesionUsuario {

    private static Usuario usuarioActual;

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static void limpiarSesion() {
        usuarioActual = null;
    }
}
