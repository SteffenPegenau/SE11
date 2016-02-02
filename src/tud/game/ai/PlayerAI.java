package tud.game.ai;

import java.util.Scanner;

import tud.game.Board;
import tud.game.Player;

public abstract class PlayerAI extends Player {

	public PlayerAI(String name) {
		super(name, 2);
	}

	public abstract String getNextTurn(Board board);

	public int checkTurn(Scanner scan, Board board, boolean isAiEnabled, int numberOfPlayers) {
		String turn = getNextTurn(board);
		return Integer.parseInt(turn);
	}

}
