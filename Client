import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The Client class contains the GUI for the game and the working methods for example clicks.
 * 
 * @author Saharsh
 *
 */
public class Client {
	
	JLabel label_name;
	JFrame frame;
	JTextField txt_inp;

	JButton btn_submit, but00, but01, but02, but10, but11, but12, but20, but21, but22;
	JPanel InfoPanel, ButPanel, NamePanel;
	int mov;
	int win;
	int pNo;
	String pmv,n;
	
	JMenuBar menuBar = new JMenuBar();
	JMenu menuC = new JMenu("Control");
	JMenu menuH = new JMenu("Help");
	JMenuItem menuItemC = new JMenuItem("Exit");
	JMenuItem menuItemH = new JMenuItem("Instructions");
	
	/**
	 * Client() constructor in which the GUI variables and methods are set and implemented
	 */
	public Client() {
		frame = new JFrame("Tic Tac Toe");
		
		menuC.add(menuItemC);
		menuH.add(menuItemH);
		menuBar.add(menuC);
		menuBar.add(menuH);
		menuBar.setBackground(Color.orange);
		
		/**
		 * ActionListener for the exit on the menubar
		 */
		menuItemC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		/**
		 * ActionListener for accessing the instructions of the game.
		 */
		menuItemH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Message = "Some information about the game:\n"
						+ "Criteria for a valid move:\n"
						+ "-The move is not occupied by any mark.\n"
						+ "-The move is made in the player's turn.\n"
						+ "-The move is made within the 3 x 3 board.\n"
						+ "The game would continue to switch among the opposite player until it reaches either one of the following conditions:\n"
						+ "-Player 1 wins.\n"
						+ "-Player 2 wins.\n"
						+ "-Draw.\n";
				JOptionPane.showMessageDialog(new Frame(), Message, "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
	
		label_name = new JLabel("Enter your player name.");
		label_name.setForeground(Color.white);
		InfoPanel = new JPanel();
		InfoPanel.setBackground(Color.blue);
		InfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		InfoPanel.setSize(400,50);
		InfoPanel.add(label_name);
	
		ButPanel = new JPanel();
		ButPanel.setLayout(new GridLayout(3,3));
		ButPanel.setBackground(Color.blue);
		but00 = new JButton();
		but00.setEnabled(false);
		but00.setBackground(Color.black);
		but00.setSize(133,133);
		ButPanel.add(but00);
		but01 = new JButton();
		but01.setEnabled(false);
		but01.setBackground(Color.black);
		ButPanel.add(but01);
		but02 = new JButton();
		but02.setEnabled(false);
		but02.setBackground(Color.black);
		ButPanel.add(but02);
		but10 = new JButton();
		but10.setEnabled(false);
		but10.setBackground(Color.black);
		ButPanel.add(but10);
		but11 = new JButton();
		but11.setEnabled(false);
		but11.setBackground(Color.black);
		ButPanel.add(but11);
		but12 = new JButton();
		but12.setEnabled(false);
		but12.setBackground(Color.black);
		ButPanel.add(but12);
		but20 = new JButton();
		but20.setEnabled(false);
		but20.setBackground(Color.black);
		ButPanel.add(but20);
		but21 = new JButton();
		but21.setEnabled(false);
		but21.setBackground(Color.black);
		ButPanel.add(but21);
		but22 = new JButton();
		but22.setEnabled(false);
		but22.setBackground(Color.black);
		ButPanel.add(but22);
		ButPanel.setSize(400,400);
		
		NamePanel = new JPanel();
		NamePanel.setBackground(Color.blue);
		NamePanel.setSize(400,50);
		txt_inp = new JTextField(20);
		btn_submit = new JButton("Submit");
		btn_submit.setBackground(Color.orange);
		NamePanel.add(txt_inp);
		NamePanel.add(btn_submit);
		
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 500);
		frame.add(InfoPanel, BorderLayout.NORTH);
		frame.add(ButPanel, BorderLayout.CENTER);
		frame.add(NamePanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		
		pmv = "";
		mov = 1;
		win = 0;
	}
	
	/**
	 * clicks() method to display X or O depending on which plays clicks on a particular button.
	 * 
	 * @param c
	 * @param b
	 */
	void clicks(char c, JButton b) {
		if(c=='1') {
			b.setFont(new Font("Times New Roman",Font.BOLD, 40));
			b.setText("X");
			b.setForeground(Color.green);
			b.setEnabled(true);
		} 
		
		else if(c=='2') {
			b.setFont(new Font("Times New Roman", Font.BOLD, 40));
			b.setText("O");
			b.setForeground(Color.red);
			b.setEnabled(true);
		}
	}
	
	/**
	 * cross() method to show the board on which the game will be played
	 * 
	 * @param val
	 */
	void cross(String val) {
		String ab = String.valueOf(val.charAt(9));
		String bc = String.valueOf(val.charAt(10));
		mov = Integer.parseInt(ab);
		win = Integer.parseInt(bc);
		
		clicks(val.charAt(0),but00);
		clicks(val.charAt(1),but01);
		clicks(val.charAt(2),but02);
		clicks(val.charAt(3),but10);
		clicks(val.charAt(4),but11);
		clicks(val.charAt(5),but12);
		clicks(val.charAt(6),but20);
		clicks(val.charAt(7),but21);
		clicks(val.charAt(8),but22);
		
	}
}

