package com.jean.tictactoe;

/**
 * Tic Tac Toe Player Interface
 */
public interface Player {
    public void setMark(Mark mark);
    public Mark getMark();
    public Integer makeNextMove(Board board);
    public boolean hasAI();
}
