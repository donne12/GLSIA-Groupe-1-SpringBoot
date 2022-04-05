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
import com.pos.admin.entity.Order;
import com.pos.admin.entity.OrderItem;


public class BillPdfGenerator {

    private BillPdfGenerator() {
    }

    public static ByteArrayInputStream customerBillPDFReport(Order order) {
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
            phrase.add(new Chunk(img, 220, 0, true));

            document.add(new Paragraph(phrase));
            document.add(Chunk.NEWLINE);
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, BaseColor.BLACK);
            Paragraph para1 = new Paragraph("Groupe 1 {SpringBoot}", font);
            Paragraph para = new Paragraph("-------------- Votre facture --------------", font);
            para.setAlignment(Element.ALIGN_CENTER);
            para1.setAlignment(Element.ALIGN_CENTER);
            document.add(para1);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(7);

            Stream.of("ID Reçu", "Total", "Rabais", "Date", "Client", "Téléphone", "Email")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });


            PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(order.getOrderId())));
            idCell.setPaddingLeft(8);
            idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(idCell);

            PdfPCell priceCell = new PdfPCell(new Phrase(String.valueOf(order.getTotalPrice())));
            priceCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            priceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            priceCell.setPaddingRight(8);
            table.addCell(priceCell);

            PdfPCell discountCell = new PdfPCell(new Phrase(String.valueOf(order.getDiscount())));
            discountCell.setPaddingLeft(8);
            discountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            discountCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(discountCell);

            PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(order.getDate())));
            dateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            dateCell.setPaddingRight(15);
            table.addCell(dateCell);

            PdfPCell customerNameCell = new PdfPCell(new Phrase(String.valueOf(order.getCustomer().getName())));
            customerNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            customerNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            customerNameCell.setPaddingRight(8);
            table.addCell(customerNameCell);

            PdfPCell customerPhoneCell = new PdfPCell(new Phrase(String.valueOf(order.getCustomer().getPhoneNumber())));
            customerPhoneCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            customerPhoneCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            customerPhoneCell.setPaddingRight(8);
            table.addCell(customerPhoneCell);

            PdfPCell customerEmailCell = new PdfPCell(new Phrase(String.valueOf(order.getCustomer().getEmail())));
            customerEmailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            customerEmailCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            customerEmailCell.setPaddingRight(8);
            table.addCell(customerEmailCell);
            document.add(table);
            document.add(Chunk.NEWLINE);
            Paragraph para3 = new Paragraph("-------------- Produits achetés --------------", font);
            para3.setAlignment(Element.ALIGN_CENTER);
            document.add(para3);
            document.add(Chunk.NEWLINE);
            PdfPTable table2 = new PdfPTable(6);
            Stream.of("ID Produit", "Libellé", "Prix unitaire", "Taxe", "Quantité", "Total")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table2.addCell(header);
                    });

            List<OrderItem> orderedItemsList = order.getOrders();
            for (OrderItem orderItem : orderedItemsList) {
                PdfPCell productIdCell = new PdfPCell(new Phrase(String.valueOf(orderItem.getProduct().getId())));
                productIdCell.setPaddingLeft(8);
                productIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                productIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table2.addCell(productIdCell);

                PdfPCell productNameCell = new PdfPCell(new Phrase(orderItem.getProduct().getName()));
                productNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                productNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                productNameCell.setPaddingRight(8);
                table2.addCell(productNameCell);

                PdfPCell mrpCell = new PdfPCell(new Phrase(String.valueOf(orderItem.getProduct().getMrp())));
                mrpCell.setPaddingLeft(8);
                mrpCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                mrpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table2.addCell(mrpCell);

                PdfPCell taxCell = new PdfPCell(new Phrase(String.valueOf(orderItem.getProduct().getTax())));
                taxCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                taxCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                taxCell.setPaddingRight(8);
                table2.addCell(taxCell);

                PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(orderItem.getQuantity())));
                quantityCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                quantityCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                quantityCell.setPaddingRight(8);
                table2.addCell(quantityCell);

                PdfPCell totalPriceCell = new PdfPCell(new Phrase(String.valueOf(orderItem.getPrice())));
                totalPriceCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                totalPriceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                totalPriceCell.setPaddingRight(8);
                table2.addCell(totalPriceCell);
            }


            document.add(table2);
            document.close();
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
