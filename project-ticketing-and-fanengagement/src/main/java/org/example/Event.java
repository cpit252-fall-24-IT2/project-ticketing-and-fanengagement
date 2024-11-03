package org.example;
public class Event {

    private int id;

    private String location;

    private String date;

    private String name;

    private int seats;

    private double price;

    public Event(int id, String location, String date, String name, int seats, double price) {

        this.id = id;

        this.location = location;

        this.date = date;

        this.name = name;

        this.seats = seats;

        this.price = price;

    }

    public void setId(int id) {

        this.id = id;

    }

    public void setLocation(String location) {

        this.location = location;

    }

    public void setDate(String date) {

        this.date = date;

    }

    public void setName(String name) {

        this.name = name;

    }

    public void setSeats(int seats) {

        this.seats = seats;

    }

    public void setPrice(double price) {

        this.price = price;

    }

    public int getId() {

        return id;

    }

    public String getLocation() {

        return location;

    }

    public String getDate() {

        return date;

    }

    public String getName() {

        return name;

    }

    public int getSeats() {

        return seats;

    }

    public double getPrice() {

        return price;

    }

}

