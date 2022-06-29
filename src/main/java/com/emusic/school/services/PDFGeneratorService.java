package com.emusic.school.services;

import com.emusic.school.dtos.MerchTicketDTO;
import com.emusic.school.models.Ticket;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
public class PDFGeneratorService {


    @Autowired
    MerchService merchService;
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(4);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

//        cell.setPhrase(new Phrase("ID PRODUCT", font));
//
//        table.addCell(cell);

        cell.setPhrase(new Phrase("QUANTITY", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NAME", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("PRICE FOR UNIT", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("AMOUNT", font));
        table.addCell(cell);
    }
    private void writeTableData(PdfPTable table, List<MerchTicketDTO> merchTicketDTOS, Ticket ticket) throws BadElementException {
//        Font fontCells = FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE);
//        fontCells.setSize(14);
        merchTicketDTOS.forEach(merchTicketDTO -> {
            table.addCell(String.valueOf(merchTicketDTO.getQuantity()));
            table.addCell(merchService.findByID(merchTicketDTO.getId()).getType());
            table.addCell(String.valueOf(merchService.findByID(merchTicketDTO.getId()).getPrice()));
            table.addCell(String.valueOf(merchTicketDTO.getQuantity() * merchService.findByID(merchTicketDTO.getId()).getPrice()));
        });
        ticket.getCourseTickets().forEach(courseTicket -> {
            table.addCell(String.valueOf(1));
            table.addCell( "CURSE:"+ courseTicket.getCourse().getName());
            table.addCell(String.valueOf(courseTicket.getCourse().getPrice()));
            table.addCell(String.valueOf(courseTicket.getCourse().getPrice()));
        });
        Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTotal.setSize(13);
        fontTotal.setColor(Color.RED);
        Paragraph total = new Paragraph("TOTAL", fontTotal);
        total.setAlignment(Paragraph.ALIGN_CENTER);
        PdfPCell cellTotal = new PdfPCell(total);
        cellTotal.setBorderColor(Color.YELLOW);
        cellTotal.setBackgroundColor(Color.black);
        cellTotal.setColspan(3);
        table.addCell(cellTotal);

//
//        table.addCell(total);
//        table.addCell("");
//        table.addCell("");
        Paragraph totalNumber = new Paragraph(String.valueOf(ticket.getTotalPrice()), fontTotal);
        PdfPCell cellTotalNumber = new PdfPCell(totalNumber);
        cellTotalNumber.setBackgroundColor(Color.black);
        cellTotalNumber.setBorderColor(Color.YELLOW);
        table.addCell(cellTotalNumber);



//            table.addCell(String.valueOf(user.getId()));
//            table.addCell(user.getEmail());
//            table.addCell(user.getFullName());
//            table.addCell(user.getRoles().toString());
//            table.addCell(String.valueOf(user.isEnabled()));
//
    }

    public void export(HttpServletResponse response, List<MerchTicketDTO> merchTicketDTOS, Ticket ticket) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(Color.BLUE);

        Paragraph paragraph = new Paragraph("Ticket N°" + ticket.getId(), fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
//        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(8);
        writeTableHeader(table);
        writeTableData(table, merchTicketDTOS, ticket);

        document.add(paragraph);
        document.add(table);
        document.close();
    }
}
