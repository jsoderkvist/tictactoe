package com.jean.tictactoe;

import junit.framework.TestCase;

/**
 * Unit Tests for Tic Tac Toe Board
 */
public class BoardTest extends TestCase {

    Board board;

    @Override
    public void setUp() {
        board = new Board();
    }

    public void testGetCellValue() {
        board.setMarkAt(5, Mark.X);
        assertEquals(Mark.X, board.getMarkAt(5));
        assertNull(board.getMarkAt(1));
    }

    public void testBoardDisplay() {  // TODO move
        System.out.print(board.toString());
        assertNotNull(board.toString());

        //System.out.print(Board.getNumberGuide());
       // assertNotNull(Board.getNumberGuide());
    }

    public void testHasWinNoWin() {
        board.setMarkAt(3, Mark.X);
        board.setMarkAt(5, Mark.X);
        board.setMarkAt(7, Mark.O);
        System.out.print(board.toString());

        assertFalse(board.hasWin(Mark.X));
        assertFalse(board.hasWin(Mark.O));
    }

    public void testHasWinDiagonal1() {
        board.setMarkAt(2, Mark.X);
        board.setMarkAt(4, Mark.X);
        board.setMarkAt(6, Mark.X);
        System.out.print(board.toString());

        assertTrue(board.hasWin(Mark.X));
        assertFalse(board.hasWin(Mark.O));
    }

    public void testHasWinDiagonal2() {
        board.setMarkAt(0, Mark.X);
        board.setMarkAt(4, Mark.X);
        board.setMarkAt(8, Mark.X);
        System.out.print(board.toString());

        assertTrue(board.hasWin(Mark.X));
    }

    public void testHasWinVertical() {
        board.setMarkAt(2, Mark.X);
        board.setMarkAt(5, Mark.X);
        board.setMarkAt(8, Mark.X);
        System.out.print(board.toString());

        assertTrue(board.hasWin(Mark.X));
    }

    public void testHasWinHorizontal() {
        board.setMarkAt(3, Mark.X);
        board.setMarkAt(4, Mark.X);
        board.setMarkAt(5, Mark.X);
        System.out.print(board.toString());

        assertTrue(board.hasWin(Mark.X));
    }

    public void testHasDraw() {
        assertFalse(board.hasDraw());

        board.setMarkAt(0, Mark.X);
        board.setMarkAt(1, Mark.O);
        board.setMarkAt(2, Mark.O);
        board.setMarkAt(3, Mark.X);
        board.setMarkAt(4, Mark.X);
        board.setMarkAt(5, Mark.O);
        board.setMarkAt(6, Mark.X);
        board.setMarkAt(7, Mark.O);
        board.setMarkAt(8, Mark.X);
        System.out.print(board.toString());

        assertTrue(board.hasDraw());
    }
}
