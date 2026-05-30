package pizzeria.pae.modelo.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Bean que representa un pedido completo.
 * Los campos fueron inferidos del PedidoFormViewController y su FXML.
 */
public class Pedido {

    private Integer idPedido;
    private LocalDate fecha;
    private BigDecimal total;
    private String estado;
    private Usuario cliente;
    private List<DetallePedido> detallePedido;
    
    public Pedido(){

    }

    public Pedido(Integer idPedido, LocalDate fecha, BigDecimal total, String estado, Usuario cliente, List<DetallePedido> detallePedido) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.detallePedido = detallePedido;
    }

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

    public void agregarDetalle(DetallePedido detalle) {
        this.detallePedido.add(detalle);
        this.total = calcularTotal();
    }

    public void quitarDetalle(DetallePedido detalle) {
        this.detallePedido.remove(detalle);
        this.total = calcularTotal();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getCliente() {
        return cliente;
    }
    
    public String getNombreCliente() {
        return this.cliente.getNombreCompleto();
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Pedido #" + idPedido + " - " + (cliente != null ? cliente.getNombreCompleto() : "Sin cliente")
                + " [" + fecha + "] Total: $" + total;
    }
}
