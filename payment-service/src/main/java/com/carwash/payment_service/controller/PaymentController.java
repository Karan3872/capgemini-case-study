package com.carwash.payment_service.controller;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.carwash.payment_service.service.BraintreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private BraintreeService braintreeService;

    @GetMapping("/client-token")
    public ResponseEntity<String> getClientToken() {
        return ResponseEntity.ok(braintreeService.generateClientToken());
    }

    @PostMapping("/process")
    public ResponseEntity<?> processPayment(
            @RequestParam("nonce") String nonce,
            @RequestParam("amount") double amount,
            @RequestParam("orderId") Long orderId) {

        Result<Transaction> result = braintreeService.processPayment(nonce, amount);

        if (result.isSuccess()) {
            String receiptText = braintreeService.generateReceipt(result.getTarget(), orderId);
            return ResponseEntity.ok(receiptText);
        } else {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
    }

    @GetMapping("/receipt/{orderId}")
    public ResponseEntity<?> downloadReceipt(@PathVariable Long orderId) {
        String fileName = "receipt_order_" + orderId + ".pdf";
        File file = new File("receipts/" + fileName);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        FileSystemResource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .body(resource);
    }
}
