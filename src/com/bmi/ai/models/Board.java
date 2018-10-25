package com.bmi.ai.models;

import com.bmi.ai.helpers.BoardHelper;

/**
 * Created by programajor on 10/16/18.
 */
public class Board {

    private char matrix[][];

    public Board(char[][] matrix) {
        this.matrix = matrix;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean equals(Object obj) {
        String first = BoardHelper.getInstance().getBoardState(this);
        String second = BoardHelper.getInstance().getBoardState((Board) obj);
        return first.equals(second);
    }
}
