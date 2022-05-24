package controller;

import model.*;
import view.*;

/**
 * Controller Class that creates the whole game which
 * contains the View (Menu and Layout (Chess Board))
 * and Model.
 * 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */

public class Controller {

    /**
     * private viewBoard variable of type Layout (Main GUI - View)
     */
    private Layout viewBoard;
    /**
     * private model variable of type Game (Model)
     */
    private Game model;
    /**
     * private menu variable of type Menu (Menu GUI - View)
     */
    private Menu menu;
    /**
     * private currentTurn variable of type boolean
     */
    private boolean currentTurn;

    /**
     * Runs the whole game and initializes both the view and model
     */
    public Controller()
    {
        viewBoard = new Layout();
        model = new Game();
        menu = new Menu();

        //Inside Button Press (ActionListener)
        model.getBoard().initializeBoard();

        currentTurn = true;
        viewBoard.updateDialogueBox(currentTurn, true);

        viewBoard.resetPieces();

        viewBoard.new_reset.addActionListener(e -> loadNewBoard());
        viewBoard.end_turn.addActionListener(e -> playMove());

        viewBoard.frame.setVisible(false);

        menu.nextFrame.addActionListener(e -> removeFrame());
    }

    /**
     * Enables the frame again for Menu
     */
    public void startMenu()
    {
        menu.frame.setVisible(true);
    }

    /**
    * Removes Menu Window after the team colors have been decided
    */
    public void removeFrame(){
        menu.setStatus(false);
        menu.frame.dispose();
        viewBoard.frame.setVisible(true);
    }

    /**
    * Creates a new Board or resets ongoing game
    */
    public void loadNewBoard()
    {
        viewBoard.resetPieces();
        viewBoard.setFinalImageforPiece(menu.getColorFP());
        viewBoard.startPiece();
        model = new Game(); //resets the Game
        model.getBoard().initializeBoard();
        model.getBoard().displayBoard(model.players); //displays the console board 
        viewBoard.dialogue_box.setText("ANIMAL CHESS GAME"); // resets dialogue box
        currentTurn = true;
        viewBoard.setColorTurn(true); 
        viewBoard.updateDialogueBox(currentTurn, true);
    }

    /**
    * Plays the move performed in the View to the Model
    */
    public void playMove()
    {
        boolean turn = false; 

        if ( (viewBoard.getNumAnimal() >= 1 && viewBoard.getNumAnimal() <= 8) && viewBoard.getStringMove() != null)
        {
            for (int i = 0; i < viewBoard.squares.length; i++)
            {   
                for (int j = 0; j < viewBoard.squares[i].length; j++)
                {
                    if (viewBoard.getChosenSquare().equals(viewBoard.squares[i][j]) && viewBoard.getbValid())
                    {
                        if (viewBoard.getColorTurn() && (currentTurn) ) {
                            model.setChooseAnimal(viewBoard.getNumAnimal());
                            model.setChooseMove(viewBoard.getStringMove());
                            turn = model.gameMove(currentTurn, model.getBoard(), model.players[0]);
                                if (turn) {
                                    currentTurn = false; // change value to next player
                                    viewBoard.updateDialogueBox(currentTurn, turn);
                                    viewBoard.setColorTurn(false);
                                    viewBoard.processClick(i, j);
                                    refreshPieces();
                                }
                                else {
                                    viewBoard.dialogue_box.setText( viewBoard.dialogue_box.getText() + "\nInvalid input (Model)" );
                                }
                        }   
                        else if (!viewBoard.getColorTurn() && (!currentTurn) ) {
                            model.setChooseAnimal(viewBoard.getNumAnimal());
                            model.setChooseMove(viewBoard.getStringMove());
                            turn = model.gameMove(currentTurn, model.getBoard(), model.players[1]);
                                if (turn) {
                                    currentTurn = true; // change value to next player
                                    viewBoard.updateDialogueBox(currentTurn, turn);
                                    viewBoard.setColorTurn(true);
                                    viewBoard.processClick(i, j);
                                    refreshPieces();
                                }
                                else {
                                    viewBoard.dialogue_box.setText( viewBoard.dialogue_box.getText() + "\nInvalid input (Model)" );
                                }
                        }
                        model.getBoard().displayBoard(model.players); //displays the console board
                    }
                }
            }
        }
        else 
            viewBoard.dialogue_box.setText( viewBoard.dialogue_box.getText() + "\nInvalid input (View)" );

        viewBoard.setStringMove(null);
        viewBoard.setNumAnimal(0);
        viewBoard.setIsFirstMove(true);

        if (model.checkWinConditions())
        {
            viewBoard.resetPieces();

            if (model.getResult()) {
                System.out.println("Player 1 Wins!");
                viewBoard.updateDialogueBoxWinner(true);
            }
            else {
                System.out.println("Player 2 Wins!");
                viewBoard.updateDialogueBoxWinner(false);
            }
        }
    }

    /**
    * Method that easily displays the piece icons whenever a move was made
    */
    public void refreshPieces()
    {
        viewBoard.squares[viewBoard.getRow()][viewBoard.getCol()].requestFocus();
    }

    /**
     * Main class only for Controller
     * @param args string
     */
    public static void main(String[] args)
    {   
        Controller controller = new Controller();

        controller.startMenu();
    }
}
