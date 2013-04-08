package com.jean.tictactoe.rule;

import com.jean.tictactoe.Board;
import com.jean.tictactoe.Cell;
import com.jean.tictactoe.Mark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for rules for finding Tic Tac Toe moves
 */
public abstract class Rule {

    public abstract Integer findMove(Board board);

    protected Integer findFork(Board board, Mark mark) {
        for (Cell cell : board.getCells()) {
            if (cell.getMark() == null) {
                Board possibleBoard = new Board(board);
                possibleBoard.setMarkAt(cell.getIndex(), mark);
                List<Integer> possibleWins = findAllWins(possibleBoard, mark);
                if (possibleWins.size() >= 2) {
                    return cell.getIndex();
                }
            }
        }

        return null;
    }

    protected List<Integer> findAllWins(Board board, Mark markToFind) {
        List<Integer> cells = new ArrayList<Integer>();
        for (Cell[] row : board.getAllRows()) {
            List<Integer> emptyCells = findEmptyCellsInRowWithMark(row, markToFind, 2);
            if (!emptyCells.isEmpty()) {
                cells.add(emptyCells.get(0));
            }
        }

        return cells;
    }

    protected List<Integer> findEmptyCellsInRowWithMark(Cell[] row, Mark mark, int markCount) {
        int rowMarkCount = 0;
        List<Integer> emptyCells = new ArrayList<Integer>();
        for (Cell cell : row) {
            Mark cellMark = cell.getMark();
            if (cellMark == null) {
                emptyCells.add(cell.getIndex());
            } else if (cellMark == mark) {
                rowMarkCount++;
            }
        }

        if (emptyCells.size() == (row.length - markCount) && rowMarkCount == markCount) {
            return emptyCells;
        }

        return Collections.emptyList();
    }

    protected Integer findEmptyCell(List<Cell> cells) {
        for (Cell cell : cells) {
            if (cell.getMark() == null) {
                return cell.getIndex();
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
