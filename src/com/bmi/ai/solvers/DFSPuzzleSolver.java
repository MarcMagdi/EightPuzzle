package com.bmi.ai.solvers;

import com.bmi.ai.helpers.BoardHelper;
import com.bmi.ai.models.Board;
import com.bmi.ai.models.State;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by programajor on 10/16/18.
 */
public class DFSPuzzleSolver implements PuzzleSolver {

    private BoardHelper boardHelper;

    public DFSPuzzleSolver() {
        this.boardHelper = BoardHelper.getInstance();
    }

    @Override
    public void solve(Board board) throws InvalidArgumentException {
        Stack<State> frontier = new Stack<>();
        Set<State> explored = new HashSet<>();
        frontier.push(new State(board));
        while (!frontier.isEmpty()) {
            State curr = frontier.pop();
            explored.add(curr);
            boardHelper.printState(curr);
            if (boardHelper.isGoalBoard(curr.getBoard())) {
                return;
            }
            List<Board> neighbours = boardHelper.getNeighbouringStates(curr.getBoard());
            for (Board neighbour : neighbours) {
                State child = new State(neighbour);
                if (!frontier.contains(child) && !explored.contains(child)) {
                    child.setParent(curr);
                    curr.getChildren().add(child);
                    frontier.push(child);
                }
            }
        }
    }
}
