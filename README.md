# Project: Ticketing and Fan Engagement System

## Overview
The Ticketing and Fan Engagement System is a Java-based application designed to manage events, tickets, and customer interactions. It allows employees to manage events and customers to book tickets with options to view tickets in multiple formats (PDF, email, or console). The application uses SQLite for data storage and incorporates modular components for scalability.

---

## Features

### Employee Functionality
- Add events with details such as name, location, date, price, and total seats.
- Remove events based on unique event IDs.
- View all available events in the system.

### Customer Functionality
- View a list of all available events.
- Book tickets for a specific event and seat number.
- Choose how to receive the ticket:
    - Download as a PDF.
    - Send via email.
    - Display directly in the console.

### System Design
- **Database Management**: SQLite database for event and booking storage.
- **Modular Architecture**: Each class handles a specific responsibility (e.g., `EventManager`, `CustomerMenu`).
- **Validation**: Input validation to ensure data integrity (e.g., name, email, price).
- **Testing**: JUnit 5 tests cover critical functionalities.

---
### Screenshots

#### Main Menu

![Main Menu Screenshot](screenshots/Main-menu.png)

#### OutPut

![OutPut](screenshots/output.png)

#### Unit Test
![Unit Test Screenshot](screenshots/Unit-test.png)


## Technologies Used
- **Programming Language**: Java
- **Database**: SQLite
- **Testing Framework**: JUnit 5
- **PDF Generation**: Apache PDFBox
- **Email Sending**: Custom implementation for email services

---

