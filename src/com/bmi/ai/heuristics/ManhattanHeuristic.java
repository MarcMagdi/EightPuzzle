package com.bmi.ai.heuristics;

import com.bmi.ai.helpers.BoardHelper;
import com.bmi.ai.models.Board;
import com.bmi.ai.models.IndexPair;

/**
 * Created by programajor on 10/18/18.
 */
public class ManhattanHeuristic implements Heuristic {

    private BoardHelper boardHelper;

    public ManhattanHeuristic() {
        this.boardHelper = BoardHelper.getInstance();
    }

    @Override
    public float getStateValue(Board board) {
        float heuristicValue = 0;
        char[][] matrix = board.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                IndexPair goalIndices = boardHelper.getGoalIndices(matrix[i][j]);
                int xDifference = Math.abs(i - goalIndices.getX());
                int yDifference = Math.abs(j - goalIndices.getY());
                heuristicValue += xDifference + yDifference;
            }
        }
        return heuristicValue;
    }
}
