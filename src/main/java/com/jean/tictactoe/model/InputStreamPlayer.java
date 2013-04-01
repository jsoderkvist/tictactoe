package com.jean.tictactoe.model;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * InputStreamPlayer reads moves from an InputStream
 */
public class InputStreamPlayer implements Player, InputPlayer {
    private Mark mark;
    private Scanner scanner;
    private Matcher moveMatcher;
    private Matcher yesNoMatcher;
    private PrintStream printStream;

    public InputStreamPlayer(InputStream inputStream, PrintStream printStream) {
        this.printStream = printStream;
        scanner = new Scanner(inputStream);
        moveMatcher = Pattern.compile("^\\s*([1-9])\\s*$").matcher("");
        yesNoMatcher = Pattern.compile("^\\s*([y,n])\\s*$").matcher("");
    }

    public InputStreamPlayer(InputStream inputStream, PrintStream printStream, Mark mark) {
        this(inputStream, printStream);
        this.setMark(mark);
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Mark getMark() {
        return mark;
    }

    public String getWinMessage() {
        return "You won! Jean has some debugging to do.";
    }

    public String getMoveMessage() {
        return "your move";
    }

    public Integer makeNextMove(Board board) {
        Integer move = null;
        while (move == null) {
            printStream.print("Enter your next move (1-9):");

            moveMatcher.reset(scanner.nextLine());
            if (moveMatcher.find()) {
                move = Integer.parseInt(moveMatcher.group(1)) - 1;
            } else {
                printStream.println("\nHmm, that's not a valid move. Here's the guide again:\n");
                printStream.println(Board.getNumberGuide());
                continue;
            }

            if (board.getMarkAt(move) != null) {
                printStream.println("\nSorry, you can't play in a spot that's already marked.\n");
                move = null;
            }
        }

        printStream.println();
        board.setMarkAt(move, mark);

        return move;
    }

    public boolean getPlayFirst() {
        Boolean playFirst = getYesOrNoAsBoolean("Would you like to go first");
        if (playFirst) {
            printStream.print("\nOkay. ");
        } else {
            printStream.println("\nOkay, I'll go.\n");
        }

        return playFirst;
    }

    public boolean getPlayAgain() {
        return getYesOrNoAsBoolean("Want to play again");
    }

    private boolean getYesOrNoAsBoolean(String question) {
        Boolean response = null;
        while (response == null) {
            printStream.format("\n%s? (y or n):", question);

            yesNoMatcher.reset(scanner.nextLine());
            if (yesNoMatcher.find()) {
                response = "y".equals(yesNoMatcher.group(1));
            } else {
                printStream.println("\nPlease enter your response as 'y' or 'n'");
            }
        }

        return response;
    }
}
