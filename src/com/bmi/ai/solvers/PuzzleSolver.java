package com.bmi.ai.solvers;

import com.bmi.ai.models.Board;
import com.bmi.ai.models.State;
import com.bmi.ai.models.Statistics;
import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by programajor on 10/16/18.
 */
public interface PuzzleSolver {
    Statistics solve(Board board);
}
