package com.jean.tictactoe.rule;

import com.jean.tictactoe.Board;
import com.jean.tictactoe.Mark;
import com.jean.tictactoe.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Rules-based Player based on http://en.wikipedia.org/wiki/Tic-tac-toe
 */
public class RulesPlayer implements Player {
    private Mark mark;
    private Mark opponentMark;
    private List<Rule> rules;

    private static Logger logger = Logger.getLogger("com.jean.tictactoe.rule.RulesPlayer");

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
        initRules();
    }

    public Mark getMark() {
        return mark;
    }

    public boolean hasAI() {
        return true;
    }

    public Integer makeNextMove(Board board) {
        if (mark == null) {
            throw new IllegalStateException("Player mark must be set");
        }

        logger.info(board.toString());

        Integer move = null;
        for (Rule rule : rules) {
            move = rule.findMove(board);
            if (move != null) {
                logger.info(rule.toString());
                board.setMarkAt(move, mark);
                break;
            }
        }

        return move;
    }

    private void initRules() {
        rules = new ArrayList<Rule>();
        rules.add(new FindWin(mark));
        rules.add(new FindWin(opponentMark));
        rules.add(new FindFork(mark));
        rules.add(new FindMoveToSetupWin(mark, opponentMark));
        rules.add(new FindCenter());
        rules.add(new FindOppositeCorner(opponentMark));
        rules.add(new FindCorner());
        rules.add(new FindSide());
    }
}
