package com.jean.tictactoe.rule;

import com.jean.tictactoe.Board;

/**
 * Finds an open side
 */
public class FindSide extends Rule {

    public Integer findMove(Board board) {
        return findEmptyCell(board.getSides());
    }
}
