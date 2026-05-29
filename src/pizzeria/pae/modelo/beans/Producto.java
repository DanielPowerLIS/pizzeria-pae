package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;
import java.sql.Blob;

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
    private Integer cantidad;
    private byte[] foto;
    private String restricciones;
    private BigDecimal precio;
    private String descripcion;
    private Integer cantidadFisica;

    public Producto() {
    }

    public Producto(Integer idProducto, String nombre, String codigo, Boolean esInsumo, 
               Boolean esUtilizado, Integer cantidad, byte[] foto, String restricciones, 
               BigDecimal precio, String descripcion, Integer cantidadFisica, Integer diferencia) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codigo = codigo;
        this.esInsumo = esInsumo;
        this.esUtilizado = esUtilizado;
        this.cantidad = cantidad;
        this.foto = foto;
        this.restricciones = restricciones;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cantidadFisica = cantidadFisica;
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

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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

    public Integer getCantidadFisica() {
        return cantidadFisica;
    }

    public void setCantidadFisica(Integer cantidadFisica) {
        this.cantidadFisica = cantidadFisica;
    }
      
}
