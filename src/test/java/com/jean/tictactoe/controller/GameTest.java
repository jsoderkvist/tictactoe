package com.jean.tictactoe.controller;

import com.jean.tictactoe.model.InputStreamPlayer;
import com.jean.tictactoe.model.Mark;
import com.jean.tictactoe.view.ConsoleView;
import com.jean.tictactoe.view.View;
import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Unit tests for Tic Tac Toe Game.
 */
public class GameTest extends TestCase {

    public void testGameWinO()
    {
        String inputMoves = "1\n2\n4\n";
        InputStream inputStream = new ByteArrayInputStream(inputMoves.getBytes());
        InputStreamPlayer xPlayer = new InputStreamPlayer(inputStream, System.out, Mark.X);

        String inputMoves2 = "5\n3\n7";
        InputStream inputStream2 = new ByteArrayInputStream(inputMoves2.getBytes());
        InputStreamPlayer oPlayer = new InputStreamPlayer(inputStream2, System.out, Mark.O);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Game game = new Game();
        game.play(xPlayer, oPlayer, new ConsoleView(inputStream, printStream));
        assertTrue(outputStream.toString().contains(oPlayer.getWinMessage()));
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
        Game game = new Game();
        game.play(xPlayer, oPlayer, new ConsoleView(inputStream, printStream));

        ByteArrayOutputStream expectedOutputStream = new ByteArrayOutputStream();
        View expectedView = new ConsoleView(inputStream, new PrintStream(expectedOutputStream));
        expectedView.displayTieMessage();
        assertTrue(outputStream.toString().contains(expectedOutputStream.toString()));
    }
}
