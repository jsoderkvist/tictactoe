package com.jean.tictactoe.model;

/**
 * Tic Tac Toe Player Interface
 */
public interface Player {
    public Mark getMark();
    public Integer makeNextMove(Board board);
    public String getWinMessage();
    public String getMoveMessage();
}