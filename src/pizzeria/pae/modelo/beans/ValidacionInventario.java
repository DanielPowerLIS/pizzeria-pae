package pizzeria.pae.modelo.beans;

/**
 * Bean que representa una fila en la vista de validación de inventario.
 * Compara la cantidad registrada en el sistema contra la cantidad
 * física contada, calculando la diferencia entre ambas.
 */
public class ValidacionInventario {

    private String codigo;
    private String nombre;
    private int cantidadSistema;
    private int cantidadFisica;
    private int diferencia;

    // Constructor vacío
    public ValidacionInventario() {
    }

    // Constructor con todos los campos — calcula la diferencia automáticamente
    public ValidacionInventario(String codigo, String nombre, int cantidadSistema, int cantidadFisica) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidadSistema = cantidadSistema;
        this.cantidadFisica = cantidadFisica;
        this.diferencia = calcularDiferencia();
    }

    // Diferencia = física - sistema (negativo significa faltante, positivo sobra)
    public int calcularDiferencia() {
        return this.cantidadFisica - this.cantidadSistema;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadSistema() {
        return cantidadSistema;
    }

    public void setCantidadSistema(int cantidadSistema) {
        this.cantidadSistema = cantidadSistema;
        this.diferencia = calcularDiferencia();
    }

    public int getCantidadFisica() {
        return cantidadFisica;
    }

    public void setCantidadFisica(int cantidadFisica) {
        this.cantidadFisica = cantidadFisica;
        this.diferencia = calcularDiferencia();
    }

    public int getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(int diferencia) {
        this.diferencia = diferencia;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre +
               " | Sistema: " + cantidadSistema +
               " | Física: " + cantidadFisica +
               " | Diferencia: " + diferencia;
    }
}
