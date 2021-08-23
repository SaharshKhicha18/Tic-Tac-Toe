import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Play class for executing gameplay and interacting with the server
 * 
 * @author Saharsh
 *
 */
public class Play {
		private Socket socket;
		private Scanner in;
		private PrintWriter out;
		private Client tac;
		
		/**
		 * Play constructor to update the client 
		 * 
		 * @param toe
		 */
		public Play(Client toe) {
			tac = toe;
		}
		
		/**
		 * Start() to connect to the server and get input and output
		 */
		public void start() {
			try {
				this.socket = new Socket("127.0.0.1", 11223);
				this.in = new Scanner(socket.getInputStream());
				this.out = new PrintWriter(socket.getOutputStream(), true);
			} catch(UnknownHostException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
			Thread handler = new ClientHandler(socket);
			handler.start();
		}
		
		/**
		 * ClientHandler for openening another thread and multi-threading 
		 * 
		 * @author Saharsh
		 *
		 */
		public class ClientHandler extends Thread {
			private Socket socket;
			
			/**
			 * ClientHandler constructor
			 * 
			 * @param socket
			 */
			public ClientHandler(Socket socket) {
				this.socket = socket;
			}
			
			/**
			 * Run() method which is overridden to read and write from the server 
			 */
			public void run() {
				try {
					tac.pNo = Integer.parseInt(in.nextLine());
					writeToServer();
					readFromServer();
				} 
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		/**
		 * WritetoServer which first updates the name that is inputted to the game and then the commands to the server
		 * 
		 * @throws Exception
		 */
		public void writeToServer() throws Exception {
			try {
				tac.btn_submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!tac.txt_inp.getText().contentEquals("")) {
							tac.n = tac.txt_inp.getText();
							tac.txt_inp.setEnabled(false);
							tac.btn_submit.setEnabled(false);
							
							tac.label_name.setText("WELCOME "+tac.n);
							tac.frame.setTitle("Tic Tac Toe-Player: "+tac.n);
							tac.pmv = tac.n;
							out.println(tac.pmv);
							tac.pmv = "";
							
						}
					}
				});
				cmdToServer();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * cmdToServer to write the commands to the server when a button is clicked
		 * 
		 * @throws Exception
		 */
		public void cmdToServer() throws Exception {
			try {
				tac.but00.addActionListener(new alist());
				tac.but01.addActionListener(new alist());
				tac.but02.addActionListener(new alist());
				tac.but10.addActionListener(new alist());
				tac.but11.addActionListener(new alist());
				tac.but12.addActionListener(new alist());
				tac.but20.addActionListener(new alist());
				tac.but21.addActionListener(new alist());
				tac.but22.addActionListener(new alist());
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * alist is a listener that implements ActionListener to execute the action when a button is clicked
		 * 
		 * @author Saharsh
		 *
		 */
		public class alist implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				tac.pmv = "";
				JButton btn = (JButton) e.getSource();
				if(tac.mov == tac.pNo) {
					tac.pmv += Integer.toString(tac.pNo);
					if(btn==tac.but00) {
						tac.pmv += "00";
					} else if(btn==tac.but01) {
						tac.pmv += "01";
					} else if(btn==tac.but02) {
						tac.pmv += "02";
					} else if(btn==tac.but10) {
						tac.pmv += "10";
					} else if(btn==tac.but11) {
						tac.pmv += "11";
					} else if(btn==tac.but12) {
						tac.pmv += "12";
					} else if(btn==tac.but20) {
						tac.pmv += "20";
					} else if(btn==tac.but21) {
						tac.pmv += "21";
					} else if(btn==tac.but22) {
						tac.pmv += "22";
					}
					out.println(tac.pmv);
				}
			}
		}
		
		/**
		 * reafFromServer to read the commands from other player and compare the outcomes
		 * 
		 * @throws Exception
		 */
		public void readFromServer() throws Exception {
			try {
				while(!in.hasNextLine()) {}
				while(in.hasNextLine()) {
					String cmd  = in.nextLine();
					if(cmd.contentEquals("2 players.")) {
						tac.but00.setEnabled(true);
						tac.but01.setEnabled(true);
						tac.but02.setEnabled(true);
						tac.but10.setEnabled(true);
						tac.but11.setEnabled(true);
						tac.but12.setEnabled(true);
						tac.but20.setEnabled(true);
						tac.but21.setEnabled(true);
						tac.but22.setEnabled(true);
					}
					else if(cmd.contentEquals("Game Ends. One of the players left.")) {
						JOptionPane.showMessageDialog(tac.frame, cmd, "Message", JOptionPane.INFORMATION_MESSAGE);
						socket.close();
						System.exit(0);
					} else {
						tac.cross(cmd);
						if(tac.pNo==tac.mov) {
							tac.label_name.setText("Your opponent has moved, now is your turn.");
							cmdToServer();
						} else {
							tac.label_name.setText("Valid Move, wait for your opponent");
						}
						if(tac.win!=0) {
							if(tac.win==tac.pNo) {
								JOptionPane.showMessageDialog(tac.frame, "Congratulations. You win.", "Message", JOptionPane.INFORMATION_MESSAGE);
							} else if(tac.win==3) {
								JOptionPane.showMessageDialog(tac.frame, "Draw.", "Message", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(tac.frame, "You lose.", "Message", JOptionPane.INFORMATION_MESSAGE);
							}
							System.exit(0);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			finally {
				socket.close();
			}
		}
	}