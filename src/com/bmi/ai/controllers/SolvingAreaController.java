package com.bmi.ai.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.bmi.ai.models.Board;
import com.bmi.ai.models.State;
import com.bmi.ai.solvers.BFSPuzzleSolver;
import com.bmi.ai.solvers.PuzzleSolver;
import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * CS 482 : Artificial Intelligence.
 * Assignment 1 : Eight Puzzle Solver
 * Main Application Class
 * @author Marc Magdi
 * Friday 25 October 2018
 */
public class SolvingAreaController implements Initializable {
    public Text box1;
    public Text box2;
    public Text box3;
    public Text box4;
    public Text box5;
    public Text box6;
    public Text box7;
    public Text box8;
    public Text box9;
    private List<Text> boxes;



    @FXML
    private GridPane gridPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.boxes = new ArrayList<>();
        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);
        boxes.add(box4);
        boxes.add(box5);
        boxes.add(box6);
        boxes.add(box7);
        boxes.add(box8);
        boxes.add(box9);

        char matrix[][] = new char[][]{{'1', '2', '5'}, {'3', '4', '0'}, {'6', '7', '8'}};
        Board board = new Board(matrix);
        PuzzleSolver solver = new BFSPuzzleSolver();
        try {
            State s = solver.solve(board);
            showState(s);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }

    public void testMethod(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getButton().name());
    }

    public void showState(State state) {
        char[][] matrix = state.getBoard().getMatrix();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = matrix[i][j];
                if (c == '0') {
                    boxes.get(3*i + j).setText("");
                } else {
                    boxes.get(3*i + j).setText(c + "");
                }
            }
        }
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
