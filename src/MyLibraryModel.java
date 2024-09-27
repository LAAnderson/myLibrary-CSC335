import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Carson Heyman
 * 
 * Model for the library. Contains various searching and sorting
 * methods.
 */
public class MyLibraryModel {

  private ArrayList<LibraryNode> library;

  MyLibraryModel() {
    library = new ArrayList<LibraryNode>();
  }

  public ArrayList<LibraryNode> findByRating(int rating) {
    ArrayList<LibraryNode> found = new ArrayList<>();
    for (LibraryNode node : library) {
      if (node.getRating() == rating) {
        found.add(node);
      }
    }
    return found;
  }

  public ArrayList<LibraryNode> findByTitle(String title) {
    ArrayList<LibraryNode> found = new ArrayList<>();
    for (LibraryNode node : library) {
      if (node.getBook().getTitle().equals(title)) {
        found.add(node);
      }
    }
    return found;
  }

  public ArrayList<LibraryNode> findByAuthor(String author) {
    ArrayList<LibraryNode> found = new ArrayList<>();
    for (LibraryNode node : library) {
      if (node.getBook().getAuthor().equals(author)) {
        found.add(node);
      }
    }
    return found;
  }

  public void addNode(String title, String author) {
    LibraryNode newBook = new LibraryNode(title, author);
    library.add(newBook);
  }

  public void setToRead(String title) {
    LibraryNode node = findByTitle(title).get(0);
    node.read();
  }

  public void rate(String title, int rating) {
    LibraryNode node = findByTitle(title).get(0);
    node.setRating(rating);
  }
  
  /**
   * @pre
   * option = [1 .. 4]
   */
  public ArrayList<LibraryNode> getBooks(int option) {
    ArrayList<LibraryNode> sorted;
    if (option == 1) {
      sorted = (ArrayList<LibraryNode>)library.clone();
      Collections.sort(sorted, new LibraryNode.compareByTitle());
    } else if (option == 2) {
      sorted = (ArrayList<LibraryNode>)library.clone();
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

  private ArrayList<LibraryNode> getReadBooks() {
    ArrayList<LibraryNode> found = new ArrayList<>();
    for (LibraryNode node : library) {
      if (node.getIsRead()) {
        found.add(node);
      }
    }
    return found;
  }

  private ArrayList<LibraryNode> getUnreadBooks() {
    ArrayList<LibraryNode> found = new ArrayList<>();
    for (LibraryNode node : library) {
      if (!node.getIsRead()) {
        found.add(node);
      }
    }
    return found;
  }

  public LibraryNode suggestRead() {
    Random rand = new Random();
    ArrayList<LibraryNode> unreadBooks = getUnreadBooks();
    LibraryNode selected = unreadBooks.get(rand.nextInt() % unreadBooks.size());
    return selected;
  }
  
  /**
   * @param name of the file containing the books to be added
   * creates and adds LibraryNodes from the input file to the library
   */
  public void addBooks(String fileName) {
	  Scanner s = new Scanner(fileName);
	  ArrayList<String> titles = new ArrayList<String>();
	  ArrayList<String> authors = new ArrayList<String>();
	  
	  while (s.hasNext()) {
		  String currLine = s.next();
		  titles.add(currLine.substring(0, currLine.indexOf(';')));
		  authors.add(currLine.substring(currLine.indexOf(';')+1));
	  }
	  
	  for (int i = 0; i < titles.size(); i++) 
		  this.addNode(titles.get(i), authors.get(i));
	  
	  s.close();
  }
}
