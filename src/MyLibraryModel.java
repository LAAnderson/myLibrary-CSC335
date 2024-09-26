import java.util.ArrayList;

/**
 * @author Carson Heyman
 * 
 * contains the relevant information regarding a book, 
 * including itself, its rating, and if it's read
 */
public class MyLibraryModel {

  private ArrayList<LibraryNode> library;

  MyLibraryModel() {
    library = new ArrayList<LibraryNode>();
  }

  public ArrayList findByRating(int rating) {
    ArrayList<LibraryNode> found = new ArrayList<>();
    for (LibraryNode node : library) {
      if (node.getRating() == rating) {
        found.add(node);
      }
    }
    return found;
  }

  public ArrayList findByTitle(String title) {
    ArrayList<LibraryNode> found = new ArrayList<>();
    for (LibraryNode node : library) {
      if (node.getBook().getTitle().equals(title)) {
        found.add(node);
      }
    }
    return found;
  }

  public ArrayList findByAuthor(String author) {
    
  }

  public void addNode(String title, String author) {
    LibraryNode newBook = new LibraryNode(title, author);
    library.add(newBook);
  }

  public void setToRead(String title) {
    LibraryNode node = findByTitle(title);
    node.read();
  }

  public void rate(String title, int rating) {
    LibraryNode node = findByTitle(title);
    node.setRating(rating);
  }
  
  public ArrayList getBooks() {
    
  }

  public LibraryNode suggestRead() {
    
  }
  
  public void addBook(String title, String author) {
    
  }
}
