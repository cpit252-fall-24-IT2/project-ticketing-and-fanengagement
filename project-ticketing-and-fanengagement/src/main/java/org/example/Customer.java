package org.example;

public class Customer {
    private String name ;
    private String email ;

       // Constructor
        public Customer(String name, String email) {
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


