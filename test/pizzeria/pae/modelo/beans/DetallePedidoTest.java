package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DetallePedidoTest {

    private DetallePedido detalle;

    @Before
    public void setup() {
        detalle = new DetallePedido();
    }

    @Test
    public void testCalcularSubtotal() {
        BigDecimal precio = new BigDecimal("120.00");

        Producto producto = new Producto();
        producto.setPrecio(precio);

        detalle.setProducto(producto);
        detalle.setCantidad(3);

        detalle.setSubtotal(detalle.calcularSubtotal());

        assertEquals(new BigDecimal("360.00"), detalle.getSubtotal());
        assertEquals(3, detalle.getCantidad());
    }

    @Test
    public void testSetGetProductoYCantidad() {
        Producto producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombre("Pizza Pepperoni");

        detalle.setProducto(producto);

        assertEquals(producto, detalle.getProducto());
        assertEquals("Pizza Pepperoni", detalle.getProducto().getNombre());
    }
}