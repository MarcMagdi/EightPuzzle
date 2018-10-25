package com.bmi.ai.models;

import com.bmi.ai.helpers.BoardHelper;
import com.bmi.ai.helpers.Directions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by programajor on 10/18/18.
 */
public class State {
    private int id;
    private Board board;
    private State parent;
    private List<State> children;

    public State(Board board) {
        this.board = board;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public List<State> getChildren() {
        return children;
    }

    public void setChildren(List<State> children) {
        this.children = children;
    }

    public Difference getDifference(State state) {
        return this.board.getDifference(state.board);
    }

    @Override
    public boolean equals(Object obj) {
        return this.getBoard().equals(((State) obj).getBoard());
    }

    @Override
    public int hashCode() {
        return BoardHelper.getInstance().getBoardState(board).hashCode();
    }
}
