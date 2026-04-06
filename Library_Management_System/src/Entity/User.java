package Entity;

import java.util.ArrayList;
import java.util.List;

public class User {
	int userId;
	String userName;
	String phoneNo;
	List<Book> booksIssued;
	double totalLateFee;
	
	public User() {
		
	}
	
	public User(String userName, String phoneNo) {
		this.userName = userName;
		this.phoneNo = phoneNo;
		this.booksIssued = new ArrayList<>();
		this.totalLateFee = 0.0;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public List<Book> getBooksIssued() {
		return booksIssued;
	}
	public void setBooksIssued(List<Book> booksIssued) {
		this.booksIssued = booksIssued;
	}
	public double getTotalLateFee() {
		return totalLateFee;
	}
	public void setTotalLateFee(double totalLateFee) {
		this.totalLateFee = totalLateFee;
	}
	
	
	
}
