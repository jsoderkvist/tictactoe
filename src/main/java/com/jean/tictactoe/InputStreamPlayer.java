package com.jean.tictactoe;

import java.io.InputStream;
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

    public InputStreamPlayer(InputStream inputStream, Mark mark) {
        this.mark = mark;
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
        return "Here's the board after your move:\n";
    }

    public Integer makeNextMove(Board board) {
        Integer move = null;
        while (move == null) {
            System.out.print("Enter your next move (1-9):");

            matcher.reset(scanner.nextLine());
            if (matcher.find()) {
                move = Integer.parseInt(matcher.group(1)) - 1;
            } else {
                System.out.println("\nHmm, that's not a valid move. Here's the guide again:\n");
                System.out.println(Board.getNumberGuide());
                continue;
            }

            if (board.getMarkAt(move) != null) {
                System.out.println("\nSorry, you can't play in a spot that's already marked.\n");
                move = null;
            }
        }

        System.out.println();
        board.setMarkAt(move, mark);

        return move;
    }
}
