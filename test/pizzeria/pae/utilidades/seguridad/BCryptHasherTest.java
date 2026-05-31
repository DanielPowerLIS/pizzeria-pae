package pizzeria.pae.utilidades.seguridad;

import static org.junit.Assert.*;
import org.junit.Test;

public class BCryptHasherTest {

    @Test
    public void testGenerarContraseniaHash_Valido() {
        String contraseniaPlana = "admin123";
        String hashGenerado = BCryptHasher.generarContraseniaHash(contraseniaPlana);

        assertNotNull("El hash generado no debe ser nulo", hashGenerado);
        assertNotEquals("El hash no debe ser igual a la contraseña plana", contraseniaPlana, hashGenerado);
        assertTrue("El hash de autenticación debe iniciar con el prefijo oficial de BCrypt ($2a$)",
                hashGenerado.startsWith("$2a$"));
    }

    @Test
    public void testVerificarContraseniaHash_Correcto() {
        String contraseniaPlana = "cajero2026";
        String hashGenerado = BCryptHasher.generarContraseniaHash(contraseniaPlana);

        assertTrue("Debe permitir el acceso cuando las contraseñas coinciden",
                BCryptHasher.verificarContraseniaHash(contraseniaPlana, hashGenerado));
    }

    @Test
    public void testVerificarContraseniaHash_Incorrecto() {
        String contraseniaPlana = "cajero2026";
        String hashGenerado = BCryptHasher.generarContraseniaHash(contraseniaPlana);

        assertFalse("Debe bloquear el acceso si se introduce una contraseña incorrecta",
                BCryptHasher.verificarContraseniaHash("passwordIncorrecto", hashGenerado));
    }
}
