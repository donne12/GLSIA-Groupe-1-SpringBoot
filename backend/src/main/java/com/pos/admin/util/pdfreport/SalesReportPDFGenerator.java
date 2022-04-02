package com.pos.admin.util.pdfreport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pos.admin.entity.Order;


public class SalesReportPDFGenerator {
	private SalesReportPDFGenerator() {}
	public static ByteArrayInputStream customerPDFReport(List<Order> orders) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Sales Report", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
        	
        	PdfPTable table = new PdfPTable(7);
        	// Add PDF Table Header ->
        	//customer details (Name, phone number, mail), price to be paid and tax details 
			Stream.of("Receipt Id","Total price","Discount","Date","Customer Name","Customer phone number","Customer mail")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (Order order : orders) {
            	
            	PdfPCell idCell = new PdfPCell(new Phrase(order.getOrderId().toString()));
            	idCell.setPaddingLeft(4);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);
                
                PdfPCell priceCell = new PdfPCell(new Phrase(String.valueOf(order.getTotalPrice())));
                priceCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                priceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                priceCell.setPaddingRight(4);
                table.addCell(priceCell);
                
                PdfPCell discountCell = new PdfPCell(new Phrase(String.valueOf(order.getDiscount())));
                discountCell.setPaddingLeft(4);
                discountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                discountCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(discountCell);

                PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(order.getDate())));
                dateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                dateCell.setPaddingRight(4);
                table.addCell(dateCell);

                PdfPCell customerNameCell = new PdfPCell(new Phrase(String.valueOf(order.getCustomer().getName())));
                customerNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                customerNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                customerNameCell.setPaddingRight(4);
                table.addCell(customerNameCell);
                
                PdfPCell customerPhoneCell = new PdfPCell(new Phrase(String.valueOf(order.getCustomer().getPhoneNumber())));
                customerPhoneCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                customerPhoneCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                customerPhoneCell.setPaddingRight(4);
                table.addCell(customerPhoneCell);
                
                PdfPCell customerEmailCell = new PdfPCell(new Phrase(String.valueOf(order.getCustomer().getEmail())));
                customerEmailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                customerEmailCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                customerEmailCell.setPaddingRight(4);
                table.addCell(customerEmailCell);
                
               
                
            }
            document.add(table);
            
            document.close();
        }catch(DocumentException e) {
        	System.out.println(e.getMessage());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
}
