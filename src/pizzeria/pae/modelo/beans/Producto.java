package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;

/**
 * Bean que representa un producto del catálogo de la pizzería.
 * Los campos fueron inferidos del ProductoFormViewController y su FXML.
 */
public class Producto {

    public String codigo;
    public String nombreProducto;
    public String descripcion;
    public BigDecimal precio;
    public int cantidad;
    public String restricciones;
    public String rutaFoto;

    // Constructor vacío
    public Producto() {
    }

    // Constructor con todos los campos
    public Producto(String codigo, String nombreProducto, String descripcion,
                    BigDecimal precio, int cantidad, String restricciones, String rutaFoto) {
        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.restricciones = restricciones;
        this.rutaFoto = rutaFoto;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombreProducto;
    }
}
