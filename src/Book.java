/**
 * @author Logan Anderson
 * 
 *         represents a book
 */
public final class Book {
	public Book(String t, String a) {
		title = t;
		author = a;
	}

	private String title;
	private String author;

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	@Override
	public String toString() {
		return this.title + ", by " + this.author;
	}
}
