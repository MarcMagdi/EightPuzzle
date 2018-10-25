package com.bmi.ai.model;

import com.bmi.ai.models.Board;
import com.bmi.ai.models.State;
import com.bmi.ai.models.Statistics;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;

/**
 * Created by programajor on 10/25/18.
 */
public interface EightPuzzleFacade {
    Statistics solvePuzzleByDFS(Board board);
    Statistics solvePuzzleByBFS(Board board);
    Statistics solvePuzzleByAStartManhattan(Board board);
    Statistics solvePuzzleByAStartEuclidean(Board board);
    List<State> getParentLevelStates(int stateId);
    List<State> getDirectChildStates(int stateId);

    Integer getGoalCost();
}
