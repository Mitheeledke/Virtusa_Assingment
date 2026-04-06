package Entity;

public class Book {
	private int bookId;
	private String bookName;
	private String Author;
	private int quantity;
	private String description;
	
	public Book(){
		
	}
	
	public Book( String name, String auth, int quant, String des){
		this.bookName = name;
		this.Author = auth;
		this.quantity = quant;
		this.description = des;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", Author=" + Author + ", quantity=" + quantity
				+ ", description=" + description + "]";
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
