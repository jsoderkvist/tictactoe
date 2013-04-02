package com.jean.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * Tic Tac Toe Board
 */
public class Board {
    private static final int NUM_CELLS = 9;
    private Cell[] cells;

    public Board() {
        cells = new Cell[NUM_CELLS];
        for (int i = 0; i < NUM_CELLS; i++) {
            cells[i] = new Cell(i, null);
        }
    }

    public Board(Board board) {
        this();
        for (Cell cell : board.getCells()) {
            int index = cell.getIndex();
            cells[index] = new Cell(index, cell.getMark());
        }
    }

    public void setMarkAt(int index, Mark value) {
        cells[index].setMark(value);
    }

    public Mark getMarkAt(int index) {
        return cells[index].getMark();
    }

    public boolean hasWin(Mark mark) {
        for (Cell[] row : getRows()) {
            if (row[0].getMark() == mark && row[1].getMark() == mark && row[2].getMark() == mark) {
                return true;
            }
        }

        for (Cell[] column : getColumns()) {
            if (column[0].getMark() == mark && column[1].getMark() == mark && column[2].getMark() == mark) {
                return true;
            }
        }

        for (Cell[] diagonal : getDiagonals()) {
            if (diagonal[0].getMark() == mark && diagonal[1].getMark() == mark && diagonal[2].getMark() == mark) {
                return true;
            }
        }

        return false;
    }

    public boolean hasDraw() {
        for (Cell cell : cells) {
            if (cell.getMark() == null) {
                return false;
            }
        }

        return true;
    }

    public List<Cell[]> getRows() {
        ArrayList<Cell[]> rows = new ArrayList<Cell[]>();
        Cell[] row1 = { cells[0], cells[1], cells[2] };
        Cell[] row2 = { cells[3], cells[4], cells[5] };
        Cell[] row3 = { cells[6], cells[7], cells[8] };
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        return rows;
    }

    public List<Cell[]> getColumns() {
        ArrayList<Cell[]> columns = new ArrayList<Cell[]>();
        Cell[] col1 = { cells[0], cells[3], cells[6] };
        Cell[] col2 = { cells[1], cells[4], cells[7] };
        Cell[] col3 = { cells[2], cells[5], cells[8] };
        columns.add(col1);
        columns.add(col2);
        columns.add(col3);

        return columns;
    }

    public List<Cell[]> getDiagonals() {
        ArrayList<Cell[]> diagonals = new ArrayList<Cell[]>();
        Cell[] diagonal1 = { cells[0], cells[4], cells[8] };
        Cell[] diagonal2 = { cells[2], cells[4], cells[6] };
        diagonals.add(diagonal1);
        diagonals.add(diagonal2);

        return diagonals;
    }

    public List<Cell> getCorners() {
        ArrayList<Cell> corners = new ArrayList<Cell>();
        corners.add(cells[0]);
        corners.add(cells[2]);
        corners.add(cells[6]);
        corners.add(cells[8]);

        return corners;
    }

    public List<Cell> getSides() {
        ArrayList<Cell> sides = new ArrayList<Cell>();
        sides.add(cells[1]);
        sides.add(cells[3]);
        sides.add(cells[5]);
        sides.add(cells[7]);

        return sides;
    }

    public Cell[] getCells() {
        return cells;
    }

    public Cell getCenter() {
        return cells[4];
    }

    public List<Cell[]> getOppositeCorners() {
        ArrayList<Cell[]> opposites = new ArrayList<Cell[]>();
        Cell[] opposite1 = { cells[0], cells[8] };
        Cell[] opposite2 = { cells[2], cells[6] };
        opposites.add(opposite1);
        opposites.add(opposite2);

        return opposites;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Cell[] row : getRows()) {
            builder.append("|");
            Mark mark = row[0].getMark();
            builder.append(mark != null ? mark : " ");
            builder.append("|");
            mark = row[1].getMark();
            builder.append(mark != null ? mark : " ");
            builder.append("|");
            mark = row[2].getMark();
            builder.append(mark != null ? mark : " ");
            builder.append("|\n");
        }

        return builder.toString();
    }
}
