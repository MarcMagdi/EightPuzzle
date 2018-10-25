package com.bmi.ai.model;

import com.bmi.ai.helpers.BoardHelper;
import com.bmi.ai.heuristics.EuclideanHeuristic;
import com.bmi.ai.heuristics.ManhattanHeuristic;
import com.bmi.ai.models.Board;
import com.bmi.ai.models.State;
import com.bmi.ai.solvers.AStarPuzzleSolver;
import com.bmi.ai.solvers.BFSPuzzleSolver;
import com.bmi.ai.solvers.DFSPuzzleSolver;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by programajor on 10/25/18.
 */
public class EightPuzzleFacadeImpl implements EightPuzzleFacade {

    private Map<Integer, State> map;
    private Integer goalCost;

    @Override
    public List<State> solvePuzzleByDFS(Board board) throws InvalidArgumentException {
        State state = new DFSPuzzleSolver().solve(board);
        return processState(state);
    }

    @Override
    public List<State> solvePuzzleByBFS(Board board) throws InvalidArgumentException {
        State state = new BFSPuzzleSolver().solve(board);
        return processState(state);
    }

    @Override
    public List<State> solvePuzzleByAStartManhattan(Board board) throws InvalidArgumentException {
        State state = new AStarPuzzleSolver(new ManhattanHeuristic()).solve(board);
        return processState(state);
    }

    @Override
    public List<State> solvePuzzleByAStartEuclidean(Board board) throws InvalidArgumentException {
        State state = new AStarPuzzleSolver(new EuclideanHeuristic()).solve(board);
        return processState(state);
    }

    private List<State> processState(State state) {
        this.map = new HashMap<>();
        addStateToMap(state, 0);
        List<State> ret = new ArrayList<>();
        ret.add(state);
        return ret;
    }

    private void addStateToMap(State state, Integer cost) {
        map.put(state.getId(), state);
        if (BoardHelper.getInstance().isGoalBoard(state.getBoard())) {
            this.goalCost = cost;
        }
        for (State child : state.getChildren()) {
            addStateToMap(child, cost + 1);
        }
    }

    @Override
    public List<State> getParentLevelStates(int stateId) {
        State state = map.get(stateId);
        if (state.getParent() == null) {
            return new ArrayList<>();
        }
        if (state.getParent().getParent() == null) {
            List<State> parents = new ArrayList<>();
            parents.add(state.getParent());
            return parents;
        }
        List<State> parents = new ArrayList<>();
        parents.addAll(state.getParent().getParent().getChildren());
        return parents;
    }

    @Override
    public List<State> getDirectChildStates(int stateId) {
        return map.get(stateId).getChildren();
    }

    @Override
    public Integer getGoalCost() {
        return goalCost;
    }
}
