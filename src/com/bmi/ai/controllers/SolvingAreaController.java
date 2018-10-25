package com.bmi.ai.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * CS 482 : Artificial Intelligence.
 * Assignment 1 : Eight Puzzle Solver
 * Main Application Class
 * @author Marc Magdi
 * Friday 25 October 2018
 */
public class SolvingAreaController implements Initializable {


    @FXML
    private GridPane gridPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridPane.add(new Label("test"), 2, 2, 1, 1);
    }

    @FXML
    private void clickGrid(MouseEvent event) {
        Node source = (Node)event.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);

        System.out.println(colIndex + ' ' + rowIndex);
    }
}
