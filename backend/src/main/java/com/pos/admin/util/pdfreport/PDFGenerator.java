package com.pos.admin.util.pdfreport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import com.itextpdf.text.*;
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
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            Image img = null;
            try {
                img = Image.getInstance("C:/IAI.jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }

            img.scaleAbsolute(70f, 70f);
            img.setAlignment(Element.ALIGN_CENTER);
            Phrase phrase = new Phrase();
            phrase.add(new Chunk(img, 0, 0, true));

            document.add(new Paragraph(phrase));
            document.add(Chunk.NEWLINE);
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, BaseColor.BLACK);
            Paragraph para1 = new Paragraph( "Groupe 1 {SpringBoot}", font);
            Paragraph para = new Paragraph( "-------------- Journal des stocks pour le mois d'Avril --------------", font);
            para.setAlignment(Element.ALIGN_CENTER);
            para1.setAlignment(Element.ALIGN_CENTER);
            document.add(para1);
            document.add(para);
            document.add(Chunk.NEWLINE);

        	PdfPTable table = new PdfPTable(9);
        	// Add PDF Table Header ->
			Stream.of("ID","Produit","Fournisseur", "Quantité","Prix d'achat","Taxe", "Date de fabrication","Date d'expiration","Date d'ajout")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (Inventory inventory : inventories) {
            	PdfPCell idCell = new PdfPCell(new Phrase(inventory.getId().toString()));
            	idCell.setPaddingLeft(8);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);
                
                PdfPCell productNameCell = new PdfPCell(new Phrase(String.valueOf(inventory.getProduct().getName())));
                productNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                productNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                productNameCell.setPaddingRight(8);
                table.addCell(productNameCell);
                

                PdfPCell vendorNameCell = new PdfPCell(new Phrase(String.valueOf(inventory.getVendor().getName())));
                vendorNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                vendorNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                vendorNameCell.setPaddingRight(8);
                table.addCell(vendorNameCell);

                PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(inventory.getQuantity())));
                quantityCell.setPaddingLeft(4);
                quantityCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                quantityCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(quantityCell);
          
                
                PdfPCell purchasedPriceCell = new PdfPCell(new Phrase(String.valueOf(inventory.getPurchasedPrice())));
                purchasedPriceCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                purchasedPriceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                purchasedPriceCell.setPaddingRight(8);
                table.addCell(purchasedPriceCell);
                
                PdfPCell taxCell = new PdfPCell(new Phrase(String.valueOf(inventory.getTax())));
                taxCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                taxCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                taxCell.setPaddingRight(8);
                table.addCell(taxCell);
                
                PdfPCell manufacturedDateCell = new PdfPCell(new Phrase(String.valueOf(inventory.getManufacturedDate())));
                manufacturedDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                manufacturedDateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                manufacturedDateCell.setPaddingRight(8);
                table.addCell(manufacturedDateCell);
                
                PdfPCell expiryDateCell = new PdfPCell(new Phrase(String.valueOf(inventory.getExpiryDate())));
                expiryDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                expiryDateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                expiryDateCell.setPaddingRight(8);
                table.addCell(expiryDateCell);
                
                PdfPCell addedDateCell = new PdfPCell(new Phrase(String.valueOf(inventory.getAddedDate())));
                addedDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                addedDateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                addedDateCell.setPaddingRight(8);
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
