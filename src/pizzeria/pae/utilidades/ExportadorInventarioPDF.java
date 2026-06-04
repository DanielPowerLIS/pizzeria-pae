package pizzeria.pae.utilidades;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image; // Nueva importación obligatoria
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import pizzeria.pae.modelo.beans.Producto;

public class ExportadorInventarioPDF extends Exportador<Producto> {

    private Document documento;
    private PdfPTable tabla;
    private final Color COLOR_CABECERA = new Color(52, 73, 94);

    @Override
    protected void abrirDocumento(String ruta) {
        try {
            documento = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();

            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, Color.BLACK);
            Paragraph titulo = new Paragraph("ITALIA PIZZA", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);

            Font fuenteSubtitulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Color.DARK_GRAY);
            Paragraph subtitulo = new Paragraph("REPORTE DE INVENTARIO (EN EXISTENCIA)", fuenteSubtitulo);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            subtitulo.setSpacingAfter(20);

            documento.add(titulo);
            documento.add(subtitulo);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("No se pudo crear el archivo PDF", ex);
        } catch (com.lowagie.text.DocumentException ex) {
            throw new RuntimeException("Error al configurar el documento PDF", ex);
        }
    }

    @Override
    protected void escribirCabecera() {
        // Se aumenta a 7 columnas
        tabla = new PdfPTable(7);
        tabla.setWidthPercentage(100);

        try {
            // Ajustamos el tamaño para darle 2 puntos de proporción a la foto
            float[] anchosColumnas = {1.5f, 2f, 2.5f, 3.5f, 1f, 1.5f, 2f};
            tabla.setWidths(anchosColumnas);
        } catch (DocumentException ex) {
            throw new RuntimeException("Error al configurar el ancho de las columnas", ex);
        }

        tabla.addCell(crearHeaderCelda("CÓDIGO"));
        tabla.addCell(crearHeaderCelda("FOTO"));
        tabla.addCell(crearHeaderCelda("NOMBRE"));
        tabla.addCell(crearHeaderCelda("DESCRIPCIÓN"));
        tabla.addCell(crearHeaderCelda("STOCK"));
        tabla.addCell(crearHeaderCelda("PRECIO"));
        tabla.addCell(crearHeaderCelda("RESTRICCIONES"));
    }

    @Override
    protected void escribirDatos(List<Producto> datos) {
        for (Producto p : datos) {
            tabla.addCell(crearCeldaDato(p.getCodigo(), Element.ALIGN_CENTER));

            // Validación y creación de la celda de la imagen
            if (p.getFoto() != null && p.getFoto().length > 0) {
                try {
                    Image img = Image.getInstance(p.getFoto());
                    img.scaleToFit(45, 45); // Forzamos a que no desborde la tabla
                    PdfPCell celdaImg = new PdfPCell(img, false);
                    celdaImg.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celdaImg.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celdaImg.setPadding(4);
                    tabla.addCell(celdaImg);
                } catch (Exception e) {
                    tabla.addCell(crearCeldaDato("Error", Element.ALIGN_CENTER));
                }
            } else {
                tabla.addCell(crearCeldaDato("Sin foto", Element.ALIGN_CENTER));
            }

            tabla.addCell(crearCeldaDato(p.getNombre(), Element.ALIGN_LEFT));
            tabla.addCell(crearCeldaDato(p.getDescripcion(), Element.ALIGN_LEFT));
            tabla.addCell(crearCeldaDato(String.valueOf(p.getCantidad()), Element.ALIGN_CENTER));
            tabla.addCell(crearCeldaDato(String.format("$%,.2f", p.getPrecio()), Element.ALIGN_RIGHT));

            String restricciones = p.getRestricciones() == null || p.getRestricciones().isEmpty() ? "Ninguna" : p.getRestricciones();
            tabla.addCell(crearCeldaDato(restricciones, Element.ALIGN_LEFT));
        }
    }

    @Override
    protected void cerrarDocumento() {
        try {
            if (documento != null && tabla != null) {
                documento.add(tabla);
                documento.close();
            }
        } catch (DocumentException ex) {
            throw new RuntimeException("Error al finalizar y cerrar el PDF", ex);
        }
    }

    private PdfPCell crearHeaderCelda(String texto) {
        Font fuenteBlanca = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.WHITE);
        PdfPCell celda = new PdfPCell(new Paragraph(texto, fuenteBlanca));
        celda.setBackgroundColor(COLOR_CABECERA);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(8);
        return celda;
    }

    private PdfPCell crearCeldaDato(String texto, int alineacion) {
        Font fuenteNormal = FontFactory.getFont(FontFactory.HELVETICA, 11);
        PdfPCell celda = new PdfPCell(new Paragraph(texto, fuenteNormal));
        celda.setPadding(6);
        celda.setHorizontalAlignment(alineacion);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return celda;
    }
}
