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
public class ProductoTest {
    
    public ProductoTest() {
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
     * Test of getIdProducto method, of class Producto.
     */
    @Test
    public void testGetIdProducto() {
        System.out.println("getIdProducto");
        Producto instance = new Producto();
        Integer expResult = null;
        Integer result = instance.getIdProducto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdProducto method, of class Producto.
     */
    @Test
    public void testSetIdProducto() {
        System.out.println("setIdProducto");
        Integer idProducto = null;
        Producto instance = new Producto();
        instance.setIdProducto(idProducto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombre method, of class Producto.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombre method, of class Producto.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Producto instance = new Producto();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCodigo method, of class Producto.
     */
    @Test
    public void testGetCodigo() {
        System.out.println("getCodigo");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.getCodigo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCodigo method, of class Producto.
     */
    @Test
    public void testSetCodigo() {
        System.out.println("setCodigo");
        String codigo = "";
        Producto instance = new Producto();
        instance.setCodigo(codigo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEsInsumo method, of class Producto.
     */
    @Test
    public void testGetEsInsumo() {
        System.out.println("getEsInsumo");
        Producto instance = new Producto();
        Boolean expResult = null;
        Boolean result = instance.getEsInsumo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEsInsumo method, of class Producto.
     */
    @Test
    public void testSetEsInsumo() {
        System.out.println("setEsInsumo");
        Boolean esInsumo = null;
        Producto instance = new Producto();
        instance.setEsInsumo(esInsumo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEsUtilizado method, of class Producto.
     */
    @Test
    public void testGetEsUtilizado() {
        System.out.println("getEsUtilizado");
        Producto instance = new Producto();
        Boolean expResult = null;
        Boolean result = instance.getEsUtilizado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEsUtilizado method, of class Producto.
     */
    @Test
    public void testSetEsUtilizado() {
        System.out.println("setEsUtilizado");
        Boolean esUtilizado = null;
        Producto instance = new Producto();
        instance.setEsUtilizado(esUtilizado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCantidad method, of class Producto.
     */
    @Test
    public void testGetCantidad() {
        System.out.println("getCantidad");
        Producto instance = new Producto();
        Integer expResult = null;
        Integer result = instance.getCantidad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCantidad method, of class Producto.
     */
    @Test
    public void testSetCantidad() {
        System.out.println("setCantidad");
        Integer cantidad = null;
        Producto instance = new Producto();
        instance.setCantidad(cantidad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFoto method, of class Producto.
     */
    @Test
    public void testGetFoto() {
        System.out.println("getFoto");
        Producto instance = new Producto();
        byte[] expResult = null;
        byte[] result = instance.getFoto();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFoto method, of class Producto.
     */
    @Test
    public void testSetFoto() {
        System.out.println("setFoto");
        byte[] foto = null;
        Producto instance = new Producto();
        instance.setFoto(foto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRestricciones method, of class Producto.
     */
    @Test
    public void testGetRestricciones() {
        System.out.println("getRestricciones");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.getRestricciones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRestricciones method, of class Producto.
     */
    @Test
    public void testSetRestricciones() {
        System.out.println("setRestricciones");
        String restricciones = "";
        Producto instance = new Producto();
        instance.setRestricciones(restricciones);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrecio method, of class Producto.
     */
    @Test
    public void testGetPrecio() {
        System.out.println("getPrecio");
        Producto instance = new Producto();
        BigDecimal expResult = null;
        BigDecimal result = instance.getPrecio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrecio method, of class Producto.
     */
    @Test
    public void testSetPrecio() {
        System.out.println("setPrecio");
        BigDecimal precio = null;
        Producto instance = new Producto();
        instance.setPrecio(precio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescripcion method, of class Producto.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescripcion method, of class Producto.
     */
    @Test
    public void testSetDescripcion() {
        System.out.println("setDescripcion");
        String descripcion = "";
        Producto instance = new Producto();
        instance.setDescripcion(descripcion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCantidadFisica method, of class Producto.
     */
    @Test
    public void testGetCantidadFisica() {
        System.out.println("getCantidadFisica");
        Producto instance = new Producto();
        Integer expResult = null;
        Integer result = instance.getCantidadFisica();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCantidadFisica method, of class Producto.
     */
    @Test
    public void testSetCantidadFisica() {
        System.out.println("setCantidadFisica");
        Integer cantidadFisica = null;
        Producto instance = new Producto();
        instance.setCantidadFisica(cantidadFisica);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Producto.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
