/**
 * @author Logan Anderson (username: logananderson), Carson Heyman (username: cheyman)
 * 
 * Runs various tests for the entire MyLibrary project (besides UIs)
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

public class MyLibraryTest {

	/**
	 * Book.java Tests
	 */
	Book b = new Book("dogs", "cats");
	@Test
	public void testBookGetters() {
		assertEquals("dogs", b.getTitle());
		assertEquals("cats", b.getAuthor());
	}
	
	@Test
	public void testToStringBook() {
		assertEquals("dogs, by cats", b.toString());
	}
	
	/**
	 * LibraryNode.java Tests
	 */
	LibraryNode l1 = new LibraryNode("birds", "cats");
	LibraryNode l2 = new LibraryNode("apples", "zebras");
	LibraryNode l3 = new LibraryNode(l1);
	
	@Test
	public void testBookField() {
		assertEquals("birds", l1.getBook().getTitle());
		assertEquals("cats", l1.getBook().getAuthor());
	}
	
	@Test
	public void testRatingField() {
		assertEquals(0, l1.getRating());
		l1.setRating(5);
		assertEquals(5, l1.getRating());
	}
	
	@Test
	public void testIsReadField() {
		assertFalse(l1.getIsRead());
		l1.read();
		assertTrue(l1.getIsRead());
	}
	
	@Test
	public void testComparators() {
		LibraryNode.compareByTitle byTitle = new LibraryNode.compareByTitle();
		LibraryNode.compareByAuthor byAuthor = new LibraryNode.compareByAuthor();
		
		assertEquals(0, byTitle.compare(l1, l3));
		assertEquals(0, byAuthor.compare(l1, l3));
		
		assertTrue(byAuthor.compare(l2, l1) > 0);
		assertTrue(byTitle.compare(l2, l1) < 0);
	}
	
	@Test
	public void testToStringLibraryNode() {
		assertEquals("birds, by cats, Unread, Rating: unrated", l1.toString());
		l1.read();
		assertEquals("birds, by cats, Read, Rating: unrated", l1.toString());
		l1.setRating(5);
		assertEquals("birds, by cats, Read, Rating: 5/5", l1.toString());
	}
	
	/**
	 * MyLibraryConroller.java and MyLibraryModel.java Tests
	 */
	MyLibraryModel m = new MyLibraryModel();
	MyLibraryController c = new MyLibraryController(m);
	
	@Test
	public void testSearchOpt1() throws FileNotFoundException {
		c.addBooks("books.txt");
		ArrayList<LibraryNode> search = c.search(1, "the genius of birds");
		assertEquals(search.get(0).toString(), "the genius of birds, by jennifer ackerman, Unread, Rating: unrated");
	}
	
	@Test
	public void testSearchOpt2() {
		c.addBook("the genius of birds", "jennifer ackerman");
		ArrayList<LibraryNode> search = c.search(2, "jennifer ackerman");
		assertEquals(search.get(0).toString(), "the genius of birds, by jennifer ackerman, Unread, Rating: unrated");
	}
	
	@Test
	public void testSearchOpt3() {
		c.addBook("the genius of birds", "jennifer ackerman");
		c.rate("the genius of birds", 4);
		ArrayList<LibraryNode> search = c.search(3, "4");
		assertEquals(search.get(0).toString(), "the genius of birds, by jennifer ackerman, Unread, Rating: 4/5");
	}
	
	@Test
	public void testSetToRead() {
		c.addBook("test book", "test author");
		c.setToRead("test book");
		ArrayList<LibraryNode> search = c.search(1, "test book");
		assertEquals(search.get(0).toString(), "test book, by test author, Read, Rating: unrated");
	}
	
	@Test
	public void testGetBooksOpt1() {
		c.addBook("a book", "a test author");
		c.addBook("b book", "b test author");
		c.addBook("c book", "c test author");
		c.addBook("d book", "a test author");
		
		c.setToRead("b book");
		c.setToRead("d book");
		
		ArrayList<LibraryNode> books = c.getBooks(1);
		
		assertEquals(books.get(0).toString(), "a book, by a test author, Unread, Rating: unrated");
		assertEquals(books.get(1).toString(), "b book, by b test author, Read, Rating: unrated");
		assertEquals(books.get(2).toString(), "c book, by c test author, Unread, Rating: unrated");
		assertEquals(books.get(3).toString(), "d book, by a test author, Read, Rating: unrated");
	}
	
	@Test
	public void testGetBooksOpt2() {
		c.addBook("a book", "a test author");
		c.addBook("b book", "b test author");
		c.addBook("c book", "c test author");
		c.addBook("d book", "a test author");
		
		c.setToRead("b book");
		c.setToRead("d book");
		
		ArrayList<LibraryNode> books = c.getBooks(2);
		
		assertEquals(books.get(0).toString(), "a book, by a test author, Unread, Rating: unrated");
		assertEquals(books.get(1).toString(), "d book, by a test author, Read, Rating: unrated");
		assertEquals(books.get(2).toString(), "b book, by b test author, Read, Rating: unrated");
		assertEquals(books.get(3).toString(), "c book, by c test author, Unread, Rating: unrated");
	}
	
	@Test
	public void testGetBooksOpt3() {
		c.addBook("a book", "a test author");
		c.addBook("b book", "b test author");
		c.addBook("c book", "c test author");
		c.addBook("d book", "a test author");
		
		c.setToRead("b book");
		c.setToRead("d book");
		
		ArrayList<LibraryNode> books = c.getBooks(3);
		
		assertEquals(books.get(0).toString(), "b book, by b test author, Read, Rating: unrated");
		assertEquals(books.get(1).toString(), "d book, by a test author, Read, Rating: unrated");
	}
	
	@Test
	public void testGetBooksOpt4() {
		c.addBook("a book", "a test author");
		c.addBook("b book", "b test author");
		c.addBook("c book", "c test author");
		c.addBook("d book", "a test author");
		
		c.setToRead("b book");
		c.setToRead("d book");
		
		ArrayList<LibraryNode> books = c.getBooks(4);
		
		assertEquals(books.get(0).toString(), "a book, by a test author, Unread, Rating: unrated");
		assertEquals(books.get(1).toString(), "c book, by c test author, Unread, Rating: unrated");
	}
	
	@Test
	public void testSuggestRead() {
		c.addBook("b book", "b test author");
		c.addBook("c book", "c test author");
		c.addBook("d book", "a test author");
		
		c.setToRead("b book");
		c.setToRead("d book");
		
		LibraryNode suggestion;
		
		// There's a lot of the same assertion just because this includes randomness
		suggestion = c.suggestRead();
		assertEquals(suggestion.toString(), "c book, by c test author, Unread, Rating: unrated");
		suggestion = c.suggestRead();
		assertEquals(suggestion.toString(), "c book, by c test author, Unread, Rating: unrated");
		suggestion = c.suggestRead();
		assertEquals(suggestion.toString(), "c book, by c test author, Unread, Rating: unrated");
		suggestion = c.suggestRead();
		assertEquals(suggestion.toString(), "c book, by c test author, Unread, Rating: unrated");
	}
	
}
