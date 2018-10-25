package com.bmi.ai.heuristics;

import com.bmi.ai.models.Board;

/**
 * Created by programajor on 10/18/18.
 */
public interface Heuristic {
    float getStateValue(Board board);
}
