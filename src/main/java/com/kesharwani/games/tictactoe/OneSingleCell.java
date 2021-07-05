package com.kesharwani.games.tictactoe;

public class OneSingleCell {
    private int cellNumber;
    private String cellValue;

    public OneSingleCell() {
        super();
    }

    public OneSingleCell(int cellNumber, String cellValue) {
        super();
        this.cellNumber = cellNumber;
        this.cellValue = cellValue;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getCellValue() {
        return cellValue;
    }

    public void setCellValue(String cellValue) {
        this.cellValue = cellValue;
    }

    @Override
    public String toString()
    {
        return cellValue;
    }
}
