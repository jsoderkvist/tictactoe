package com.jean.tictactoe;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Unit tests for Tic Tac Toe Game.
 */
public class GameTest extends TestCase {

    public void testGame()
    {
        String inputMoves = "y\n1\n2\n4\nn";
        InputStream inputStream = new ByteArrayInputStream(inputMoves.getBytes());
        InputStreamPlayer xPlayer = new InputStreamPlayer(inputStream, Mark.X);

        String inputMoves2 = "5\n3\n7";
        InputStream inputStream2 = new ByteArrayInputStream(inputMoves2.getBytes());
        InputStreamPlayer oPlayer = new InputStreamPlayer(inputStream2, Mark.O);

        Game game = new Game(xPlayer, oPlayer);
        game.play();
    }

    public void testGamePlayAgain()
    {
        String inputMoves = "n\n3\n9\n4\n2\ny\ny\n1\n2\n4\nn";
        InputStream inputStream = new ByteArrayInputStream(inputMoves.getBytes());
        InputStreamPlayer oPlayer = new InputStreamPlayer(inputStream, Mark.O);

        String inputMoves2 = "5\n7\n6\n1\n8\n5\n3\n7";
        InputStream inputStream2 = new ByteArrayInputStream(inputMoves2.getBytes());
        InputStreamPlayer xPlayer = new InputStreamPlayer(inputStream2, Mark.X);

        Game game = new Game(xPlayer, oPlayer);
        game.play();
    }
}
