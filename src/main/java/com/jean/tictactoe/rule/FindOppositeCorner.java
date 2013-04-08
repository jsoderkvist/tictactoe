package com.jean.tictactoe.rule;

import com.jean.tictactoe.Board;
import com.jean.tictactoe.Cell;
import com.jean.tictactoe.Mark;

/**
 * Finds an open corner opposite specified mark
 */
public class FindOppositeCorner extends Rule {
    private Mark mark;

    public FindOppositeCorner(Mark mark) {
        this.mark = mark;
    }

    public Integer findMove(Board board) {
        for (Cell[] opposites : board.getOppositeCorners()) {
            if (opposites[0].getMark() == mark && opposites[1].getMark() == null) {
                return opposites[1].getIndex();
            } else if (opposites[0].getMark() == null && opposites[1].getMark() == mark) {
                return opposites[0].getIndex();
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " for " + mark;
    }
}
