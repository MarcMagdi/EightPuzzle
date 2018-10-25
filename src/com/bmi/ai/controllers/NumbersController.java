package com.bmi.ai.controllers;

import com.bmi.ai.models.Statistics;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class NumbersController implements Initializable {
    public Text pathCost;
    public Text nodesExpanded;
    public Text searchDepth;
    public Text runningTime;
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

    public void reset(MouseEvent mouseEvent) {
        this.mainController.reset();
    }

    public void showStatistics(Statistics statistics) {
        pathCost.setText("Path Cost: " + statistics.getCostOfPath());
        nodesExpanded.setText("Nodes Expanded: " + statistics.getNodesExpanded());
        searchDepth.setText("Search Depth: " + statistics.getSearchDepth());
        runningTime.setText("Running Time: " + statistics.getRunningTime() + " ms");
    }

    public void resetStatistics() {
        pathCost.setText("");
        nodesExpanded.setText("");
        searchDepth.setText("");
        runningTime.setText("");
    }
}
