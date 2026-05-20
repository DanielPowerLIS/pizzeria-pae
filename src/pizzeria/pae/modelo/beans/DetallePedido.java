package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;

/**
 * Bean que representa un renglón dentro del detalle de un pedido.
 * Corresponde a las columnas de la tabla tblDetallePedido:
 * Código | Producto | Cantidad | Precio Unitario | Subtotal
 */
public class DetallePedido {
    
    private Integer idPedido;
    private Producto producto;
    private int cantidad;
    private BigDecimal subtotal;
    
    public DetallePedido() {
    }

    public DetallePedido(Integer idPedido, Producto producto, int cantidad) {
        this.idPedido = idPedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public BigDecimal calcularSubtotal() {
        return producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
