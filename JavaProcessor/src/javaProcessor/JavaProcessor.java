package javaProcessor;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class JavaProcessor extends JFrame implements ActionListener {

	public static final int WIDTH = 700;
	public static final int HEIGHT = 600;
	public static final int XCOORD = 140;
	public static final int YCOORD = 75;
	public static final int HORIZONTAL_STRUT_SIZE_1 = 250;
	public static final int HORIZONTAL_STRUT_SIZE_2 = 15;
	public static final int VERTICAL_STRUT_SIZE_1 = 8;

	JButton okButton, exitButton, clearButton;
	JMenuBar menuBar;
	JMenu optionMenu;
	JMenuItem okMenuItem, exitMenuItem, clearMenuItem;
	JLabel inLabel, outLabel, inTextLabel, outTextLabel;
	JTextField inField, outField;
	JTextArea inTextArea, outTextArea;
	Container contentPane;
	Dimension buttonSize;
	BevelBorder buttonBorder;
	ImageIcon okIcon, cancelIcon, clearIcon, codeIcon;
	String fileIn, fileOut;
	Scanner fileScanner;

	public JavaProcessor() {

		setTitle("Java Processor");
		setSize(WIDTH, HEIGHT);
		setLocation(XCOORD, YCOORD);
		contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		buttonSize = new Dimension(50, 50);
		buttonBorder = new BevelBorder(BevelBorder.RAISED);

		Image codeImage = null;
		try {
			codeImage = ImageIO.read(getClass()
					.getResource("/images/code2.png")); // Set the windows icon
														// in the top left
														// corner.
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIconImage(codeImage);
		codeIcon = new ImageIcon(codeImage);

		// Add a Window Exit Listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		okMenuItem = new JMenuItem("OK");
		okMenuItem.setActionCommand("OK");
		okMenuItem.addActionListener(this);
		exitMenuItem = new JMenuItem("EXIT");
		exitMenuItem.setActionCommand("EXIT");
		exitMenuItem.addActionListener(this);
		clearMenuItem = new JMenuItem("CLEAR");
		clearMenuItem.setActionCommand("CLEAR");
		clearMenuItem.addActionListener(this);

		optionMenu = new JMenu("OPTIONS");
		optionMenu.add(okMenuItem);
		optionMenu.add(clearMenuItem);
		optionMenu.add(exitMenuItem);

		menuBar = new JMenuBar();
		menuBar.add(optionMenu);
		setJMenuBar(menuBar);

		inLabel = new JLabel("Please enter the input .java file name.");
		inLabel.setAlignmentX(CENTER_ALIGNMENT);
		outLabel = new JLabel("Please enter the output .java file name.");
		outLabel.setAlignmentX(CENTER_ALIGNMENT);
		inTextLabel = new JLabel("        Input Text");
		outTextLabel = new JLabel("Output Text");

		inField = new JTextField(45);
		outField = new JTextField(45);

		inTextArea = new JTextArea(20, 20);
		outTextArea = new JTextArea(20, 20);

		Image okImage = null;
		try {
			okImage = ImageIO.read(getClass().getResource("/images/ok3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		okIcon = new ImageIcon(okImage);

		Image cancelImage = null;
		try {
			cancelImage = ImageIO.read(getClass().getResource(
					"/images/cancel4.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cancelIcon = new ImageIcon(cancelImage);

		Image clearImage = null;
		try {
			clearImage = ImageIO.read(getClass().getResource(
					"/images/clear2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		clearIcon = new ImageIcon(clearImage);

		okButton = new JButton(okIcon);
		okButton.addActionListener(this);
		okButton.setPreferredSize(buttonSize);
		okButton.setBorder(buttonBorder);
		okButton.setToolTipText("OK");
		okButton.setActionCommand("OK");

		exitButton = new JButton(cancelIcon);
		exitButton.addActionListener(this);
		exitButton.setPreferredSize(buttonSize);
		exitButton.setBorder(buttonBorder);
		exitButton.setToolTipText("EXIT");
		exitButton.setActionCommand("EXIT");

		clearButton = new JButton(clearIcon);
		clearButton.addActionListener(this);
		clearButton.setPreferredSize(buttonSize);
		clearButton.setBorder(buttonBorder);
		clearButton.setToolTipText("CLEAR");
		clearButton.setActionCommand("CLEAR");

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(okButton);
		buttonPanel.add(clearButton);
		buttonPanel.add(exitButton);

		Component hStrut1 = Box.createHorizontalStrut(HORIZONTAL_STRUT_SIZE_1);
		Component hStrut2 = Box.createHorizontalStrut(HORIZONTAL_STRUT_SIZE_2);
		JPanel displayLabelPanel = new JPanel();
		displayLabelPanel.setLayout(new BoxLayout(displayLabelPanel,
				BoxLayout.X_AXIS));
		displayLabelPanel.add(inTextLabel);
		displayLabelPanel.add(hStrut1);
		displayLabelPanel.add(outTextLabel);
		JPanel displayTextPanel = new JPanel();
		displayTextPanel.setLayout(new BoxLayout(displayTextPanel,
				BoxLayout.X_AXIS));
		displayTextPanel.add(inTextArea);
		displayTextPanel.add(hStrut2);
		displayTextPanel.add(outTextArea);

		JPanel interfacePanel = new JPanel();
		Component vStrut1 = Box.createVerticalStrut(VERTICAL_STRUT_SIZE_1);
		interfacePanel
				.setLayout(new BoxLayout(interfacePanel, BoxLayout.Y_AXIS));
		interfacePanel.add(vStrut1);
		interfacePanel.add(inLabel);
		interfacePanel.add(vStrut1);
		interfacePanel.add(inField);
		interfacePanel.add(vStrut1);
		interfacePanel.add(outLabel);
		interfacePanel.add(vStrut1);
		interfacePanel.add(outField);
		interfacePanel.add(vStrut1);
		interfacePanel.add(buttonPanel);
		interfacePanel.add(vStrut1);
		interfacePanel.add(displayLabelPanel);
		interfacePanel.add(vStrut1);
		interfacePanel.add(displayTextPanel);

		contentPane.add(interfacePanel);

	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int result;
		switch (e.getActionCommand()) {
		case "OK":
			fileIn = inField.getText();
			fileOut = outField.getText();
			/*
			 * code for making sure input and output file names are in .java
			 * form here?
			 *
			 */ 
			
			
			/* followed by code for reading from the input file and displaying
			 * input
			 */ 
			try {
				fileScanner = new Scanner(new File(fileIn));
			} catch (FileNotFoundException e1) {
				System.out.println("Error opening the file " + fileIn);
				e1.printStackTrace();
				System.exit(1);
			}
			String currentLine;
			while(fileScanner.hasNextLine()){
				currentLine = fileScanner.nextLine();
				inTextArea.append(currentLine + "\n");
				
				 /* followed by processing, correcting saving and displaying output
				 * 
				 * 
				 */
				
			}//end of loop for reading from file
			JOptionPane.showMessageDialog(null, "Processing Complete!", "COMPLETE", 3, codeIcon);
			break;
		case "EXIT":
			result = JOptionPane.showConfirmDialog(null,
					"Would you like to exit the program?", "EXIT",
					JOptionPane.YES_NO_OPTION, 3, codeIcon);
			if(result == JOptionPane.YES_OPTION)
				System.exit(0);
			break;
		case "CLEAR":
			/*
			 * clear fields
			 */
			// ask for confirmation
			result = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to clear the fields?", "CLEAR",
					JOptionPane.YES_NO_OPTION, 3, codeIcon);
			if (result == JOptionPane.YES_OPTION) {
				inField.setText("");
				outField.setText("");
				inTextArea.setText("");
				outTextArea.setText("");
			}

			break;
		}

	}

}
