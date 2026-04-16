# Smart Expense Tracker with Insights

A Python CLI tool for recording daily expenses, categorizing spending, and generating monthly insights.

## Features

- Record expenses with date, category, amount, and description
- Categorize spending into common categories
- Store expense data in `expenses.json`
- Generate monthly summaries and category-wise breakdowns
- Detect highest spending category
- Show pie chart visualization if `matplotlib` is installed

## Setup

1. Install dependencies:

```bash
pip install -r requirements.txt
```

2. Run the tracker:

```bash
python expense_tracker.py
```

## Usage

- Choose `Add expense` to log a new entry
- Choose `Show monthly summary` to see totals and category breakdown
- Choose `List recent expenses` to view the latest records

## Notes

- Default data file: `expenses.json`
- Use `YYYY-MM-DD` for date input, or press Enter to default to today
- Use `YYYY-MM` for monthly summary input, or press Enter to use the current month
