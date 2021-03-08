package com.example.moduleb.gameoflife;

import java.util.Arrays;

public class GameOfLife {

    public boolean[][] advance(boolean[][] grid) {
        boolean[][] next = copy(grid);

        for (int row = 0; row != grid.length;   row++) {
            for (int col = 0; col != grid[row].length; col++) {
                int count = neighbourCount(row, col, grid);
                if (count > 3 || count < 2) {
                    next[row][col] = false;
                } else if (count == 3) {
                    next[row][col] = true;
                }
            }
        }

        return next;
    }

    private boolean[][] copy(boolean[][] grid) {
        boolean next[][] = new boolean[grid.length][grid[0].length];
        for (int row = 0; row != grid.length; row++) {
            next[row] = Arrays.copyOf(grid[row], grid[row].length);
        }
        return next;
    }

    private int neighbourCount(int row, int col, boolean[][] grid) {
        return countCell(row, col + 1, grid)
                + countCell(row, col - 1, grid)
                + countCell(row + 1, col, grid)
                + countCell(row + 1, col - 1, grid)
                + countCell(row + 1, col + 1, grid)
                + countCell(row - 1, col, grid)
                + countCell(row - 1, col - 1, grid)
                + countCell(row - 1, col + 1, grid);
    }

    private int countCell(int row, int col, boolean[][] grid) {
        if (isLive(row, col, grid)) {
            return 1;
        }
        return 0;
    }

    private boolean isLive(int row, int col, boolean[][] grid) {
        if (row < 0 || col < 0 || row == grid.length || col == grid[0].length) {
            return false;
        }
        return grid[row][col];
    }

}
