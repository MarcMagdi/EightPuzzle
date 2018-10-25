package com.bmi.ai.controllers;

import com.bmi.ai.models.State;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * CS 482 : Artificial Intelligence.
 * Assignment 1 : Eight Puzzle Solver
 * Main Application Class
 * @author Marc Magdi
 * Friday 25 October 2018
 */
public class MainController implements Initializable {

    @FXML private BorderPane borderPane;

    @FXML
    private SolvingAreaController solvingAreaController;

    @FXML
    private NumbersController numbersController;

    @FXML
    private ActionsController actionsController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        borderPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getText().length() == 1
                        && Character.isDigit(event.getText().charAt(0))
                        && event.getText().charAt(0) != '9') {
                    updateValue(event.getText());
                }
            }
        });
        solvingAreaController.init(this);
        numbersController.init(this);
        actionsController.init(this);
    }

    void updateValue(String value) {
        this.solvingAreaController.updateGridCell(value);
//        this.solvingAreaController.resetAllStackes();
    }

    char[][] getInitialState() {
        return this.solvingAreaController.getInitialState();
    }

    void showPath(List<State> states) {
        this.solvingAreaController.showPath(states);
    }
}
