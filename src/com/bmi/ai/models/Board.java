package com.bmi.ai.models;

import com.bmi.ai.helpers.BoardHelper;
import com.bmi.ai.helpers.Directions;

import static com.bmi.ai.helpers.Directions.*;

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

    Difference getDifference(Board board) {
        Difference difference = new Difference();
        char[][] newMatrix = this.matrix;
        char[][] oldMatrix = board.matrix;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (newMatrix[i][j] != oldMatrix[i][j] && newMatrix[i][j] != '0') {
                    difference.value = newMatrix[i][j];
                    difference.row = i;
                    difference.column = j;
                    difference.direction = getDirection(newMatrix[i][j], i, j, oldMatrix);
                    break;
                }
            }
        }
        return difference;
    }

    private Directions getDirection(char c, int x, int y, char[][] matrix) {
        Directions direction;
        if (x + 1 < 3 && matrix[x+1][y] == c) {
            direction = UP;
        } else if (x - 1 >= 0 && matrix[x-1][y] == c) {
            direction = DOWN;
        } else if (y + 1 < 3 && matrix[x][y+1] == c) {
            return LEFT;
        } else {
            return RIGHT;
        }

        return  direction;
    }
}
