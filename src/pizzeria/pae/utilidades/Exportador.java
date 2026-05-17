package pizzeria.pae.utilidades;

import java.util.List;

/**
 *
 * @author jdani
 */
public abstract class Exportador<T> {
    
    public final void exportar(List<T> datos, String rutaArchivo) {
        
        abrirDocumento(rutaArchivo);
        escribirCabecera();
        escribirDatos(datos);
        cerrarDocumento();
    }
    
    
    protected abstract void abrirDocumento(String ruta);
    protected abstract void escribirCabecera();
    protected abstract void escribirDatos(List<T> datos);
    protected abstract void cerrarDocumento();
   
}
