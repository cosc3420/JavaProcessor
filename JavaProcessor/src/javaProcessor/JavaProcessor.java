/*Jason Holt and Melody Snitker, Group 8
COSC 3420.501
Project 4
Due 4/8/2015
Corrects minor errors in java code.
*/

package javaProcessor;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

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
	String currentLine;
	StringBuilder addSpaces;

	// stores the number of brackets
	int openbrack = 0;
	int closebrack = 0; 
	
	// stores the current indent level during printing
	int indent = 0; 

	// holds the currently read character;
	char readout; 

	// Stores the currently in construction input of the next element of the
	// list
	String input = new String("");

	// Stores the code to be formatted and saved to a new file
	ArrayList<String> code = new ArrayList<String>();

	// The output stream
	PrintWriter out = null; 

	public JavaProcessor() {

		setTitle("Java Processor");
		setSize(WIDTH, HEIGHT);
		setLocation(XCOORD, YCOORD);
		
		contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		buttonSize = new Dimension(50, 50);
		buttonBorder = new BevelBorder(BevelBorder.RAISED);

		// Set the windows icon in the top left corner.
		Image codeImage = null;
		try {
			codeImage = ImageIO.read(getClass()
					.getResource("/images/code2.png"));

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
		
		int result;
		
		switch (e.getActionCommand()) {
		
		case "OK":
			
			fileIn = inField.getText();
			fileOut = outField.getText();

			try {
				fileScanner = new Scanner(new File(fileIn));
			} catch (FileNotFoundException e1) {
				System.out.println("Error opening the file " + fileIn);
				e1.printStackTrace();
				System.exit(1);
			}

			while (fileScanner.hasNextLine()) {
				currentLine = fileScanner.nextLine();
				inTextArea.append(currentLine + "\n");

				for (int i = 0; i < currentLine.length(); i++) {
					
					readout = currentLine.charAt(i);
					
					switch (readout) {
					
					case '{':
						
						indentInput();
						
						// add the string to the list
						code.add(input);
						outTextArea.append(input + "\n");
						
						indentBracket();
						
						// add the bracket string to the list
						code.add(input);
						outTextArea.append(input + "\n");
						indent++;// add a level of indentation
						openbrack++; // Increments open bracket counter
						input="";
						break;
						
					case '}':
						
						indentInput();
						
						// add the string to the list
						if(input!="")
							code.add(input);
						if(input!="")
							outTextArea.append(input + "\n");
						
						//decrement indents
						indent--;
						
						indentBracket();
						
						// add the string to the list
						code.add(input);
						outTextArea.append(input + "\n");
						closebrack++; // Increments open bracket counter
						input="";
						break;
						
					case ';':
						
						// add the character to the input string
						input = input + readout; 
						
						indentInput();
						
						// add the string to the list
						code.add(input);
						outTextArea.append(input + "\n");
						input = "";
						//trying to catch extra spaces after semicolons
						if(i < currentLine.length() - 2 && currentLine.charAt(i+1) == ' ')
							i++;
						
						break;
						
					case '.':
						
						// add the character to the input string
						input = input + readout; 
						
						// if this line is a comment
						// add the string to the list
						if (input.contains("//")) {
							code.add(input);
							outTextArea.append(input + "\n");
							
							// Empties the input string with indentation
							for (int j = 0; j < (4 * indent); j++)
								addSpaces.append(" ");
							if (addSpaces != null)
								input = addSpaces.toString();									// input with
							else
								input = ""; 
						}
						break;
						
					default: 
						
						// if it is none of the above characters, OR
						// the end of the file...
						// Add the character to the input string
						input = input + readout;	

					}// end switch

				}// end for currentLine
				
				//if we're missing a semicolon
				if (input.length() > 0) {
					if (input.charAt(input.length() - 1) != ';' && !input.contains("public static void")) {
						input = input + ";";
						
						indentInput();
						
						code.add(input);
						outTextArea.append(input + "\n");
						input = "";

					}
				}
			}// end of while filescanner hasNextLine

			// If we have too many open brackets
			while (openbrack > closebrack) 
			{
				// Append a closed bracket to the end
				code.add("}"); 
				// Increment the counter
				closebrack++; 
			}

			fileScanner.close();
			
			try {
				out = new PrintWriter(fileOut);
			} catch (FileNotFoundException e1) {
				System.out.println("Error writing to file " + fileOut);
				e1.printStackTrace();
				System.exit(1);
			}
			
			for (int i = 0; i < code.size(); i++) {
				out.println(code.get(i));
			}// end of writing to file
			out.close();

			JOptionPane.showMessageDialog(null, "Processing Complete!",
					"COMPLETE", 3, codeIcon);
			break;//end ok case
			
		case "EXIT":
			
			result = JOptionPane.showConfirmDialog(null,
					"Would you like to exit the program?", "EXIT",
					JOptionPane.YES_NO_OPTION, 3, codeIcon);
			
			if (result == JOptionPane.YES_OPTION)
				System.exit(0);
			break;
			
		case "CLEAR":
			
			//clear fields
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
	
	private void indentInput(){
		//add indentation to the input string
		addSpaces = new StringBuilder();
		for (int j = 0; j < (4 * indent); j++)
			addSpaces.append(" ");
		if (addSpaces != null && input != "" )
			input = addSpaces.toString()+input; 
	}//end indentInput method
	
	private void indentBracket(){
		// replace the input string with the bracket
		addSpaces = new StringBuilder();
		for (int j = 0; j < (4 * indent); j++)
			addSpaces.append(" ");
		if (addSpaces != null)
			input = addSpaces.toString() + readout;
		else
			input = Character.toString(readout);
	}//end indentBracket method

}
