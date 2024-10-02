import java.util.Comparator;

/**
 * @author Logan Anderson
 * 
 *         contains the relevant information regarding a book, including itself,
 *         its rating, and if it's read
 */
public class LibraryNode {
	public LibraryNode(String title, String author) {
		this.book = new Book(title, author);
		this.rating = 0;
		this.isRead = false;
	}

	private Book book;
	private int rating;
	private boolean isRead;

	public Book getBook() {
		return this.book;
	}

	public int getRating() {
		return this.rating;
	}

	/**
	 * @pre r must be [1 .. 5]
	 */
	public void setRating(int r) {
		this.rating = r;
	}

	public boolean getIsRead() {
		return this.isRead;
	}

	public void read() {
		this.isRead = true;
	}

	static class compareByAuthor implements Comparator<LibraryNode> {
		@Override
		public int compare(LibraryNode l1, LibraryNode l2) {
			return l1.getBook().getAuthor().compareTo(l2.getBook().getAuthor());
		}
	}

	static class compareByTitle implements Comparator<LibraryNode> {
		@Override
		public int compare(LibraryNode l1, LibraryNode l2) {
			return l1.getBook().getTitle().compareTo(l2.getBook().getTitle());
		}
	}

	@Override
	public String toString() {
		return this.book.toString() + (this.isRead ? ", Read" : ", Unread") + ", Rating: "
				+ (this.rating == 0 ? "unrated" : this.rating + "/5");
	}
}
