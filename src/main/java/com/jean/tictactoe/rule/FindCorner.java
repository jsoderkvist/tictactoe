package com.jean.tictactoe.rule;

import com.jean.tictactoe.Board;

/**
 * Finds an open corner
 */
public class FindCorner extends Rule {

    public Integer findMove(Board board) {
        return findEmptyCell(board.getCorners());
    }
}
