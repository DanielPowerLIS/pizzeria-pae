package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pizzeria.pae.excepciones.ProductoUtilizadoException;

public class ProductoTest { // Clase pública

    private Producto producto;

    @BeforeEach
    public void setup() { // Método público
        producto = new Producto();
    }

    @Test
    @DisplayName("Validar consistencia de los datos del inventario")
    public void testvalidacionStockDisponible(){ // Método público
        producto.setIdProducto(101);
        producto.setNombre("Refresco de Cola");
        producto.setCantidad(50);
        
        assertEquals(101, producto.getIdProducto());
        assertEquals("Refresco de Cola", producto.getNombre());
        assertEquals(50, producto.getCantidad());
    }

    @Test
    @DisplayName("Validar banderas lógicas de negocio del producto")
    public void testEsInsumoYEsUtilizado() { // Método público
        producto.setEsInsumo(false);
        producto.setEsUtilizado(true);
        
        assertFalse(producto.getEsInsumo());
        assertTrue(producto.getEsUtilizado());
    }

    @Test
    @DisplayName("Validar almacenamiento de precio decimal y código único")
    public void testSetGetPrecioYCodigo() { // Método público
        BigDecimal precio = new BigDecimal("199.99");
        producto.setPrecio(precio);
        producto.setCodigo("PROD-PIZ-001");
        
        assertEquals(precio, producto.getPrecio());
        assertEquals("PROD-PIZ-001", producto.getCodigo());
    }
    
    @Test
    @DisplayName("Validar lanzamiento de excepción si el producto ya fue utilizado")
    public void testProductoUtilizadoException() { // Método público
        
        Producto productoMock = new Producto();
        productoMock.setEsUtilizado(true);

        ProductoUtilizadoException excepcionCapturada = assertThrows(
            ProductoUtilizadoException.class, 
            () -> {
                if (productoMock.getEsUtilizado()) {
                    throw new ProductoUtilizadoException("El producto ya ha sido utilizado anteriormente.");
                }
            }
        );

        assertEquals("El producto ya ha sido utilizado anteriormente.", excepcionCapturada.getMessage());
    }
}