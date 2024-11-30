package org.example;

import org.example.Product;
import org.example.SendEmail;

import java.util.List;

public class EmailReceipt extends Receipt {
    private String subject;
    private String recipient;

    public EmailReceipt(String subject, String recipient, List<Product> products) {
        super(products);
        this.subject = subject;
        this.recipient = recipient;

    }

    @Override
    public void generate() {
        StringBuilder htmlContent = new StringBuilder();

        // Start the HTML structure
        htmlContent.append("<!DOCTYPE html>");
        htmlContent.append("<html><head><style>");
        htmlContent.append("table {border-collapse: collapse; width: 100%;}");
        htmlContent.append("th, td {border: 1px solid #ddd; padding: 8px;}");
        htmlContent.append("th {background-color: #f2f2f2; text-align: left;}");
        htmlContent.append("</style></head><body>");
        htmlContent.append("<h2>Your Ticket Details</h2>");

        // Add general receipt information
        htmlContent.append("<p>Receipt ID: ").append(this.getId()).append("</p>");
        htmlContent.append("<p>Issue Date: ").append(this.getIssueDate()).append("</p>");

        // Add the tickets in a table format
        htmlContent.append("<table>");
        htmlContent.append("<tr><th>Event</th><th>Price (SAR)</th></tr>");
        double total = 0.0;
        for (Product product : this.getProducts()) {
            htmlContent.append("<tr>");
            htmlContent.append("<td>").append(product.getName()).append("</td>");
            htmlContent.append("<td>").append(product.getPrice()).append("</td>");
            htmlContent.append("</tr>");
            total += product.getPrice();
        }

        // Add the total price
        htmlContent.append("<tr><td><strong>Total</strong></td><td><strong>").append(total).append(" SAR</strong></td></tr>");
        htmlContent.append("</table>");

        // Closing message
        htmlContent.append("<p>Thank you for booking with us!</p>");
        htmlContent.append("</body></html>");

        // Send the email with the HTML content
        try {
            SendEmail.send(this.subject, htmlContent.toString(), this.recipient);
            System.out.println("Receipt email sent to " + this.recipient);
        } catch (Exception e) {
            System.err.println("Failed to email the receipt: Reason: " + e.getMessage());
        }
    }

}
