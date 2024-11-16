/**
 * @authors Logan Anderson, Carson Heyman
 * 
 * GUI for the MyLibraryProject (intended to work with the same implementation as the Text-Based UI)
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.FileNotFoundException;



public class MyLibraryGui {
	private MyLibraryController CONTROLLER = new MyLibraryController(new MyLibraryModel());
	/**
	 * @see https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
	 * @see https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
	 */
	

	public MyLibraryGui() {
		JFrame mainFrame = new JFrame();
		JPanel outputPanel = new JPanel();
	    JPanel inputPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonPanel.setLayout(new GridLayout(2,0));
		
		/*
		 *  By pressing on the search button, a cardstack(?) of panels is created and placed
		 *  in the inputPanel, each card accessible by selecting an option in the comboBox
		 *  the cards carry out the functionally of a search by title, author, and rating.
		 */
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputPanel.removeAll();
				outputPanel.removeAll();
				
				inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				CardLayout cardLayout = new CardLayout();
				
				JPanel cards = new JPanel();
				cards.setLayout(cardLayout);

				
				JPanel titlePanel = new JPanel();
				titlePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
				JTextField titleToSearch = new JTextField(25);
				titlePanel.add(titleToSearch, BorderLayout.SOUTH);
				
				JButton doTitleSearch = new JButton("Search By Title");
				doTitleSearch.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						outputPanel.removeAll();
						mainFrame.pack();
						JLabel titleSearchOut = new JLabel();
						
						ArrayList<LibraryNode> matchingNodes = CONTROLLER.search(1, titleToSearch.getText().toLowerCase());
							
						if (matchingNodes.size() == 0) {
							titleSearchOut.setText("Book not found or input not provided.");
							titleSearchOut.setForeground(Color.RED);
						} else {
							titleSearchOut.setText(matchingNodes.toString());
						}
						
						
						outputPanel.add(titleSearchOut);
						mainFrame.pack();
					}
					
				});
				titlePanel.add(doTitleSearch, BorderLayout.NORTH);
				
				
				JPanel authorPanel = new JPanel();
				authorPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
				JTextField authorToSearch = new JTextField(25);
				authorPanel.add(authorToSearch, BorderLayout.SOUTH);
				
				JButton doAuthorSearch = new JButton("Search By Author");
				doAuthorSearch.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						outputPanel.removeAll();
						mainFrame.pack();
						JLabel authorSearchOut = new JLabel();
						
						ArrayList<LibraryNode> matchingNodes = CONTROLLER.search(2, authorToSearch.getText().toLowerCase());
						
						if (matchingNodes.size() == 0) {
							authorSearchOut.setText("Book not found or input not provided.");
							authorSearchOut.setForeground(Color.RED);
						} else {
							authorSearchOut.setText(matchingNodes.toString());
						}
						
						
						outputPanel.add(authorSearchOut);
						mainFrame.pack();
					}
				});
				authorPanel.add(doAuthorSearch, BorderLayout.NORTH);
				
				
				JPanel ratingPanel = new JPanel();
				ratingPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
				JTextField ratingToSearch = new JTextField(25);
				ratingPanel.add(ratingToSearch, BorderLayout.SOUTH);
				
				JButton doRatingSearch = new JButton("Search By Rating");
				doRatingSearch.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e ) {
						outputPanel.removeAll();
						mainFrame.pack();;
						JLabel ratingSearchOut = new JLabel();
						try {
							ArrayList<LibraryNode> matchingNodes = CONTROLLER.search(3, ratingToSearch.getText().toLowerCase());
							
							if (matchingNodes.size() == 0) {
								ratingSearchOut.setText("Book not found.");
								ratingSearchOut.setForeground(Color.RED);
							} else {
								ratingSearchOut.setText(matchingNodes.toString());
							}
						} catch (Exception NumberFormatException) {
							ratingSearchOut.setText("Please provide numeric [1..5] input");
							ratingSearchOut.setForeground(Color.RED);
						}
						
						outputPanel.add(ratingSearchOut);
						mainFrame.pack();
					}
				});
				ratingPanel.add(doRatingSearch, BorderLayout.NORTH);
				
				
				
				JComboBox<String> options = new JComboBox<String>(new String[] {"Find By Title", "Find By Author", "Find By Rating"});
				options.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String choice = (String) options.getSelectedItem();

						outputPanel.removeAll();
						cardLayout.show(cards, choice);
						mainFrame.pack();
					}
				});
				
				cards.add(titlePanel, "Find By Title");
				cards.add(authorPanel, "Find By Author");
				cards.add(ratingPanel, "Find By Rating");
				
				inputPanel.add(options, BorderLayout.NORTH);
				inputPanel.add(cards, BorderLayout.SOUTH);
				mainFrame.pack();
				
			}
		});
		buttonPanel.add(searchButton);
		
		
		/*
		 * by pressing on the addBook button, two input text fields and a button are
		 * added to the inputPanel. when no input is detected in either field, the
		 * output panel is populated with a warning. when both fields are populated,
		 * the input fields are cleared and the book is added
		 */
		JButton addBookButton = new JButton("Add Book");
		addBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputPanel.removeAll();
				outputPanel.removeAll();
				
				JTextField titleInput = new JTextField(18);
				JTextField authorInput = new JTextField(18);
				JLabel title = new JLabel("Title:");
				JLabel author = new JLabel("Author:");
				JButton doAddBook = new JButton("Add Book");
				
				inputPanel.add(title, BorderLayout.NORTH);
				inputPanel.add(titleInput, BorderLayout.NORTH);
				inputPanel.add(author, BorderLayout.CENTER);
				inputPanel.add(authorInput, BorderLayout.CENTER);
				inputPanel.add(doAddBook, BorderLayout.SOUTH);
				
				doAddBook.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						outputPanel.removeAll();
						mainFrame.pack();
						
						String titleIn = titleInput.getText().toLowerCase();
						String authorIn = authorInput.getText().toLowerCase();
						JLabel outputLabel = new JLabel();
						
						if (!titleIn.equals("") && !authorIn.equals("")) { // if there are no empty inputs
							CONTROLLER.addBook(titleIn, authorIn);
							outputLabel.setText(titleIn + ", by " + authorIn + " added successfully.");
							outputLabel.setForeground(Color.GREEN);
							titleInput.setText("");
							authorInput.setText("");
						} else {
							outputLabel.setText("One or more missing inputs");
							outputLabel.setForeground(Color.RED);
							outputPanel.add(outputLabel);
						}
						
						outputPanel.add(outputLabel);
						mainFrame.pack();
					}
				});
				
				mainFrame.pack();
			}
		});
		buttonPanel.add(addBookButton);
		
		/*
		 * by pressing the setToRead button, the input panel is populated by a textfield
		 * and button. when the button is pressed, it will try to find a matching book. if 
		 * successful, it will report so. if not, it will report it failed. if no input is
		 * provided it will prompt the user for input
		 */
		JButton setToReadButton = new JButton("Set to Read");
		setToReadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputPanel.removeAll();
				outputPanel.removeAll();
				
				JLabel title = new JLabel("Title:");
				JTextField titleInput = new JTextField(40);
				JButton doSetToRead = new JButton("Set To Read");
				doSetToRead.addActionListener(new ActionListener () {
					@Override
					public void actionPerformed(ActionEvent e) {
						outputPanel.removeAll();
						mainFrame.pack();
						
						String userIn = titleInput.getText().toLowerCase();
						JLabel outputLabel = new JLabel();
						
						if(!userIn.equals("")) { // if there is input
							ArrayList<LibraryNode> matchingNodes = CONTROLLER.search(1, userIn);
							if (matchingNodes.size() != 0) { // if there is a found book
								CONTROLLER.setToRead(userIn);
								outputLabel.setText(matchingNodes.get(0).getBook().toString() + " was read");
								outputLabel.setForeground(Color.GREEN);
							} else {
								outputLabel.setText("Book not found");
								outputLabel.setForeground(Color.RED);
							}
						} else {
							outputLabel.setText("Please provide input");
							outputLabel.setForeground(Color.RED);
						}
						
						outputPanel.add(outputLabel);
						mainFrame.pack();
					}
				});
				
				inputPanel.add(title, BorderLayout.WEST);
				inputPanel.add(titleInput, BorderLayout.CENTER);
				inputPanel.add(doSetToRead, BorderLayout.EAST);
				
				mainFrame.pack();
			}
		});
		buttonPanel.add(setToReadButton);
		
		/*
		 * opens new frame where user provides input
		 * hides input fields upon submission and provides output
		 */
		JButton rateButton = new JButton("Rate");
		rateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputPanel.removeAll();
				outputPanel.removeAll();
				
				JLabel title = new JLabel("Title:");
				JTextField titleIn = new JTextField(40);
				JComboBox<Integer> ratingIn = new JComboBox<Integer>(new Integer[] {1, 2, 3, 4, 5});
				JButton doRate = new JButton("Rate");
				doRate.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JLabel outputLabel = new JLabel();
						String titleInput = titleIn.getText().toString();
						
						if (!titleInput.equals("")) { // if a title is provided
							ArrayList<LibraryNode> matchingNodes = CONTROLLER.search(1, titleInput);
							if (matchingNodes.size() != 0) { // if a matching node is found
								CONTROLLER.rate(titleInput, (Integer)ratingIn.getSelectedItem());
								outputLabel.setText(matchingNodes.get(0).getBook().toString() + " was rated");
								outputLabel.setForeground(Color.GREEN);
							} else {
								outputLabel.setText("Book not found");
								outputLabel.setForeground(Color.RED);
							}
							
							titleIn.setText("");
						} else {
							outputLabel.setText("Please provide input");
							outputLabel.setForeground(Color.RED);
						}
						
						outputPanel.add(outputLabel);
						mainFrame.pack();
					}
				});
				
				inputPanel.add(title, BorderLayout.WEST);
				inputPanel.add(titleIn, BorderLayout.CENTER);
				inputPanel.add(ratingIn, BorderLayout.CENTER);
				inputPanel.add(doRate, BorderLayout.EAST);
				
				mainFrame.pack();
			}
		});
		buttonPanel.add(rateButton);
		
		/*
		 * Creates a JComboBox where the user can provide
		 * input from a selection. When the new "Get Books"
		 * button beside the input is pressed, we feed the
		 * input to the model and display the output in an
		 * uneditable, scrollable text area.
		 */
		JButton getBooksButton = new JButton("Get Books");
		getBooksButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputPanel.removeAll();
				outputPanel.removeAll();

				inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				outputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 25, 20));

				String[] dropdownOptions = new String[] {"Title", "Author", "Read", "Unread"};
				JComboBox<String> dropdown = new JComboBox<String>(dropdownOptions);
				JLabel outputLabel = new JLabel();
				JTextArea outputText = new JTextArea(10, 70);

				outputLabel.setForeground(Color.RED);
				outputText.setEditable(false);

				inputPanel.add(new JLabel("Sort by:"));
				inputPanel.add(dropdown);

				JButton getBooksButton = new JButton("Get Books");
				getBooksButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						// Converts the array to a list and uses the indexOf method
						// to get the index of the element and then adds 1 to it.
						int userInput = Arrays.asList(dropdownOptions).indexOf((String) dropdown.getSelectedItem()) + 1;
						ArrayList<LibraryNode> bookList = CONTROLLER.getBooks(userInput);

						outputText.setText("");

						if (bookList.size() == 0) {
							outputLabel.setText("No books found!");
						} else {
							outputLabel.setText("");
							for (LibraryNode book : bookList) {
								outputText.append(book.toString() + '\n');
							}
						}
					}
				});
				inputPanel.add(getBooksButton);
				outputPanel.add(new JScrollPane(outputText));
				outputPanel.add(outputLabel);
				mainFrame.pack();
			}
		});
		buttonPanel.add(getBooksButton);
		
		/*
		 * Displays a JLabel with the output (no input).
		 */
		JButton suggestReadButton = new JButton("Suggest Read");
		suggestReadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputPanel.removeAll();
				outputPanel.removeAll();

				outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				JLabel output = new JLabel();

				try {
					LibraryNode suggestion = CONTROLLER.suggestRead();
					output.setText(suggestion.getBook().getTitle() + ", by " + suggestion.getBook().getAuthor());
				} catch (ArithmeticException exception) {
					output.setText("Add books to your library first!");
					output.setForeground(Color.RED);
				}

				outputPanel.add(output);
				mainFrame.pack();
			}
		});
		buttonPanel.add(suggestReadButton);
		
		/*
		 * Creates a text field for the user to enter the
		 * filename. Adds the books through the controller,
		 * and outputs whether it was successful or not.
		 */
		JButton addBooksButton = new JButton("Add Books");
		addBooksButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputPanel.removeAll();
				outputPanel.removeAll();

				inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

				JTextField inputField = new JTextField(20);
				JLabel inputLabel = new JLabel();
				JLabel outputLabel = new JLabel();
				
				JButton enterButton = new JButton("Enter");
				enterButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						try {
							CONTROLLER.addBooks(inputField.getText());
							outputLabel.setText("Books added successfully");
							outputLabel.setForeground(Color.GREEN);
						} catch (FileNotFoundException ex) {
							outputLabel.setText("File not found!");
							outputLabel.setForeground(Color.RED);
						}
					}
				});

				inputLabel.setText("Enter the filename:");

				inputPanel.add(inputLabel);
				inputPanel.add(inputField);
				inputPanel.add(enterButton);
				outputPanel.add(outputLabel);
				mainFrame.pack();
			}
		});
		buttonPanel.add(addBooksButton);
		
		/**
		 * Creates a text area in the output panel and
		 * adds the help messages.
		 */
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputPanel.removeAll();
				outputPanel.removeAll();
				
				JTextArea textArea = new JTextArea(10, 70);

				outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				textArea.append("Search: search for a book by title (0), author (1), or rating(2)\n");
				textArea.append("Add Book: add a book to your library\n");
				textArea.append("Set to Read: set a book in your library to read\n");
				textArea.append("Rate: rate a book in your library\n");
				textArea.append("Get Books: display a list of books sorted by title (0), author (1), read books (2), or unread books (3)\n");
				textArea.append("Suggest Read: suggest an unread book\n");
				textArea.append("Add Books: add multiple books from a file formatted as (title;author)\n");
				textArea.setEditable(false);
				
				outputPanel.add(new JScrollPane(textArea));

				mainFrame.pack();
			}
		});
		buttonPanel.add(helpButton);
	
       
		/*
		 * Standard closing functs
		 */
		mainFrame.add(buttonPanel, BorderLayout.NORTH);
		mainFrame.add(inputPanel, BorderLayout.CENTER);
        mainFrame.add(outputPanel, BorderLayout.SOUTH);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("My Library");
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new MyLibraryGui();
	}

	

}
