package tud.game;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class BoardTest {

	Map<String, Player> players = new HashMap<String, Player>();

	{
		players.put("p1", new Player("test",1));
		players.put("p2", new Player("test",2));
	}

	@Test
	public void printBoardTest1() {
		new Board(1).printBoard();
	}
	
	@Test
	public void actionTest() {
		Action.valueOf("Again");
		Action.values();
	}

	@Test
	public void printBoardTest2() {
		Board board = new Board(2);
		board.nextAction(1, "p1");
		board.nextAction(3, "p1");
		board.nextAction(4, "p1");
		board.nextAction(6, "p1");
		board.printBoard();
	}

	@Test
	public void checkRulesTest1() {
		assertFalse(new Board(1).checkRules(-1));
	}

	@Test
	public void checkRulesTest2() {
		assertFalse(new Board(1).checkRules(5));
	}

	@Test
	public void checkRulesTest3() {
		assertTrue(new Board(1).checkRules(2));
	}

	@Test
	public void nextActionTest() {
		Board board = new Board(1);
		board.nextAction(1, "p1");
		assertThat(board.nextAction(1, "p2"), equalTo(Action.NextTurn));
	}

	@Test
	public void isEndedTest1() {
		Board board = new Board(1);
		board.nextAction(1, "p1");
		board.nextAction(3, "p1");
		board.nextAction(2, "p1");
		board.nextAction(4, "p1");
		assertTrue(board.isEnded());
	}

	@Test
	public void isEndedTest2() {
		Board board = new Board(1);
		board.nextAction(1, "p1");
		board.nextAction(3, "p1");
		board.nextAction(2, "p1");
		assertFalse(board.isEnded());
	}

	@Test
	public void showScoreTest1() {
		Board board = new Board(1);
		board.nextAction(1, "p1");
		board.nextAction(3, "p1");
		board.nextAction(2, "p1");
		board.nextAction(4, "p1");

		board.showScore(players);
	}

	@Test
	public void showScoreTest2() {
		Board board = new Board(1);
		board.nextAction(1, "p1");
		board.nextAction(3, "p1");
		board.nextAction(2, "p1");
		board.nextAction(4, "p2");

		board.showScore(players);
	}

	@Test
	public void showScoreTest3() {
		Board board = new Board(2);

		board.nextAction(1, "p1");
		board.nextAction(3, "p1");
		board.nextAction(4, "p1");
		board.nextAction(6, "p1");

		board.nextAction(2, "p2");
		board.nextAction(5, "p2");
		board.nextAction(7, "p2");

		board.nextAction(8, "p1");
		board.nextAction(9, "p1");
		board.nextAction(11, "p1");

		board.nextAction(10, "p2");
		board.nextAction(12, "p2");

		board.showScore(players);
	}
}
