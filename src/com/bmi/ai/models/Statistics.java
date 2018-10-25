package com.bmi.ai.models;

/**
 * Created by programajor on 10/25/18.
 */
public class Statistics {
    private State initalState;
    private int costOfPath;
    private int nodesExpaned;
    private int searchDepth;
    private float runningTime;

    public State getInitalState() {
        return initalState;
    }

    public void setInitalState(State initalState) {
        this.initalState = initalState;
    }

    public int getCostOfPath() {
        return costOfPath;
    }

    public void setCostOfPath(int costOfPath) {
        this.costOfPath = costOfPath;
    }

    public int getNodesExpaned() {
        return nodesExpaned;
    }

    public void setNodesExpaned(int nodesExpaned) {
        this.nodesExpaned = nodesExpaned;
    }

    public int getSearchDepth() {
        return searchDepth;
    }

    public void setSearchDepth(int searchDepth) {
        this.searchDepth = searchDepth;
    }

    public float getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(float runningTime) {
        this.runningTime = runningTime;
    }
}
