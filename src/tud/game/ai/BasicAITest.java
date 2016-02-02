package tud.game.ai;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import tud.game.Board;

public class BasicAITest {

	@Test
	public void constructorTest() {
		new BasicAI();
	}

	@Test
	public void firstWallTest1() {
		assertThat(new BasicAI().getNextTurn(new Board(1)), anyOf(equalTo("1"), equalTo("2"), equalTo("3"), equalTo("4")));
	}

	@Test
	public void firstWallTest2() {
		assertThat(new BasicAI().getNextTurn(new Board(1)), anyOf(equalTo("1"), equalTo("2"), equalTo("3"), equalTo("4")));
	}

	@Test
	public void firstWallTest3() {
		assertThat(new BasicAI().getNextTurn(new Board(1)), anyOf(equalTo("1"), equalTo("2"), equalTo("3"), equalTo("4")));
	}

	@Test
	public void firstWallTest4() {
		assertThat(new BasicAI().getNextTurn(new Board(1)), anyOf(equalTo("1"), equalTo("2"), equalTo("3"), equalTo("4")));
	}

	@Test
	public void lastWallTest1() {
		Board board = new Board(1);
		board.nextAction(1, "p1");
		board.nextAction(2, "p1");
		board.nextAction(3, "p1");

		assertThat(new BasicAI().getNextTurn(board), equalTo("4"));
	}
	
	@Test
	public void lastWallTest2() {
		Board board = new Board(1);
		board.nextAction(1, "p1");
		board.nextAction(2, "p1");
		board.nextAction(4, "p1");

		assertThat(new BasicAI().getNextTurn(board), equalTo("3"));
	}
	
	@Test
	public void lastWallTest3() {
		Board board = new Board(1);
		board.nextAction(1, "p1");
		board.nextAction(4, "p1");
		board.nextAction(3, "p1");

		assertThat(new BasicAI().getNextTurn(board), equalTo("2"));
	}
	
	@Test
	public void lastWallTest4() {
		Board board = new Board(1);
		board.nextAction(4, "p1");
		board.nextAction(2, "p1");
		board.nextAction(3, "p1");

		assertThat(new BasicAI().getNextTurn(board), equalTo("1"));
	}

}
