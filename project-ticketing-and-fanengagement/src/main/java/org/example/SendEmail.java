package org.example;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;


public class SendEmail {
    public static void send(String subject, String body, String recipient) throws MissingRequiredPropetiesException {
        final String email = System.getenv("email");
        final String apiKey = System.getenv("apiKey");


        if (email == null || email.isEmpty() || apiKey == null || apiKey.isEmpty()) {
            throw new MissingRequiredPropetiesException("Missing email and/or apiKey. Please set these environment variables.");
        }

        Resend resend = new Resend(apiKey);
        SendEmailRequest request = SendEmailRequest.builder()
                .from(email)
                .to(recipient)
                .subject(subject)
                .html(body)
                .build();

        try {
            SendEmailResponse response = resend.emails().send(request);
            System.out.println("Email sent successfully. ID: " + response.getId());
        } catch (ResendException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}

