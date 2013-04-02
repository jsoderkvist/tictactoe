package com.jean.tictactoe;

import junit.framework.TestCase;

/**
 * Unit Tests for RulesPlayer
 */
public class RulesPlayerTest extends TestCase {
    RulesPlayer player;
    Board board;

    @Override
    public void setUp() {
        player = new RulesPlayer(Mark.X);
        board = new Board();
    }

    public void testFindWinInFirstDiagonal() {
        board.setMarkAt(4, Mark.X);
        board.setMarkAt(2, Mark.X);
        board.setMarkAt(5, Mark.O);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(6, move);
    }

    public void testFindWinInSecondDiagonal() {
        board.setMarkAt(0, Mark.X);
        board.setMarkAt(8, Mark.X);
        board.setMarkAt(5, Mark.O);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(4, move);
    }

    public void testFindWinInHorizontalRow() {
        board.setMarkAt(1, Mark.X);
        board.setMarkAt(2, Mark.X);
        board.setMarkAt(3, Mark.O);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(0, move);
    }

    public void testFindWinInVerticalRow() {
        board.setMarkAt(2, Mark.X);
        board.setMarkAt(5, Mark.X);
        board.setMarkAt(6, Mark.O);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(8, move);
    }

    public void testBlockWin() {
        board.setMarkAt(0, Mark.X);
        board.setMarkAt(8, Mark.X);
        board.setMarkAt(4, Mark.O);
        board.setMarkAt(5, Mark.O);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(3, move);
    }

    public void testFindFork() {
        board.setMarkAt(0, Mark.X);
        board.setMarkAt(7, Mark.X);
        board.setMarkAt(1, Mark.O);
        board.setMarkAt(5, Mark.O);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(6, move);
    }

    public void testFindFork2() {
        board.setMarkAt(0, Mark.O);
        board.setMarkAt(4, Mark.X);
        board.setMarkAt(5, Mark.O);
        board.setMarkAt(8, Mark.X);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(6, move);
    }

    public void testBlockFork1() {
        player = new RulesPlayer(Mark.O);
        board.setMarkAt(0, Mark.O);
        board.setMarkAt(4, Mark.X);
        board.setMarkAt(8, Mark.X);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(2, move);
    }

    public void testBlockFork2() {
        player = new RulesPlayer(Mark.O);
        board.setMarkAt(0, Mark.X);
        board.setMarkAt(4, Mark.O);
        board.setMarkAt(8, Mark.X);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(3, move);
    }

    public void testPlayCenter() {
        player = new RulesPlayer(Mark.O);
        board.setMarkAt(0, Mark.X);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(4, move);
    }

    public void testPlayOppositeCorner() {
        board.setMarkAt(4, Mark.X);
        board.setMarkAt(0, Mark.O);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(8, move);
    }

    public void testPlayCorner() {
        player = new RulesPlayer(Mark.O);
        board.setMarkAt(4, Mark.X);
        System.out.println(board);

        int move = player.makeNextMove(board);
        System.out.println(board);

        assertEquals(0, move);
    }
}
