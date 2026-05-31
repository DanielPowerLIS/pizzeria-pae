package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PedidoTest {

    private Pedido pedido;

    @Before
    public void setup() {
        pedido = new Pedido();
        pedido.setDetallePedido(new ArrayList<>());
    }

    @Test
    public void testCalcularTotal_MultiplesProductos() {
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
    public void testCalcularTotal_Pedidovacio() {
        pedido.calcularTotal();

        assertEquals(BigDecimal.ZERO, pedido.getTotal());
    }

    @Test
    public void testAgregarDetalle_IncrementaTamano() {
        DetallePedido detalle = new DetallePedido();

        pedido.agregarDetalle(detalle);

        assertEquals(1, pedido.getDetallePedido().size());
        assertTrue(pedido.getDetallePedido().contains(detalle));
    }

    @Test
    public void testSetGetFechaYEstado() {
        LocalDate fecha = LocalDate.of(2026, 5, 30);

        pedido.setFecha(fecha);
        pedido.setEstado("APROBADO");

        assertEquals(fecha, pedido.getFecha());
        assertEquals("APROBADO", pedido.getEstado());
    }
}