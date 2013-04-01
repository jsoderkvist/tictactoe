package com.jean.tictactoe.view;

import com.jean.tictactoe.model.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Console View implementation
 */
// TODO rename to ScannerView? something other than view? Referee?? (combined with Game?)
public class ConsoleView implements View {
    private Scanner scanner;
    private PrintStream outputStream;
    private Matcher matcher;

    public ConsoleView(InputStream inputStream, PrintStream outputStream) {
        scanner = new Scanner(inputStream);
        this.outputStream = outputStream;
        Pattern pattern = Pattern.compile("^\\s*([y,n])\\s*$");
        matcher = pattern.matcher("");
    }

    public void displayBoard(Board board, Player player) {
        outputStream.format("Here's the board after %s:\n", player.getMoveMessage());
        outputStream.println(board);
    }

    public void displayWinMessage(Player player) {
        outputStream.println(player.getWinMessage());
    }

    public void displayTieMessage() {
        outputStream.println("It's a tie. Good game.");
    }

    public void displayWelcome() {
        outputStream.println("Let's play Tic Tac Toe!\n");
        outputStream.println("You can enter your move as a number using the guide below:\n");
        outputStream.println(Board.getNumberGuide());
    }

    public void displayGoodbye() {
        outputStream.println("Okay. Bye!");
    }

    public boolean getPlayFirst() {   // TODO move to InputStreamPlayer?
        Boolean playFirst = getYesOrNoResponse("Would you like to go first");
        if (playFirst) {
            outputStream.print("\nOkay. ");
        } else {
            outputStream.println("\nOkay, I'll go.\n");
        }

        return playFirst;
    }

    public boolean getPlayAgain() {
        Boolean playAgain = getYesOrNoResponse("Want to play again");
        outputStream.println();

        return playAgain;
    }

    private boolean getYesOrNoResponse(String question) {
        Boolean response = null;
        while (response == null) {
            outputStream.format("\n%s? (y or n):", question);

            matcher.reset(scanner.nextLine());
            if (matcher.find()) {
                response = "y".equals(matcher.group(1));
            } else {
                outputStream.println("\nPlease enter your response as 'y' or 'n'\n");
            }
        }

        return response;
    }
}
