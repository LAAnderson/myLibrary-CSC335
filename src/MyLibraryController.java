/**
 * @author Logan Anderson (username: logananderson), Carson Heyman (username: cheyman)
 * 
 * Coordinates between the MODEL and view of the library
 */

import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * The controller ensures encapsulation in many of the same ways the MODEL does.
 * Because this class acts as a buffer between the view and MODEL, it serves mostly to
 * make sure the correct methods in the MODEL are called furthermore, the MODEL doesn't 
 * allow outside classes to act on the MODEL, further ensuring encapsulation.
 */

public class MyLibraryController {
	public MyLibraryController(MyLibraryModel model) {
		this.MODEL = model;
	}

	private final MyLibraryModel MODEL;

	/**
	 * @pre option is within [1 .. 3] option 1 searches by title option 2 searches
	 *      by author option 3 searches by rating
	 * @return ArrayList of LibraryNodes matching the search parameters
	 */
	public ArrayList<LibraryNode> search(int option, String search) {
		switch (option) {
		case 1:
			return MODEL.findByTitle(search);
		case 2:
			return MODEL.findByAuthor(search);
		default:
			return MODEL.findByRating(Integer.parseInt(search));
		}
	}

	/**
	 * @post adds a single book to the MODEL's library
	 */
	public void addBook(String title, String author) {
		MODEL.addNode(title, author);
	}

	/**
	 * @post sets LibraryNode with book of the given title's isRead to true
	 */
	public void setToRead(String title) {
		MODEL.setToRead(title);
	}

	/**
	 * @pre rating must be [1 .. 5]
	 * @post sets LibraryNode with book of the given title's rating to the given rating
	 */
	public void rate(String title, int rating) {
		MODEL.rate(title, rating);
	}

	/**
	 * @pre option may only be [1 .. 4]
	 * 
	 * @param option 1-MODEL's library sorted by title 2-MODEL's library sorted by
	 *               author 3-MODEL's library's read nodes sorted by title 4-MODEL's
	 *               library's unread nodes sorted by title
	 * 
	 * @return an arrayList of the LibraryNodes sorted accordingly
	 */
	public ArrayList<LibraryNode> getBooks(int option) {
		return MODEL.getBooks(option);
	}

	/**
	 * @return a randomly selected unread node of MODEL's library
	 */
	public LibraryNode suggestRead() {
		return MODEL.suggestRead();
	}

	/**
	 * @post adds LibraryNodes to MODEL's library read in from fileName
	 */
	public void addBooks(String fileName) throws FileNotFoundException {
		MODEL.addBooks(fileName);
	}
}
