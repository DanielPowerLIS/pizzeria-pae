package pizzeria.pae.utilidades;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import pizzeria.pae.modelo.beans.Pedido;
import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;
import pizzeria.pae.modelo.beans.DetallePedido;
/**
 *
 * @author jdani
 */
public class ExportadorPDF extends Exportador<Pedido>{

    private Document documento;
    
    @Override
    protected void abrirDocumento(String ruta) {
        
        try {
            documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    protected void escribirCabecera() {
        
        documento.add(new Paragraph("REPORTE DE PEDIDOS"));
        documento.add(new Paragraph(" "));
        
    }

    @Override
    protected void escribirDatos(List<Pedido> pedidos) {
        for(Pedido p : pedidos){
            documento.add(new Paragraph("Pedido: " + p.getIdPedido()));
            documento.add(new Paragraph("Cliente: " + p.getCliente().getNombre()));
            
            documento.add(new Paragraph(" "));
            
            for(DetallePedido dp : p.getDetallePedido()) {
                documento.add(new Paragraph(
                        dp.getProducto().getNombreProducto() + 
                        " | Cantidad: " + dp.getCantidad() + 
                        " | Subtotal: " + dp.getSubtotal()
                ));
            }
            
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("TOTAL: " +  p.getTotal()));
            documento.add(new Paragraph("--------------------------------"));
        }
    }

    @Override
    protected void cerrarDocumento() {
        if(documento != null) {
            documento.close();
        }
    }
    
}
