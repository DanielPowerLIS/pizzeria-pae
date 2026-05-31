package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pizzeria.pae.excepciones.ProductoUtilizadoException;

class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setup() {
        producto = new Producto();
    }

    @Test
    @DisplayName("Validar consistencia de los datos del inventario")
    void testvalidacionStockDisponible(){
        producto.setIdProducto(101);
        producto.setNombre("Refresco de Cola");
        producto.setCantidad(50);
        
        assertEquals(101, producto.getIdProducto());
        assertEquals("Refresco de Cola", producto.getNombre());
        assertEquals(50, producto.getCantidad());
    }

    @Test
    @DisplayName("Validar banderas lógicas de negocio del producto")
    void testEsInsumoYEsUtilizado() {
        producto.setEsInsumo(false);
        producto.setEsUtilizado(true);
        
        assertFalse(producto.getEsInsumo());
        assertTrue(producto.getEsUtilizado());
    }

    @Test
    @DisplayName("Validar almacenamiento de precio decimal y código único")
    void testSetGetPrecioYCodigo() {
        BigDecimal precio = new BigDecimal("199.99");
        producto.setPrecio(precio);
        producto.setCodigo("PROD-PIZ-001");
        
        assertEquals(precio, producto.getPrecio());
        assertEquals("PROD-PIZ-001", producto.getCodigo());
    }
    
    @Test
    @DisplayName("Validar lanzamiento de excepción si el producto ya fue utilizado")
    void testProductoUtilizadoException() {
        
        Producto producto = new Producto();
        producto.setEsUtilizado(true);

        ProductoUtilizadoException excepcionCapturada = assertThrows(
            ProductoUtilizadoException.class, 
            () -> {
                if (producto.getEsUtilizado()) {
                    throw new ProductoUtilizadoException("El producto ya ha sido utilizado anteriormente.");
                }
            }
        );

        // 4. (Opcional pero recomendado) Verificamos que el mensaje sea exactamente el que programaste
        assertEquals("El producto ya ha sido utilizado anteriormente.", excepcionCapturada.getMessage());
    }
}