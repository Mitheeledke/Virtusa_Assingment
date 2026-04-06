package Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entity.Book;
import Entity.User;

public class Search {

	public void searchByTitleOrAuthor(String title, String author) {
		// TODO Auto-generated method stub
		
		String sqlQuery1 = "select * from books where title like ?%";
		String sqlQuery2 = "select * from books where author like ?%";
		
		Connection con;
		PreparedStatement ps;
		try {
			if(title == null) {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(sqlQuery2);
				ps.setString(1, author);
			}
			else {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(sqlQuery1);
				ps.setString(1, title);
			}
			
			ResultSet rs = ps.executeQuery();
			System.out.println("Books Are:--");
			while(rs.next()) {
				System.out.println("bookId=" + rs.getInt("bookId") + ", bookName=" + rs.getString("bookName") + 
						", Author=" + rs.getString("author") + ", quantity=" + rs.getInt("quantity")
						+ ", description=" + rs.getString("description") );
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayAllBooks() {
		// TODO Auto-generated method stub
		Connection con;
		PreparedStatement ps ;
		
		String sqlQuery = "select * from books";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("bookId=" + rs.getInt("bookId") + ", bookName=" + rs.getString("bookName") + 
						", Author=" + rs.getString("author") + ", quantity=" + rs.getInt("quantity")
						+ ", description=" + rs.getString("description") );
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Book isBookAvailable(int bookId) {
		// TODO Auto-generated method stub
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from books where bookId = ?");
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();
			Book book = new Book(); 
			if(rs.next()) {
				book.setBookId(rs.getInt("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthor(rs.getString("author"));
				book.setQuantity(rs.getInt("quantity"));
				book.setDescription(rs.getString("description"));
			}
			else {
				System.out.println("Invalid BookId..");
				return null;
			}
			if(book.getQuantity() <=0) return null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public User isUserValid(int userId) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from users where userId = ?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			User user = new User(); 
			if(rs.next()) {
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPhoneNo(rs.getString("phoneNo"));
				user.setTotalLateFee(rs.getInt("totalLateFee"));
				return user;
			}
			System.out.println("Invalid userId.. please Register first");
			
			} 
		catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		return null;
	}
	

	
}
