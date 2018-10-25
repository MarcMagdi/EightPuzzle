package com.bmi.ai.solvers;

import com.bmi.ai.models.Board;
import com.bmi.ai.models.State;
import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by programajor on 10/16/18.
 */
public interface PuzzleSolver {
    State solve(Board board);
}
