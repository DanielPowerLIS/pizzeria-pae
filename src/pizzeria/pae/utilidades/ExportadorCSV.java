package pizzeria.pae.utilidades;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import pizzeria.pae.modelo.beans.DetallePedido;
import pizzeria.pae.modelo.beans.Pedido;

/**
 *
 * @author jdani
 */
public class ExportadorCSV extends Exportador<Pedido>{

    private PrintWriter writer;
    
    @Override
    protected void abrirDocumento(String ruta) {
        
        try {
            writer = new PrintWriter(ruta);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void escribirCabecera() {
        writer.println("Nombre del cliente,Item,Cantidad,Subtotal,Total");
        
    }

    @Override
    protected void escribirDatos(List<Pedido> pedidos) {
        for(Pedido p: pedidos) {
            for(DetallePedido dp : p.getDetallePedido()) {
                writer.println(
                        p.getCliente().getNombre() + "," +
                        dp.getProducto().getNombre() + "," +
                        dp.getCantidad() + "," +
                        dp.getSubtotal() + "," +
                        p.getTotal()
                );
            }
        }
    }

    @Override
    protected void cerrarDocumento() {
        writer.close();
    }
    
}
