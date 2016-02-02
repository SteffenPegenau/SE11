package tud.game.ai;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import tud.game.Board;

public class AdvancedAITest {

	@Test
	public void constructorTest() {
		assertThat(new AdvancedAI().getName(), equalTo("Advanced AI"));
	}

	@Test
	public void EnumTest() {
		MiniMax.values();
		MiniMax.valueOf("MIN");
	}

	@Test
	public void getNextTurnTest() {
		assertThat(new AdvancedAI().getNextTurn(new Board(1)), equalTo("1"));
	}

	@Test
	public void checkCalculationDepthTest1() {
		assertThat(AdvancedAI.supportPlayer(new Board(1), "p2"), equalTo("1"));
	}

	@Test
	public void checkCalculationDepthTest2() {
		Board board = new Board(2);
		board.nextAction(1, "p1");
		assertThat(AdvancedAI.supportPlayer(board, "p2"), equalTo("2"));
	}

	@Test
	public void checkCalculationDepthTest3() {
		Board board = new Board(2);
		assertThat(AdvancedAI.supportPlayer(board, "p2"), equalTo("1"));
	}

	@Test
	public void checkCalculationDepthTest4() {
		Board board = new Board(3);
		assertThat(AdvancedAI.supportPlayer(board, "p2"), equalTo("1"));
	}

	@Test
	public void checkCalculationDepthTest5() {
		Board board = new Board(7);
		assertThat(AdvancedAI.supportPlayer(board, "p2"), equalTo("1"));
	}

	@Test
	public void supportPlayerTestTakeFirstWall() {
		Board board = new Board(2);
		assertThat(AdvancedAI.supportPlayer(board, "p1"), equalTo("1"));
	}

	@Test
	public void supportPlayerTestTakeSndWall() {
		Board board = new Board(2);
		board.nextAction(1, "p1");
		assertThat(AdvancedAI.supportPlayer(board, "p2"), equalTo("2"));
	}

	// *-* *
	// | |
	// * * *
	//
	// * * *

	@Test
	public void supportPlayerTestP2CloseBox() {
		Board board = new Board(2);
		board.nextAction(1, "p1");
		board.nextAction(3, "p1");
		board.nextAction(4, "p1");
		assertThat(AdvancedAI.supportPlayer(board, "p2"), equalTo("6"));
	}
	// *-*-*
	// |
	// * * *
	//
	// * * *

	@Test
	public void supportPlayerTestP2NotOfferBox() {
		Board board = new Board(2);
		board.nextAction(1, "p1");
		board.nextAction(2, "p1");
		board.nextAction(3, "p1");
		assertThat(AdvancedAI.supportPlayer(board, "p2"), not(equalTo("4")));
	}

	// *-*-*
	// | | |
	// *-* *
	//
	// * * *

	@Test
	public void supportPlayerTestP2CloseSndBox() {
		Board board = new Board(2);
		board.nextAction(1, "p1");
		board.nextAction(3, "p1");
		board.nextAction(4, "p1");
		board.nextAction(6, "p1");
		board.nextAction(2, "p2");
		board.nextAction(5, "p2");
		assertThat(AdvancedAI.supportPlayer(board, "p2"), equalTo("7"));
	}

	// *-*-*
	// | | |
	// * * *
	// | |
	// * * *

	@Test
	public void supportPlayerTestP2GoWinning() {
		Board board = new Board(2);
		board.nextAction(1, "p1");
		board.nextAction(2, "p1");
		board.nextAction(3, "p1");
		board.nextAction(4, "p1");
		board.nextAction(5, "p2");
		board.nextAction(8, "p2");
		board.nextAction(9, "p2");
		assertThat(AdvancedAI.supportPlayer(board, "p2"), equalTo("6"));
		board.nextAction(6, "p2");
		assertThat(AdvancedAI.supportPlayer(board, "p2"), not(equalTo("10")));
	}

	// *-*-*-*
	// |
	// *-*-* *
	// |
	// *-*-*-*
	// | |
	// *-*-*-*

	@Test
	public void supportPlayerTestDontTake19P2() {
		Board board = new Board(3);
		board.nextAction(1, "p1");
		board.nextAction(2, "p1");
		board.nextAction(3, "p1");
		board.nextAction(7, "p1");
		board.nextAction(8, "p2");
		board.nextAction(9, "p2");
		board.nextAction(14, "p2");
		board.nextAction(15, "p2");
		board.nextAction(16, "p2");
		board.nextAction(17, "p2");
		board.nextAction(21, "p2");
		board.nextAction(22, "p2");
		board.nextAction(23, "p2");
		board.nextAction(24, "p2");
		board.nextAction(20, "p2");
		assertThat(AdvancedAI.supportPlayer(board, "p2"), equalTo("18"));
	}

	@Test
	public void supportPlayerTestDontTake19P1() {
		Board board = new Board(3);
		board.nextAction(1, "p1");
		board.nextAction(2, "p1");
		board.nextAction(3, "p1");
		board.nextAction(7, "p1");
		board.nextAction(8, "p2");
		board.nextAction(9, "p2");
		board.nextAction(14, "p2");
		board.nextAction(15, "p2");
		board.nextAction(16, "p2");
		board.nextAction(17, "p2");
		board.nextAction(21, "p2");
		board.nextAction(22, "p2");
		board.nextAction(23, "p2");
		board.nextAction(24, "p2");
		board.nextAction(20, "p2");
		assertThat(AdvancedAI.supportPlayer(board, "p1"), equalTo("18"));
	}

	@Test
	public void supportPlayerTestUseTheSmallerFail() {
		Board board = new Board(3);
		board.nextAction(1, "p1");
		board.nextAction(2, "p1");
		board.nextAction(3, "p1");
		board.nextAction(7, "p1");
		board.nextAction(8, "p2");
		board.nextAction(9, "p2");
		board.nextAction(14, "p2");
		board.nextAction(15, "p2");
		board.nextAction(16, "p2");
		board.nextAction(17, "p2");
		board.nextAction(21, "p2");
		board.nextAction(22, "p2");
		board.nextAction(23, "p2");
		board.nextAction(24, "p2");
		board.nextAction(20, "p2");
		board.nextAction(18, "p2");
		assertThat(AdvancedAI.supportPlayer(board, "p2"), equalTo("19"));
	}

}
