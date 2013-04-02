package com.jean.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * Tic Tac Toe Board
 */
public class Board {
    private static final int NUM_CELLS = 9;
    private Mark[] cells;

    public Board() {
        cells = new Mark[NUM_CELLS];
    }

    public Board(Board board) {
        this();
        for (int i = 0; i < NUM_CELLS; i++) {
            cells[i] = board.getMarkAt(i);
        }
    }

    public void setMarkAt(int index, Mark value) {
        cells[index] = value;
    }

    public Mark getMarkAt(int index) {
        return cells[index];
    }

    public boolean hasWin(Mark mark) {
        for (int[] row : getRows()) {
            if (cells[row[0]] == mark && cells[row[1]] == mark && cells[row[2]] == mark) {
                return true;
            }
        }

        for (int[] column : getColumns()) {
            if (cells[column[0]] == mark && cells[column[1]] == mark && cells[column[2]] == mark) {
                return true;
            }
        }

        for (int[] diagonal : getDiagonals()) {
            if (cells[diagonal[0]] == mark && cells[diagonal[1]] == mark && cells[diagonal[2]] == mark) {
                return true;
            }
        }

        return false;
    }

    public boolean hasDraw() {
        for (int i = 0; i < NUM_CELLS; i++) {
            if (cells[i] == null) {
                return false;
            }
        }

        return true;
    }

    public static List<int[]> getRows() {
        ArrayList<int[]> rows = new ArrayList<int[]>();
        int[] row1 = { 0, 1, 2 };
        int[] row2 = { 3, 4, 5 };
        int[] row3 = { 6, 7, 8 };
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        return rows;
    }

    public static List<int[]> getColumns() {
        ArrayList<int[]> columns = new ArrayList<int[]>();
        int[] col1 = { 0, 3, 6 };
        int[] col2 = { 1, 4, 7 };
        int[] col3 = { 2, 5, 8 };
        columns.add(col1);
        columns.add(col2);
        columns.add(col3);

        return columns;
    }

    public static List<int[]> getDiagonals() {
        ArrayList<int[]> diagonals = new ArrayList<int[]>();
        int[] diagonal1 = { 0, 4, 8 };
        int[] diagonal2 = { 2, 4, 6 };
        diagonals.add(diagonal1);
        diagonals.add(diagonal2);

        return diagonals;
    }

    public static List<Integer> getCorners() {
        ArrayList<Integer> corners = new ArrayList<Integer>();
        corners.add(0);
        corners.add(2);
        corners.add(6);
        corners.add(8);

        return corners;
    }

    public static List<Integer> getSides() {
        ArrayList<Integer> sides = new ArrayList<Integer>();
        sides.add(1);
        sides.add(3);
        sides.add(5);
        sides.add(7);

        return sides;
    }

    public static List<Integer> getCells() {
        ArrayList<Integer> cells = new ArrayList<Integer>();
        for (int i = 0; i < Board.NUM_CELLS; i++) {
            cells.add(i);
        }

        return cells;
    }

    public static int getCenter() {
        return 4;
    }

    public static List<int[]> getOppositeCorners() {
        ArrayList<int[]> opposites = new ArrayList<int[]>();
        int[] opposite1 = { 0, 8 };
        int[] opposite2 = { 2, 6 };
        opposites.add(opposite1);
        opposites.add(opposite2);

        return opposites;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] row : getRows()) {
            builder.append("|");
            Mark mark = cells[row[0]];
            builder.append(mark != null ? mark : " ");
            builder.append("|");
            mark = cells[row[1]];
            builder.append(mark != null ? mark : " ");
            builder.append("|");
            mark = cells[row[2]];
            builder.append(mark != null ? mark : " ");
            builder.append("|\n");
        }

        return builder.toString();
    }
}
