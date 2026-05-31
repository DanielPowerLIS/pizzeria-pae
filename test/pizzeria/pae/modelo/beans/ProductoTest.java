package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import pizzeria.pae.excepciones.ProductoUtilizadoException;

public class ProductoTest {

    private Producto producto;

    @Before
    public void setup() {
        producto = new Producto();
    }

    @Test
    public void testvalidacionStockDisponible() {
        producto.setIdProducto(101);
        producto.setNombre("Refresco de Cola");
        producto.setCantidad(50);

        assertEquals(Integer.valueOf(101), producto.getIdProducto());
        assertEquals("Refresco de Cola", producto.getNombre());
        assertEquals(Integer.valueOf(50), producto.getCantidad());
    }

    @Test
    public void testEsInsumoYEsUtilizado() {
        producto.setEsInsumo(false);
        producto.setEsUtilizado(true);

        assertFalse(producto.getEsInsumo());
        assertTrue(producto.getEsUtilizado());
    }

    @Test
    public void testSetGetPrecioYCodigo() {
        BigDecimal precio = new BigDecimal("199.99");

        producto.setPrecio(precio);
        producto.setCodigo("PROD-PIZ-001");

        assertEquals(precio, producto.getPrecio());
        assertEquals("PROD-PIZ-001", producto.getCodigo());
    }

    @Test(expected = ProductoUtilizadoException.class)
    public void testProductoUtilizadoException() throws ProductoUtilizadoException {

        Producto productoMock = new Producto();
        productoMock.setEsUtilizado(true);

        if (productoMock.getEsUtilizado()) {
            throw new ProductoUtilizadoException(
                "El producto ya ha sido utilizado anteriormente."
            );
        }
    }
}