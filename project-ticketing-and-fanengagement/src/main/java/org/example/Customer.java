package org.example;



public class Customer {

    private final String name;
    private final String email;


       // Constructor
        public Customer(String name, String email) {

            if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Name and email must not be null or empty.");
            }

            this.name = name;
            this.email = email;
            Logger logger = Logger.getInstance();  // Log the creation of a new customer
            logger.log("New customer created: " + name + ", Email: " + email);

        }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}


