//package com.carwash.payment_service.util;
//
//import org.apache.pdfbox.pdmodel.*;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.UUID;
//
//import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA;
//import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;
//
//public class ReceiptGenerator {
//
//    public static byte[] generateReceipt(String payerId, String paymentId, double amount, String status) throws IOException {
//        // Create a new document and a page
//        PDDocument document = new PDDocument();
//        PDPage page = new PDPage(PDRectangle.A4);
//        document.addPage(page);
//        PDType1Font pdType1Font1= new PDType1Font(HELVETICA_BOLD );
//        PDType1Font pdType1Font2 = new PDType1Font(HELVETICA);
//
//        // Create content stream for adding content to the page
//        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
//            // Start adding text
//            contentStream.beginText();
//            // Set font to Helvetica-Bold with size 20
//            contentStream.setFont(pdType1Font1, 20);
//            contentStream.newLineAtOffset(100, 750);
//            contentStream.showText("i-Transform Car Wash - Payment Receipt");
//            contentStream.endText();
//
//            contentStream.beginText();
//            // Set font to Helvetica with size 14
//            contentStream.setFont(pdType1Font2, 14);
//            contentStream.newLineAtOffset(100, 700);
//            contentStream.showText("Receipt ID: " + UUID.randomUUID());
//            contentStream.newLineAtOffset(0, -20);
//            contentStream.showText("Payment ID: " + paymentId);
//            contentStream.newLineAtOffset(0, -20);
//            contentStream.showText("Payer ID: " + payerId);
//            contentStream.newLineAtOffset(0, -20);
//            contentStream.showText("Amount: $" + amount);
//            contentStream.newLineAtOffset(0, -20);
//            contentStream.showText("Status: " + status);
//            contentStream.endText();
//        }
//
//        // Write the document to a ByteArrayOutputStream
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        document.save(output);
//        document.close();
//
//        return output.toByteArray();
//    }
//}
