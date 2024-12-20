package com.receipts.controller;

import com.receipts.model.Receipt;
import com.receipts.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/process")
    public Map<String, String> processReceipt(@RequestBody Receipt receipt) {
        return receiptService.processReceipt(receipt);
    }

    @GetMapping("/{id}/points")
    public Map<String, Integer> getPoints(@PathVariable String id) {
        return receiptService.getPoints(id);
    }
}
