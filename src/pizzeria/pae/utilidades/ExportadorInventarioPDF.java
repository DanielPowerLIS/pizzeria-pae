package pizzeria.pae.utilidades;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.util.List;
import pizzeria.pae.modelo.beans.Producto;

public class ExportadorInventarioPDF extends Exportador<Producto> {

    // Variables globales de la clase para usarlas en los distintos métodos
    private Document documento;
    private PdfPTable tabla;

    @Override
    protected void abrirDocumento(String ruta) {
        try {
            documento = new Document(PageSize.A4.rotate()); 
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();

            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("REPORTE DE INVENTARIO (EN EXISTENCIA)", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            documento.add(titulo);
        } catch (Exception e) {
            // Se envuelve en RuntimeException porque el método padre no lanza excepciones
            throw new RuntimeException("Error al abrir e inicializar el PDF", e);
        }
    }

    @Override
    protected void escribirCabecera() {
        tabla = new PdfPTable(6);
        tabla.setWidthPercentage(100);

        try {
            float[] anchosColumnas = {1.5f, 2.5f, 3f, 1f, 1.5f, 2f};
            tabla.setWidths(anchosColumnas);
        } catch (Exception e) {
            throw new RuntimeException("Error al configurar el ancho de las columnas", e);
        }

        tabla.addCell(crearHeaderCelda("CÓDIGO"));
        tabla.addCell(crearHeaderCelda("NOMBRE"));
        tabla.addCell(crearHeaderCelda("DESCRIPCIÓN"));
        tabla.addCell(crearHeaderCelda("STOCK"));
        tabla.addCell(crearHeaderCelda("PRECIO"));
        tabla.addCell(crearHeaderCelda("RESTRICCIONES"));
    }

    @Override
    protected void escribirDatos(List<Producto> datos) {
        for (Producto p : datos) {
            tabla.addCell(crearCeldaDato(p.getCodigo()));
            tabla.addCell(crearCeldaDato(p.getNombre()));
            tabla.addCell(crearCeldaDato(p.getDescripcion()));
            tabla.addCell(crearCeldaDato(String.valueOf(p.getCantidad())));
            tabla.addCell(crearCeldaDato(String.format("$%,.2f", p.getPrecio())));
            
            String restricciones = p.getRestricciones() == null || p.getRestricciones().isEmpty() ? "Ninguna" : p.getRestricciones();
            tabla.addCell(crearCeldaDato(restricciones));
        }
    }

    @Override
    protected void cerrarDocumento() {
        try {
            // Añadimos la tabla ya llena al documento y lo cerramos
            if (documento != null && tabla != null) {
                documento.add(tabla);
                documento.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al finalizar y cerrar el PDF", e);
        }
    }

    // --- Métodos auxiliares de diseño ---

    private PdfPCell crearHeaderCelda(String texto) {
        Font fuenteBlanca = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
        PdfPCell celda = new PdfPCell(new Paragraph(texto, fuenteBlanca));
        celda.setBackgroundColor(Color.BLUE);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(8);
        return celda;
    }

    private PdfPCell crearCeldaDato(String texto) {
        PdfPCell celda = new PdfPCell(new Paragraph(texto));
        celda.setPadding(5);
        return celda;
    }
}