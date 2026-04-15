package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

	public boolean returnBook(User user, Book book, Transaction tr) {
		// TODO Auto-generated method stub
		String query = "update from transactions set status=? , fineAmount=?, returnDate=? where transactionId=? ";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, tr.getStatus());
			ps.setDouble(2, tr.getFineAmount());
			ps.setObject(3, tr.getReturnDate());
			
			if(ps.executeUpdate()>0) return true;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public Transaction calculateLateFee(Book book, User user) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM transactions WHERE userId = ? AND bookId = ? AND (status = 'ISSUED' OR status = 'OVERDUE')";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			ps.setInt(1, user.getUserId());
			ps.setInt(2, book.getBookId());
			
			ResultSet rs = ps.executeQuery();
			Transaction tr = new Transaction();
			if(rs.next()) {
				tr.setBookId(rs.getInt("bookId"));
				tr.setUserId(rs.getInt("userId"));
				tr.setFineAmount(rs.getDouble("feeAmount"));
				tr.setDueDate(rs.getObject("dueDate",LocalDate.class));
				tr.setTransactionId(rs.getInt("transactionId"));
				tr.setReturnDate(LocalDate.now());
			}
			else {
				System.out.println("No transactoin Found..!");
				return null;
			}
			tr.setFineAmount((double)(ChronoUnit.DAYS.between(tr.getDueDate(), LocalDate.now()) * 5.0));
//			System.out.println("Your fine Amount is :-"+tr.getFineAmount());
			if(tr.getFineAmount() >0.0) {
				tr.setStatus("OVERDUE");
			}
			return tr;
	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
		
	}

	
		
}


