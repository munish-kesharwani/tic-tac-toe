package com.kesharwani.games.tictactoe;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TicTacToeServices {
    private static List<OneSingleRow> tiles = new ArrayList<>();

    static {
        // iterate over 3 rows
        for (int i = 0; i < 3; i++) {
            // iterate over 3 calls
            OneSingleRow row = new OneSingleRow();
            row.setRowNumber(i);
            List<OneSingleCell> cells = new ArrayList<>();
            for (int j=0;j<3;j++){
                cells.add (new OneSingleCell(i*3+(j+1), "Toe"));
            }
            row.setValue(cells);
            tiles.add(row);
        }

    }

    private static List<OneSingleRow> tiles2 = new ArrayList<>();

    static {
        // iterate over 3 rows
        for (int i = 0; i < 3; i++) {
            // iterate over 3 calls
            OneSingleRow row = new OneSingleRow();
            row.setRowNumber(i);
            List<OneSingleCell> cells = new ArrayList<>();
            for (int j=0;j<3;j++){
                cells.add (new OneSingleCell(i*3+(j+1), j==0?"Tic":(j==1?"Tac":"Toe")));
            }
            row.setValue(cells);
            tiles2.add(row);
        }
    }
    public Tile getTiles() {
        Tile tile = new Tile(1,"Let's play - all the best!","Start",convertToContext(tiles),0,tiles);
        return tile;
    }
    public Tile postTiles() {
        Tile tile = new Tile(1,"Let's continue to play - select your play and submit","In Progress",convertToContext(tiles2),0,tiles2);
        return tile;

    }

    private String convertToContext (List<OneSingleRow> tiles)
    {

        StringBuffer value = new StringBuffer();
        Iterator<OneSingleRow> crunchifyIterator = tiles.iterator();
        while (crunchifyIterator.hasNext()) {
            value.append( crunchifyIterator.next().toString());
        }
    return value.toString();
    }
}
