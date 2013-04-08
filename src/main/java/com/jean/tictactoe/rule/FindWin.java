package com.jean.tictactoe.rule;

import com.jean.tictactoe.Board;
import com.jean.tictactoe.Cell;
import com.jean.tictactoe.Mark;

import java.util.List;

/**
 * Finds win for specified mark if available
 */
public class FindWin extends Rule {
    private Mark mark;

    public FindWin(Mark mark) {
        this.mark = mark;
    }

    public Integer findMove(Board board) {
        for (Cell[] row : board.getAllRows()) {
            List<Integer> emptyCells = findEmptyCellsInRowWithMark(row, mark, 2);
            if (!emptyCells.isEmpty()) {
                return emptyCells.get(0);
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " for " + mark;
    }
}
