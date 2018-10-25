package com.bmi.ai.helpers;

/**
 * CS 482 : Artificial Intelligence.
 * Assignment 1 : Eight Puzzle Solver
 * Main Application Class
 * @author Marc Magdi
 * Friday 25 October 2018
 */
public enum Directions {
    UP, DOWN, LEFT, RIGHT;

    public String toString() {
        switch (this) {
            case UP: return "⬆";
            case DOWN: return "⬇";
            case LEFT: return "⬅";
            case RIGHT: return "➡";
        }

        return "";
    }
}
