package com.jean.tictactoe.view;

import com.jean.tictactoe.model.*;

import java.util.Scanner;

/**
 * Console View implementation
 */
public class ConsoleView implements View {
    Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    public void displayBoard(Board board) {
        System.out.println(board);
    }

    public void displayMoveMessage(Player player) {
        System.out.println(player.getMoveMessage());
    }

    public void displayWinMessage(Player player) {
        System.out.println(player.getWinMessage());
    }

    public void displayTieMessage() {
        System.out.println("It's a tie. Good game.");
    }

    public void displayWelcome() {
        System.out.println("Let's play Tic Tac Toe!\n");
        System.out.println("You can enter your move as a number using the guide below:\n");
        System.out.println(Board.getNumberGuide());
    }

    public void displayGoodbye() {
        System.out.println("Okay. Bye!");
    }

    public boolean getPlayFirst() {
        System.out.print("Would you like to go first? (y or n):");

        boolean playFirst = scanner.nextLine().startsWith("y");
        if (playFirst) {
            System.out.print("\nOkay. ");
        } else {
            System.out.println("\nOkay, I'll go.\n");
        }

        return playFirst;
    }

    public boolean getPlayAgain() {
        System.out.print("\nWant to play again? (y or n):");
        boolean playAgain = scanner.nextLine().startsWith("y");
        System.out.println();

        return playAgain;
    }

}
