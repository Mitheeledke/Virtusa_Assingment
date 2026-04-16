import json
import os
from collections import defaultdict
from datetime import datetime
from pathlib import Path

try:
    import matplotlib.pyplot as plt
except ImportError:
    plt = None

DATA_FILE = Path(__file__).with_name("expenses.json")
CATEGORIES = ["Food", "Travel", "Bills", "Entertainment", "Health", "Shopping", "Other"]


def load_expenses():
    if not DATA_FILE.exists():
        return []
    try:
        with open(DATA_FILE, "r", encoding="utf-8") as f:
            return json.load(f)
    except (json.JSONDecodeError, ValueError):
        return []


def save_expenses(expenses):
    with open(DATA_FILE, "w", encoding="utf-8") as f:
        json.dump(expenses, f, indent=2)


def prompt_date():
    while True:
        date_str = input("Enter date (YYYY-MM-DD) [today]: ").strip()
        if not date_str:
            return datetime.today().date().isoformat()
        try:
            return datetime.fromisoformat(date_str).date().isoformat()
        except ValueError:
            print("Invalid date format. Please use YYYY-MM-DD.")


def prompt_category():
    print("Choose a category:")
    for index, category in enumerate(CATEGORIES, start=1):
        print(f"  {index}. {category}")
    while True:
        choice = input("Enter category number or name: ").strip()
        if choice.isdigit():
            index = int(choice) - 1
            if 0 <= index < len(CATEGORIES):
                return CATEGORIES[index]
        else:
            normalized = choice.title()
            if normalized in CATEGORIES:
                return normalized
        print("Invalid category. Try again.")


def prompt_amount():
    while True:
        amount_str = input("Enter amount: ").strip()
        try:
            amount = float(amount_str)
            if amount <= 0:
                raise ValueError
            return round(amount, 2)
        except ValueError:
            print("Please enter a positive numeric amount.")


def add_expense(expenses):
    print("\nAdd a new expense")
    date = prompt_date()
    category = prompt_category()
    amount = prompt_amount()
    description = input("Description (optional): ").strip()
    expense = {
        "date": date,
        "category": category,
        "amount": amount,
        "description": description,
    }
    expenses.append(expense)
    save_expenses(expenses)
    print("Expense saved.\n")


def filter_expenses_by_month(expenses, year, month):
    return [e for e in expenses if datetime.fromisoformat(e["date"]).year == year and datetime.fromisoformat(e["date"]).month == month]


def summarize_month(expenses, year, month):
    monthly = filter_expenses_by_month(expenses, year, month)
    total = sum(e["amount"] for e in monthly)
    summary = defaultdict(float)
    for expense in monthly:
        summary[expense["category"]] += expense["amount"]
    sorted_summary = sorted(summary.items(), key=lambda item: item[1], reverse=True)
    return total, monthly, sorted_summary


def show_monthly_summary(expenses):
    date_input = input("Enter month to review (YYYY-MM) [current]: ").strip()
    if not date_input:
        today = datetime.today()
        year, month = today.year, today.month
    else:
        try:
            year, month = map(int, date_input.split("-"))
            if not (1 <= month <= 12):
                raise ValueError
        except ValueError:
            print("Invalid format. Use YYYY-MM.\n")
            return
    total, monthly, summary = summarize_month(expenses, year, month)
    month_name = datetime(year, month, 1).strftime("%B %Y")
    print(f"\n{month_name} Expense Summary")
    print("---------------------------")
    print(f"Total spent: ${total:.2f}")
    if not monthly:
        print("No expenses found for this month.\n")
        return
    print("\nCategory breakdown:")
    for category, amount in summary:
        pct = amount / total * 100
        print(f"  {category}: ${amount:.2f} ({pct:.1f}%)")
    show_highest_spending_category(summary)
    if plt:
        render_pie_chart(summary, month_name)
    else:
        print("\nInstall matplotlib to enable pie chart visualization.")
    print()


def show_highest_spending_category(summary):
    if not summary:
        return
    highest_category, highest_amount = summary[0]
    print(f"\nHighest spending category: {highest_category} (${highest_amount:.2f})")


def render_pie_chart(summary, title):
    if not plt:
        return
    categories, amounts = zip(*summary)
    plt.figure(figsize=(8, 6))
    plt.pie(amounts, labels=categories, autopct="%.1f%%", startangle=140, wedgeprops={"edgecolor": "white"})
    plt.title(f"{title} - Category Breakdown")
    plt.tight_layout()
    plt.show()


def list_recent_expenses(expenses, max_items=10):
    if not expenses:
        print("No expenses recorded yet.\n")
        return
    print("\nRecent expenses:")
    for expense in sorted(expenses, key=lambda e: e["date"], reverse=True)[:max_items]:
        print(f"  {expense['date']} | {expense['category']} | ${expense['amount']:.2f} | {expense['description']}")
    print()


def main_menu():
    print("Smart Expense Tracker")
    print("======================")
    print("1. Add expense")
    print("2. Show monthly summary")
    print("3. List recent expenses")
    print("4. Exit")


def main():
    expenses = load_expenses()
    while True:
        main_menu()
        choice = input("Select an option: ").strip()
        if choice == "1":
            add_expense(expenses)
        elif choice == "2":
            show_monthly_summary(expenses)
        elif choice == "3":
            list_recent_expenses(expenses)
        elif choice == "4":
            print("Goodbye!")
            break
        else:
            print("Invalid selection. Please choose 1-4.\n")


if __name__ == "__main__":
    main()
