package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;

/**
 * Bean que representa un renglón dentro del detalle de un pedido.
 * Corresponde a las columnas de la tabla tblDetallePedido:
 * Código | Producto | Cantidad | Precio Unitario | Subtotal
 */
public class DetallePedido {

    public String codigo;
    public Producto producto;
    public int cantidad;
    public BigDecimal precioUnitario;
    public BigDecimal subtotal;

    // Constructor vacío
    public DetallePedido() {
    }

    // Constructor con todos los campos
    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.codigo = producto.getCodigo();
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
        this.subtotal = calcularSubtotal();
    }

    // Calcula el subtotal automáticamente al asignar cantidad o precio
    public BigDecimal calcularSubtotal() {
        if (precioUnitario != null && cantidad > 0) {
            return precioUnitario.multiply(new BigDecimal(cantidad));
        }
        return BigDecimal.ZERO;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        if (producto != null) {
            this.codigo = producto.getCodigo();
            this.precioUnitario = producto.getPrecio();
            this.subtotal = calcularSubtotal();
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal = calcularSubtotal();
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return codigo + " x" + cantidad + " = $" + subtotal;
    }
}
