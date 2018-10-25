package com.bmi.ai.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

    @FXML
    public Label errorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void init(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void solve(MouseEvent event) {
        char[][] initState = this.mainController.getInitialState();
        boolean validState = isValid(initState);
        if (validState) {
            errorLabel.setText("");
        } else {
            errorLabel.setText("Invalid Input");
        }
    }

    private boolean isValid(char[][] initState) {
        Set<Character> digits = new HashSet<Character>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                digits.add(initState[i][j]);
            }
        }
        return digits.size() == 9;
    }
}
