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

	private static String inputString;
	private static boolean inputEnteredFlag;
	private static MyLibraryController controller = new MyLibraryController(new MyLibraryModel());
	
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
				JTextField titleToSearch = new JTextField(50);
				titlePanel.add(titleToSearch, BorderLayout.SOUTH);
				
				JButton doTitleSearch = new JButton("Search By Title");
				doTitleSearch.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						outputPanel.removeAll();
						JLabel titleSearchOut = new JLabel();
						
						ArrayList<LibraryNode> matchingNodes = CONTROLLER.search(1, titleToSearch.getText().toLowerCase());
							
						if (matchingNodes.size() == 0) {
							titleSearchOut.setText("Book not found or input not provided.");
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
				JTextField authorToSearch = new JTextField(50);
				authorPanel.add(authorToSearch, BorderLayout.SOUTH);
				
				JButton doAuthorSearch = new JButton("Search By Author");
				doAuthorSearch.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						outputPanel.removeAll();
						JLabel authorSearchOut = new JLabel();
						
						ArrayList<LibraryNode> matchingNodes = CONTROLLER.search(2, authorToSearch.getText());
						
						if (matchingNodes.size() == 0) {
							authorSearchOut.setText("Book not found or input not provided.");
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
				JTextField ratingToSearch = new JTextField(50);
				ratingPanel.add(ratingToSearch, BorderLayout.SOUTH);
				
				JButton doRatingSearch = new JButton("Search By Rating");
				doRatingSearch.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e ) {
						outputPanel.removeAll();
						JLabel ratingSearchOut = new JLabel();
						try {
							ArrayList<LibraryNode> matchingNodes = CONTROLLER.search(3, ratingToSearch.getText());
							
							if (matchingNodes.size() == 0) {
								ratingSearchOut.setText("Book not found or input not provided.");
							} else {
								ratingSearchOut.setText(matchingNodes.toString());
							}
						} catch (Exception NumberFormatException) {
							ratingSearchOut.setText("Please provide numeric [1..5] input");
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
		 * opens new frame where user provides author and title
		 * hides input fields upon submission and provides output
		 */
		JButton addBookButton = new JButton("Add Book");
		addBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputPanel.removeAll();
				outputPanel.removeAll();
				
				JTextField titleInput = new JTextField(20);
				JTextField authorInput = new JTextField(20);
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
						// add book implementation !
					}
				});
				
				mainFrame.pack();
			}
		});
		buttonPanel.add(addBookButton);
		
		/*
		 * opens new frame where user provides input
		 * hides input fields upon submission and provides output
		 */
		JButton setToReadButton = new JButton("Set to Read");
		setToReadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// open set to read window
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
				// open rate window
			}
		});
		buttonPanel.add(rateButton);
		
		/*
		 * opens new frame where user provides input
		 * hides input fields upon submission and provides output
		 * refer to API above constructor
		 */
		JButton getBooksButton = new JButton("Get Books");
		getBooksButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// open getbooks window
			}
		});
		buttonPanel.add(getBooksButton);
		
		/*
		 * opens new frame with output (no need for input)
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
					LibraryNode suggestion = controller.suggestRead();
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
		 * opens new frame where user provides input
		 * hides input fields upon submission and provides output
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
				
				// When the user clicks this button, the text will be saved.
				JButton enterButton = new JButton("Enter");
				enterButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						inputString = inputField.getText();
						inputField.setText("");
						inputEnteredFlag = true;
						addBooksButton.doClick();
					}
				});

				inputLabel.setText("Enter the filename:");

				if (inputEnteredFlag) {
					try {
						controller.addBooks(inputString);
						outputLabel.setText("Books added successfully");
						outputLabel.setForeground(Color.GREEN);
					} catch (FileNotFoundException ex) {
						outputLabel.setText("File not found!");
						outputLabel.setForeground(Color.RED);
					}
					inputEnteredFlag = false;
				}

				inputPanel.add(inputLabel);
				inputPanel.add(inputField);
				inputPanel.add(enterButton);
				outputPanel.add(outputLabel);
				mainFrame.pack();
			}
		});
		buttonPanel.add(addBooksButton);
		
		/**
		 * @see below is an example of how to implement the opening window. 
		 * Note the use of DISPOSE_ON_CLOSE instead of EXIT_ON_CLOSE. the latter 
		 * will terminate the entire program
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
