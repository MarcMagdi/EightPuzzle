package com.bmi.ai.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * CS 482 : Artificial Intelligence.
 * Assignment 1 : Eight Puzzle Solver
 * Main Application Class
 * @author Marc Magdi
 * Friday 25 October 2018
 */
public class MainController implements Initializable {

    @FXML
    private SolvingAreaController solvingAreaController;

    @FXML
    private NumbersController numbersController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        solvingAreaController.init(this);
        numbersController.init(this);
    }

    void updateValue(String value) {
        this.solvingAreaController.updateGridCell(value);
        this.solvingAreaController.resetAllStackes();
    }
}
