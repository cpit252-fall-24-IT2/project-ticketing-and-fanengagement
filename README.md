# Ticket Booking System

## 📖 Project Overview
The **Ticket Booking System** is a Java-based console application designed to allow users to book tickets for various events. It features two main roles:
1. **Employee**: Employees can add new events to the system, specifying details such as event name, location, date, ticket price, and the number of seats.
2. **Customer**: Customers can browse available events, select an event, and book tickets for specific seats.

---

## 🚀 How to Use the Application:

### Steps to Run the Application
1. Clone or download the project files.
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse) or navigate to the project folder in your terminal.
3. Compile and run the `Main` class.

### Using the System
#### 1. **Employee Role**
- At the main menu, select `1` for the **Employee** role.
- Enter the username and password for authentication.
    - Example credentials:
        - Username: `saleh`
        - Password: `1234`
- Once authenticated, you can:
    - Add a new event by providing details like name, location, date, number of seats, and ticket price. The system ensures that both price and seats are valid.

#### 2. **Customer Role**
- At the main menu, select `2` for the **Customer** role.
- Enter your name and email to proceed.
- Once inside the customer menu, you can:
    - View all available events.
    - Book a seat for an event by providing the event ID and seat number.
    - View your bookings to see the events and seats you have reserved.

---

## ✨ Features
- **Employee Authentication**: Ensures only authorized employees can add events.
- **Customer Interaction**: Customers can book seats for events easily.
- **Validation**: Ensures no event is created with zero or negative values for seats and price.
- **Logging**: Tracks system actions, such as booking confirmation or invalid attempts.

---

## 📋 Example Scenarios
### Employee Adding an Event:

Enter event name: Art Exhibition
Enter event location: Jeddah
Enter event date (YYYY-MM-DD): 2024-12-15
Enter number of seats : 50
Enter ticket price: 120
Event added successfully!

---

## Screenshots

![Ticketing and fan engagement Demo](https://github.com/user-attachments/assets/44228fb0-776c-4e6a-9de2-ae165914c13e)
