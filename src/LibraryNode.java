/**
 * @authors Logan Anderson (username: logananderson), Carson Heyman (username: cheyman)
 * 
 *         contains the relevant information regarding a book, including itself,
 *         its rating, and if it's read
 */

import java.util.Comparator;

/**
 * Encapsulation is maintained in this class thru the immutable nature of the 
 * Book class and by the primitive fields. Meaningful escaping references cannot 
 * come from this class, ensuring encapsulation. 
 */

public class LibraryNode {
	private final Book BOOK;
	private int rating;
	private boolean isRead;
	
	public LibraryNode(String title, String author) {
		this.BOOK = new Book(title, author);
		this.rating = 0;
		this.isRead = false;
	}
	
	public LibraryNode(LibraryNode node) {
		this.BOOK = node.BOOK;
		this.rating = node.rating;
		this.isRead = node.isRead;
	}


	/**
	 * @return the book associated with the instance of this class
	 */
	public Book getBook() {
		return this.BOOK;
	}

	/**
	 * @return the integer rating [1 .. 5] of the Book
	 */
	public int getRating() {
		return this.rating;
	}

	/**
	 * @pre r must be [1 .. 5]
	 */
	public void setRating(int r) {
		this.rating = r;
	}

	/**
	 * @return the boolean containing if the Book has been read
	 */
	public boolean getIsRead() {
		return this.isRead;
	}

	/**
	 * @post sets the boolean isRead to True
	 */
	public void read() {
		this.isRead = true;
	}

	/**
	 * private Comparator classes to allow collections of LibraryNodes to be sorted
	 */
	public static class compareByAuthor implements Comparator<LibraryNode> {
		@Override
		public int compare(LibraryNode l1, LibraryNode l2) {
			return l1.getBook().getAuthor().compareTo(l2.getBook().getAuthor());
		}
	}

	public static class compareByTitle implements Comparator<LibraryNode> {
		@Override
		public int compare(LibraryNode l1, LibraryNode l2) {
			return l1.getBook().getTitle().compareTo(l2.getBook().getTitle());
		}
	}

	/**
	 * @return a string representation of the LibraryNode instance
	 */
	@Override
	public String toString() {
		return this.BOOK.toString() + (this.isRead ? ", Read" : ", Unread") + ", Rating: "
				+ (this.rating == 0 ? "unrated" : this.rating + "/5");
	}
}
