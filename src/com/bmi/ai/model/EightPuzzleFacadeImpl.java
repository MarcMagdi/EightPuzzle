package com.bmi.ai.model;

import com.bmi.ai.helpers.BoardHelper;
import com.bmi.ai.heuristics.EuclideanHeuristic;
import com.bmi.ai.heuristics.ManhattanHeuristic;
import com.bmi.ai.models.Board;
import com.bmi.ai.models.State;
import com.bmi.ai.models.Statistics;
import com.bmi.ai.solvers.AStarPuzzleSolver;
import com.bmi.ai.solvers.BFSPuzzleSolver;
import com.bmi.ai.solvers.DFSPuzzleSolver;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * Created by programajor on 10/25/18.
 */
public class EightPuzzleFacadeImpl implements EightPuzzleFacade {
    private Integer goalCost;
    private Integer maxDepth;

    @Override
    public Statistics solvePuzzleByDFS(Board board) {
        this.goalCost = 0;
        this.maxDepth = 0;
        Statistics statistics = new DFSPuzzleSolver().solve(board);
        State state = statistics.getInitalState();
        statistics.setStates(processState(state));
        statistics.setCostOfPath(this.goalCost);
        statistics.setSearchDepth(this.maxDepth);
        return statistics;
    }

    @Override
    public Statistics solvePuzzleByBFS(Board board) {
        this.goalCost = 0;
        this.maxDepth = 0;
        Statistics statistics = new BFSPuzzleSolver().solve(board);
        State state = statistics.getInitalState();
        statistics.setStates(processState(state));
        statistics.setCostOfPath(this.goalCost);
        statistics.setSearchDepth(this.maxDepth);
        return statistics;
    }

    @Override
    public Statistics solvePuzzleByAStartManhattan(Board board) {
        this.goalCost = 0;
        this.maxDepth = 0;
        Statistics statistics = new AStarPuzzleSolver(new ManhattanHeuristic()).solve(board);
        State state = statistics.getInitalState();
        statistics.setStates(processState(state));
        statistics.setCostOfPath(this.goalCost);
        statistics.setSearchDepth(this.maxDepth);
        return statistics;
    }

    @Override
    public Statistics solvePuzzleByAStartEuclidean(Board board) {
        this.goalCost = 0;
        this.maxDepth = 0;
        Statistics statistics = new AStarPuzzleSolver(new EuclideanHeuristic()).solve(board);
        State state = statistics.getInitalState();
        statistics.setStates(processState(state));
        statistics.setCostOfPath(this.goalCost);
        statistics.setSearchDepth(this.maxDepth);
        return statistics;
    }

    private List<State> processState(State state) {
        List<State> states = addStateToMap(state, 0);
        Collections.reverse(states);
        return states;
    }

    private List<State> addStateToMap(State state, Integer cost) {
        List<State> states = null;
        this.maxDepth = Math.max(this.maxDepth, cost);
        if (BoardHelper.getInstance().isGoalBoard(state.getBoard())) {
            this.goalCost = cost;
            State curr = state;
            states = new ArrayList<>();
            while (curr != null) {
                states.add(curr);
                curr = curr.getParent();
            }
        }
        for (State child : state.getChildren()) {
            BoardHelper boardHelper = BoardHelper.getInstance();
            System.out.println(boardHelper.getBoardState(child.getBoard()) + " " + cost);
            List<State> childStates = addStateToMap(child, cost + 1);
            if (childStates != null) {
                states = childStates;
            }
        }
        return states;
    }
}
