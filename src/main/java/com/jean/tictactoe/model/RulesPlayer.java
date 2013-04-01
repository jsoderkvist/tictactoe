package com.jean.tictactoe.model;

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

    private static Logger logger = Logger.getLogger("com.jean.tictactoe.model.RulesPlayer");

    public RulesPlayer(Mark mark) {
        this.mark = mark;
        this.opponentMark = mark == Mark.X ? Mark.O : Mark.X;
        logger.setLevel(Level.OFF);
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
            move = findCorner(board);
        }

        if (move == null) {
            logger.info("Looking for a side...");
            move = findSide(board);
        }

        if (move != null) {
            board.setMarkAt(move, mark);
        }

        return move;
    }

    private Integer findOppositeCorner(Board board) {
        for (int[] opposites : Board.getOppositeCorners()) {
            if (board.getMarkAt(opposites[0]) == opponentMark && board.getMarkAt(opposites[1]) == null) {
                return opposites[1];
            }
        }

        return null;
    }

    private Integer findCorner(Board board) {
        for (Integer i : Board.getCorners()) {
            Mark mark = board.getMarkAt(i);
            if (mark == null) {
                return i;
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

        return findPossibleWins(possibleBoard, opponentMark).size() >= 2;
    }

    private Integer findSide(Board board) {
        for (Integer i : Board.getSides()) {
            Mark mark = board.getMarkAt(i);
            if (mark == null) {
                return i;
            }
        }

        return null;
    }

    private Integer findCenter(Board board) {
        int center = Board.getCenter();
        if (board.getMarkAt(center) == null) {
            return center;
        }

        return null;
    }

    private Integer findFork(Board board, Mark markToFind) {
        for (Integer i : Board.getCells()) {
            if (board.getMarkAt(i) == null) {
                Board possibleBoard = new Board(board);
                possibleBoard.setMarkAt(i, markToFind);
                List<Integer> possibleWins = findPossibleWins(possibleBoard, markToFind);
                if (possibleWins.size() >= 2) {
                    return i;
                }
            }
        }

        return null;
    }

    private Integer findWin(Board board, Mark markToFind) {
        // find first winning move
        List<Integer> cells = findEmptyCellsInRowWithMark(board, markToFind, 2);

        if (cells.isEmpty()) {
            cells = findEmptyCellsInColumnWithMark(board, markToFind, 2);
        }

        if (cells.isEmpty()) {
            cells = findEmptyCellsInDiagonalWithMark(board, markToFind, 2);
        }

        return cells.isEmpty() ? null : cells.get(0);
    }

    private List<Integer> findPossibleWins(Board board, Mark markToFind) {
        // find all winning moves
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
        return findEmptyCellsInRowWithMark(board, Board.getRows(), markToFind, markCount);
    }

    private List<Integer> findEmptyCellsInColumnWithMark(Board board, Mark markToFind, int markCount) {
        return findEmptyCellsInRowWithMark(board, Board.getColumns(), markToFind, markCount);
    }

    private List<Integer> findEmptyCellsInDiagonalWithMark(Board board, Mark markToFind, int markCount) {
        return findEmptyCellsInRowWithMark(board, Board.getDiagonals(), markToFind, markCount);
    }

    private List<Integer> findEmptyCellsInRowWithMark(Board board, List<int[]> rows, Mark mark, int markCount) {
        // find row with specified count of mark and nothing else, return the "nothing else" spot(s)
        for (int[] row : rows) {
            int rowMarkCount = 0;
            List<Integer> emptyCells = new ArrayList<Integer>();
            for (int i : row) {
                Mark cellMark = board.getMarkAt(i);
                if (cellMark == null) {
                    emptyCells.add(i);
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
