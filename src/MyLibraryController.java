/**
 * @author Logan Anderson (username: logananderson), Carson Heyman (username: cheyman)
 * 
 * Coordinates between the model and view of the library
 */

import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * The controller ensures encapsulation in many of the same ways the model does.
 * Because this class acts as a buffer between the view and model, it serves mostly to
 * make sure the correct methods in the model are called furthermore, the model doesn't 
 * allow outside classes to act on the model, further ensuring encapsulation.
 */

public class MyLibraryController {
	public MyLibraryController(MyLibraryModel model) {
		this.model = model;
	}

	private final MyLibraryModel model;

	/**
	 * @pre option is within [1 .. 3] option 1 searches by title option 2 searches
	 *      by author option 3 searches by rating
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
	 * @post adds a single book to the model's library
	 */
	public void addBook(String title, String author) {
		model.addNode(title, author);
	}

	/**
	 * @post sets LibraryNode with book of the given title's isRead to true
	 */
	public void setToRead(String title) {
		model.setToRead(title);
	}

	/**
	 * @pre rating must be [1 .. 5]
	 * @post sets LibraryNode with book of the given title's rating to the given rating
	 */
	public void rate(String title, int rating) {
		model.rate(title, rating);
	}

	/**
	 * @pre option may only be [1 .. 4]
	 * 
	 * @param option 1-model's library sorted by title 2-model's library sorted by
	 *               author 3-model's library's read nodes sorted by title 4-model's
	 *               library's unread nodes sorted by title
	 * 
	 * @return an arrayList of the LibraryNodes sorted accordingly
	 */
	public ArrayList<LibraryNode> getBooks(int option) {
		return model.getBooks(option);
	}

	/**
	 * @return a randomly selected unread node of model's library
	 */
	public LibraryNode suggestRead() {
		return model.suggestRead();
	}

	/**
	 * @post adds LibraryNodes to model's library read in from fileName
	 */
	public void addBooks(String fileName) throws FileNotFoundException {
		model.addBooks(fileName);
	}
}
