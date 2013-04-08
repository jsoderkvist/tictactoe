package com.jean.tictactoe.rule;

import com.jean.tictactoe.Board;
import com.jean.tictactoe.Cell;
import com.jean.tictactoe.Mark;

import java.util.List;

/**
 * Finds a move that sets up a win for specified mark to block fork for specified opponent mark
 */
public class FindMoveToSetupWin extends Rule {
    private Mark mark;
    private Mark opponentMark;

    public FindMoveToSetupWin(Mark mark, Mark opponentMark) {
        this.mark = mark;
        this.opponentMark = opponentMark;
    }

    public Integer findMove(Board board) {
        Integer move = findFork(board, opponentMark);
        if (move != null) {
            move = findMoveToSetupWin(board);
        }

        return move;
    }

    private Integer findMoveToSetupWin(Board board) {
        // find row with one of my mark and nothing else
        for (Cell[] row : board.getAllRows()) {
            List<Integer> possibleMoves = findEmptyCellsInRowWithMark(row, mark, 1);
            if (!possibleMoves.isEmpty()) {
                // choose the cell that doesn't force opponent into a fork
                if (!isForcedOpponentFork(board, possibleMoves.get(0), possibleMoves.get(1))) {
                    return possibleMoves.get(0);
                } else {
                    return possibleMoves.get(1);
                }
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

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " for " + mark + " against " + opponentMark;
    }
}
