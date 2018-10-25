package com.bmi.ai.solvers;

import com.bmi.ai.helpers.BoardHelper;
import com.bmi.ai.models.Board;
import com.bmi.ai.models.State;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.*;

/**
 * Created by programajor on 10/16/18.
 */
public class BFSPuzzleSolver implements PuzzleSolver {

    private BoardHelper boardHelper;

    public BFSPuzzleSolver() {
        this.boardHelper = BoardHelper.getInstance();
    }

    @Override
    public void solve(Board board) throws InvalidArgumentException {
        Queue<State> frontier = new LinkedList<>();
        Set<State> explored = new HashSet<>();
        frontier.add(new State(board));
        while (!frontier.isEmpty()) {
            State curr = frontier.poll();
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
                    frontier.add(child);
                }
            }
        }
    }
}
