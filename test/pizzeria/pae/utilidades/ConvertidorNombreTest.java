package pizzeria.pae.utilidades;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConvertidorNombreTest {

    @Test
    public void testPrepararNombre() {

        String[] resultado =
                ConvertidorNombre.prepararNombre(
                        "Gabriel Hernández Martínez"
                );

        assertNotNull(resultado);
        assertEquals("Gabriel", resultado[0]);
        assertEquals("Hernández", resultado[1]);
        assertEquals("Martínez", resultado[2]);
    }
}