import java.util.ArrayList;
import java.util.Scanner;

/**
 * @authors Carson Heyman, Logan Anderson
 * 
 *          Handles the user interactions for the MyLibrary project
 */

public class MyLibrary {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		MyLibraryController controller = new MyLibraryController(new MyLibraryModel());

		System.out.println("Welcome to Your Library");
		String userInput = s.nextLine().toLowerCase();

		/*
		 * All strings should be stored as lower case, user input will be cast to lower
		 * case
		 * 
		 * 
		 */
		while (!userInput.equals("exit")) {
			if (userInput.equals("search")) { // Carson

			} else if (userInput.equals("addbook")) { // Logan
				System.out.print("title: ");
				String t = s.nextLine().toLowerCase();
				System.out.print("author: ");
				String a = s.nextLine().toLowerCase();
				
				controller.addBook(t, a);
				System.out.println("book added");
			} else if (userInput.equals("settoread")) { // Carson

			} else if (userInput.equals("rate")) { // Logan
				System.out.print("title: ");
				String t = s.nextLine().toLowerCase();
				System.out.print("rating [1 - 5]: ");
				int r = Integer.parseInt(s.nextLine());
				
				try {
					controller.rate(t, r);
					System.out.println("rating added");
				} catch(Exception ArrayIndexOutOfBoundsException) {
					System.out.println("book not in library.");
				}

			} else if (userInput.equals("getbooks")) { // Carson
				ArrayList<LibraryNode> books = controller.getBooks(0);
				for(LibraryNode l : books) {
					System.out.println(l.toString());
				}

			} else if (userInput.equals("suggestread")) { // Logan
				System.out.println(controller.suggestRead().getBook().getTitle() + ", by " + controller.suggestRead().getBook().getAuthor());

			} else if (userInput.equals("addbooks")) { // Carson

			} else if (userInput.equals("help")) { // Logan
				System.out.println("search: search for a book by title (0), author (1), or rating(2)\n"
						+ "addBook: add a book to your library\n"
						+ "setToRead: set a book in your library to read\n"
						+ "rate: rate a book in your library\n"
						+ "getBooks: display a list of books sorted by title (0), author (1), read books (2), or unread books (3)\n"
						+ "suggestRead: suggest and unread book\n"
						+ "addBooks: add multiple books from a file formatted as (title;author)\n"
						+ "exit: terminate library");
			} else {
				System.out.println("Invalid Command. Try 'help'");
			}
			
			userInput = s.nextLine().toLowerCase();
		}

		s.close();
	}

}
