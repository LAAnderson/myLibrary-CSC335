/**
 * @authors Logan Anderson, Carson Heyman
 * 
 * GUI for the MyLibraryProject (intended to work with the same implementation as the Text-Based UI)
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.io.FileNotFoundException;



public class MyLibraryGui {

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
		 *  opens new frame where user chooses search method and provides inputs
		 *  hides input fields upon submission and provides output
		 *  refer to API above constructor
		 */
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
				//Open add book window
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
				
				inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				inputPanel.add(new JLabel("Inputs here!"));
				outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				outputPanel.add(new JLabel("Outputs here!"));
				
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
