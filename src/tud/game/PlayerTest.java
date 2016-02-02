package tud.game;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

public class PlayerTest {

	Player p = new Player("test", 1);

	@Test
	public void getPlayerNameTest() {
		assertThat(p.getPlayerName(), equalTo("p" + p.getPlayerId()));

	}

	@Test
	public void getPlayerIdTest() {
		assertThat(p.getPlayerId(), instanceOf(int.class));

	}

	@Test
	public void idGeneratorTest1() {
		new IdGenerator();

	}

	@Test
	public void idGeneratorTest2() {
		assertThat(new IdGenerator().createNewId(), equalTo(1));
	}

	@Test
	public void idGeneratorTest3() {
		IdGenerator gen = new IdGenerator();
		gen.createNewId();
		assertThat(gen.createNewId(), equalTo(2));
	}

	@Test
	public void nextPlayerTest1() {
		Map<String, Player> players = new HashMap<String, Player>();
		Player p2 = new Player("test", 2);
		players.put("p1", p);
		players.put("p2", p2);
		assertThat(p.nextPlayer(players), equalTo(p2));
	}

	@Test
	public void checkTurnTest1() {
		Scanner scanner = new Scanner(new ByteArrayInputStream("1\n".getBytes()));
		Board board = new Board(1);
		Player p = new Player("test", 1);
		assertThat(p.checkTurn(scanner, board, false, 2), equalTo(1));
		scanner.close();
	}

	@Test
	public void checkTurnTest2() {
		Scanner scanner = new Scanner(new ByteArrayInputStream("a\n1\n".getBytes()));
		Board board = new Board(1);
		Player p = new Player("test", 1);
		assertThat(p.checkTurn(scanner, board, false, 2), equalTo(1));
		scanner.close();
	}
	
	@Test
	public void checkTurnTest3() {
		Scanner scanner = new Scanner(new ByteArrayInputStream("?\n1\n".getBytes()));
		Board board = new Board(1);
		Player p = new Player("test", 1);
		assertThat(p.checkTurn(scanner, board, false, 2), equalTo(1));
		scanner.close();
	}
	
	@Test
	public void checkTurnTest4() {
		Scanner scanner = new Scanner(new ByteArrayInputStream("?\n1\n".getBytes()));
		Board board = new Board(1);
		Player p = new Player("test", 1);
		assertThat(p.checkTurn(scanner, board, true, 2), equalTo(1));
		scanner.close();

	}
	
	@Test
	public void checkTurnTest5() {
		Scanner scanner = new Scanner(new ByteArrayInputStream("?\n1\n".getBytes()));
		Board board = new Board(1);
		Player p = new Player("test", 1);
		assertThat(p.checkTurn(scanner, board, false, 3), equalTo(1));
		scanner.close();

	}
	
	@Test
	public void checkTurnTest6() {
		Scanner scanner = new Scanner(new ByteArrayInputStream("10\n1\n".getBytes()));
		Board board = new Board(1);
		Player p = new Player("test", 1);
		assertThat(p.checkTurn(scanner, board, false, 2), equalTo(1));
		scanner.close();

	}
	
	@Test
	public void checkTurnTest7() {
		Scanner scanner = new Scanner(new ByteArrayInputStream("1\n2\n".getBytes()));
		Board board = new Board(1);
		board.nextAction(1, "p1");
		Player p = new Player("test", 1);
		assertThat(p.checkTurn(scanner, board, false, 2), equalTo(2));
		scanner.close();

	}
	
	@Test
	public void checkTurnTest8() {
		Scanner scanner = new Scanner(new ByteArrayInputStream("1\n2\n".getBytes()));
		Board board = new Board(1);
		board.nextAction(1, "p1");
		Player p = new Player("test", 1);
		assertThat(p.checkTurn(scanner, board, true, 2), equalTo(2));
		scanner.close();

	}
	
	@Test
	public void checkTurnTest9() {
		Scanner scanner = new Scanner(new ByteArrayInputStream("0\n10\n1".getBytes()));
		Board board = new Board(1);
		Player p = new Player("test", 1);
		assertThat(p.checkTurn(scanner, board, false, 2), equalTo(1));
		scanner.close();
	}
	

	
	

}
