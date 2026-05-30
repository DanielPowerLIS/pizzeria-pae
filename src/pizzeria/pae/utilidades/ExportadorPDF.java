package pizzeria.pae.utilidades;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import pizzeria.pae.modelo.beans.Pedido;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
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
        }catch (FileNotFoundException ex) {
            throw new RuntimeException(
                    "No se pudo crear el archivo PDF",
                    ex
            );
        } catch (DocumentException ex) {
            throw new RuntimeException(
                    "Error al configurar el documento PDF",
                    ex
            );
        }
        
    }

    @Override
    protected void escribirCabecera() {
        try {
            documento.add(new Paragraph("REPORTE DE PEDIDOS"));
            documento.add(new Paragraph(" "));
        } catch (DocumentException ex) {
            throw new RuntimeException(
                    "Error al escribir la cabecera del PDF",
                    ex
            );
        }
        
    }

    @Override
    protected void escribirDatos(List<Pedido> pedidos) {
        try {

            for(Pedido p : pedidos){

                documento.add(new Paragraph("Pedido: " + p.getIdPedido()));

                documento.add(new Paragraph(
                        "Cliente: " +
                        p.getCliente().getNombre() + " " +
                        p.getCliente().getApellidoPaterno() + " " +
                        p.getCliente().getApellidoMaterno()
                ));

                documento.add(new Paragraph(" "));

                for(DetallePedido dp : p.getDetallePedido()) {

                    documento.add(new Paragraph(
                            dp.getProducto().getNombre() +
                            " | Cantidad: " + dp.getCantidad() +
                            " | Subtotal: " + dp.getSubtotal()
                    ));
                }

                documento.add(new Paragraph(" "));
                documento.add(new Paragraph("TOTAL: " + p.getTotal()));
                documento.add(new Paragraph("--------------------------------"));
            }

        } catch (DocumentException ex) {

            throw new RuntimeException(
                    "Error al escribir los datos del PDF",
                    ex
            );
        }
    }

    @Override
    protected void cerrarDocumento() {
        if(documento != null) {
            documento.close();
        }
    }
    
}
