package com.jean.tictactoe.view;

import com.jean.tictactoe.model.Board;
import com.jean.tictactoe.model.Player;

/**
 * View interface
 */
public interface View {
    public void displayBoard(Board board, Player player);
    public void displayWinMessage(Player player);
    public void displayTieMessage();
    public void displayWelcome();
    public void displayGoodbye();
    public boolean getPlayFirst();
    public boolean getPlayAgain();
}
