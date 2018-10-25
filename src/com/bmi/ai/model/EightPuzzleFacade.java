package com.bmi.ai.model;

import com.bmi.ai.models.Board;
import com.bmi.ai.models.State;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;

/**
 * Created by programajor on 10/25/18.
 */
public interface EightPuzzleFacade {
    List<State> solvePuzzleByDFS(Board board);
    List<State> solvePuzzleByBFS(Board board);
    List<State> solvePuzzleByAStartManhattan(Board board);
    List<State> solvePuzzleByAStartEuclidean(Board board);
    List<State> getParentLevelStates(int stateId);
    List<State> getDirectChildStates(int stateId);

    Integer getGoalCost();
}
