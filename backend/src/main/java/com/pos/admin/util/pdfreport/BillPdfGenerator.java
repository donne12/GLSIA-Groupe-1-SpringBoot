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
import com.pos.admin.entity.OrderItem;


public class BillPdfGenerator {
	
	private BillPdfGenerator() {}
	public static ByteArrayInputStream customerBillPDFReport(Order order) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Customer Bill Receipt", font);
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
            
           
            	
            	PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(order.getOrderId())));
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
                
//              Paragraph para1 = new Paragraph( "Purchased Products", font);
//    			para.setAlignment(Element.ALIGN_CENTER);
//    			para1.setSpacingAfter(72f);
//    			document.add(para1);
//    			document.add(Chunk.NEWLINE);
                PdfPTable table1 = new PdfPTable(6);
            	// Add PDF Table Header ->
            	//customer details (Name, phone number, mail), price to be paid and tax details 
    			Stream.of("Product Id","Product Name","MRP","Tax","Quantity","Total Price")
    			    .forEach(headerTitle -> {
    			          PdfPCell header = new PdfPCell();
    			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
    			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
    			          header.setPhrase(new Phrase(headerTitle, headFont));
    			          table1.addCell(header);
    			    });
                
    				List<OrderItem> orderedItemsList=order.getOrders();
                	for(OrderItem orderItem:orderedItemsList) {
                	PdfPCell productIdCell = new PdfPCell(new Phrase(String.valueOf(orderItem.getProduct().getId())));
                	productIdCell.setPaddingLeft(4);
                	productIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                	productIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table1.addCell(productIdCell);
                    
                    PdfPCell productNameCell = new PdfPCell(new Phrase(orderItem.getProduct().getName()));
                    productNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    productNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    productNameCell.setPaddingRight(4);
                    table1.addCell(productNameCell);
                    
                    PdfPCell mrpCell = new PdfPCell(new Phrase(String.valueOf(orderItem.getProduct().getMrp())));
                    mrpCell.setPaddingLeft(4);
                    mrpCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    mrpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table1.addCell(mrpCell);

                    PdfPCell taxCell = new PdfPCell(new Phrase(String.valueOf(orderItem.getProduct().getTax())));
                    taxCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    taxCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    taxCell.setPaddingRight(4);
                    table1.addCell(taxCell);

                    PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(orderItem.getQuantity())));
                    quantityCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    quantityCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    quantityCell.setPaddingRight(4);
                    table1.addCell(quantityCell);
                    
                    PdfPCell totalPriceCell = new PdfPCell(new Phrase(String.valueOf(orderItem.getPrice())));
                    totalPriceCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    totalPriceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    totalPriceCell.setPaddingRight(4);
                    table1.addCell(totalPriceCell);
                    
                	}
              
            document.add(table);
            document.add(table1);
            document.close();
        }catch(DocumentException e) {
        	System.out.println(e.getMessage());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
}
