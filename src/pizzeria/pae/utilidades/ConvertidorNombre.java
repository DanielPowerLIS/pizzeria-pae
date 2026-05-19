package pizzeria.pae.utilidades;

/**
 *
 * @author adair
 */
public class ConvertidorNombre {
    public static String[] prepararNombre(String nombreCompleto){ 
        String nombrePartes[] = nombreCompleto.split(" ");
        Integer partes = nombrePartes.length;
        
        String apellidoPaterno = nombrePartes[partes - 2];
        String apellidoMaterno = nombrePartes[partes - 1];
        
        StringBuilder unirNombre = new StringBuilder();
        for(int i = 0; i < partes - 2; i++){
            unirNombre.append(nombrePartes[i]).append(" ");
        }
        
        String nombre = unirNombre.toString().trim();
        
        return new String[]{nombre, apellidoPaterno, apellidoMaterno};
    }
}
