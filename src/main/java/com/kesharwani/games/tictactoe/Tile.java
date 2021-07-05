package com.kesharwani.games.tictactoe;

import java.util.Iterator;
import java.util.List;

public class Tile {

private long id;
/**
 * this carries either error or success message to be displayed to the user.
 * Example : "let the game begin (all the best !)" (for "Get" request)
 * Example : in case of post process - in case user didn't provide input "Please provide an input"
 * Example : "User Won" or "Computer Won" - please play new game - link".
 */
private String msgForUser;
    /**
     * this holds status of the game.
     * start
     * complete
     * in prgress
     */
    private String status;
    /**
     * these are state-less transitions in v1.
     * so this carries all the values of 9 cells back as hidden field.
     * this needs to be JSON //todo
     * but now can we do csv ?
     * 1,2,3,4,5,6,7,8,9 (this is more like Tic,Toe,Toe.... meaning only first cell has user marked.
     *
     */
    private String context;
    /**
     * the tic-tac-toe grid is numbered
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * when user input is provided - it carries that cell number as input to play.
     */
    private int userInput;

    private List<OneSingleRow> grid;

    public Tile() {
        super();
    }

    public Tile(long id, String msgForUser, String status, String context, int userInput, List<OneSingleRow> grid) {
        this.id = id;
        this.msgForUser = msgForUser;
        this.status = status;
        this.context = context;
        this.userInput = userInput;
        this.grid = grid;
    }



    // getter/setters here.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsgForUser() {
        return msgForUser;
    }

    public void setMsgForUser(String msgForUser) {
        this.msgForUser = msgForUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getUserInput() {
        return userInput;
    }

    public void setUserInput(int userInput) {
        this.userInput = userInput;
    }

    public List<OneSingleRow> getGrid() {
        return grid;
    }

    public void setGrid(List<OneSingleRow> grid) {
        this.grid = grid;
    }

    @Override
    public String toString()
    {
        StringBuffer values = new StringBuffer();
        values.append("ID:"+this.getId());
        values.append(",Status"+this.status);
        values.append(",Context:"+this.context);
        values.append(",userInput:"+this.userInput);
        if (null!=this.grid) {
            Iterator<OneSingleRow> crunchifyIterator = this.grid.iterator();
            int i = 1;
            while (crunchifyIterator.hasNext()) {
                values.append("Row:" + i + ":" + crunchifyIterator.next().toString());
            }
        }
        return values.toString();
    }
}
