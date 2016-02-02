package tud.game;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import tud.game.ai.AdvancedAI;
import tud.game.ai.BasicAI;

public class Game implements Runnable {
	private static final String STARTSCREENTXT = "startScreen.txt";
	private static final String HELPTEXTTXT = "helpText.txt";
	/**
	 * play a game against computer
	 */
	boolean isAiEnabled = false;
	/**
	 * player count
	 */
	int numberOfPlayers = 2;

	/**
	 * begin of game
	 * 
	 * @param args
	 *            - not needed parameter array
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in, "UTF-8");
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	private Scanner scan;

	public Game(Scanner scan) {
		this.scan = scan;
	}

	public void run() {
		Map<String, Player> players;

		isAiEnabled = displayStartScreen();

		if (isAiEnabled) {
			players = createPlayers(1);
			players.put("p2", selectAI());

		} else {
			numberOfPlayers = getNumberOfPlayers();
			players = createPlayers(numberOfPlayers);
		}

		Board board = new Board(getFieldLen());
		Player currPlayer = players.get("p1");
		while (true) {
			board.printBoard();
			System.out.println("It is your turn " + currPlayer.getName() + "(" + currPlayer.getPlayerName() + "):");
			int pos = currPlayer.checkTurn(scan, board, isAiEnabled, numberOfPlayers);
			if (board.nextAction(pos, currPlayer.getPlayerName()).equals(Action.NextTurn)) {
				currPlayer = currPlayer.nextPlayer(players);
			}
			if (board.isEnded()) {
				board.printBoard();
				board.showScore(players);
				break;
			}
			System.out.println("---------------------------------------------------------------");
		}
	}

	/**
	 * creates players and asks for their names
	 * 
	 * @param scan
	 *            - input scanner
	 * @param playerCount
	 *            - count of how many players should be created
	 * @return - map of internal player names to players
	 */
	private Map<String, Player> createPlayers(int playerCount) {
		IdGenerator gen = new IdGenerator();
		Map<String, Player> players = new HashMap<String, Player>();
		for (int i = 0; i < playerCount; i++) {
			System.out.println("Player " + (i + 1) + " what is your name?");
			System.out.print("Name:");
			String pstr = scan.next();
			Player p = new Player(pstr, gen.createNewId());
			players.put(p.getPlayerName(), p);
			System.out.println("Welcome " + pstr);
		}

		System.out.println("---------------------------------------------------------------");

		return players;
	}

	/**
	 * asks for player count and returns the count
	 * 
	 * @return player count
	 */
	private int getNumberOfPlayers() {
		System.out.println("Enter number of players:");
		String numberOfPlayers = scan.next();
		while (!isNumber(numberOfPlayers)) {
			System.out.println("Your option is not valid! Please make another.");
			numberOfPlayers = scan.next();
		}

		System.out.println("---------------------------------------------------------------");

		return Integer.parseInt(numberOfPlayers);
	}

	/**
	 * asks if a game should be played against a computer or a human
	 * 
	 * @param scan
	 *            - input scanner
	 * @return true if human versus computer have been chosen, false otherwise
	 */
	private boolean displayStartScreen() {
		// display start screen
		readAndDisplayFile(STARTSCREENTXT);
		// wait for user input
		String input = scan.next();
		while (!input.matches("[h,H,c,C]")) {
			if (input.matches("[?]")) {
				// display help text
				readAndDisplayFile(HELPTEXTTXT);
				// the user has to press b or B to
				// get back to the start screen
				while (!input.matches("[b,B]")) {
					input = scan.nextLine();
				}
				// display start screen again
				readAndDisplayFile(STARTSCREENTXT);
			} else {
				System.out.println("Your option is not valid! Please make another.");
			}
			input = scan.next();
		}

		System.out.println("---------------------------------------------------------------");

		return input.equalsIgnoreCase("c");
	}

	/**
	 * asks for AI level
	 * 
	 * @param scan
	 *            - input scanner
	 * @return true if human versus computer have been chosen, false otherwise
	 */
	private Player selectAI() {
		System.out.println("Select AI?");
		System.out.println("basicAI(b) or advancedAI(a):");
		String selectedAI = scan.next();
		while (!selectedAI.matches("[a,A,b,B]")) {
			System.out.println("Your option is not valid! Please make another.");
			selectedAI = scan.next();
		}

		System.out.println("---------------------------------------------------------------");

		if (selectedAI.equalsIgnoreCase("a"))
			return new AdvancedAI();
		return new BasicAI();
	}

	/**
	 * asks for board length
	 * 
	 * @param scan
	 *            - input scanner
	 * @return board length
	 */
	private int getFieldLen() {
		System.out.println("Enter length of gameboard:");
		String fieldLen = scan.next();
		while (!isNumber(fieldLen)) {
			System.out.println("Your option is not valid! Please make another.");
			fieldLen = scan.next();
		}

		System.out.println("---------------------------------------------------------------");

		return Integer.parseInt(fieldLen);
	}

	/**
	 * checks if the string is a positive number
	 * 
	 * @param value
	 *            - string, which should be checked
	 * @return true if string is a positive number, false otherwise
	 */
	public static boolean isNumber(String value) {
		return value.matches("[1-9][0-9]*");
	}

	/**
	 * Reads a text file and displays it on the console
	 * 
	 * @param fileName
	 *            name and path of the file which should be read
	 */
	public void readAndDisplayFile(String fileName) {
		ClassLoader classLoader = Game.class.getClassLoader();
		InputStream in = classLoader.getResourceAsStream(fileName);
		Scanner scanner = new Scanner(in, "UTF-8");

		String line = scanner.nextLine();

		while (scanner.hasNextLine()) {
			System.out.println(line);
			line = scanner.nextLine();
		}
		scanner.close();

	}
}
