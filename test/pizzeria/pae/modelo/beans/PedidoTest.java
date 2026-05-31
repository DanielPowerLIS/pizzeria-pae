package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    private Pedido pedido;

    @BeforeEach
    void setup() {
        pedido = new Pedido();
        pedido.setDetallePedido(new ArrayList<>());
    }

    @Test
    @DisplayName("Validar sumatoria total con múltiples detalles válidos")
    void testCalcularTotal_MultiplesProductos() {
        DetallePedido detalle1 = new DetallePedido();
        detalle1.setSubtotal(new BigDecimal("150.50"));

        DetallePedido detalle2 = new DetallePedido();
        detalle2.setSubtotal(new BigDecimal("49.50"));

        pedido.agregarDetalle(detalle1);
        pedido.agregarDetalle(detalle2);
        pedido.calcularTotal();

        assertEquals(new BigDecimal("200.00"), pedido.getTotal());
    }

    @Test
    @DisplayName("Validar que un pedido vacio inicialice el total en cero")
    void testCalcularTotal_Pedidovacio() {
        pedido.calcularTotal();
        assertEquals(BigDecimal.ZERO, pedido.getTotal());
    }

    @Test
    @DisplayName("Validar adición e incremento de tamaño en la colección")
    void testAgregarDetalle_IncrementaTamano() {
        DetallePedido detalle = new DetallePedido();
        pedido.agregarDetalle(detalle);
        assertEquals(1, pedido.getDetallePedido().size());
        assertTrue(pedido.getDetallePedido().contains(detalle));
    }

    @Test
    @DisplayName("Validar control de cambios de estado y fecha del pedido")
    void testSetGetFechaYEstado() {
        LocalDate fecha = LocalDate.of(2026, 5, 30);
        pedido.setFecha(fecha);
        pedido.setEstado("APROBADO");
        assertEquals(fecha, pedido.getFecha());
        assertEquals("APROBADO", pedido.getEstado());
    }
}
