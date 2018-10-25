package com.bmi.ai.models;

/**
 * Created by programajor on 10/18/18.
 */
public class HeuristicState extends State implements Comparable<HeuristicState> {

    private float heuristicValue;
    private int actualCost;

    public HeuristicState(Board board, float heuristicValue, int actualCost) {
        super(board);
        this.heuristicValue = heuristicValue;
        this.actualCost = actualCost;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public float getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(float heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public int getActualCost() {
        return actualCost;
    }

    public void setActualCost(int actualCost) {
        this.actualCost = actualCost;
    }

    private Float getComparingValue() {
        return actualCost + heuristicValue;
    }

    @Override
    public int compareTo(HeuristicState state) {
        return getComparingValue().compareTo(state.getComparingValue());
    }
}
