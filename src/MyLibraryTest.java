import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	 * MyLibraryModel.java Tests
	 */
	
	MyLibraryModel m = new MyLibraryModel();
	
	
	
	/**
	 * MyLibraryConroller.java Tests
	 */
}
