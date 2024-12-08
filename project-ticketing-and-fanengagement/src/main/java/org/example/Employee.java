package org.example;

import java.util.Scanner;

public class Employee {
    private final String username;
    private final String password;

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Authenticate the employee
    public boolean authenticate(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }





}
