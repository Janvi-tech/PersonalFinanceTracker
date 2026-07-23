# 💰 Personal Finance Tracker

A Personal Finance Tracker is a web-based application developed using Java, Spring Boot, Thymeleaf, SQLite, HTML, CSS, and JavaScript. The application helps users manage their personal finances by tracking income and expenses, categorizing transactions, and viewing financial summaries through an interactive dashboard
---
## 📌 Project Overview

Managing personal finances manually can be difficult and time-consuming. This application provides a simple and user-friendly platform to record financial transactions, monitor spending habits, and analyze income and expenses.

The system is designed with secure user authentication so that each user can access only their own financial records.

---

## 🚀 Features

### User Authentication
- User Registration
- User Login
- Secure Password Encryption
- Session Management
- Logout

### Dashboard
- Total Income
- Total Expenses
- Total Savings
- Recent Transactions
- Expense Summary
- Income Summary

### Income Management
- Add Income
- Edit Income
- Delete Income
- View Income History

### Expense Management
- Add Expense
- Edit Expense
- Delete Expense
- View Expense History

### Category Management
- Create Categories
- Update Categories
- Delete Categories
- Assign Categories to Transactions

### Reports
- Monthly Income Report
- Monthly Expense Report
- Savings Report

### Search & Filter
- Search by Date
- Search by Category
- Search by Transaction Type

---

## 🛠 Technologies Used

### Backend
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA

### Frontend
- HTML5
- CSS3
- JavaScript
- Thymeleaf

### Database
- SQLite

### Build Tool
- Maven

### IDE
- Eclipse IDE

---

## 📂 Project Structure

```
PersonalFinanceTracker
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── controller
│   │   │   ├── model
│   │   │   ├── repository
│   │   │   ├── service
│   │   │   ├── config
│   │   │   └── PersonalFinanceTrackerApplication.java
│   │   │
│   │   ├── resources
│   │   │   ├── templates
│   │   │   ├── static
│   │   │   └── application.properties
│
└── pom.xml
```

---

## 📊 Database Tables

### Users
- User ID
- Full Name
- Email
- Username
- Password

### Income
- Income ID
- User ID
- Source
- Amount
- Date
- Description

### Expense
- Expense ID
- User ID
- Category
- Amount
- Date
- Description

### Category
- Category ID
- Category Name

---

## 🔒 Security

- Passwords are encrypted before storing.
- Session-based authentication.
- Each user can access only their own financial data.
- Input validation on forms.

---

## ⚙ Installation

### Clone Repository

```bash
git clone https://github.com/YourUsername/PersonalFinanceTracker.git
```

### Open Project

Open the project in Eclipse IDE.

### Configure Database

Update the SQLite database path in:

```
application.properties
```

### Run Project

Run

```
PersonalFinanceTrackerApplication.java
```

Open

```
http://localhost:8080
```

---

## 📸 Screenshots

Add screenshots of:

- Login Page
- Registration Page
- Dashboard
- Income Page
- Expense Page
- Reports Page

---

## 🎯 Future Enhancements

- Email Notifications
- Budget Planning
- Bill Reminder
- Export Reports to PDF
- Export Reports to Excel
- Mobile Responsive UI
- Dark Mode
- Charts and Graphs
- Multi-Currency Support

---

## 🎓 Learning Outcomes

This project helped in understanding:

- Spring Boot
- MVC Architecture
- CRUD Operations
- SQLite Database Integration
- User Authentication
- Session Management
- Form Validation
- Thymeleaf
- Maven Project Structure

---

## 👩‍💻 Author

**Janvi Ghodageri**

Technologies:
- Java
- Spring Boot
- HTML
- CSS
- JavaScript
- SQLite
- Maven

---

## 📄 License

This project is developed for learning and educational purposes.
