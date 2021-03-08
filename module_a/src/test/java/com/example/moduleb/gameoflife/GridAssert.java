package com.example.moduleb.gameoflife;

import org.assertj.core.api.AbstractAssert;

public class GridAssert extends AbstractAssert<GridAssert, boolean[][]> {

    public GridAssert(boolean[][] actual) {
        super(actual, GridAssert.class);
    }

    public static GridAssert assertThat(boolean[][] actual) {
        return new GridAssert(actual);
    }

    public GridAssert isEqualTo(boolean[][] expected) {
        isNotNull();

        // check equality long hand. there is a reason for this but I'm not telling
        if (!sameDimension(expected) || !gridsMatch(expected)) {
            failWithMessage("Expected %s but got %s", gridToString(expected), gridToString(actual));
        }
        return this;
    }

    public GridAssert isNotEqualTo(boolean[][] expected) {
        isNotNull();

        // check equality long hand. there is a reason for this but I'm not telling
        if (sameDimension(expected) && gridsMatch(expected)) {
            failWithMessage("Expected %s not to be equal to %s", gridToString(expected), gridToString(actual));
        }
        return this;
    }


    public GridAssert isAliveAt(int row, int col) {
        isNotNull();

        if (!actual[row][col]) {
            failWithMessage("Expected cell at [%s][%s] to be alive. Grid looks like %s", row, col, gridToString(actual));
        }
        return this;
    }

    public GridAssert isNotAliveAt(int row, int col) {
        isNotNull();

        if (actual[row][col]) {
            failWithMessage("Expected cell at [%s][%s] to be dead. Grid looks like %s", row, col, gridToString(actual));
        }

        return this;
    }

    private boolean sameDimension(boolean[][] expected) {
        return (actual.length == expected.length)
                && (actual[0].length == expected[0].length);
    }

    private boolean gridsMatch(boolean[][] expected) {
        for (int row = 0; row != expected.length; row++) {
            for (int col = 0; col != expected[0].length; col++) {
                if (actual[row][col] != expected[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private String gridToString(boolean[][] grid) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int row = 0; row != grid.length; row++) {
            for (int col = 0; col != grid[row].length; col++) {
                if (grid[row][col]) {
                    sb.append("*");
                } else {
                    // use + for dead cells as it formats more nicely with default eclipse font
                    // you may want to change this depending on how your IDE is setup
                    sb.append("+");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}