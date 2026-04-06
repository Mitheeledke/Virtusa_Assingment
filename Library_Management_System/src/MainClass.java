import java.util.Scanner;

import Entity.Book;
import Entity.User;
import Services.AdminServices;
import Services.Management;
import Services.Search;

public class MainClass {
	public static Scanner sc;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 sc = new Scanner(System.in);
		System.out.println("Welcome To Library Management System..>!!");
		
		while(true) {
			System.out.println("Select using number\r\n"
					+ "1.search Book\r\n"
					+ "2.see all Books\r\n"
					+ "3.Issue Book \r\n"
					+ "4.Add Book\r\n"
					+ "5.Remove Book\r\n"
					+ "6.Update Book\r\n"
					+ "7.user Register\r\n"
					+ "8.Return Book\r\n"
					+ "9.show all transaction\r\n"
					+ "10.show transactions of user\r\n"
					+ "11.user Issued Books\r\n"
					+ "12.late fee payment of user\r\n"
					);
			int val = sc.nextInt();
			sc.nextLine();
			if(!(val>0 && val<13)) {
				System.out.println("Enter Proper Number ..>>>>>..!!!");
				continue;
			}
			if(val == 1) searchBook();
			if(val == 2) seeAllBooks();
			if(val == 3) issueBook();
			if(val == 4) addBook();
			if(val == 5) removeBook();
			if(val == 6) updateBook();
			if(val == 7) registerUser();
			if(val == 8) returnBook();
			if(val == 9) allTransactionsAdmin();
			if(val == 10) showTransactions();
			if(val == 11) userBooks();
			if(val == 12) feePayment();
			
		}

	}

	private static void feePayment() {
		// TODO Auto-generated method stub
		
	}

	private static void userBooks() {
		// TODO Auto-generated method stub
		
		
	}

	private static void showTransactions() {
		// TODO Auto-generated method stub
		
	}

	private static void allTransactionsAdmin() {
		// TODO Auto-generated method stub
		
	}

	private static void returnBook() {
		// TODO Auto-generated method stub
		
	}

	private static void registerUser() {
		// TODO Auto-generated method
		System.out.println("Enter User Name:-");
		String userName = sc.nextLine();
		System.out.println("Enter Phone No:-");
		String phoneNo = sc.nextLine();
		User user = new User(userName, phoneNo);
		AdminServices adser = new AdminServices();
		user =  adser.registerUser(user);
		System.out.println("User register Success: - UserID - "+user.getUserId());
		
	}

	private static void updateBook() {
		// TODO Auto-generated method stub
		Search sr = new Search();
		System.out.println("Enter BookId:-");
		int bookId = sc.nextInt();
		Book book = sr.isBookAvailable(bookId);
		if(book == null) System.out.println("No Book Found..!");
		System.out.println("Enter Want you want to update\n "
				+ "1.Book Name \n"
				+ "2.Author \n"
				+ "3.Description \n"
				+ "4.quantity");
		int ch = sc.nextInt();
		sc.nextLine();
		if(ch == 1) book.setBookName(sc.nextLine());
		else if(ch == 2) book.setAuthor(sc.nextLine());
		else if(ch == 3) book.setDescription(sc.nextLine());
		else {
			book.setQuantity(sc.nextInt());
			sc.nextLine();
		}
		AdminServices adser = new AdminServices();
		if(adser.updateBook(book)) System.out.println("Success..");
		else System.out.println("Failed...");
	}

	private static void removeBook() {
		// TODO Auto-generated method stub
		System.out.println("Enter BookId:-");
		int bookId = sc.nextInt();
		AdminServices adser = new AdminServices();
		if(adser.removeBook(bookId)) System.out.println("Success..");
		else System.out.println("No book found..");
		return;
	}

	private static void addBook() {
		// TODO Auto-generated method stub
		System.out.println("Enter book name:");
		String name = sc.nextLine();
		System.out.println("Enter Author name:");
		String author = sc.nextLine();
		System.out.println("Enter Quantity of books:");
		int quant = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Description:");
		String des = sc.nextLine();
		Book book = new Book(name, author, quant, des);
		AdminServices ad = new AdminServices();
		if(ad.addBook(book)) System.out.println("Success");
		return;
		
	}

	private static void issueBook() {
		// TODO Auto-generated method stub
		Search sr =new Search();
		System.out.println("Enter BookId:-");
		int bookId = sc.nextInt();
		sc.nextLine();
		Book book = sr.isBookAvailable(bookId);
		if( book == null) {
			System.out.println("Book not Available..!");
			return;
		}
		System.out.println("Enter UserId if not registered register First..!");
		int userId = sc.nextInt();
		sc.nextLine();
		User user = sr.isUserValid(userId);
		if(user == null) {
			System.out.println("userId not exist.>!");
			return;
		}
		Management mg = new Management();
		if(mg.issueBook(user,book)) {
			System.out.println("Success");
		}
		return;
	}

	private static void seeAllBooks() {
		// TODO Auto-generated method stub
		Search sr =new Search();
		sr.displayAllBooks();
		
	}

	private static void searchBook() {
		System.out.println("1.Search by title.\n"
				+ "2.search By Author..");
		int val = sc.nextInt();
		sc.nextLine();
		if(!(val>0 && val<3)) {
			return;
		}
		Search sr = new Search();
		if(val == 1) {
			System.out.print("Enter Title name write starting name correctly");
			String title = sc.nextLine();
			sr.searchByTitleOrAuthor(title, null);
		
		}
		if(val == 2) {
			System.out.print("Enter Author name write starting name correctly");
			String author = sc.nextLine();
			sr.searchByTitleOrAuthor(null, author);
		}
		return;
		
	}

}
