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
    private int counter;
    private BoardHelper boardHelper;

    public DFSPuzzleSolver() {
        this.counter = 0;
        this.boardHelper = BoardHelper.getInstance();
    }

    @Override
    public State solve(Board board) {
        Stack<State> frontier = new Stack<>();
        Set<State> frontierSet = new HashSet<>();
        Set<State> explored = new HashSet<>();
        State initial = new State(board);
        initial.setId(counter++);
        frontier.push(initial);
        frontierSet.add(initial);
        while (!frontier.isEmpty()) {
            State curr = frontier.pop();
            frontierSet.remove(curr);
            explored.add(curr);
//            boardHelper.printState(curr);
            if (boardHelper.isGoalBoard(curr.getBoard())) {
                return initial;
            }
            List<Board> neighbours = boardHelper.getNeighbouringStates(curr.getBoard());
            for (Board neighbour : neighbours) {
                State child = new State(neighbour);
                if (!frontierSet.contains(child) && !explored.contains(child)) {
                    child.setId(counter++);
                    child.setParent(curr);
                    curr.getChildren().add(child);
                    frontier.push(child);
                    frontierSet.add(child);
                }
            }
        }
        return initial;
    }
}
