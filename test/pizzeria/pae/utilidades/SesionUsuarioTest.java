package pizzeria.pae.utilidades;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pizzeria.pae.modelo.beans.Usuario;

public class SesionUsuarioTest {

    @Before
    public void reiniciarSesion() {
        SesionUsuario.limpiarSesion();
    }

    @Test
    public void testManejoSesionActual() {
        assertNull("Al iniciar, la sesión no debe tener ningún usuario cargado",
                SesionUsuario.getUsuarioActual());

        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setIdUsuario(2);
        usuarioPrueba.setNombreUsuario("alopez_emp");

        SesionUsuario.setUsuarioActual(usuarioPrueba);

        assertNotNull("La sesión ya no debe ser nula tras la asignación",
                SesionUsuario.getUsuarioActual());

        assertEquals(2, (int) SesionUsuario.getUsuarioActual().getIdUsuario());
        assertEquals("alopez_emp", SesionUsuario.getUsuarioActual().getNombreUsuario());
    }

    @Test
    public void testLimpiarSesion_EstableceNulo() {
        Usuario usuarioPrueba = new Usuario();
        SesionUsuario.setUsuarioActual(usuarioPrueba);

        assertNotNull("Se debió guardar el usuario temporal", SesionUsuario.getUsuarioActual());

        SesionUsuario.limpiarSesion();

        assertNull("La sesión debe ser destruida al limpiarla",
                SesionUsuario.getUsuarioActual());
    }
}
