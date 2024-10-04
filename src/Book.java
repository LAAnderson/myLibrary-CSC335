/**
 * @authors Logan Anderson (username: logananderson), Carson Heyman (username: cheyman)
 * 
 *         An immutable book class containing information about its title and author
 */

/**
 * Encapsulation is maintained in this class by the class' immutability. By implementing fields
 * such as rating and isRead in LibraryNode instead of in Book, the entire Book class maintains
 * its immutability.
 */
public final class Book {
	public Book(String t, String a) {
		title = t;
		author = a;
	}

	private String title;
	private String author;

	/**
	 * @return a string containing the title of the book
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @return a string containing the author of the book
	 */
	public String getAuthor() {
		return this.author;
	}
	
	@Override
	public String toString() {
		return this.title + ", by " + this.author;
	}
}
