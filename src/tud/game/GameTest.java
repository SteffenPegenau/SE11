package tud.game;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Game game;
	Scanner scan;

	@Before
	public void setUp() {
		scan = new Scanner(new ByteArrayInputStream("".getBytes()));
		game = new Game(scan);
		scan.close();
	}

	@Test
	public void startScreenTest() {
		game.readAndDisplayFile("startScreen.txt");
	}

	@Test
	public void helpTxtTest() {
		game.readAndDisplayFile("helpText.txt");
	}

	@Test(expected = NullPointerException.class)
	public void failTest() {
		game.readAndDisplayFile("text.txt");
	}

	/*
	 * Checks that no exception are thrown while game execution
	 */

	@Test
	public void aiEnabledTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("c\ntest\na\n1\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void basicAITest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("c\ntest\nb\n1\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void showHelpTextTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("?\nb\nc\ntest\nb\n1\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void startWithWrongInputTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("1\n?\nb\nc\ntest\nb\n1\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void wrongAiTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("c\ntest\n1\nb\n1\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void askForHelpTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("c\ntest\na\n1\n?\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void wrongBoardSizeTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("c\ntest\na\na\n1\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void humanPlayerGameTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("h\n2\ntest\na\n1\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void wrongNumberHumanPlayerTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("h\na\n2\ntest\na\n1\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void helpMeAITest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("h\n2\ntest\na\n1\n?\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void moreThen2PlayersTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("h\n3\ntest\na\nb\n1\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}
	
	@Test
	public void moreThen2PlayersAskAiTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("h\n3\ntest\na\nb\n1\n?\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void selectSetWallTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("h\n2\ntest\na\n1\n1\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

	@Test
	public void mainTest() {
		InputStream orig = System.in;
		System.setIn(new ByteArrayInputStream("h\n2\ntest\na\n1\n1\n2\n3\n4\n".getBytes()));
		Game.main(new String[] {});
		System.setIn(orig);
	}
	
	@Test
	public void tooHighNumberTest() {
		Scanner scan = new Scanner(new ByteArrayInputStream("h\n2\ntest\na\n1\n5\n1\n2\n3\n4\n".getBytes()));
		Game game = new Game(scan);
		game.run();
		scan.close();
	}

}
