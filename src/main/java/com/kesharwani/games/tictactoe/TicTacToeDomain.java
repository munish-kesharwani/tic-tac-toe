package com.kesharwani.games.tictactoe;

import java.lang.String;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * this is the domain class, which is heart of the logic ...
 */
public class TicTacToeDomain {

    /**
     * constants
     */
    public static final int TIC=1;
    public static final int BLANK=0;
    public static final int TAC=2;
    public static final int ILLEGAL_MOVE=99;
    /**
     * these are cells 1 through 9.
     * int : 0 : not occupied or blank.
     *  : 1 : occupied by Tic
     *  : 2 : occupied by Tac
     */
    private int cell1=BLANK;
    private int cell2=BLANK;
    private int cell3=BLANK;
    private int cell4=BLANK;
    private int cell5=BLANK;
    private int cell6=BLANK;
    private int cell7=BLANK;
    private int cell8=BLANK;
    private int cell9=BLANK;

    public static final int GAME_IN_PROGRESS = 0;
    public static final int GAME_WON_TIC=1;
    public static final int GAME_WON_TAC=2;
    public static final int GAME_DRAW=3;

    public TicTacToeDomain() {
        super();
    }

    /**
     * this does not process userInput.
     * takes different rows from UI. and loads in.
     * @param pModel : the model.
     * @return
     */
    public int loadModel (List<OneSingleRow> pModel)
    {
        // loads the given inputs in to the cells.
        Iterator<OneSingleRow> crunchifyIterator = pModel.iterator();
        while (crunchifyIterator.hasNext()) {
            OneSingleRow row= crunchifyIterator.next();//.toString());
            Iterator<OneSingleCell> cells = (row.getValue()).iterator();
            while (cells.hasNext()){
                OneSingleCell cell = cells.next();
                setCell(cell.getCellNumber(),getOccupier(cell.getCellValue()) );
            }
        }

        // then determines : is game Won ?
        // return status appropriate if game is won.
        return isGameWon();
    }

    public int loadModel (String context)
    {
        int i=0; int inc=3;
        while (i<27 ) {
            setCell((i/inc)+1, getOccupier(context.substring(i, i + inc)) );
            i = i + inc;
        }

        return isGameWon();
    }

    public String getContext()
    {
        StringBuffer value = new StringBuffer();
        value.append( cell1 == BLANK ? "Toe" : (cell1==TAC?"Tac":"Tic") ) ;
        value.append( cell2 == BLANK ? "Toe" : (cell2==TAC?"Tac":"Tic") ) ;
        value.append( cell3 == BLANK ? "Toe" : (cell3==TAC?"Tac":"Tic") ) ;
        value.append( cell4 == BLANK ? "Toe" : (cell4==TAC?"Tac":"Tic") ) ;
        value.append( cell5 == BLANK ? "Toe" : (cell5==TAC?"Tac":"Tic") ) ;
        value.append( cell6 == BLANK ? "Toe" : (cell6==TAC?"Tac":"Tic") ) ;
        value.append( cell7 == BLANK ? "Toe" : (cell7==TAC?"Tac":"Tic") ) ;
        value.append( cell8 == BLANK ? "Toe" : (cell8==TAC?"Tac":"Tic") ) ;
        value.append( cell9 == BLANK ? "Toe" : (cell9==TAC?"Tac":"Tic") ) ;

        return value.toString();
    }

    public int getOccupier(String pVal)
    {
        if ( "Tic".equalsIgnoreCase(pVal) ) return TIC;
        if ( "Tac".equalsIgnoreCase(pVal) ) return TAC;
        return BLANK;

    }

    /**
     * basically posts next move by Tic.
     * //TODO can UI post 2-3 Tic moves ??
     * @param pCellNumber
     * @return
     */
    public int postMove(int pCellNumber)
    {
        // if game is in progress then only we can accept move.
        if (isGameWon() == GAME_IN_PROGRESS)
        {
            // is pCellNumber valid? between 1 & 9
            if (pCellNumber >9 || pCellNumber <0) return ILLEGAL_MOVE;
            // given cell is not already occupied then accept the move.
            if (getCell(pCellNumber) != BLANK) {
                // else return illegalMove
                return ILLEGAL_MOVE;
            }
            // since cell is blank - occupy it and determine if games is won.
            setCell(pCellNumber, TIC);
        }
        return isGameWon();
    }

    /**
     * since this is private method - it is ok.
     * but //TODO this needs to check if BLANK
     * @param pCellNumber : the cell
     * @param occupier : te
     */
    private void setCell(int pCellNumber, int occupier)
    {
        switch (pCellNumber) {
            case 1: cell1=occupier;break;
            case 2: cell2=occupier;break;
            case 3: cell3=occupier;break;
            case 4: cell4=occupier;break;
            case 5: cell5=occupier;break;
            case 6: cell6=occupier;break;
            case 7: cell7=occupier;break;
            case 8: cell8=occupier;break;
            case 9: cell9=occupier;break;
            default:
                break;
        }
    }
    public int getCell(int pNumber) {
        switch (pNumber) {
            case 1: return cell1;
            case 2: return cell2;
            case 3: return cell3;
            case 4: return cell4;
            case 5: return cell5;
            case 6: return cell6;
            case 7: return cell7;
            case 8: return cell8;
            case 9: return cell9;
            default:
                return ILLEGAL_MOVE;
        }
    }
    /**
     * next Move method determins next computer move.
     * @return
     */
    public int nextMove()
    {
        // computer move possible only if game is in progress.
        if ( isGameWon() == GAME_IN_PROGRESS )
        {
            int i=5;
            // TODO here comes the big logic to determine next computer move optimized to win !!
            // right now computer logic is to occupy next blank cel starting from top-left.

            /**
             * P0 - if entire table is blank or only one non-center cell is occupied by user -take the center
             * P1 - can computer win in the next move - do it.
             * P2 - can user win in the next move - block it
             * P3 - our special logic. center occupied by comp, corner occupied by user -
             * P4 - counter of our special logic - center occupied by user :- only one cell occupied so far
             *      , pick specific corner to avoid getting traipped
             * P5 - Occupy adjancent one to already occupied
             */
            //P0
            if (  checkP0Logic() == 0)
            {
                i = 5;
            }
            else
            {
                i = checkP1Logic();
                if (i ==0 )
                {
                    i = checkP2Logic();
                    if ( i== 0)
                    {
                        i = checkP3Logic();
                        if ( i == 0 )
                        {
                            i = checkP4Logic();
                            if (i==0)
                            {
                                i = checkP5Logic();
                                if (i==0)
                                {
                                    // everthing else has failed to find next blank cell and return
                                    i = findNextEmpty();
                                }
                            }
                        }
                    }
                }
            }

            setCell(i,TAC);
        }
        return isGameWon();
    }

    // finding next empty is good, but priority is given to center cell.
    private int findNextEmpty()
    {
        if (cell5 == BLANK) return 5;
        int i =1;
        // watch out - it can go out of bounds ....
        while (getCell(i) != BLANK) i++;
        return i;
    }
    private int checkP0Logic()
    {
        return cell1+cell2+cell3+cell4+cell5+cell6+cell7+cell8+cell9 ;
    }

    // checks with multiplication is 0 and non-blank multiplication is 4;
    private int utility(int i1, int i2, int i3,int i4)
    {
        int lCel1 = getCell(i1);
        int lCel2 = getCell(i2);
        int lCel3 = getCell(i3);
        if ( lCel1 * lCel2 * lCel3 ==0)
        {
            if (lCel1*lCel2 == i4 ) return i3;
            if (lCel1*lCel3==i4) return i2;
            if (lCel2*lCel3 == i4 ) return i1;
            return 0;
        }
        return 0;
    }
    private int checkP1Logic()
    {
        System.out.println("P1 :");
        int i = 0;
        //Check would be : row mul = 0 and non-blank 4
        // row 1 row2 row 3 followed by col 1 col 2 and col3 and diagonal1, 2
        i = utility(1,2,3,4);
        if (i !=0) return i;
        i = utility(4,5,6,4);
        if (i !=0) return i;
        i = utility(7,8,9,4);
        if (i !=0) return i;

        i = utility(1,4,7,4);
        if (i !=0) return i;
        i = utility(2,5,8,4);
        if (i !=0) return i;
        i = utility(3,6,9,4);
        if (i !=0) return i;

        i = utility(1,5,9,4);
        if (i !=0) return i;
        i = utility(3,5,7,4);
        if (i !=0) return i;

        System.out.println("0");
        return 0;
    }

    private int checkP2Logic()
    {
        int i = 0;
        System.out.println("P2");
        //Check would be : row mul = 0 and non-blank 1
        // row 1 row2 row 3 followed by col 1 col 2 and col3 and diagonal1, 2
        i = utility(1,2,3,1);
        if (i !=0) return i;
        i = utility(4,5,6,1);
        if (i !=0) return i;
        i = utility(7,8,9,1);
        if (i !=0) return i;

        i = utility(1,4,7,1);
        if (i !=0) return i;
        i = utility(2,5,8,1);
        if (i !=0) return i;
        i = utility(3,6,9,1);
        if (i !=0) return i;

        i = utility(1,5,9,1);
        if (i !=0) return i;
        i = utility(3,5,7,1);
        if (i !=0) return i;

        System.out.println("0");
        return 0;
    }

    private int checkP3Logic()
    {
        int i = 0;
        // P3 - our special logic. center occupied by comp, corner occupied by user -
        // TODO
        return 0;
    }

    private int checkP4Logic()
    {
        int i = 0;
        // P4 - counter of our special logic - center occupied by user :- only one cell occupied so far
        //    *      , pick specific corner to avoid getting traipped
        // TODO - probably not needed current logic is holding.
        return 0;
    }

    private int checkP5Logic()
    {
        int i = 0;
        // P5 - Occupy adjancent one to already occupied
        // row -wise , col wise and then diag
        i = utilityAdjacent(1,2,3);
        if (i !=0) return i;
        i = utilityAdjacent(4,5,6);
        if (i !=0) return i;
        i = utilityAdjacent(7,8,9);
        if (i !=0) return i;

        i = utilityAdjacent(1,4,7);
        if (i !=0) return i;
        i = utilityAdjacent(2,5,8);
        if (i !=0) return i;
        i = utilityAdjacent(3,6,9);
        if (i !=0) return i;

        i = utilityAdjacent(1,5,9);
        if (i !=0) return i;
        i = utilityAdjacent(3,5,7);
        if (i !=0) return i;

        return 0;
    }

    private int utilityAdjacent(int i1, int i2, int i3)
    {
        // two of them are zero and one of them is TAC
        int lCel1 = getCell(i1);
        int lCel2 = getCell(i2);
        int lCel3 = getCell(i3);
        if (lCel3 == TAC && lCel1==BLANK && lCel2==BLANK ) return i1;
        if (lCel2 == TAC && lCel3==BLANK && lCel1==BLANK ) return i3;
        if (lCel1 == TAC && lCel2==BLANK && lCel3==BLANK ) return i2;

        return 0;
    }

    public int isGameWon()
    {
        // do your logic - rows wise, column wise , diagonal wise to determine if somebody won.
        // row 1:
        if (cell1*cell2*cell3 == 8) return GAME_WON_TAC;
        if (cell1*cell2*cell3 == 1) return GAME_WON_TIC;

        // row 2:
        if (cell4*cell5*cell6 == 8) return GAME_WON_TAC;
        if (cell4*cell5*cell6 == 1) return GAME_WON_TIC;

        // row 3:
        if (cell7*cell8*cell9 == 8) return GAME_WON_TAC;
        if (cell7*cell8*cell9 == 1) return GAME_WON_TIC;

        // col 1:
        if (cell1*cell4*cell7 == 8) return GAME_WON_TAC;
        if (cell1*cell4*cell7 == 1) return GAME_WON_TIC;

        // col 2:
        if (cell2*cell5*cell8 == 8) return GAME_WON_TAC;
        if (cell2*cell5*cell8 == 1) return GAME_WON_TIC;

        // col 3:
        if (cell3*cell6*cell9 == 8) return GAME_WON_TAC;
        if (cell3*cell6*cell9 == 1) return GAME_WON_TIC;

        // diag 1:
        if (cell1*cell5*cell9 == 8) return GAME_WON_TAC;
        if (cell1*cell5*cell9 == 1) return GAME_WON_TIC;

        // diag 3:
        if (cell3*cell5*cell7 == 8) return GAME_WON_TAC;
        if (cell3*cell5*cell7 == 1) return GAME_WON_TIC;


        // if all cells are occupied then return draw;
        if ( cell1*cell2*cell3*cell4*cell5*cell6*cell7*cell8*cell9 != 0 ) return GAME_DRAW;


        return GAME_IN_PROGRESS;
    }

    public String getCellValue(int pCell)
    {
        switch (pCell)
        {
            case TIC: return "Tic";
            case TAC: return "Tac";
            default:
                return "Toe";
        }

    }
    OneSingleRow getRow(int pNumber)
    {
        OneSingleRow row = new OneSingleRow();
        List<OneSingleCell> cells = new ArrayList<>();
        switch ( pNumber)
        {
            case 1:
                cells.add(new OneSingleCell(1,getCellValue(cell1)) );
                cells.add(new OneSingleCell(2,getCellValue(cell2)) );
                cells.add(new OneSingleCell(3,getCellValue(cell3)) );
                break;
            case 2:
                cells.add(new OneSingleCell(4,getCellValue(cell4)) );
                cells.add(new OneSingleCell(5,getCellValue(cell5)) );
                cells.add(new OneSingleCell(6,getCellValue(cell6)) );
                break;
            case 3:
                cells.add(new OneSingleCell(7,getCellValue(cell7)) );
                cells.add(new OneSingleCell(8,getCellValue(cell8)) );
                cells.add(new OneSingleCell(9,getCellValue(cell9)) );
                break;
            default:
                break;
        }

        row.setRowNumber(pNumber);
        row.setValue(cells);
        return row;
    }
}
