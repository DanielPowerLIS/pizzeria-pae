package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DetallePedidoTest {

    private DetallePedido detalle;

    @BeforeEach
    void setup() {
        detalle = new DetallePedido();
    }

    @Test
    @DisplayName("Validar asignación y cálculo manual de subtotal")
    void testCalcularSubtotal() {
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
    @DisplayName("Validar acoplamiento con la entidad Producto")
    void testSetGetProductoYCantidad() {
        Producto producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombre("Pizza Pepperoni");
        detalle.setProducto(producto);
        
        assertEquals(producto, detalle.getProducto());
        assertEquals("Pizza Pepperoni", detalle.getProducto().getNombre());
    }
}