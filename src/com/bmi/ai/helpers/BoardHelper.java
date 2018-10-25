package com.bmi.ai.helpers;

import com.bmi.ai.models.Board;
import com.bmi.ai.models.IndexPair;
import com.bmi.ai.models.State;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by programajor on 10/16/18.
 */
public class BoardHelper {
    private static BoardHelper instance;
    private static final String GOAL_STATE = "012345678";
    private int directions[][] = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private BoardHelper() {

    }

    public static BoardHelper getInstance() {
        if (instance == null) {
            instance = new BoardHelper();
        }
        return instance;
    }

    public String getBoardState(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] row : board.getMatrix()) {
            stringBuilder.append(row);
        }
        return stringBuilder.toString();
    }

    public void printState(State state) {
        Board board = state.getBoard();
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] row : board.getMatrix()) {
            stringBuilder.append(row).append('\n');
        }
        stringBuilder.append("===");
        System.out.println(stringBuilder.toString());
    }

    public List<Board> getNeighbouringStates(Board board) {
        List<Board> nextStates = new ArrayList<>();
        IndexPair indices = getZeroIndices(board);
        for (int[] dir : directions) {
            if (isValidIndex(indices.getX() + dir[0], indices.getY() + dir[1])) {
                nextStates.add(getBoardAfterOneMove(board,
                        new IndexPair(indices.getX() + dir[0], indices.getY() + dir[1])));
            }
        }
        return nextStates;
    }

    private IndexPair getZeroIndices(Board board) {
        char[][] matrix = board.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '0') {
                    return new IndexPair(i, j);
                }
            }
        }
        return null;
    }

    private boolean isValidIndex(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    private Board getBoardAfterOneMove(Board board, IndexPair moveIndex) {
        char[][] boardMatrix = board.getMatrix();
        char[][] matrix = new char[boardMatrix.length][];
        for (int i = 0; i < boardMatrix.length; i++) {
            matrix[i] = new char[boardMatrix[i].length];
            for (int j = 0; j < boardMatrix[i].length; j++) {
                if (i == moveIndex.getX() && j == moveIndex.getY()) {
                    matrix[i][j] = '0';
                } else if (boardMatrix[i][j] == '0') {
                    matrix[i][j] = boardMatrix[moveIndex.getX()][moveIndex.getY()];
                } else {
                    matrix[i][j] = boardMatrix[i][j];
                }
            }
        }
        return new Board(matrix);
    }

    public boolean isGoalBoard(Board board) {
        return getBoardState(board).equals(GOAL_STATE);
    }

    public IndexPair getGoalIndices(char c) {
        int number = c - '0';
        int y = (number % 3);
        int x = (number / 3);
        return new IndexPair(x, y);
    }
}
