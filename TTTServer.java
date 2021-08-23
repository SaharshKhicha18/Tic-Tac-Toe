import java.net.ServerSocket;

/**
 * TTTServer to execute the server
 * 
 * @author Saharsh
 *
 */
public class TTTServer {
	/**
	 * main function that will initiate and run the server
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println("Tic Tac Toe Server is running...");
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			/**
			 * run() overridden to stop the server.
			 */
			public void run() {
				System.out.println("Tic Tac Toe Server stopped.");
			}
		}));
		
		try (var listener = new ServerSocket(11223)) {
			Server myServer = new Server(listener);
			myServer.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
