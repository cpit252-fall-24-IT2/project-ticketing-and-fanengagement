package org.example;

public class Event {
    private int id;
    private String location;
    private String date;
    private String name;
    private boolean[] seats;
    private double price;

    public Event(int id, String location, String date, String name, int totalSeats, double price) {
        this.id = id;
        this.location = location;
        this.date = date;
        this.name = name;
        this.seats = new boolean[totalSeats]; // false means the seat is available
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

    public double getPrice() {
        return price;
    }

    public int getAvailableSeats() {
        int count = 0;
        for (boolean seat : seats) {
            if (!seat) count++;
        }
        return count;
    }

    public boolean isSeatAvailable(int seatNumber) {
        return seatNumber >= 1 && seatNumber <= seats.length && !seats[seatNumber - 1];
    }

    public boolean bookSeat(int seatNumber) {
        if (isSeatAvailable(seatNumber)) {
            seats[seatNumber - 1] = true;
            return true;
        }
        return false;
    }

    public int getTotalSeats() {
        return seats.length;
    }
}
