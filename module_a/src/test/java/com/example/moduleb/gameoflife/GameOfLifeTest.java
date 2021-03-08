package com.example.moduleb.gameoflife;

import org.junit.jupiter.api.Test;

import static com.example.moduleb.gameoflife.GridAssert.assertThat;


public class GameOfLifeTest {

    GameOfLife testee = new GameOfLife();

    static boolean[][] grid(String... rows) {
        boolean[][] grid = new boolean[rows.length][rows[0].length()];
        for (int row = 0; row != rows.length; row++) {
            grid[row] = stringToRow(rows[row]);
        }
        return grid;
    }

    private static boolean[] stringToRow(String row) {
        boolean[] bs = new boolean[row.length()];
        for (int i = 0; i != row.length(); i++) {
            if (row.charAt(i) == '*') {
                bs[i] = true;
            }
        }
        return bs;
    }

    @Test
    public void shouldProduceEmptyGridWhenGivenEmptyGrid() {
        boolean[][] actual = testee.advance(empty8By4Grid());
        assertThat(actual).isEqualTo(empty8By4Grid());
    }

    @Test
    public void liveTopLeftCornerCellWithNoNeighboursShouldDie() {
        boolean[][] lastGen =
                grid("*.......",
                        "........",
                        "........",
                        "........");

        boolean[][] expected = empty8By4Grid();
        assertThat(testee.advance(lastGen)).isEqualTo(expected);
    }

    @Test
    public void liveTopLeftCornerCellWithOneNeighbourShouldDie() {
        boolean[][] lastGen =
                grid("**......",
                        "........",
                        "........",
                        "........");

        assertLiveCellKilled(0, 0, lastGen);
    }

    @Test
    public void liveTopLeftCornerCellWithTwoNeighboursShouldNotDie() {
        boolean[][] lastGen =
                grid("**......",
                        "*.......",
                        "........",
                        "........");
        assertLiveCellSurvives(0, 0, lastGen);
    }

    @Test
    public void liveTopRowCellWithNoNeighboursShouldDie() {
        boolean[][] lastGen =
                grid("....*...",
                        "........",
                        "........",
                        "........");
        assertLiveCellKilled(0, 4, lastGen);
    }

    @Test
    public void liveTopRowCellWithLeftAndRightNeighbourShouldNotDie() {
        boolean[][] lastGen =
                grid("...***..",
                        "........",
                        "........",
                        "........");
        assertLiveCellSurvives(0, 4, lastGen);
    }

    @Test
    public void liveTopRowCellWithLeftAndLowerNeighbourShouldNotDie() {
        boolean[][] lastGen =
                grid("...**...",
                        "....*...",
                        "........",
                        "........");
        assertLiveCellSurvives(0, 4, lastGen);
    }

    @Test
    public void liveCellWithUpperAndLowerNeighbourShouldNotDie() {
        boolean[][] lastGen =
                grid("....*...",
                        "....*...",
                        "....*...",
                        "........");
        assertLiveCellSurvives(1, 4, lastGen);
    }

    @Test
    public void liveCellWithBothUpperDiagonalNeighboursShouldNotDie() {
        boolean[][] lastGen =
                grid("...*.*..",
                        "....*...",
                        "........",
                        "........");
        assertLiveCellSurvives(1, 4, lastGen);
    }

    @Test
    public void liveCellWithBothLowerDiagonalNeighboursShouldNotDie() {
        boolean[][] lastGen =
                grid("........",
                        "....*...",
                        "...*.*..",
                        "........");
        assertLiveCellSurvives(1, 4, lastGen);
    }

    @Test
    public void liveCellWithTopLeftToRightDiagonalNeighboursShouldNotDie() {
        boolean[][] lastGen =
                grid("...*....",
                        "....*...",
                        ".....*..",
                        "........");
        assertLiveCellSurvives(1, 4, lastGen);
    }

    @Test
    public void liveCellWithBottomLeftToRightDiagonalNeighboursShouldNotDie() {
        boolean[][] lastGen =
                grid(".....*..",
                        "....*...",
                        "...*....",
                        "........");
        assertLiveCellSurvives(1, 4, lastGen);
    }

    @Test
    public void liveCellWithFourNeighboursShouldDie() {
        boolean[][] lastGen =
                grid("........",
                        "...***..",
                        "...*.*..",
                        "........");
        assertLiveCellKilled(1, 4, lastGen);
    }

    @Test
    public void liveCellWithFiveNeighboursShouldDie() {
        boolean[][] lastGen =
                grid("...***..",
                        "....*...",
                        "...*.*..",
                        "........");
        assertLiveCellKilled(1, 4, lastGen);
    }

    @Test
    public void liveCellWithThreeNeighboursShouldLive() {
        boolean[][] lastGen =
                grid("...***..",
                        "....*...",
                        "........",
                        "........");
        assertLiveCellSurvives(1, 4, lastGen);
    }

    @Test
    public void deadCellWithThreeDiagonalNeighboursShouldBecomeAlive() {
        boolean[][] lastGen =
                grid("........",
                        "....*...",
                        "........",
                        "..*.*...");
        assertDeadCellBroughtToLife(2, 3, lastGen);
    }

    @Test
    public void deadCellWithThreeNonDiagonalNeighboursShouldBecomeAlive() {
        boolean[][] lastGen =
                grid("...*....",
                        "..*.*...",
                        "........",
                        "........");
        assertDeadCellBroughtToLife(1, 3, lastGen);
    }

    private void assertDeadCellBroughtToLife(int row, int col, boolean[][] lastGen) {
        assertThat(lastGen).describedAs("Cell was unexpectedly alive at start of the test, this test has a bug").isNotAliveAt(row, col);
        assertThat(testee.advance(lastGen)).isAliveAt(row, col);
    }

    private void assertLiveCellKilled(int row, int col, boolean[][] lastGen) {
        assertThat(lastGen).describedAs("Cell was not live at start of the test, this test has a bug").isAliveAt(row, col);
        assertThat(testee.advance(lastGen)).isNotAliveAt(row, col);
    }

    private void assertLiveCellSurvives(int row, int col, boolean[][] lastGen) {
        assertThat(lastGen).describedAs("Cell was not live at start of the test, this test has a bug").isAliveAt(row, col);
        assertThat(testee.advance(lastGen)).isAliveAt(row, col);
    }

    private boolean[][] empty8By4Grid() {
        return new boolean[4][8];
    }

}
