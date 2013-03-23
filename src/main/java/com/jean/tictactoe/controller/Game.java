package com.jean.tictactoe.controller;

import com.jean.tictactoe.model.*;
import com.jean.tictactoe.view.ConsoleView;
import com.jean.tictactoe.view.View;

/**
 * Tic Tac Toe Game
 */
public class Game {

    public void play(Player xPlayer, Player oPlayer, View view) {
        Player currentPlayer = xPlayer;
        Board board = new Board();

        while (true) {
            // get player move
            currentPlayer.makeNextMove(board);

            view.displayMoveMessage(currentPlayer);
            view.displayBoard(board);

            // update board (check for win)
            if (board.hasWin(currentPlayer.getMark())) {
                view.displayWinMessage(currentPlayer);
                break;
            } else if (board.hasDraw()) {
                view.displayTieMessage();
                break;
            }

            // swap players
            currentPlayer = currentPlayer == xPlayer ? oPlayer : xPlayer;
        }
    }

    public static void main( String[] args )
    {
        View view = new ConsoleView();
        view.displayWelcome();

        boolean playAgain = true;
        while (playAgain) {
            Player xPlayer;
            Player oPlayer;
            if (view.getPlayFirst()) {
                xPlayer = new InputStreamPlayer(System.in, Mark.X);
                oPlayer = new RulesPlayer(Mark.O);
            } else {
                xPlayer = new RulesPlayer(Mark.X);
                oPlayer = new InputStreamPlayer(System.in, Mark.O);
            }

            Game game = new Game();
            game.play(xPlayer, oPlayer, view);

            playAgain = view.getPlayAgain();
        }

        view.displayGoodbye();
    }
}
