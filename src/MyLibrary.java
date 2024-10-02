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
		System.out.println("Enter a command: ");
		String userInput = s.nextLine().toLowerCase();

		/*
		 * All strings should be stored as lower case, user input will be cast to lower
		 * case
		 * 
		 * 
		 */
		while (!userInput.equals("exit")) {

			if (userInput.equals("search")) { // Carson
				while (!userInput.equals("exit")) {
					System.out.println(
							"Enter the number representing the search method [1 .. 3]\n1 - title\n2 - author\n3 - rating");
					userInput = s.nextLine();
					String option = userInput;

					if (option.equals("1")) {
						System.out.println("Enter the title: ");
					} else if (option.equals("2")) {
						System.out.println("Enter the author: ");
					} else if (option.equals("3")) {
						System.out.println("Enter the rating [1 .. 5]");
					} else if (option.equals("exit")) {
						continue;
					} else {
						System.out.println("\u001B[31m" + "Invalid input!" + "\u001B[0m");
						continue;
					}

					userInput = s.nextLine();
					String search = userInput;
					int optionInt = Integer.parseInt(option);
					ArrayList<LibraryNode> found = controller.search(optionInt, search);
					printBookList(found);
					break;
				}
			} else if (userInput.equals("addbook")) { // Logan
				System.out.print("title: ");
				String t = s.nextLine().toLowerCase();
				System.out.print("author: ");
				String a = s.nextLine().toLowerCase();

				controller.addBook(t, a);
				System.out.println("\u001B[32m" + "Successfully added '" + t + "', by '" + a + "'" + "\u001B[0m");

			} else if (userInput.equals("settoread")) { // Carson
				System.out.println("Enter the book title: ");
				String title = s.nextLine();
				controller.setToRead(title);
				System.out.println("\u001B[32m" + "Successfully set '" + title + "' to read." + "\u001B[0m");

			} else if (userInput.equals("rate")) { // Logan
				System.out.print("title: ");
				String t = s.nextLine().toLowerCase();
				System.out.print("rating [1 - 5]: ");
				int r = Integer.parseInt(s.nextLine());

				try {
					controller.rate(t, r);
					System.out.println("\u001B[32m" + "Rating added successfully" + "\u001B[0m");
				} catch (Exception ArrayIndexOutOfBoundsException) {
					System.out.println("\u001B[31m" + "Book not in library." + "\u001B[0m");
				}

			} else if (userInput.equals("getbooks")) { // Carson
				ArrayList<LibraryNode> books = controller.getBooks(0);
				for (LibraryNode l : books) {
					System.out.println(l.toString());
				}

			} else if (userInput.equals("suggestread")) { // Logan
				System.out.println(controller.suggestRead().getBook().getTitle() + ", by "
						+ controller.suggestRead().getBook().getAuthor());

			} else if (userInput.equals("addbooks")) { // Carson

			} else if (userInput.equals("help")) { // Logan
				System.out.println("search: \tsearch for a book by title (0), author (1), or rating(2)\n"
						+ "addBook:\tadd a book to your library\n" + "setToRead:\tset a book in your library to read\n"
						+ "rate:\t\trate a book in your library\n"
						+ "getBooks:\tdisplay a list of books sorted by title (0), author (1), read books (2), or unread books (3)\n"
						+ "suggestRead:\tsuggest and unread book\n"
						+ "addBooks:\tadd multiple books from a file formatted as (title;author)\n"
						+ "exit:\t\tterminate library");
			} else {
//<<<<<<< HEAD
//=======
				System.out.println("\u001B[31m" + "Invalid Command. Try 'help'" + "\u001B[0m");
//>>>>>>> 949a540cfb222e5cb576a89fdb2a029d3e5b783e
			}

			System.out.println("Enter a command: ");
			userInput = s.nextLine().toLowerCase();
		}

		s.close();
	}

	private static void printBookList(ArrayList<LibraryNode> bookList) {
		for (LibraryNode node : bookList) {
			System.out.println(node);
		}
	}

}
