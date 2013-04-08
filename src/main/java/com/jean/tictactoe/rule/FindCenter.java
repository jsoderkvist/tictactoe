package com.jean.tictactoe.rule;

import com.jean.tictactoe.Board;
import com.jean.tictactoe.Cell;

/**
 * Finds the center of the board if open
 */
public class FindCenter extends Rule {

    public Integer findMove(Board board) {
        Cell center = board.getCenter();
        if (center.getMark() == null) {
            return center.getIndex();
        }

        return null;
    }
}
