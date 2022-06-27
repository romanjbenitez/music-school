package com.emusic.school.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("This is a title.", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Table table = new Table(2);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell("HOLA");
        table.addCell("CHAU");
        table.setAlignment(Table.ALIGN_CENTER);

        table.setWidth(40);

        document.add(paragraph);
        document.add(table);
        document.close();
    }
}
