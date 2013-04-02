package com.jean.tictactoe;

/**
 * Cell represents a single spot on the board.
 */
public class Cell {
    private int index;
    private Mark mark;

    public Cell(int index, Mark mark) {
        this.index = index;
        this.mark = mark;
    }

    public int getIndex() {
        return index;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
