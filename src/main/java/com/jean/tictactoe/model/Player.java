package com.jean.tictactoe.model;

/**
 * Tic Tac Toe Player Interface   TODO rename to TicTacToePlayer
 */
public interface Player {
    public void setMark(Mark mark);
    public Mark getMark();
    public Integer makeNextMove(Board board);
    public String getWinMessage();
    public String getMoveMessage();
}
