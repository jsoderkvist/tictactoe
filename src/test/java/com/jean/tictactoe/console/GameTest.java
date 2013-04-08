package com.jean.tictactoe.console;

import com.jean.tictactoe.Mark;
import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Unit tests for Tic Tac Toe Game.
 */
public class GameTest extends TestCase {

    public void testGameWin()
    {
        String inputMoves = "1\n2\n4\n";
        InputStream inputStream = new ByteArrayInputStream(inputMoves.getBytes());
        InputStreamPlayer xPlayer = new InputStreamPlayer(inputStream, System.out, Mark.X);

        String inputMoves2 = "5\n3\n7";
        InputStream inputStream2 = new ByteArrayInputStream(inputMoves2.getBytes());
        InputStreamPlayer oPlayer = new InputStreamPlayer(inputStream2, System.out, Mark.O);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Game game = new Game(printStream);
        game.play(xPlayer, oPlayer);
        assertTrue(outputStream.toString().contains("won"));
    }

    public void testGameTie()
    {
        String inputMoves = "3\n9\n4\n2\ny\ny\n1\n2\n4\nn";
        InputStream inputStream = new ByteArrayInputStream(inputMoves.getBytes());
        InputStreamPlayer oPlayer = new InputStreamPlayer(inputStream, System.out, Mark.X);

        String inputMoves2 = "5\n7\n6\n1\n8\n5\n3\n7";
        InputStream inputStream2 = new ByteArrayInputStream(inputMoves2.getBytes());
        InputStreamPlayer xPlayer = new InputStreamPlayer(inputStream2, System.out, Mark.O);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Game game = new Game(printStream);
        game.play(xPlayer, oPlayer);

        assertTrue(outputStream.toString().contains("tie"));
    }

    public void testGetMoveGuide() {
        String guide = Game.getMoveGuide();
        for (int i = 1; i <= 9; i++) {
            assertTrue(guide.contains(String.valueOf(i)));
        }
    }
}
