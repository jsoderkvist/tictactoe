package com.jean.tictactoe.console;

import com.jean.tictactoe.Board;
import com.jean.tictactoe.Mark;
import com.jean.tictactoe.Player;
import com.jean.tictactoe.RulesPlayer;

import java.io.PrintStream;

/**
 * Console Tic Tac Toe Game
 */
public class Game {
    private PrintStream printStream;

    public Game(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void play(Player xPlayer, Player oPlayer) {
        xPlayer.setMark(Mark.X);
        oPlayer.setMark(Mark.O);

        Player currentPlayer = xPlayer;
        Board board = new Board();

        while (true) {
            currentPlayer.makeNextMove(board);

            printStream.format("Here's the board after %s:\n", currentPlayer.getMoveMessage());
            printStream.println(board);

            if (board.hasWin(currentPlayer.getMark())) {
                printStream.println(currentPlayer.getWinMessage());
                break;
            } else if (board.hasDraw()) {
                printStream.println("It's a tie. Good game.");
                break;
            }

            // swap players
            currentPlayer = currentPlayer == xPlayer ? oPlayer : xPlayer;
        }
    }

    public void welcome() {
        printStream.println("\nLet's play Tic Tac Toe!\n");
        printStream.println("You can enter your move as a number using the guide below:\n");
        printStream.print(Game.getMoveGuide());
    }

    public void goodbye() {
        printStream.println("\nOkay. Bye!");
    }

    public static String getMoveGuide() {
        StringBuilder builder = new StringBuilder();
        builder.append("|1|2|3|\n");
        builder.append("|4|5|6|\n");
        builder.append("|7|8|9|\n");

        return builder.toString();
    }

    public static void main(String[] args) {
        PrintStream printStream = System.out;
        Game game = new Game(printStream);
        game.welcome();

        InputStreamPlayer inputStreamPlayer = new InputStreamPlayer(System.in, printStream);
        RulesPlayer rulesPlayer = new RulesPlayer();

        boolean playAgain = true;
        while (playAgain) {
            if (inputStreamPlayer.getPlayFirst()) {
                game.play(inputStreamPlayer, rulesPlayer);
            } else {
                game.play(rulesPlayer, inputStreamPlayer);
            }

            playAgain = inputStreamPlayer.getPlayAgain();
        }

        game.goodbye();
    }
}
