package com.bmi.ai.solvers;

import com.bmi.ai.helpers.BoardHelper;
import com.bmi.ai.models.Board;
import com.bmi.ai.models.State;
import com.bmi.ai.models.Statistics;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * Created by programajor on 10/16/18.
 */
public class BFSPuzzleSolver implements PuzzleSolver {
    private int counter;
    private BoardHelper boardHelper;

    public BFSPuzzleSolver() {
        this.counter = 0;
        this.boardHelper = BoardHelper.getInstance();
    }

    @Override
    public Statistics solve(Board board) {
        Statistics statistics = new Statistics();
        Instant start = Instant.now();
        Queue<State> frontier = new LinkedList<>();
        Set<State> frontierSet = new HashSet<>();
        Set<State> explored = new HashSet<>();
        State initial = new State(board);
        initial.setId(counter++);
        frontier.add(initial);
        frontierSet.add(initial);
        while (!frontier.isEmpty()) {
            State curr = frontier.poll();
            frontierSet.remove(curr);
            explored.add(curr);
            if (boardHelper.isGoalBoard(curr.getBoard())) {
                Instant end = Instant.now();
                long timeElapsed = Duration.between(start, end).toMillis();
                statistics.setRunningTime(timeElapsed);
                statistics.setInitalState(initial);
                statistics.setNodesExpanded(explored.size());
                return statistics;
            }
            List<Board> neighbours = boardHelper.getNeighbouringStates(curr.getBoard());
            for (Board neighbour : neighbours) {
                State child = new State(neighbour);
                if (!frontierSet.contains(child) && !explored.contains(child)) {
                    child.setId(counter++);
                    child.setParent(curr);
                    curr.getChildren().add(child);
                    frontier.add(child);
                    frontierSet.add(child);
                }
            }
        }
        return null;
    }
}
