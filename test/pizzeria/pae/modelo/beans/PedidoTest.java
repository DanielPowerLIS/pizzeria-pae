/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jdani
 */
public class PedidoTest {
    
    public PedidoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of calcularTotal method, of class Pedido.
     */
    @Test
    public void testCalcularTotal() {
        System.out.println("calcularTotal");
        Pedido instance = new Pedido();
        BigDecimal expResult = null;
        BigDecimal result = instance.calcularTotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarDetalle method, of class Pedido.
     */
    @Test
    public void testAgregarDetalle() {
        System.out.println("agregarDetalle");
        DetallePedido detalle = null;
        Pedido instance = new Pedido();
        instance.agregarDetalle(detalle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quitarDetalle method, of class Pedido.
     */
    @Test
    public void testQuitarDetalle() {
        System.out.println("quitarDetalle");
        DetallePedido detalle = null;
        Pedido instance = new Pedido();
        instance.quitarDetalle(detalle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdPedido method, of class Pedido.
     */
    @Test
    public void testGetIdPedido() {
        System.out.println("getIdPedido");
        Pedido instance = new Pedido();
        int expResult = 0;
        int result = instance.getIdPedido();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdPedido method, of class Pedido.
     */
    @Test
    public void testSetIdPedido() {
        System.out.println("setIdPedido");
        int idPedido = 0;
        Pedido instance = new Pedido();
        instance.setIdPedido(idPedido);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCliente method, of class Pedido.
     */
    @Test
    public void testGetCliente() {
        System.out.println("getCliente");
        Pedido instance = new Pedido();
        Usuario expResult = null;
        Usuario result = instance.getCliente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreCliente method, of class Pedido.
     */
    @Test
    public void testGetNombreCliente() {
        System.out.println("getNombreCliente");
        Pedido instance = new Pedido();
        String expResult = "";
        String result = instance.getNombreCliente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCliente method, of class Pedido.
     */
    @Test
    public void testSetCliente() {
        System.out.println("setCliente");
        Usuario cliente = null;
        Pedido instance = new Pedido();
        instance.setCliente(cliente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFecha method, of class Pedido.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        Pedido instance = new Pedido();
        LocalDate expResult = null;
        LocalDate result = instance.getFecha();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFecha method, of class Pedido.
     */
    @Test
    public void testSetFecha() {
        System.out.println("setFecha");
        LocalDate fecha = null;
        Pedido instance = new Pedido();
        instance.setFecha(fecha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetallePedido method, of class Pedido.
     */
    @Test
    public void testGetDetallePedido() {
        System.out.println("getDetallePedido");
        Pedido instance = new Pedido();
        List<DetallePedido> expResult = null;
        List<DetallePedido> result = instance.getDetallePedido();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDetallePedido method, of class Pedido.
     */
    @Test
    public void testSetDetallePedido() {
        System.out.println("setDetallePedido");
        List<DetallePedido> detallePedido = null;
        Pedido instance = new Pedido();
        instance.setDetallePedido(detallePedido);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotal method, of class Pedido.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        Pedido instance = new Pedido();
        BigDecimal expResult = null;
        BigDecimal result = instance.getTotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotal method, of class Pedido.
     */
    @Test
    public void testSetTotal() {
        System.out.println("setTotal");
        BigDecimal total = null;
        Pedido instance = new Pedido();
        instance.setTotal(total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEstado method, of class Pedido.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Pedido instance = new Pedido();
        String expResult = "";
        String result = instance.getEstado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEstado method, of class Pedido.
     */
    @Test
    public void testSetEstado() {
        System.out.println("setEstado");
        String estado = "";
        Pedido instance = new Pedido();
        instance.setEstado(estado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Pedido.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Pedido instance = new Pedido();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
