package com.jean.tictactoe.model;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * InputStreamPlayer reads moves from an InputStream
 */
public class InputStreamPlayer implements Player {
    private Mark mark;
    private Scanner scanner;
    private Matcher matcher;
    private PrintStream printStream;

    public InputStreamPlayer(InputStream inputStream, PrintStream printStream, Mark mark) {
        this.mark = mark;
        this.printStream = printStream;
        scanner = new Scanner(inputStream);
        Pattern pattern = Pattern.compile("^\\s*([1-9])\\s*$");
        matcher = pattern.matcher("");
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

            matcher.reset(scanner.nextLine());
            if (matcher.find()) {
                move = Integer.parseInt(matcher.group(1)) - 1;
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
}
