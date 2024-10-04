/**
 * @authors Carson Heyman (username: cheyman), Logan Anderson (username: logananderson)
 * 
 *          Model for the library. Contains various searching and sorting
 *          methods.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Encapsulation is maintained by only allowing primitive or immutable types
 * to escape. We only accept ints or Strings as parameters. When we return a
 * sorted list of the library we make sure to clone. Other than that all the
 * other ArrayLists being returned are not the library instance variable, 
 * but are instead constructed by iterating through library's elements.
 */
public class MyLibraryModel {

	private ArrayList<LibraryNode> library;

	MyLibraryModel() {
		library = new ArrayList<LibraryNode>();
	}
	
	/**
	 * @param	rating target rating to check for when searching books
	 *
	 * @return an ArrayList that contains all the LibraryNodes that match the
	 *				 target rating
	 */
	public ArrayList<LibraryNode> findByRating(int rating) {
		ArrayList<LibraryNode> found = new ArrayList<>();
		for (LibraryNode node : library) {
			if (node.getRating() == rating) {
				found.add(node);
			}
		}
		return found;
	}

	/**
	 * @param title target title to check for when searching books
	 *
	 * @return an ArrayList that contains all the LibraryNodes that match the
	 *				 target title
	 */
	public ArrayList<LibraryNode> findByTitle(String title) {
		ArrayList<LibraryNode> found = new ArrayList<>();
		for (LibraryNode node : library) {
			if (node.getBook().getTitle().equals(title)) {
				found.add(node);
			}
		}
		return found;
	}

	/**
	 * @param author target author to check for when searching books
	 *
	 * @return an ArrayList that contains all the LibraryNodes that match the
	 *				 target author 
	 */
	public ArrayList<LibraryNode> findByAuthor(String author) {
		ArrayList<LibraryNode> found = new ArrayList<>();
		for (LibraryNode node : library) {
			if (node.getBook().getAuthor().equals(author)) {
				found.add(node);
			}
		}
		return found;
	}

	/**
	 * @param title the desired title for the new book
	 * @param author the desired author for the new book
	 *
	 * @post contructs a new LibraryNode and adds it to the library ArrayList
	 */
	public void addNode(String title, String author) {
		LibraryNode newBook = new LibraryNode(title, author);
		library.add(newBook);
	}

	/**
	 * @param title the title of the target book that is being set to read
	 *
	 * @post sets the book that matches the title to read
	 */
	public void setToRead(String title) {
		ArrayList<LibraryNode> books = findByTitle(title);
		if (books.size() >= 1) {
			LibraryNode node = books.get(0);
			node.read();
		}
	}

	/**
	 * @pre rating must be [1 .. 5]
	 *
	 * @param title the title of the target book that is being rated
	 * @param rating the rating to set the target book to
	 *
	 * @post finds the book with the matching title, and if it exists
	 *			 it rates it the desired rating
	 */
	public void rate(String title, int rating) {
		ArrayList<LibraryNode> books = findByTitle(title);
		if (books.size() >= 1) {
			LibraryNode node = books.get(0);
			node.setRating(rating);
		}
	}

	/**
	 * @pre option = [1 .. 4]
	 *
	 * @param option an integer that corresponds to the option that is
	 *							 selected in the user interface
	 * 
	 * @return an ArrayList that contains either: all the books sorted
	 *				 by title, all the books sorted by author, all the read
	 *				 books sorted by title, or all the unread books sorted
	 *				 by title, depending on the option selected
	 */
	public ArrayList<LibraryNode> getBooks(int option) {
		ArrayList<LibraryNode> sorted;
		if (option == 1) {
			sorted = (ArrayList<LibraryNode>) library.clone();
			Collections.sort(sorted, new LibraryNode.compareByTitle());
		} else if (option == 2) {
			sorted = (ArrayList<LibraryNode>) library.clone();
			Collections.sort(sorted, new LibraryNode.compareByAuthor());
		} else if (option == 3) {
			sorted = getReadBooks();
			Collections.sort(sorted, new LibraryNode.compareByTitle());
		} else {
			sorted = getUnreadBooks();
			Collections.sort(sorted, new LibraryNode.compareByTitle());
		}
		return sorted;
	}

	/**
	 * A private helper method for the getBooks method
	 *
	 * @return an ArrayList containing all the read books in the library
	 */
	private ArrayList<LibraryNode> getReadBooks() {
		ArrayList<LibraryNode> found = new ArrayList<>();
		for (LibraryNode node : library) {
			if (node.getIsRead()) {
				found.add(node);
			}
		}
		return found;
	}

	/**
	 * A private helper method for the getBooks method
	 *
	 * @return an ArrayList containing all the unread books in the library
	 */
	private ArrayList<LibraryNode> getUnreadBooks() {
		ArrayList<LibraryNode> found = new ArrayList<>();
		for (LibraryNode node : library) {
			if (!node.getIsRead()) {
				found.add(node);
			}
		}
		return found;
	}

	/**
	 * @return a LibraryNode that was randomly selected from the list of
	 *				 unread books in the library
	 */
	public LibraryNode suggestRead() {
		Random rand = new Random();
		ArrayList<LibraryNode> unreadBooks = getUnreadBooks();
		LibraryNode selected = unreadBooks.get(Math.abs(rand.nextInt()) % unreadBooks.size());
		return selected;
	}

	/**
	 * @param name of the file containing the books to be added creates and adds
	 *             LibraryNodes from the input file to the library
	 *
	 * @post Reads in a file and composes it's lines into two ArrayLists: one for
	 *			 the titles and one for the authors. It then adds each seperate book
	 *			 with its appropriate information to the library
	 */
	public void addBooks(String fileName) throws FileNotFoundException{
		Scanner s = new Scanner(new File(fileName));
		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> authors = new ArrayList<String>();

		while (s.hasNext()) {
			String currLine = s.nextLine().toLowerCase();
			titles.add(currLine.substring(0, currLine.indexOf(';')));
			authors.add(currLine.substring(currLine.indexOf(';') + 1));
		}

		for (int i = 0; i < titles.size(); i++)
			this.addNode(titles.get(i), authors.get(i));

		s.close();
	}
}
