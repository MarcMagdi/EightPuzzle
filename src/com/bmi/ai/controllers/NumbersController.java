package com.bmi.ai.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class NumbersController implements Initializable {
    private MainController mainController;

    /**
     * Initializes the reference to the reference to the main controller pane.
     * @param mainController reference to the the parent.
     */
    public final void init(final MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void updateValue(MouseEvent event) {
        Node source = (Node)event.getSource() ;
        this.mainController.updateValue(((Button)source).getText());
    }
}
