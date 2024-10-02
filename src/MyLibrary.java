import java.util.Scanner;
import java.util.ArrayList;

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
					System.out.println("Enter the number representing the search method [1 .. 3]\n1 - title\n2 - author\n3 - rating");
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

			} else if (userInput.equals("settoread")) { // Carson
				System.out.println("Enter the book title: ");
				String title = s.nextLine();
				controller.setToRead(title);
				System.out.println("\u001B[32m"+ "Successfully set '" + title + "' to read." + "\u001B[0m");
			} else if (userInput.equals("rate")) { // Logan

			} else if (userInput.equals("getbooks")) { // Carson

			} else if (userInput.equals("suggestread")) { // Logan

			} else if (userInput.equals("addbooks")) { // Carson

			} else if (userInput.equals("help")) { // Logan

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
