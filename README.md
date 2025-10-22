# Expense Tracker

A simple and intuitive Java Swing desktop application for tracking personal expenses.

## Features

- **Add Expenses**: Input expense description, amount, and category
- **View All Expenses**: Display expenses in an organized table format
- **Category Selection**: Choose from predefined categories (Food, Transportation, Entertainment, Utilities, Healthcare, Other)
- **Real-time Total**: Automatically calculates and displays the total amount of all expenses
- **Input Validation**: Ensures valid data entry with user-friendly error messages
- **Clean UI**: Simple and intuitive interface built with Java Swing

## Requirements

- Java Development Kit (JDK) 8 or higher
- No external dependencies required

## Installation

1. Clone or download this repository
2. Navigate to the project directory

## Usage

### Compile the Application

```bash
javac ExpenseTracker.java
```

### Run the Application

```bash
java ExpenseTracker
```

## How to Use

1. **Enter Expense Details**:
   - Type a description in the "Description" field
   - Enter the amount in the "Amount" field (must be a positive number)
   - Select a category from the dropdown menu

2. **Add Expense**:
   - Click the "Add Expense" button
   - The expense will appear in the table below
   - The total will update automatically

3. **View Expenses**:
   - All expenses are displayed in the table with three columns: Description, Amount, and Category
   - Scroll through the table if you have many entries

4. **Track Total**:
   - The total amount is displayed at the bottom in bold green text
   - Updates automatically as you add expenses

## Application Structure

- **ExpenseTracker.java**: Main application class containing:
  - GUI components and layout
  - Event handlers for adding expenses
  - Input validation logic
  - Expense data model (inner class)
  - ArrayList for storing expense records

## Screenshots

The application features:
- Top section: Input fields and Add button
- Middle section: Scrollable table showing all expenses
- Bottom section: Bold total amount display

## Future Enhancements

Possible improvements for future versions:
- Save expenses to a file or database
- Edit or delete existing expenses
- Filter expenses by category or date
- Generate expense reports
- Add date/time tracking for each expense
- Export data to CSV or PDF

## License

This project is open source and available for personal and educational use.
