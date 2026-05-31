/*
package pizzeria.pae.modelo.beans;

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
/*
public class UsuarioTest {
    
    public UsuarioTest() {
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
     * Test of getIdUsuario method, of class Usuario.
     */
/*
    @Test
    public void testGetIdUsuario() {
        System.out.println("getIdUsuario");
        Usuario instance = new Usuario();
        int expResult = 0;
        int result = instance.getIdUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdUsuario method, of class Usuario.
     */
/*
    @Test
    public void testSetIdUsuario() {
        System.out.println("setIdUsuario");
        int idUsuario = 0;
        Usuario instance = new Usuario();
        instance.setIdUsuario(idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombre method, of class Usuario.
     */
/*
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombre method, of class Usuario.
     */
/*
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Usuario instance = new Usuario();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApellidoPaterno method, of class Usuario.
     */
/*
    @Test
    public void testGetApellidoPaterno() {
        System.out.println("getApellidoPaterno");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getApellidoPaterno();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setApellidoPaterno method, of class Usuario.
     */
/*
    @Test
    public void testSetApellidoPaterno() {
        System.out.println("setApellidoPaterno");
        String apellidoPaterno = "";
        Usuario instance = new Usuario();
        instance.setApellidoPaterno(apellidoPaterno);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApellidoMaterno method, of class Usuario.
     */
/*
    @Test
    public void testGetApellidoMaterno() {
        System.out.println("getApellidoMaterno");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getApellidoMaterno();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setApellidoMaterno method, of class Usuario.
     */
/*
    @Test
    public void testSetApellidoMaterno() {
        System.out.println("setApellidoMaterno");
        String apellidoMaterno = "";
        Usuario instance = new Usuario();
        instance.setApellidoMaterno(apellidoMaterno);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTelefono method, of class Usuario.
     */
/*
    @Test
    public void testGetTelefono() {
        System.out.println("getTelefono");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getTelefono();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTelefono method, of class Usuario.
     */
/*
    @Test
    public void testSetTelefono() {
        System.out.println("setTelefono");
        String telefono = "";
        Usuario instance = new Usuario();
        instance.setTelefono(telefono);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Usuario.
     */
/*
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Usuario.
     */
/*
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Usuario instance = new Usuario();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHaPedido method, of class Usuario.
     *//*
    @Test
    public void testGetHaPedido() {
        System.out.println("getHaPedido");
        Usuario instance = new Usuario();
        boolean expResult = false;
        boolean result = instance.getHaPedido();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHaPedido method, of class Usuario.
     */
/*
    @Test
    public void testSetHaPedido() {
        System.out.println("setHaPedido");
        boolean haPedido = false;
        Usuario instance = new Usuario();
        instance.setHaPedido(haPedido);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEsEmpleado method, of class Usuario.
     */
/*
    @Test
    public void testGetEsEmpleado() {
        System.out.println("getEsEmpleado");
        Usuario instance = new Usuario();
        boolean expResult = false;
        boolean result = instance.getEsEmpleado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEsEmpleado method, of class Usuario.
     */
/*
    @Test
    public void testSetEsEmpleado() {
        System.out.println("setEsEmpleado");
        boolean esEmpleado = false;
        Usuario instance = new Usuario();
        instance.setEsEmpleado(esEmpleado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEsActivo method, of class Usuario.
     */
/*
    @Test
    public void testGetEsActivo() {
        System.out.println("getEsActivo");
        Usuario instance = new Usuario();
        boolean expResult = false;
        boolean result = instance.getEsActivo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEsActivo method, of class Usuario.
     */
    /*
    @Test
    public void testSetEsActivo() {
        System.out.println("setEsActivo");
        boolean esActivo = false;
        Usuario instance = new Usuario();
        instance.setEsActivo(esActivo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEliminado method, of class Usuario.
     */
    /*
    @Test
    public void testGetEliminado() {
        System.out.println("getEliminado");
        Usuario instance = new Usuario();
        boolean expResult = false;
        boolean result = instance.getEliminado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEliminado method, of class Usuario.
     */
    /*
    @Test
    public void testSetEliminado() {
        System.out.println("setEliminado");
        boolean eliminado = false;
        Usuario instance = new Usuario();
        instance.setEliminado(eliminado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreUsuario method, of class Usuario.
     */
    /*
    @Test
    public void testGetNombreUsuario() {
        System.out.println("getNombreUsuario");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getNombreUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombreUsuario method, of class Usuario.
     */
    /*
    @Test
    public void testSetNombreUsuario() {
        System.out.println("setNombreUsuario");
        String nombreUsuario = "";
        Usuario instance = new Usuario();
        instance.setNombreUsuario(nombreUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContrasenia method, of class Usuario.
     */
    /*
    @Test
    public void testGetContrasenia() {
        System.out.println("getContrasenia");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getContrasenia();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContrasenia method, of class Usuario.
     */
    /*
    @Test
    public void testSetContrasenia() {
        System.out.println("setContrasenia");
        String contrasenia = "";
        Usuario instance = new Usuario();
        instance.setContrasenia(contrasenia);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRol method, of class Usuario.
     */
    /*
    @Test
    public void testGetRol() {
        System.out.println("getRol");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getRol();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRol method, of class Usuario.
     */
    /*
    @Test
    public void testSetRol() {
        System.out.println("setRol");
        String rol = "";
        Usuario instance = new Usuario();
        instance.setRol(rol);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDireccion method, of class Usuario.
     */
    /*
    @Test
    public void testGetDireccion() {
        System.out.println("getDireccion");
        Usuario instance = new Usuario();
        Direccion expResult = null;
        Direccion result = instance.getDireccion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDireccion method, of class Usuario.
     */
    /*
    @Test
    public void testSetDireccion() {
        System.out.println("setDireccion");
        Direccion direccion = null;
        Usuario instance = new Usuario();
        instance.setDireccion(direccion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreCompleto method, of class Usuario.
     */
    /*
    @Test
    public void testGetNombreCompleto() {
        System.out.println("getNombreCompleto");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getNombreCompleto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTipo method, of class Usuario.
     */
    /*
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getTipo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Usuario.
     */
    /*
    @Test
    public void testToString() {
        System.out.println("toString");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
*/