package com.bmi.ai.models;

import java.util.List;

/**
 * Created by programajor on 10/25/18.
 */
public class Statistics {
    private State initalState;
    private List<State> states;
    private int costOfPath;
    private int nodesExpanded;
    private int searchDepth;
    private long runningTime;

    public State getInitalState() {
        return initalState;
    }

    public void setInitalState(State initalState) {
        this.initalState = initalState;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public int getCostOfPath() {
        return costOfPath;
    }

    public void setCostOfPath(int costOfPath) {
        this.costOfPath = costOfPath;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
    }

    public void setNodesExpanded(int nodesExpanded) {
        this.nodesExpanded = nodesExpanded;
    }

    public int getSearchDepth() {
        return searchDepth;
    }

    public void setSearchDepth(int searchDepth) {
        this.searchDepth = searchDepth;
    }

    public long getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(long runningTime) {
        this.runningTime = runningTime;
    }
}
