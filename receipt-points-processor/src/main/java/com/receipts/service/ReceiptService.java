package com.receipts.service;

import com.receipts.model.Item;
import com.receipts.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReceiptService {

    private final Map<String, Receipt> receiptStore = new ConcurrentHashMap<>();

    public Map<String, String> processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receipt.setPoints(calculatePoints(receipt));
        receiptStore.put(id, receipt);
        return Map.of("id", id);
    }

    public Map<String, Integer> getPoints(String id) {
        Receipt receipt = receiptStore.get(id);
        if (receipt == null) {
            throw new IllegalArgumentException("Receipt not found");
        }
        return Map.of("points", receipt.getPoints());
    }

    private int calculatePoints(Receipt receipt) {
        int points = 0;
        String retailer = receipt.getRetailer();
        points += retailer.chars().filter(Character::isLetterOrDigit).count();

        double total = Double.parseDouble(receipt.getTotal());
        if (total == Math.floor(total)) points += 50;
        if (total % 0.25 == 0) points += 25;

        points += (receipt.getItems().size() / 2) * 5;

        for (Item item : receipt.getItems()) {
            String description = item.getShortDescription().trim();
            if (description.length() % 3 == 0) {
                points += Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
            }
        }

        String[] dateParts = receipt.getPurchaseDate().split("-");
        if (Integer.parseInt(dateParts[2]) % 2 != 0) points += 6;

        String[] timeParts = receipt.getPurchaseTime().split(":");
        int hour = Integer.parseInt(timeParts[0]);
        if (hour >= 14 && hour < 16) points += 10;

        return points;
    }
}
