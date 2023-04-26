package tn.esprit.biol.PDFEquipment;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.dom4j.DocumentException;
import tn.esprit.biol.entity.Equipment;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class EquipmentPDF {
    private Equipment equipment;
    public EquipmentPDF(Equipment equipment) {
        this.equipment = equipment;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("type", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Sterilization_Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Expiration_Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
    }
    private void writeTableData(PdfPTable table) {
        table.addCell(String.valueOf(equipment.getType()));
        table.addCell(equipment.getSterilization_Date().toString());
        table.addCell(equipment.getExpiration_Date().toString());
        table.addCell(equipment.getQuantity().toString());

    }
    public void export(HttpServletResponse response) throws DocumentException, IOException, com.lowagie.text.DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Equipment Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();


    }
}

