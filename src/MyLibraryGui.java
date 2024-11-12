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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class MyLibraryGui {
	private JFrame helpFrame;
	/**
	 * @see https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
	 * @see https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
	 */
	
	public MyLibraryGui() {
		JFrame mainFrame = new JFrame();
		
        JLabel outputLabel = new JLabel("output");

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new GridLayout(2,0));
		
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
		panel.add(searchButton);
		
		
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
		panel.add(addBookButton);
		
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
		panel.add(setToReadButton);
		
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
		panel.add(rateButton);
		
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
		panel.add(getBooksButton);
		
		/*
		 * opens new frame with output (no need for input)
		 */
		JButton suggestReadButton = new JButton("Suggest Read");
		suggestReadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// open suggest read window
			}
		});
		panel.add(suggestReadButton);
		
		/*
		 * opens new frame where user provides input
		 * hides input fields upon submission and provides output
		 */
		JButton addBooksButton = new JButton("Add Books");
		addBooksButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// open add books window
			}
		});
		panel.add(addBooksButton);
		
		/**
		 * @see below is an example of how to implement the opening window. 
		 * Note the use of DISPOSE_ON_CLOSE instead of EXIT_ON_CLOSE. the latter 
		 * will terminate the entire program
		 */
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if (helpFrame == null || !helpFrame.isVisible()) {
				// 	helpFrame = new JFrame("Help");
				// 	JPanel helpPanel = new JPanel();
				// 	helpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				// 	helpPanel.setLayout(new CardLayout(10, 10));
				// 	helpPanel.add(new JLabel("SUPER SWAG MODE!"));
				// 	
				// 	helpFrame.add(helpPanel, BorderLayout.CENTER);
				// 	helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// 	helpFrame.setTitle("My Library");
				// 	helpFrame.pack();
				// 	helpFrame.setVisible(true);
				// }
                outputLabel.setText("SUPER SWAG MODE!");
			}
		});
		panel.add(helpButton);
		
        /*
         * output label
         */
        JPanel outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        outputPanel.add(outputLabel);

		/*
		 * Standard closing functs
		 */
		mainFrame.add(panel, BorderLayout.CENTER);
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
