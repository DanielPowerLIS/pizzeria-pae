package pizzeria.pae.utilidades.seguridad;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *
 * @author jdani
 */
public class BCryptHasher {
    
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    private BCryptHasher(){}
    
    public static String generarContraseniaHash(String contrasenia) {
        return encoder.encode(contrasenia);
    }
    
    public static boolean verificarContraseniaHash(String contrasenia, String hashGuardado) {
        return encoder.matches(contrasenia, hashGuardado);
    }
}
