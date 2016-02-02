package tud.game;

import java.util.Map;
import java.util.Scanner;

import tud.game.ai.AdvancedAI;

public class Player {

	protected String name;

	protected String playerName;
	private int playerId;

	public Player(String name, int id) {
		playerId = id;
		playerName = "p" + playerId;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getPlayerId() {
		return playerId;
	}

	/**
	 * returns the next player in the game
	 * 
	 * @param players
	 *            - map of internal player names to players
	 * @return next player in the row, if current player is the last it returns player 1
	 */
	public Player nextPlayer(Map<String, Player> players) {
		return players.get("p" + ((this.getPlayerId() % players.size()) + 1));
	}

	/**
	 * checks if the input is a valid turn
	 * 
	 * @param scan
	 *            - input scanner
	 * @param board
	 *            - game board
	 * @param isAiEnabled
	 *            - describes if an AI is enabled
	 * @param numberOfPlayers
	 *            - number of players
	 * @return valid turn
	 */
	public int checkTurn(Scanner scan, Board board, boolean isAiEnabled, int numberOfPlayers) {
		String turn = scan.next();
		while (!(Game.isNumber(turn) && board.checkRules(Integer.parseInt(turn)))) {
			if (turn.matches("[?]") && !isAiEnabled && numberOfPlayers == 2) {
				String nextPossibleTurn = AdvancedAI.supportPlayer(board, getPlayerName());
				System.out.println("Next possible turn: " + nextPossibleTurn);
			} else {
				System.out.println("Your turn is not valid! Please make another.");
			}
			turn = scan.next();
		}
		return Integer.parseInt(turn);
	}
}
