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
		String userInput = "help";

		/*
		 * All strings should be stored as lower case, user input will be cast to lower
		 * case
		 * 
		 * 
		 */
		while (!userInput.equals("exit")) {
			userInput = s.nextLine().toLowerCase();

			if (userInput.equals("search")) { // Carson

			} else if (userInput.equals("addbook")) { // Logan

			} else if (userInput.equals("settoread")) { // Carson

			} else if (userInput.equals("rate")) { // Logan

			} else if (userInput.equals("getbooks")) { // Carson

			} else if (userInput.equals("suggestread")) { // Logan

			} else if (userInput.equals("addbooks")) { // Carson

			} else if (userInput.equals("help")) { // Logan

			} else {
				System.out.println("Invalid Command. Try 'help");
			}
		}

		s.close();
	}

}
