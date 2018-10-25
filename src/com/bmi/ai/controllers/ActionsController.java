package com.bmi.ai.controllers;

import com.bmi.ai.model.EightPuzzleFacade;
import com.bmi.ai.model.EightPuzzleFacadeImpl;
import com.bmi.ai.models.Board;
import com.bmi.ai.models.Statistics;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * CS 482 : Artificial Intelligence.
 * Assignment 1 : Eight Puzzle Solver
 * Main Application Class
 * @author Marc Magdi
 * Friday 25 October 2018
 */
public class ActionsController implements Initializable {

    private MainController mainController;
    private EightPuzzleFacade puzzleFacade;

    @FXML
    public Label errorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.puzzleFacade = new EightPuzzleFacadeImpl();
    }

    void init(MainController mainController) {
        this.mainController = mainController;
    }

    public void solveByBFS(MouseEvent mouseEvent) {
        this.mainController.reset();
        char[][] initState = this.mainController.getInitialState();
        boolean validState = isValid(initState);
        boolean solvable = isSolvable(initState);
        if (validState && solvable) {
            errorLabel.setText("");
            Statistics statistics = puzzleFacade.solvePuzzleByBFS(new Board(initState));
            this.mainController.showPath(statistics);
        } else if (!validState) {
            errorLabel.setText("Invalid Input");
        } else {
            errorLabel.setText("This puzzle is not solvable.");
        }
    }

    public void solveByDFS(MouseEvent mouseEvent) {
        this.mainController.reset();
        char[][] initState = this.mainController.getInitialState();
        boolean validState = isValid(initState);
        boolean solvable = isSolvable(initState);
        if (validState && solvable) {
            errorLabel.setText("");
            Statistics statistics = puzzleFacade.solvePuzzleByDFS(new Board(initState));
            this.mainController.showPath(statistics);
        } else if (!validState) {
            errorLabel.setText("Invalid Input");
        } else {
            errorLabel.setText("This puzzle is not solvable.");
        }
    }

    public void solveByAStarManhattan(MouseEvent mouseEvent) {
        this.mainController.reset();
        char[][] initState = this.mainController.getInitialState();
        boolean validState = isValid(initState);
        boolean solvable = isSolvable(initState);
        if (validState && solvable) {
            errorLabel.setText("");
            Statistics statistics = puzzleFacade.solvePuzzleByAStartManhattan(new Board(initState));
            this.mainController.showPath(statistics);
        } else if (!validState) {
            errorLabel.setText("Invalid Input");
        } else {
            errorLabel.setText("This puzzle is not solvable.");
        }
    }

    public void solveByAStarEuclidean(MouseEvent mouseEvent) {
        this.mainController.reset();
        char[][] initState = this.mainController.getInitialState();
        boolean validState = isValid(initState);
        boolean solvable = isSolvable(initState);
        if (validState && solvable) {
            errorLabel.setText("");
            Statistics statistics = puzzleFacade.solvePuzzleByAStartEuclidean(new Board(initState));
            this.mainController.showPath(statistics);
        } else if (!validState) {
            errorLabel.setText("Invalid Input");
        } else {
            errorLabel.setText("This puzzle is not solvable.");
        }
    }

    private boolean isValid(char[][] initState) {
        Set<Character> digits = new HashSet<Character>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                digits.add(initState[i][j]);
            }
        }
        return digits.size() == 9 && !digits.contains(' ');
    }

    private boolean isSolvable(char[][] initState) {
        int inv_count = 0;
        int[] arr = getArr(initState);
        for (int i = 0; i < 9 - 1; i++)
            for (int j = i+1; j < 9; j++)
                // Value 0 is used for empty space
                if (arr[j] != 0 && arr[i] != 0 &&  arr[i] > arr[j])
                    inv_count++;
        return inv_count%2 == 0;
    }

    private int[] getArr(char[][] state) {
        int arr[] = new int[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[3*i + j] = state[i][j] - '0';
            }
        }

        return arr;
    }
}
