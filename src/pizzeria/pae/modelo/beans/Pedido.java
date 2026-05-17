package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean que representa un pedido completo.
 * Los campos fueron inferidos del PedidoFormViewController y su FXML.
 */
public class Pedido {

    public int idPedido;
    public Usuario cliente;
    public LocalDate fecha;
    public List<DetallePedido> detallePedido;
    public BigDecimal total;

    // Constructor vacío — inicializa la lista de detalle y la fecha actual
    public Pedido() {
        this.detallePedido = new ArrayList<>();
        this.fecha = LocalDate.now();
        this.total = BigDecimal.ZERO;
    }

    // Constructor con todos los campos
    public Pedido(int idPedido, Usuario cliente, LocalDate fecha, List<DetallePedido> detallePedido) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.fecha = fecha;
        this.detallePedido = detallePedido != null ? detallePedido : new ArrayList<>();
        this.total = calcularTotal();
    }

    // Suma todos los subtotales del detalle
    public BigDecimal calcularTotal() {
        BigDecimal suma = BigDecimal.ZERO;
        if (detallePedido != null) {
            for (DetallePedido detalle : detallePedido) {
                if (detalle.getSubtotal() != null) {
                    suma = suma.add(detalle.getSubtotal());
                }
            }
        }
        return suma;
    }

    // Agrega un renglón al detalle y recalcula el total
    public void agregarDetalle(DetallePedido detalle) {
        this.detallePedido.add(detalle);
        this.total = calcularTotal();
    }

    // Quita un renglón del detalle y recalcula el total
    public void quitarDetalle(DetallePedido detalle) {
        this.detallePedido.remove(detalle);
        this.total = calcularTotal();
    }

    // Getters y Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<DetallePedido> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(List<DetallePedido> detallePedido) {
        this.detallePedido = detallePedido;
        this.total = calcularTotal();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido #" + idPedido + " - " + (cliente != null ? cliente.getNombreCompleto() : "Sin cliente")
                + " [" + fecha + "] Total: $" + total;
    }
}
