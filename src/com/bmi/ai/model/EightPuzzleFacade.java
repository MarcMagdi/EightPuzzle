package com.bmi.ai.model;

import com.bmi.ai.models.Board;
import com.bmi.ai.models.State;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;

/**
 * Created by programajor on 10/25/18.
 */
public interface EightPuzzleFacade {
    List<State> solvePuzzleByDFS(Board board) throws InvalidArgumentException;
    List<State> solvePuzzleByBFS(Board board) throws InvalidArgumentException;
    List<State> solvePuzzleByAStartManhattan(Board board) throws InvalidArgumentException;
    List<State> solvePuzzleByAStartEuclidean(Board board) throws InvalidArgumentException;
    List<State> getParentLevelStates(int stateId);
    List<State> getDirectChildStates(int stateId);

    Integer getGoalCost();
}
