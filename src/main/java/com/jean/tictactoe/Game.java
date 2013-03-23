package com.jean.tictactoe;

import java.util.Scanner;

/**
 * Tic Tac Toe Game
 */
public class Game {
    private Player xPlayer;
    private Player oPlayer;
    private Player currentPlayer;

    public Game(Player xPlayer, Player oPlayer) {
        if (xPlayer.getMark() != Mark.X || oPlayer.getMark() != Mark.O) {
            throw new IllegalArgumentException();
        }

        this.xPlayer = xPlayer;
        this.oPlayer = oPlayer;
        this.currentPlayer = xPlayer;
    }

    public void play() {
        // init and board
        Board board = new Board();

        while (true) {
            // get player move
            currentPlayer.makeNextMove(board);

            System.out.println(currentPlayer.getMoveMessage());
            System.out.println(board);

            // update board (check for win)
            if (board.hasWin(currentPlayer.getMark())) {
                System.out.println(currentPlayer.getWinMessage());
                break;
            } else if (board.hasDraw()) {
                System.out.println("It's a tie. Good game.");
                break;
            }

            // swap players
            currentPlayer = currentPlayer == xPlayer ? oPlayer : xPlayer;
        }
    }

    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's play Tic Tac Toe!\n");
        System.out.println("You can enter your move as a number using the guide below:\n");
        System.out.println(Board.getNumberGuide());

        boolean playAgain = true;
        while (playAgain) {
            System.out.print("Would you like to go first? (y or n):");

            Game game;
            if (scanner.nextLine().startsWith("y")) {
                System.out.print("\nOkay. ");
                game = new Game(new InputStreamPlayer(System.in, Mark.X), new RulesPlayer(Mark.O));
            } else {
                System.out.println("\nOkay, I'll go.\n");
                game = new Game(new RulesPlayer(Mark.X), new InputStreamPlayer(System.in, Mark.O));
            }
            game.play();

            System.out.print("\nWant to play again? (y or n):");
            playAgain = scanner.nextLine().startsWith("y");
            System.out.println();
        }

        System.out.println("Okay. Bye!");
    }
}
