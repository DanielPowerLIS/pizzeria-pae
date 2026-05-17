package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;

/**
 * Bean que representa un producto del catálogo de la pizzería.
 * Los campos fueron inferidos del ProductoFormViewController y su FXML.
 */
public class Producto {
     
    private Integer idProducto;
    private String nombre;
    private String codigo;
    private Boolean esInsumo;
    private Boolean esUtilizado;
    private int cantidad;
    private String rutaFoto;
    private String restricciones;
    private BigDecimal precio;
    private String descripcion;

    public Producto() {
    }

    public Producto(Integer idProducto, String nombre, String codigoProducto, Boolean esInsumo, 
            Boolean esUtilizado, int cantidad, String rutaFoto, String restricciones, 
            BigDecimal precio, String descripcion) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codigo = codigoProducto;
        this.esInsumo = esInsumo;
        this.esUtilizado = esUtilizado;
        this.cantidad = cantidad;
        this.rutaFoto = rutaFoto;
        this.restricciones = restricciones;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigoProducto) {
        this.codigo = codigoProducto;
    }

    public Boolean getEsInsumo() {
        return esInsumo;
    }

    public void setEsInsumo(Boolean esInsumo) {
        this.esInsumo = esInsumo;
    }

    public Boolean getEsUtilizado() {
        return esUtilizado;
    }

    public void setEsUtilizado(Boolean esUtilizado) {
        this.esUtilizado = esUtilizado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
      
}
