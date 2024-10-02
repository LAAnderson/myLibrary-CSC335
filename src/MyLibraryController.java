
/**
 * @author Logan Anderson
 * 
 * Coordinates between the model and view of the library
 */

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class MyLibraryController {
	public MyLibraryController(MyLibraryModel model) {
		this.model = model;
	}

	private final MyLibraryModel model;

	/**
	 * @pre option is within [0 .. 2] option 0 searches by title option 1 searches
	 *      by author option 2 searches by rating
	 * @return ArrayList of LibraryNodes matching the search parameters
	 */
	public ArrayList<LibraryNode> search(int option, String search) {
		switch (option) {
		case 1:
			return model.findByTitle(search);
		case 2:
			return model.findByAuthor(search);
		default:
			return model.findByRating(Integer.parseInt(search));
		}
	}

	/**
	 * adds a single book to the model's library
	 */
	public void addBook(String title, String author) {
		model.addNode(title, author);
	}

	/**
	 * sets LibraryNode with book of this title's isRead to true
	 */
	public void setToRead(String title) {
		model.setToRead(title);
	}

	/**
	 * @pre rating must be [1 .. 5]
	 */
	public void rate(String title, int rating) {
		model.rate(title, rating);
	}

	/**
	 * @pre option may only be [0 .. 3]
	 * 
	 * @param option 0-model's library sorted by title 1-model's library sorted by
	 *               author 2-model's library's read nodes sorted by title 3-model's
	 *               library's unread nodes sorted by title
	 * 
	 * @return an arrayList of the LibraryNodes sorted accordingly
	 */
	public ArrayList<LibraryNode> getBooks(int option) {
		return model.getBooks(option);
	}

	/**
	 * @return an unread node of model's library
	 */
	public LibraryNode suggestRead() {
		return model.suggestRead();
	}

	/**
	 * adds LibraryNodes to model's library read in from fileName
	 */
	public void addBooks(String fileName) throws FileNotFoundException {
		model.addBooks(fileName);
	}
}
