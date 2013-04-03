package com.jean.tictactoe;

/**
 * Cell or spot on a Board
 */
public class Cell {
    private int index;
    private Mark mark;

    public Cell(int index) {
        this.index = index;
        this.mark = null;
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
