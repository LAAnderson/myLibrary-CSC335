import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * @authors Logan Anderson (username: logananderson), Carson Heyman (username: cheyman)
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

		
		
		while (!userInput.equals("exit")) {

			if (userInput.equals("search")) { 
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

					userInput = s.nextLine().toLowerCase();
					String search = userInput;
					int optionInt = Integer.parseInt(option);
					ArrayList<LibraryNode> found = controller.search(optionInt, search);
					if (found.size() == 0) {
						System.out.println("\u001B[31m" + "Book not found!" + "\u001B[0m");
					} else {
						printBookList(found);
					}
					break;
				}
			} else if (userInput.equals("addbook")) { 
				System.out.print("title: ");
				String t = s.nextLine().toLowerCase();
				System.out.print("author: ");
				String a = s.nextLine().toLowerCase();

				controller.addBook(t, a);
				System.out.println("\u001B[32m" + "Successfully added '" + t + "', by '" + a + "'" + "\u001B[0m");

			} else if (userInput.equals("settoread")) { 
				System.out.println("Enter the book title: ");
				String title = s.nextLine().toLowerCase();
				controller.setToRead(title);
				System.out.println("\u001B[32m" + "Successfully set '" + title + "' to read." + "\u001B[0m");

			} else if (userInput.equals("rate")) { 
				System.out.print("title: ");
				String t = s.nextLine().toLowerCase();
				System.out.print("rating [1 - 5]: ");
				String r = s.nextLine();

				try {
					if (Integer.parseInt(r) >= 1 && Integer.parseInt(r) <= 5) {
						controller.rate(t, Integer.parseInt(r));
						System.out.println("\u001B[32m" + "Rating added successfully" + "\u001B[0m");
					} else {
						System.out.println("\u001B[31m" + "Improper rating value." + "\u001B[0m");
					}
					
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("\u001B[31m" + "Book not in library." + "\u001B[0m");
				} catch (NumberFormatException e) {
					System.out.println("\u001B[31m" + "Improper rating value." + "\u001B[0m");
				}

			} else if (userInput.equals("getbooks")) { 
				while (!userInput.equals("exit")) {
					System.out.println(
							"Enter the number representing which set of books to retrieve [1 .. 4]\n1 - all books sorted by title\n2 - all books sorted by author\n3 - books that have been read\n4 - books that have not been read");
					userInput = s.nextLine();

					if (userInput.equals("1") || userInput.equals("2") || userInput.equals("3")
							|| userInput.equals("4")) {
						System.out.println("Books:");
					} else if (userInput.equals("exit")) {
						continue;
					} else {
						System.out.println("\u001B[31m" + "Invalid input!" + "\u001B[0m");
						continue;
					}

					ArrayList<LibraryNode> bookList = controller.getBooks(Integer.parseInt(userInput));
					printBookList(bookList);
					break;
				}
			} else if (userInput.equals("suggestread")) { 
				try {
					LibraryNode suggestion = controller.suggestRead();
					System.out.println(suggestion.getBook().getTitle() + ", by " + suggestion.getBook().getAuthor());
				} catch (ArithmeticException e) {
					System.out.println("\u001B[31m" + "Add books to your library first!" + "\u001B[0m");
				}
			} else if (userInput.equals("addbooks")) { 
				try {
					System.out.println("Enter the filename:");
					String filename = s.nextLine();
					controller.addBooks(filename);
					System.out.println("\u001B[32m" + "Books added successfully" + "\u001B[0m");
				} catch (FileNotFoundException e) {
					System.out.println("\u001B[31m" + "File not found!" + "\u001B[0m");
				}
			} else if (userInput.equals("help")) { 
				System.out.println("search: \tsearch for a book by title (0), author (1), or rating(2)\n"
						+ "addBook:\tadd a book to your library\n" + "setToRead:\tset a book in your library to read\n"
						+ "rate:\t\trate a book in your library\n"
						+ "getBooks:\tdisplay a list of books sorted by title (0), author (1), read books (2), or unread books (3)\n"
						+ "suggestRead:\tsuggest and unread book\n"
						+ "addBooks:\tadd multiple books from a file formatted as (title;author)\n"
						+ "exit:\t\tterminate library");
			} else {
				System.out.println("\u001B[31m" + "Invalid Command. Try 'help'" + "\u001B[0m");
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
