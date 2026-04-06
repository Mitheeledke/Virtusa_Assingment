package Entity;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
	private int transactionId;
    private int bookId;
    private int userId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fineAmount;
    private String status; // "ISSUED", "RETURNED", "OVERDUE"
    
    public Transaction(){
    }
    
	public Transaction(int bookId, int userId, LocalDate issueDate, LocalDate dueDate) {
		this.bookId = bookId;
		this.userId = userId;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.fineAmount = 0.0;
		this.status = "ISSUED";
	}


	public int getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}


	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public LocalDate getIssueDate() {
		return issueDate;
	}


	public LocalDate getDueDate() {
		return dueDate;
	}


	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}


	public LocalDate getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}


	public double getFineAmount() {
		return fineAmount;
	}


	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
