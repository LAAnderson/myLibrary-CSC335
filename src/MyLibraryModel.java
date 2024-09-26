import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
  
  public ArrayList getBooks(int option) {
    ArrayList<LibraryNode> sorted = new ArrayList<>();
    switch (option) {
      case 1: // all books sorted by title
        Collections.sort(sorted, new LibraryNode.compareByTitle());
        break;
      case 2: // all books sorted by author
        Collections.sort(sorted, new LibraryNode.compareByAuthor());
        break;
      case 3: // all books that have been read
        Collections.sort(sorted, new LibraryNode.compareByTitle());
        break;
      case 4: // all books that have not been read
        Collections.sort(sorted, new LibraryNode.compareByTitle());
        break;

      default:
        break;
    }
    return sorted;
  }

  public LibraryNode suggestRead() {
    Random rand = new Random();
    // ArrayList unreadBooks = 
    return new LibraryNode("", "");
  }
  
  public void addBooks(String title, String author) {
    
  }
}
