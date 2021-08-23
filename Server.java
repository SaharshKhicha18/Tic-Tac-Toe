import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executors;

/**
 * Server class to make a server for the clients to join
 * 
 * @author Saharsh
 *
 */
public class Server {
	private Set<PrintWriter> players = new HashSet<>();
	String res;
	int move, wins, nplay, mvc;
	int cross[][];
	private ServerSocket serverSocket;
	
	/**
	 * Server constructor to initiate the serverSocket variables 
	 * 
	 * @param serverSocket
	 * @throws IOException
	 */
	Server(ServerSocket serverSocket) throws IOException{
		move= 1;
		wins = 0;
		nplay = 0;
		mvc = 0;
		cross = new int[3][3];
		for(int i=0; i<3; i++) {
			for(int j=0;j<3; j++) {
				cross[i][j] = 0;
			}
		}
		this.serverSocket = serverSocket;
		res = "";
	}
	
	/**
	 * start() to accept the clients and get the output
	 */
	public void start() {
		var pool = Executors.newFixedThreadPool(200);
		int clientCount = 0;
		while (true) {
			try {
				Socket socket1 = serverSocket.accept();
				System.out.println("Connected to client " + ++clientCount);
				PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
				out1.println("1");
				
				Socket socket2 = serverSocket.accept();
				System.out.println("Connected to client " + ++clientCount);
				PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
				out2.println("2");
				
				Scanner in1 = new Scanner(socket1.getInputStream());
				Scanner in2 = new Scanner(socket2.getInputStream());
				
				while(!in1.hasNextLine()) {}
				nplay++;
				String temp = in1.nextLine();
				while(!in2.hasNextLine()) {}
				nplay++;
				temp = in2.nextLine();
				
				players.add(out1);
				players.add(out2);
				
				out1.println("2 players.");
				pool.execute(new Handler(socket1, in1, out1));
				out2.println("2 players.");
				pool.execute(new Handler(socket2, in2, out2));				
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * moves() takes string in the format player, row, column and then checks if a move is valid. It also sends it to clients.
	 * 
	 * @param str
	 */
	public void moves(String str) {
		if(nplay==2) {
			String aa = String.valueOf(str.charAt(0));
			String bb = String.valueOf(str.charAt(1));
			String cc = String.valueOf(str.charAt(2));
			int wyp = Integer.parseInt(aa);
			int rw = Integer.parseInt(bb);
			int cl = Integer.parseInt(cc);
			if(wyp == move&& cross[rw][cl]==0) {
				cross[rw][cl] = wyp;
				mvc++;
				switchPlay();
			}
			String st = "";
			for(int i=0; i<=2;i++) {
				for(int j=0; j<=2; j++) {
					st += Integer.toString(cross[i][j]);
				}
			}
			st+= Integer.toString(move);
			checkForwins();
			st += Integer.toString(wins);
			res = st;
		}
	}
	
	 /**
	  * switchPlay() to change the turns of the players after one move.
	  */
	public void switchPlay() {
		if(move== 1) {
			move= 2;
		} else if(move== 2) {
			move= 1;
		}
	}
	
	/**
	 * check Rows, Columns and Diagonals to determine the winner.
	 */
	public void checkRowColDiag() {
		if(cross[0][1]==cross[0][2] && cross[0][1]==cross[0][0]) {
			if(cross[0][0]==2) {
				wins = 2;
			} else if(cross[0][0]==1) {
				wins = 1;
			}
		}
		else if(cross[2][2]==cross[2][1] && cross[2][1]==cross[2][0]) {
			if(cross[2][0]==2) {
				wins = 2;
			} else if(cross[2][0]==1) {
				wins = 1;
			}
		}
		else if(cross[1][2]==cross[1][1] && cross[1][1]==cross[1][0]) {
			if(cross[1][0]==2) {
				wins = 2;
			} else if(cross[1][0]==1) {
				wins = 1;
			}
		}
		else if(cross[0][0]==cross[1][0] && cross[1][0]==cross[2][0]) {
			if(cross[0][0]==2) {
				wins = 2;
			} else if(cross[0][0]==1) {
				wins = 1;
			}
		}
		else if(cross[2][1]==cross[1][1] && cross[1][1]==cross[0][1]) {
			if(cross[0][1]==2) {
				wins = 2;
			} else if(cross[0][1]==1) {
				wins = 1;
			}
		}
		else if(cross[2][2]==cross[1][2] && cross[1][2]==cross[0][2]) {
			if(cross[0][2]==2) {
				wins = 2;
			} else if(cross[0][2]==1) {
				wins = 1;
			}
		}
		else if(cross[1][1]==cross[2][2] && cross[1][1]==cross[0][0]) {
			if(cross[1][1]==2) {
				wins = 2;
			} else if(cross[1][1]==1) {
				wins = 1;
			}
		}
		else  if(cross[1][1]==cross[0][2] && cross[1][1]==cross[2][0]) {
			if(cross[1][1]==2) {
				wins = 2;
			} else if(cross[1][1]==1) {
				wins = 1;
			}
		}
	}
	
	/**
	 * Checkforwins() determines the winner 
	 */
	public void checkForwins() {
		checkRowColDiag();
		if(wins==0 && mvc==9) {
			wins = 3;
		}
	}
	
	/**
	 * reset() to bring the values back to default
	 */
	public void reset() {
		for(int i=0; i<=2; i++) {
			for(int j=0;j<=2; j++) {
				cross[i][j] = 0;
			}
		}
		nplay = 0;
		mvc = 0;
		move= 1;
		wins = 0;
		res = "";
	}
	
	/**
	 * Handler that implements the runnable to handle the input and output of the clients
	 * 
	 * @author Saharsh
	 *
	 */
	public class Handler implements Runnable {
		private Socket soc;
		private Scanner inp;
		private PrintWriter outp;
		
		/**
		 * Handler constructor to update the socket, input and output
		 * 
		 * @param soc
		 * @param inp
		 * @param outp
		 */
		public Handler(Socket soc, Scanner inp, PrintWriter outp) {
			this.soc = soc;
			this.outp = outp;
			this.inp = inp;
		}
		
		/**
		 * run() overridden according to TicTacToe
		 */
		public void run() {
			System.out.println("Connected: " + soc);
			try {
				while(inp.hasNextLine()) {
					nplay = 2;
					String command = inp.nextLine();
					moves(command);
					for(PrintWriter player : players) {
						player.println(res);
					}
				}
			} 
			catch(Exception e) {
				System.out.println(e.getMessage());
			} 
			finally {
				
				if(outp != null) {
					players.remove(outp);
					nplay--;
					for(PrintWriter player : players) {
						player.println("Game Ends. One of the players left.");
					}
					
					reset();
				}
			}
		}
	}
}