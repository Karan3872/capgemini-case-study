package com.carwash.payment_service.service;

import com.braintreegateway.*;
import com.carwash.payment_service.config.OrderServiceClient;
import com.carwash.payment_service.model.Order;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class BraintreeService {

    @Autowired
    private BraintreeGateway braintreeGateway;

    @Autowired
    private OrderServiceClient orderServiceClient;

    public String generateClientToken() {
        return braintreeGateway.clientToken().generate();
    }

    public Result<Transaction> processPayment(String paymentMethodNonce, double amount) {
        TransactionRequest request = new TransactionRequest()
                .amount(BigDecimal.valueOf(amount))
                .paymentMethodNonce(paymentMethodNonce)
                .options()
                .submitForSettlement(true)
                .done();

        return braintreeGateway.transaction().sale(request);
    }

    public Order getOrderById(long id) {
        return orderServiceClient.getById(id).getBody();
    }

    public String generateReceipt(Transaction transaction, Long orderId) {
        Order order = getOrderById(orderId);
        StringBuilder receipt = new StringBuilder();

        receipt.append("===== Car Wash Payment Receipt =====\n");
        receipt.append("Order ID      : ").append(order.getId()).append("\n");
        receipt.append("Customer ID   : ").append(order.getCustomerId()).append("\n");
        receipt.append("Washer ID     : ").append(order.getWasherId()).append("\n");

        if (order.getWashPlan() != null) {
            receipt.append("Plan Name     : ").append(order.getWashPlan().getName()).append("\n");
            receipt.append("Plan Price    : $").append(order.getWashPlan().getPrice()).append("\n");
        }

        receipt.append("Scheduled Date: ").append(order.getScheduledDate()).append("\n");
        receipt.append("Transaction ID: ").append(transaction.getId()).append("\n");
        receipt.append("Amount Paid   : $").append(transaction.getAmount()).append("\n");
        receipt.append("Status        : ").append(transaction.getStatus()).append("\n");
        receipt.append("Paid At       : ").append(transaction.getCreatedAt().getTime()).append("\n");
        receipt.append("====================================\n");

        String fileName = "receipt_order_" + order.getId() + ".pdf";
        String filePath = "receipts/" + fileName;
        generatePdfReceipt(transaction, order, filePath);

        String downloadLink = "http://localhost:8087/api/payment/receipt/" + order.getId();
        receipt.append("Download PDF  : ").append(downloadLink).append("\n");

        return receipt.toString();
    }

    public void generatePdfReceipt(Transaction transaction, Order order, String filePath) {
        try {
            File folder = new File("receipts");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

            document.add(new Paragraph("Car Wash Payment Receipt", titleFont));
            document.add(new Paragraph("Generated on: " + LocalDateTime.now()));
            document.add(new Paragraph("--------------------------------------------------"));

            document.add(new Paragraph("Order ID      : " + order.getId(), normalFont));
            document.add(new Paragraph("Customer ID   : " + order.getCustomerId(), normalFont));
            document.add(new Paragraph("Washer ID     : " + order.getWasherId(), normalFont));
            if (order.getWashPlan() != null) {
                document.add(new Paragraph("Plan Name     : " + order.getWashPlan().getName(), normalFont));
                document.add(new Paragraph("Plan Price    : $" + order.getWashPlan().getPrice(), normalFont));
            }

            document.add(new Paragraph("Scheduled Date: " + order.getScheduledDate(), normalFont));
            document.add(new Paragraph("Transaction ID: " + transaction.getId(), normalFont));
            document.add(new Paragraph("Amount Paid   : $" + transaction.getAmount(), normalFont));
            document.add(new Paragraph("Status        : " + transaction.getStatus(), normalFont));
            document.add(new Paragraph("Paid At       : " + transaction.getCreatedAt().getTime(), normalFont));

            document.add(new Paragraph("--------------------------------------------------"));
            document.add(new Paragraph("Thank you for choosing i-Transform Car Wash!", normalFont));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate PDF receipt", e);
        }
    }
}
