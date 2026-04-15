package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.Book;
import Entity.User;

public class AdminServices {

	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		String query = "insert into books (bookName, author, quantity, description) values"
				+ "(?, ?, ?, ?)";
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, book.getBookName());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getQuantity());
			ps.setString(4, book.getDescription());
			if(ps.executeUpdate()>0) return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean removeBook(int bookId) {
		// TODO Auto-generated method stub
		String query = "delete from books where bookId = ?";
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, bookId);
			if(ps.executeUpdate()>0) return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
	}

	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		String query = "update books set bookName=?, author=?, description=?, quantity=? where bookId=?";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			
			ps.setString(1, book.getBookName());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getDescription());
			ps.setInt(4, book.getQuantity());
			ps.setInt(5, book.getBookId());
			
			return ps.executeUpdate()>0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public User registerUser(User user) {
		// TODO Auto-generated method stub
		String query = "insert into users (userName, phoneNo, totalLateFee) values"
				+ "(?, ?, ?)";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPhoneNo());
			ps.setDouble(3, user.getTotalLateFee());
			
			if(ps.executeUpdate()>0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					user.setUserId(id);
					return user;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
