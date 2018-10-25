package com.bmi.ai.controllers;

import javafx.fxml.FXML;
import com.bmi.ai.models.State;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
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
    private final static int TEXT = 1;
    private final static int RECT = 0;
    private List<StackPane> stackPanes;
    public StackPane stack1;
    public StackPane stack2;
    public StackPane stack3;
    public StackPane stack4;
    public StackPane stack5;
    public StackPane stack6;
    public StackPane stack7;
    public StackPane stack8;
    public StackPane stack9;

    private MainController mainController;
    private StackPane selectedCell;

    @FXML
    private GridPane gridPane;

    @FXML
    private ScrollPane scrollPane;

    /**
     * Initializes the reference to the reference to the main controller pane.
     * @param mainController reference to the the parent.
     */
    public final void init(final MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.stackPanes = new ArrayList<>();
        stackPanes.add(stack1);
        stackPanes.add(stack2);
        stackPanes.add(stack3);
        stackPanes.add(stack4);
        stackPanes.add(stack5);
        stackPanes.add(stack6);
        stackPanes.add(stack7);
        stackPanes.add(stack8);
        stackPanes.add(stack9);
    }

    @FXML
    private void cellSelected(MouseEvent event) {
        this.dimAllStackes();
        Node source = (Node)event.getSource();
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        StackPane stackPane = (StackPane) gridPane.getChildren().get(rowIndex*3+colIndex);
        List<Node> children = stackPane.getChildren();
        Rectangle rectangle = (Rectangle) children.get(0);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        rectangle.setFill(Color.DODGERBLUE);
        this.selectedCell = stackPane;
    }

    void updateGridCell(String value) {
        if(selectedCell != null) {
            if (value.equals("Emp") || value.equals("0")) {
                Text text = (Text) selectedCell.getChildren().get(1);
                text.setText("E");
                Rectangle rectangle = (Rectangle) selectedCell.getChildren().get(0);
                rectangle.setFill(Color.WHITE);
            } else {
                Text text = (Text) selectedCell.getChildren().get(1);
                text.setText(value);
            }
        }
    }

    private void dimAllStackes() {
        for (StackPane stackPane : stackPanes) {
            Rectangle rectangle = (Rectangle) stackPane.getChildren().get(0);
            rectangle.setStrokeWidth(0);
            rectangle.setFill(Color.GRAY);
        }
    }

    void resetAllStackes() {
        for (StackPane stackPane : stackPanes) {
            Rectangle rectangle = (Rectangle) stackPane.getChildren().get(0);
            rectangle.setStrokeWidth(1);
            if (((Text) stackPane.getChildren().get(1)).getText().equals("E"))
                rectangle.setFill(Color.WHITE);
            else
                rectangle.setFill(Color.DODGERBLUE);
        }
        selectedCell = null;
    }

    GridPane createPuzzleInstance(State state, int index) {
        GridPane gridPane = new GridPane();
        gridPane.setLayoutY(index*300);
        gridPane.setMaxHeight(250.0);
        gridPane.setMaxWidth(250.0);
        gridPane.setMinWidth(250.0);
        gridPane.setMinHeight(250.0);
        gridPane.setPrefHeight(250.0);
        gridPane.setPrefWidth(250.0);
        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.SOMETIMES);
            column.setMinWidth(10.0);
            column.setPrefWidth(100.0);
            gridPane.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 3; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.SOMETIMES);
            row.setMinHeight(10.0);
            row.setPrefHeight(30.0);
            gridPane.getRowConstraints().add(row);
        }
        char[][] matrix = state.getBoard().getMatrix();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = matrix[i][j];
                StackPane stackPane = new StackPane();
                stackPane.setPrefHeight(150.0);
                stackPane.prefWidth(200.0);
                Rectangle rectangle = new Rectangle();
                rectangle.setArcHeight(5.0);
                rectangle.setArcWidth(5.0);
                rectangle.setFill(Color.DODGERBLUE);
                rectangle.setHeight(80.0);
                rectangle.setWidth(80.0);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeType(StrokeType.INSIDE);
                Text text = new Text();
                text.setStrokeType(StrokeType.OUTSIDE);
                text.setStrokeWidth(0.0);
                Font font = new Font(46.0);
                text.setFont(font);
                if (c == '0') {
                    rectangle.setFill(Color.WHITE);
                    text.setText("");
                } else {
                    rectangle.setFill(Color.DODGERBLUE);
                    text.setText(c + "");
                }
                stackPane.getChildren().addAll(rectangle, text);
                gridPane.add(stackPane, j, i);
            }
        }
        return gridPane;
    }

    char[][] getInitialState() {
        char[][] values = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Text text = (Text)stackPanes.get(i * 3 + j).getChildren().get(TEXT);
                if (text.getText().equals("E"))
                    values[i][j] = '0';
                else if (text.getText().length() == 0)
                    values[i][j] = ' ';
                else
                    values[i][j] = text.getText().charAt(0);
            }
        }
        return values;
    }

    void showPath(List<State> states) {
        AnchorPane anchorPane = new AnchorPane();
        int index = 0;
        for (State state : states) {
            anchorPane.getChildren().add(createPuzzleInstance(state, index++));
        }
        scrollPane.setContent(anchorPane);
        scrollPane.setPannable(true);
    }
}
