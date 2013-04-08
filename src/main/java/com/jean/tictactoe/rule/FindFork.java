package com.jean.tictactoe.rule;

import com.jean.tictactoe.Board;
import com.jean.tictactoe.Mark;

/**
 * Finds a fork for the specified mark
 */
public class FindFork extends Rule {
    private Mark mark;

    public FindFork(Mark mark) {
        this.mark = mark;
    }

    public Integer findMove(Board board) {
        return findFork(board, mark);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " for " + mark;
    }
}
