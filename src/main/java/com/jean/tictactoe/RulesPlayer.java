package com.jean.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Rules-based Player based on http://en.wikipedia.org/wiki/Tic-tac-toe
 */
public class RulesPlayer implements Player {
    private Mark mark;
    private Mark opponentMark;

    private static Logger logger = Logger.getLogger("com.jean.tictactoe.RulesPlayer");

    public RulesPlayer() {
        logger.setLevel(Level.OFF);
    }

    public RulesPlayer(Mark mark) {
        this();
        this.setMark(mark);
    }

    public void setMark(Mark mark) {
        this.mark = mark;
        this.opponentMark = mark == Mark.X ? Mark.O : Mark.X;
    }

    public Mark getMark() {
        return mark;
    }

    public String getWinMessage() {
        return "Sorry, you lost. Better luck next time.";
    }

    public String getMoveMessage() {
        return "my move";
    }

    public Integer makeNextMove(Board board) {
        logger.info("Looking for win for me...");
        Integer move = findWin(board, mark);

        if (move == null) {
            logger.info("Looking for win for you...");
            move = findWin(board, opponentMark);
        }

        if (move == null) {
            logger.info("Looking for a fork for me...");
            move = findFork(board, mark);
        }

        if (move == null) {
            logger.info("Looking for a fork for you...");
            move = findFork(board, opponentMark);
            if (move != null) {
                logger.info("Looking to setup a win for me...");
                move = findMoveToSetupWin(board);
            }
        }

        if (move == null) {
            logger.info("Looking for the center...");
            move = findCenter(board);
        }

        if (move == null) {
            logger.info("Looking for an opposite corner...");
            move = findOppositeCorner(board);
        }

        if (move == null) {
            logger.info("Looking for any corner...");
            move = findEmptyCell(board.getCorners());
        }

        if (move == null) {
            logger.info("Looking for a side...");
            move = findEmptyCell(board.getSides());
        }

        if (move != null) {
            board.setMarkAt(move, mark);
        }

        return move;
    }

    private Integer findOppositeCorner(Board board) {
        for (Cell[] opposites : board.getOppositeCorners()) {
            if (opposites[0].getMark() == opponentMark && opposites[1].getMark() == null) {
                return opposites[1].getIndex();
            }
        }

        return null;
    }

    private Integer findEmptyCell(List<Cell> cells) {
        for (Cell cell : cells) {
            if (cell.getMark() == null) {
                return cell.getIndex();
            }
        }

        return null;
    }

    private Integer findMoveToSetupWin(Board board) {
        // find row with one of my mark and nothing else
        List<Integer> possibleMoves = findEmptyCellsInRowWithMark(board, mark, 1);

        if (possibleMoves.isEmpty()) {
            possibleMoves = findEmptyCellsInColumnWithMark(board, mark, 1);
        }

        if (possibleMoves.isEmpty()) {
            possibleMoves = findEmptyCellsInDiagonalWithMark(board, mark, 1);
        }

        // choose the cell that doesn't force opponent into a fork
        if (!possibleMoves.isEmpty()) {
            if (!isForcedOpponentFork(board, possibleMoves.get(0), possibleMoves.get(1))) {
                return possibleMoves.get(0);
            } else {
                return possibleMoves.get(1);
            }
        }

        return null;
    }

    private boolean isForcedOpponentFork(Board board, Integer move, Integer opponentMove) {
        Board possibleBoard = new Board(board);
        possibleBoard.setMarkAt(move, mark);
        possibleBoard.setMarkAt(opponentMove, opponentMark);

        return findAllWins(possibleBoard, opponentMark).size() >= 2;
    }

    private Integer findCenter(Board board) {
        Cell center = board.getCenter();
        if (center.getMark() == null) {
            return center.getIndex();
        }

        return null;
    }

    private Integer findFork(Board board, Mark markToFind) {
        for (Cell cell : board.getCells()) {
            if (cell.getMark() == null) {
                Board possibleBoard = new Board(board);
                possibleBoard.setMarkAt(cell.getIndex(), markToFind);
                List<Integer> possibleWins = findAllWins(possibleBoard, markToFind);
                if (possibleWins.size() >= 2) {
                    return cell.getIndex();
                }
            }
        }

        return null;
    }

    private Integer findWin(Board board, Mark markToFind) {
        List<Integer> cells = findEmptyCellsInRowWithMark(board, markToFind, 2);

        if (cells.isEmpty()) {
            cells = findEmptyCellsInColumnWithMark(board, markToFind, 2);
        }

        if (cells.isEmpty()) {
            cells = findEmptyCellsInDiagonalWithMark(board, markToFind, 2);
        }

        return cells.isEmpty() ? null : cells.get(0);
    }

    private List<Integer> findAllWins(Board board, Mark markToFind) {
        List<Integer> cells = new ArrayList<Integer>();

        List<Integer> emptyCells = findEmptyCellsInRowWithMark(board, markToFind, 2);
        if (!emptyCells.isEmpty()) {
            cells.add(emptyCells.get(0));
        }

        emptyCells = findEmptyCellsInColumnWithMark(board, markToFind, 2);
        if (!emptyCells.isEmpty()) {
            cells.add(emptyCells.get(0));
        }

        emptyCells = findEmptyCellsInDiagonalWithMark(board, markToFind, 2);
        if (!emptyCells.isEmpty()) {
            cells.add(emptyCells.get(0));
        }

        return cells;
    }

    private List<Integer> findEmptyCellsInRowWithMark(Board board, Mark markToFind, int markCount) {
        return findEmptyCellsInRowWithMark(board.getRows(), markToFind, markCount);
    }

    private List<Integer> findEmptyCellsInColumnWithMark(Board board, Mark markToFind, int markCount) {
        return findEmptyCellsInRowWithMark(board.getColumns(), markToFind, markCount);
    }

    private List<Integer> findEmptyCellsInDiagonalWithMark(Board board, Mark markToFind, int markCount) {
        return findEmptyCellsInRowWithMark(board.getDiagonals(), markToFind, markCount);
    }

    private List<Integer> findEmptyCellsInRowWithMark(List<Cell[]> rows, Mark mark, int markCount) {
        // find row with specified count of mark and nothing else, return the "nothing else" spot(s)
        for (Cell[] row : rows) {
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
        }
        return Collections.emptyList();
    }
}
