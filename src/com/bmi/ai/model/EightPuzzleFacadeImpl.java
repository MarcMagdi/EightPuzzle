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
        List<State> states = getPathFromGoalToInitialState(state);
        Collections.reverse(states);
        return states;
    }

    private List<State> getPathFromGoalToInitialState(State state) {
        Stack<StackDTO> stack = new Stack<>();
        stack.push(new StackDTO(state, 0));
        while (!stack.isEmpty()) {
            StackDTO curr = stack.pop();
            this.maxDepth = Math.max(this.maxDepth, curr.getCost());
            if (BoardHelper.getInstance().isGoalBoard(curr.getState().getBoard())) {
                this.goalCost = curr.getCost();
                List<State> states = new ArrayList<>();
                State currentState = curr.getState();
                while (currentState != null) {
                    states.add(currentState);
                    currentState = currentState.getParent();
                }
                return states;
            }
            List<State> children = curr.getState().getChildren();
            if (children != null) {
                for (State child : curr.getState().getChildren()) {
                    stack.push(new StackDTO(child, curr.getCost() + 1));
                }
            }
        }
        return null;
    }

    class StackDTO {
        private State state;
        private int cost;

        public StackDTO(State state, int cost) {
            this.state = state;
            this.cost = cost;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }
    }
}
