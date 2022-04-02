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
import com.pos.admin.entity.Inventory;


public class PDFGenerator {
	private PDFGenerator() {}
	public static ByteArrayInputStream customerPDFReport(List<Inventory> inventories) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Stock Report", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
        	
        	PdfPTable table = new PdfPTable(9);
        	// Add PDF Table Header ->
			Stream.of("ID","Product Name","Vendor Name", "Quantity","Purchased Price","Tax", "Manufactured Date","Expiry Date","Added Date")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (Inventory inventory : inventories) {
            	PdfPCell idCell = new PdfPCell(new Phrase(inventory.getId().toString()));
            	idCell.setPaddingLeft(4);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);
                
                PdfPCell productNameCell = new PdfPCell(new Phrase(String.valueOf(inventory.getProduct().getName())));
                productNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                productNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                productNameCell.setPaddingRight(4);
                table.addCell(productNameCell);
                

                PdfPCell vendorNameCell = new PdfPCell(new Phrase(String.valueOf(inventory.getVendor().getName())));
                vendorNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                vendorNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                vendorNameCell.setPaddingRight(4);
                table.addCell(vendorNameCell);

                PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(inventory.getQuantity())));
                quantityCell.setPaddingLeft(4);
                quantityCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                quantityCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(quantityCell);
          
                
                PdfPCell purchasedPriceCell = new PdfPCell(new Phrase(String.valueOf(inventory.getPurchasedPrice())));
                purchasedPriceCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                purchasedPriceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                purchasedPriceCell.setPaddingRight(4);
                table.addCell(purchasedPriceCell);
                
                PdfPCell taxCell = new PdfPCell(new Phrase(String.valueOf(inventory.getTax())));
                taxCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                taxCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                taxCell.setPaddingRight(4);
                table.addCell(taxCell);
                
                PdfPCell manufacturedDateCell = new PdfPCell(new Phrase(String.valueOf(inventory.getManufacturedDate())));
                manufacturedDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                manufacturedDateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                manufacturedDateCell.setPaddingRight(4);
                table.addCell(manufacturedDateCell);
                
                PdfPCell expiryDateCell = new PdfPCell(new Phrase(String.valueOf(inventory.getExpiryDate())));
                expiryDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                expiryDateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                expiryDateCell.setPaddingRight(4);
                table.addCell(expiryDateCell);
                
                PdfPCell addedDateCell = new PdfPCell(new Phrase(String.valueOf(inventory.getAddedDate())));
                addedDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                addedDateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                addedDateCell.setPaddingRight(4);
                table.addCell(addedDateCell);
                
            }
            document.add(table);
            
            document.close();
        }catch(DocumentException e) {
        	System.out.println(e.getMessage());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
}
