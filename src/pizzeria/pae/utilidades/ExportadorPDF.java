package pizzeria.pae.utilidades;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import pizzeria.pae.modelo.beans.DetallePedido;
import pizzeria.pae.modelo.beans.Pedido;

public class ExportadorPDF extends Exportador<Pedido> {

    private Document documento;

    @Override
    protected void abrirDocumento(String ruta) {
        try {
            documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("No se pudo crear el archivo PDF", ex);
        } catch (DocumentException ex) {
            throw new RuntimeException("Error al configurar el documento PDF", ex);
        }
    }

    @Override
    protected void escribirCabecera() {
        try {
            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, Color.BLACK);
            Paragraph titulo = new Paragraph("ITALIA PIZZA", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);

            Font fuenteSubtitulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Color.DARK_GRAY);
            Paragraph subtitulo = new Paragraph("REPORTE DETALLADO DE PEDIDOS", fuenteSubtitulo);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            subtitulo.setSpacingAfter(20);

            documento.add(titulo);
            documento.add(subtitulo);

        } catch (DocumentException ex) {
            throw new RuntimeException("Error al escribir la cabecera del PDF", ex);
        }
    }

    @Override
    protected void escribirDatos(List<Pedido> pedidos) {
        try {
            Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 11);
            Font fontHeaderTabla = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.WHITE);
            Color colorCabecera = new Color(52, 73, 94);

            for (Pedido p : pedidos) {

                Paragraph infoPedido = new Paragraph();
                infoPedido.add(new Phrase("Folio: ", fontBold));
                infoPedido.add(new Phrase(p.getIdPedido() + "   |   ", fontNormal));

                infoPedido.add(new Phrase("Fecha: ", fontBold));
                infoPedido.add(new Phrase(p.getFecha().toString() + "\n", fontNormal));

                infoPedido.add(new Phrase("Estatus: ", fontBold));
                infoPedido.add(new Phrase(p.getEstado() + "\n", fontNormal));

                String nombreCliente = p.getCliente().getNombre() + " "
                        + p.getCliente().getApellidoPaterno() + " "
                        + (p.getCliente().getApellidoMaterno() != null ? p.getCliente().getApellidoMaterno() : "");

                infoPedido.add(new Phrase("Cliente: ", fontBold));
                infoPedido.add(new Phrase(nombreCliente, fontNormal));

                infoPedido.setSpacingBefore(10);
                infoPedido.setSpacingAfter(10);
                documento.add(infoPedido);

                PdfPTable tabla = new PdfPTable(4);
                tabla.setWidthPercentage(100);
                tabla.setWidths(new float[]{4.5f, 1.5f, 2f, 2f});

                String[] cabeceras = {"Producto", "Cant.", "Precio U.", "Subtotal"};
                for (String cabecera : cabeceras) {
                    PdfPCell celda = new PdfPCell(new Phrase(cabecera, fontHeaderTabla));
                    celda.setBackgroundColor(colorCabecera);
                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celda.setPadding(5);
                    tabla.addCell(celda);
                }

                for (DetallePedido dp : p.getDetallePedido()) {
                    PdfPCell cProd = new PdfPCell(new Phrase(dp.getProducto().getNombre(), fontNormal));
                    cProd.setPadding(4);
                    tabla.addCell(cProd);

                    PdfPCell cCant = new PdfPCell(new Phrase(String.valueOf(dp.getCantidad()), fontNormal));
                    cCant.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cCant.setPadding(4);
                    tabla.addCell(cCant);

                    PdfPCell cPrec = new PdfPCell(new Phrase(String.format("$%,.2f", dp.getPrecioUnitario()), fontNormal));
                    cPrec.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cPrec.setPadding(4);
                    tabla.addCell(cPrec);

                    PdfPCell cSub = new PdfPCell(new Phrase(String.format("$%,.2f", dp.getSubtotal()), fontNormal));
                    cSub.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cSub.setPadding(4);
                    tabla.addCell(cSub);
                }
                documento.add(tabla);

                Paragraph totalPedido = new Paragraph();
                totalPedido.setAlignment(Element.ALIGN_RIGHT);
                totalPedido.add(new Phrase("TOTAL A PAGAR: ", fontBold));
                totalPedido.add(new Phrase(String.format("$%,.2f", p.getTotal()), fontBold));
                totalPedido.setSpacingBefore(5);
                totalPedido.setSpacingAfter(15);
                documento.add(totalPedido);

                Paragraph separador = new Paragraph("---------------------------------------------------------------------------------------------------------");
                separador.setAlignment(Element.ALIGN_CENTER);
                documento.add(separador);
            }

        } catch (DocumentException ex) {
            throw new RuntimeException("Error al escribir los datos del PDF", ex);
        }
    }

    @Override
    protected void cerrarDocumento() {
        if (documento != null && documento.isOpen()) {
            documento.close();
        }
    }
}
