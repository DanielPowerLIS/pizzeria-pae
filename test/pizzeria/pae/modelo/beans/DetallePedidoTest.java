package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;
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
public class DetallePedidoTest {
    
    public DetallePedidoTest() {
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
     * Test of calcularSubtotal method, of class DetallePedido.
     */
    @Test
    public void testCalcularSubtotal() {
        System.out.println("calcularSubtotal");
        DetallePedido instance = new DetallePedido();
        BigDecimal expResult = null;
        BigDecimal result = instance.calcularSubtotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdPedido method, of class DetallePedido.
     */
    @Test
    public void testGetIdPedido() {
        System.out.println("getIdPedido");
        DetallePedido instance = new DetallePedido();
        Integer expResult = null;
        Integer result = instance.getIdPedido();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdPedido method, of class DetallePedido.
     */
    @Test
    public void testSetIdPedido() {
        System.out.println("setIdPedido");
        Integer idPedido = null;
        DetallePedido instance = new DetallePedido();
        instance.setIdPedido(idPedido);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProducto method, of class DetallePedido.
     */
    @Test
    public void testGetProducto() {
        System.out.println("getProducto");
        DetallePedido instance = new DetallePedido();
        Producto expResult = null;
        Producto result = instance.getProducto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProducto method, of class DetallePedido.
     */
    @Test
    public void testSetProducto() {
        System.out.println("setProducto");
        Producto producto = null;
        DetallePedido instance = new DetallePedido();
        instance.setProducto(producto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCantidad method, of class DetallePedido.
     */
    @Test
    public void testGetCantidad() {
        System.out.println("getCantidad");
        DetallePedido instance = new DetallePedido();
        int expResult = 0;
        int result = instance.getCantidad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCantidad method, of class DetallePedido.
     */
    @Test
    public void testSetCantidad() {
        System.out.println("setCantidad");
        int cantidad = 0;
        DetallePedido instance = new DetallePedido();
        instance.setCantidad(cantidad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubtotal method, of class DetallePedido.
     */
    @Test
    public void testGetSubtotal() {
        System.out.println("getSubtotal");
        DetallePedido instance = new DetallePedido();
        BigDecimal expResult = null;
        BigDecimal result = instance.getSubtotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSubtotal method, of class DetallePedido.
     */
    @Test
    public void testSetSubtotal() {
        System.out.println("setSubtotal");
        BigDecimal subtotal = null;
        DetallePedido instance = new DetallePedido();
        instance.setSubtotal(subtotal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrecioUnitario method, of class DetallePedido.
     */
    @Test
    public void testGetPrecioUnitario() {
        System.out.println("getPrecioUnitario");
        DetallePedido instance = new DetallePedido();
        BigDecimal expResult = null;
        BigDecimal result = instance.getPrecioUnitario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCodigoProducto method, of class DetallePedido.
     */
    @Test
    public void testGetCodigoProducto() {
        System.out.println("getCodigoProducto");
        DetallePedido instance = new DetallePedido();
        String expResult = "";
        String result = instance.getCodigoProducto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreProducto method, of class DetallePedido.
     */
    @Test
    public void testGetNombreProducto() {
        System.out.println("getNombreProducto");
        DetallePedido instance = new DetallePedido();
        String expResult = "";
        String result = instance.getNombreProducto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
