import javax.swing.SwingUtilities;

/**
 * TTTClient() contains the main function to execute the Client program
 * 
 * @author Saharsh
 *
 */
public class TTTClient {
	/**
	 * main function to execute
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			/**
			 * run() overridden to create a new client and initiate the play
			 */
			public void run() {
				Client tac = new Client();
				Play play = new Play(tac);
				play.start();
			}
		});
	}
}
