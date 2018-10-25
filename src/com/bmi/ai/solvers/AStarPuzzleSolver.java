package com.bmi.ai.solvers;

import com.bmi.ai.helpers.BoardHelper;
import com.bmi.ai.models.Board;
import com.bmi.ai.heuristics.Heuristic;
import com.bmi.ai.models.HeuristicState;
import com.bmi.ai.models.State;
import com.bmi.ai.models.Statistics;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by programajor on 10/18/18.
 */
public class AStarPuzzleSolver implements PuzzleSolver {
    private int counter;
    private Heuristic heuristic;
    private BoardHelper boardHelper;

    public AStarPuzzleSolver(Heuristic heuristic) {
        this.counter = 0;
        this.heuristic = heuristic;
        this.boardHelper = BoardHelper.getInstance();
    }

    @Override
    public Statistics solve(Board board) {
        Statistics statistics = new Statistics();
        Instant start = Instant.now();
        PriorityQueue<HeuristicState> frontier = new PriorityQueue<>();
        Set<State> frontierSet = new HashSet<>();
        Set<State> explored = new HashSet<>();
        HeuristicState initial = new HeuristicState(board,  heuristic.getStateValue(board), 0);
        initial.setId(counter++);
        frontier.add(initial);
        frontierSet.add(initial);
        while (!frontier.isEmpty()) {
            HeuristicState curr = frontier.poll();
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
                HeuristicState child = new HeuristicState(neighbour,
                                                heuristic.getStateValue(neighbour),
                                                curr.getActualCost() + 1);
                if (!frontierSet.contains(child) && !explored.contains(child)) {
                    child.setId(counter++);
                    child.setParent(curr);
                    curr.getChildren().add(child);
                    frontier.add(child);
                    frontierSet.add(child);
                } else {
                    HeuristicState childInFrontier = getStateFromFrontiers(frontier, child);
                    if (childInFrontier != null && childInFrontier.getActualCost() > child.getActualCost()) {
                        childInFrontier.getParent().getChildren().remove(childInFrontier);
                        childInFrontier.setParent(curr);
                        childInFrontier.setActualCost(child.getActualCost());
                        curr.getChildren().add(childInFrontier);
                    }
                }
            }
        }
        return null;
    }

    private HeuristicState getStateFromFrontiers(PriorityQueue<HeuristicState> frontiers,
                                                 HeuristicState state) {
        for (HeuristicState heuristicState : frontiers) {
            if (heuristicState.equals(state)) {
                return heuristicState;
            }
        }
        return null;
    }
}
