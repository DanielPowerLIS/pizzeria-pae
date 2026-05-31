package pizzeria.pae.modelo.beans;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import pizzeria.pae.excepciones.UsuarioNoEncontradoException;

public class UsuarioTest {

    private Usuario usuario;

    @Before
    public void setUp() {
        usuario = new Usuario();
    }

    @Test
    public void testGetNombreCompleto() {
        usuario.setNombre("Gabriel");
        usuario.setApellidoPaterno("Hernández");
        usuario.setApellidoMaterno("Martínez");

        assertEquals(
            "Gabriel Hernández Martínez",
            usuario.getNombreCompleto()
        );
    }

    @Test
    public void testGetTipoEmpleado() {
        usuario.setEsEmpleado(true);

        assertEquals(
            "Empleado",
            usuario.getTipo()
        );
    }

    @Test
    public void testGetTipoCliente() {
        usuario.setEsEmpleado(false);

        assertEquals(
            "Cliente",
            usuario.getTipo()
        );
    }

    @Test(expected = UsuarioNoEncontradoException.class)
    public void testUsuarioNoEncontradoException()
            throws UsuarioNoEncontradoException {

        Usuario usuarioPrueba = null;

        if (usuarioPrueba == null) {
            throw new UsuarioNoEncontradoException(
                "No se encontró el usuario."
            );
        }
    }
}