package com.kesharwani.games.tictactoe;

import java.util.Iterator;
import java.util.List;

/**
 * this class models view Model -
 * 3x3 grid representing 9 cells.
 * this class models each cell of the
 * cellNumber is place of the cell in the grid.
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * value is -
 *   Tic - X (played by user)
 *   Tac - O (played by computer)
 *   Toe - blank (not yet played)
 */
public class OneSingleRow {
    /**
     * which row in the grid
     * 1
     * 2
     * 3
     */
    private int rowNumber;
    /**
     * List of values in the row
     * "Tic" "Tac" "Toe"
     */
    private List<OneSingleCell> value;

    public OneSingleRow() {
        super();
    }

    public OneSingleRow(int rowNumber, List<OneSingleCell> value) {
        super();
        this.rowNumber = rowNumber;
        this.value = value;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public List<OneSingleCell> getValue() {
        return value;
    }

    public void setValue(List<OneSingleCell> value) {
        this.value = value;
    }

    @Override
    public String toString()
    {
        StringBuffer values = new StringBuffer();
        Iterator<OneSingleCell> crunchifyIterator = value.iterator();
        while (crunchifyIterator.hasNext()) {
            values.append( crunchifyIterator.next().toString());
        }
        return values.toString();
    }
}
