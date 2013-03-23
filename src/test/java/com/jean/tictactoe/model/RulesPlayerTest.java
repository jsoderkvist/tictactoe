package com.jean.tictactoe.model;

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

        int cell = player.makeNextMove(board);
        System.out.println(board);

        assertNotNull(cell);
        assertEquals(6, cell);
    }

    public void testFindWinInSecondDiagonal() {
        board.setMarkAt(0, Mark.X);
        board.setMarkAt(8, Mark.X);
        board.setMarkAt(5, Mark.O);
        System.out.println(board);

        int cell = player.makeNextMove(board);
        System.out.println(board);

        assertNotNull(cell);
        assertEquals(4, cell);
    }

    public void testFindWinInHorizontalRow() {
        board.setMarkAt(1, Mark.X);
        board.setMarkAt(2, Mark.X);
        board.setMarkAt(3, Mark.O);
        System.out.println(board);

        int cell = player.makeNextMove(board);
        System.out.println(board);

        assertNotNull(cell);
        assertEquals(0, cell);
    }

    public void testFindWinInVerticalRow() {
        board.setMarkAt(2, Mark.X);
        board.setMarkAt(5, Mark.X);
        board.setMarkAt(6, Mark.O);
        System.out.println(board);

        int cell = player.makeNextMove(board);
        System.out.println(board);

        assertNotNull(cell);
        assertEquals(8, cell);
    }

    public void testBlockWin() {
        board.setMarkAt(0, Mark.X);
        board.setMarkAt(8, Mark.X);
        board.setMarkAt(4, Mark.O);
        board.setMarkAt(5, Mark.O);
        System.out.println(board);

        int cell = player.makeNextMove(board);
        System.out.println(board);

        assertNotNull(cell);
        assertEquals(3, cell);
    }

    public void testFindFork() {
        board.setMarkAt(0, Mark.X);
        board.setMarkAt(7, Mark.X);
        board.setMarkAt(1, Mark.O);
        board.setMarkAt(5, Mark.O);
        System.out.println(board);

        int cell = player.makeNextMove(board);
        System.out.println(board);

        assertNotNull(cell);
        assertEquals(6, cell);
    }

    public void testFindFork2() {
        board.setMarkAt(0, Mark.O);
        board.setMarkAt(4, Mark.X);
        board.setMarkAt(5, Mark.O);
        board.setMarkAt(8, Mark.X);
        System.out.println(board);

        int cell = player.makeNextMove(board);
        System.out.println(board);

        assertNotNull(cell);
        assertEquals(6, cell);
    }

    public void testBlockFork1() {
        player = new RulesPlayer(Mark.O);
        board.setMarkAt(0, Mark.O);
        board.setMarkAt(4, Mark.X);
        board.setMarkAt(8, Mark.X);
        System.out.println(board);

        int cell = player.makeNextMove(board);
        System.out.println(board);

        assertNotNull(cell);
        assertEquals(2, cell);
    }

    public void testBlockFork2() {
        player = new RulesPlayer(Mark.O);
        board.setMarkAt(0, Mark.X);
        board.setMarkAt(4, Mark.O);
        board.setMarkAt(8, Mark.X);
        System.out.println(board);

        int cell = player.makeNextMove(board);
        System.out.println(board);

        assertNotNull(cell);
        assertEquals(3, cell);
    }

    public void testPlayCenter() {
        player = new RulesPlayer(Mark.O);
        board.setMarkAt(0, Mark.X);
        System.out.println(board);

        int cell = player.makeNextMove(board);
        System.out.println(board);

        assertNotNull(cell);
        assertEquals(4, cell);
    }

    public void testPlayOppositeCorner() {
        board.setMarkAt(4, Mark.X);
        board.setMarkAt(0, Mark.O);
        System.out.println(board);

        int cell = player.makeNextMove(board);
        assertNotNull(cell);
        assertEquals(8, cell);
    }

    public void testPlayCorner() {
        player = new RulesPlayer(Mark.O);
        board.setMarkAt(4, Mark.X);
        System.out.println(board);

        int cell = player.makeNextMove(board);
        System.out.println(board);

        assertNotNull(cell);
        assertTrue(Board.getCorners().contains(cell));
    }
}
