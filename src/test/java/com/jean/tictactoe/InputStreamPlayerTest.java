package com.jean.tictactoe;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Unit Tests for InputStreamPlayer
 */
public class InputStreamPlayerTest extends TestCase {
    Board board;

    @Override
    public void setUp() {
        board = new Board();
    }

    public void testNextMove() {
        String input = "4";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputStreamPlayer player = new InputStreamPlayer(inputStream, Mark.X);

        int move = player.makeNextMove(board);
        assertEquals(3, move);
    }

    public void testNextMoveWrongFormat() {
        String input = "topleft\n5";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputStreamPlayer player = new InputStreamPlayer(inputStream, Mark.X);

        int move = player.makeNextMove(board);
        assertEquals(4, move);
    }

    public void testNextMoveWrongAlreadyPlayed() {
        board.setMarkAt(3, Mark.X);
        String input = "4\n5";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputStreamPlayer player = new InputStreamPlayer(inputStream, Mark.X);

        int move = player.makeNextMove(board);
        assertEquals(4, move);
    }
}
