package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import Entity.Book;
import Entity.Transaction;
import Entity.User;

public class Management {

	public boolean issueBook(User user, Book book) {
		// TODO Auto-generated method stub
		Connection con;
		String sql1 = "insert into transactions(bookId, userId, issueDate, dueDate ) values (?, ?, ?, ?)";
		
		String sql2 = "update books set quantity = ? where bookId = ?";
		
		
		Transaction tr = new Transaction(book.getBookId(), user.getUserId(), 
				LocalDate.now(), LocalDate.now().plusDays(30));
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql1);
			ps.setInt(1, tr.getBookId());
			ps.setInt(2, tr.getUserId());
			ps.setObject(3, tr.getIssueDate());
			ps.setObject(4, tr.getReturnDate());
			if(ps.executeUpdate()>0) {
				System.out.println("Transaction intiated..");
			}
			ps = con.prepareStatement(sql2);
			ps.setInt(1, book.getQuantity()-1);
			ps.setInt(2, book.getBookId());
			if(ps.executeUpdate()>0) return true;
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	

}
