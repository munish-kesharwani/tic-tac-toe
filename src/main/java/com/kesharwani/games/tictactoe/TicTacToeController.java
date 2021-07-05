package com.kesharwani.games.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tic-tac-toe")
public class TicTacToeController {

    private TicTacToeServices ticTacToeServices;

    @Autowired
    public TicTacToeController(TicTacToeServices ticTacToeServices) {
        super();
        this.ticTacToeServices = ticTacToeServices;
    }
    @GetMapping
    public String getAllTiles(Model aModel)
    {
        aModel.addAttribute("tiles",this.ticTacToeServices.getTiles());
        return "tic-tac-toe";
    }
    @PostMapping
    public String processAllTiles(@ModelAttribute Tile pInput, Model pOutput)
    {
        System.out.println("processing ----------- ");
        System.out.println("Input received: " +pInput.toString());
        /**
         * step 5, 6, and 7 happens here.
         */
        //step 5:
        TicTacToeDomain domain = new TicTacToeDomain();
        domain.loadModel(pInput.getContext());
        domain.postMove(pInput.getUserInput());
        domain.nextMove();
        Tile tile = new Tile();
        tile.setId(1);
        tile.setStatus("END");
        switch (domain.isGameWon() )
        {
            case TicTacToeDomain.GAME_WON_TIC: tile.setMsgForUser("You Won !!");break;
            case TicTacToeDomain.GAME_WON_TAC: tile.setMsgForUser("Computer Won !!");break;
            case TicTacToeDomain.GAME_IN_PROGRESS:
            {
                tile.setMsgForUser("Let's continue to play - select your play and submit");
                tile.setStatus("In Progress");
                break;
            }
            case TicTacToeDomain.GAME_DRAW:tile.setMsgForUser("Game Drawn !!");break;
            default:break;
        }
        tile.setContext(domain.getContext());
        List<OneSingleRow> tiles2 = new ArrayList<OneSingleRow>();
        tiles2.add(domain.getRow(1));
        tiles2.add(domain.getRow(2));
        tiles2.add(domain.getRow(3));
        tile.setGrid(tiles2);

        pOutput.addAttribute("tiles",tile);


        // change the static postTitles to output from step #7 above.
        //pOutput.addAttribute("tiles",this.ticTacToeServices.postTiles());


        System.out.println("complete ++++++++++");
        return "tic-tac-toe";
    }
}
