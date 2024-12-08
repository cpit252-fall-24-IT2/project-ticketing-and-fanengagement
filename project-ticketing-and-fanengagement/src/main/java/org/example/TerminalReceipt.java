package org.example;

import java.util.List;

public class TerminalReceipt extends Receipt {
    public TerminalReceipt(List<Product> products) {
        super(products);
    }

    @Override
    public void generate() {
        System.out.println("\n--- Ticket Details ---");
        System.out.println("Receipt ID: " + this.getId());
        System.out.println("Issue Date: " + this.getIssueDate());
        double total = 0.0;

        for (Product product : this.getProducts()) {
            System.out.println("Event: " + product.getName() + " | Price: " + product.getPrice());
            total += product.getPrice();
        }

        System.out.println("Total: " + total);
        System.out.println("-----------------------");
    }
}
