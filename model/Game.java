package model;
/**
 * This class has the display of the program game and allows each player 
 * to take its turn respectively until the game ends with a successful winner.
 * 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */

public class Game
{
    /** Array of players, 2 players initialized for the game */
    public Player[] players = new Player[2];

    //Originally from startGame()

    /** private board variable of data type Board */
    private Board board;
    /* private bCurrentTurn variable with boolean data type. Determines who's turn it is
    private boolean bCurrentTurn;*/
    /** private bEndGame variable with boolean data type. Determines if winner is found */
    private boolean bEndGame;
    /** private bResult variable with boolean data type. Determines the game's winner */
    private boolean bResult;

    //Originally from gameMove()

    /** private nChooseAnimal variable with integer data type. For numerical equivalence of animal pieces */
    private int nChooseAnimal;
    /** private strChooseMove variable with String data type. For string equivalence of move performed */
    private String strChooseMove;
    /**  private bMoveDone variable with boolean data type. Determines if current player plays a move */
    private boolean bMoveDone;
    /** private moveN variable with Move data type. For move performed northward */
    private Move move; 

    /**
     * Initialize player's team color. True for red team, false for blue team
     */
    public Game()
    {
        players[0] = new Player(true);
        players[1] = new Player(false);

        //kb = new Scanner (System.in);
        board = new Board();
        //bCurrentTurn = true;
        bEndGame = false;
        bResult = false;
        move = new Move();
        nChooseAnimal = 0; //Default (Mouse): can be changed if you think of a safe while loop
        strChooseMove = "W"; //Default (North): Can be changed to null but not advised

    }

    /**
     * Prints an animal according to its number in the menu
     * @param nChooseAnimal Animal piece's number in the menu
     * @return a String that contains the animal's name
     */
    public String printAnimal (int nChooseAnimal)
    {
        if (nChooseAnimal == 8)
            return "Elephant";
        else if (nChooseAnimal == 7)
            return "Lion";
        else if (nChooseAnimal == 6)
            return "Tiger";
        else if (nChooseAnimal == 5)
            return "Leopard";
        else if (nChooseAnimal == 4)
            return "Dog";
        else if (nChooseAnimal == 3)
            return "Wolf";
        else if (nChooseAnimal == 2)
            return "Cat";
        else if (nChooseAnimal == 1)
            return "Mouse";
        
        return null;
    }

    /**
     * The method allows you select a move from directions North, West, East, South
     * it will return a boolean value for bCurrentMove to detect which next player turn is next 
     * if returned value is true then it will be Red Player's turn but if false it will be Blue's
     *
     * @param bCurrentTurn Determine which player has its turn currently
     * @param board shows the current state of the game board
     * @param player shows the current state of the game board
     * @return boolean for next turn
     */
    public boolean gameMove (boolean bCurrentTurn, Board board, Player player )
    {
        //int nChooseAnimal; //String strChooseMove;  //Move moveN = new Move(), moveS = new Move(), moveW = new Move(), moveE = new Move();
        bMoveDone = false;

        board.displayBoard(players);

        if (bCurrentTurn)
                System.out.println("Red Player: Choose an animal to make a move: ");
        else
                System.out.println("Blue Player: Choose an animal to make a move: ");
        
        System.out.println("[8] Elephant \n[7] Lion \n[6] Tiger \n[5] Leopard \n[4] Dog \n[3] Wolf \n[2] Cat \n[1] Mouse");
        System.out.print("Animal Input: ");
        //nChooseAnimal = Integer.parseInt(kb.nextLine()); //will be changed to button press piece.getRank();
        bMoveDone = false;

        System.out.println("Animal chosen is " + printAnimal(nChooseAnimal) );

        System.out.println("\nInput W for North, A for West, S for South, D for East.");
        System.out.print("Direction Input: ");
        //remove : strChooseMove = kb.nextLine(); //will be changed to button press of return key W, A, S, D from Controller siguro?

        System.out.println("\nAnswer was " + strChooseMove);

        int key = findPieceIndex(player, nChooseAnimal); //To find piece[index]

        if (key >= 0 && key <= 8)
        {
            for (int i = 0; i < 9; i++)
            {
                for (int j = 0; j < 7; j++)
                {
                    if (bCurrentTurn)
                    {
                    
                        if ( (board.tiles[i][j].getRow() == players[0].piece.get(key).getX() && board.tiles[i][j].getCol() == players[0].piece.get(key).getY()) && !bMoveDone && !players[0].piece.get(key).getCaptureStatus())
                        {
                            move.setSrcPiece(players[0].piece.get(key));

                            if (strChooseMove.equals("W"))
                                    bMoveDone = move.moveNorth (board, players); 
                            else if (strChooseMove.equals("A"))
                                    bMoveDone = move.moveWest (board, players);
                            else if (strChooseMove.equals("S"))
                                    bMoveDone = move.moveSouth (board, players);
                            else if (strChooseMove.equals("D"))
                                    bMoveDone = move.moveEast (board, players);
                            else
                                    System.out.println("Invalid input");
                        }
                    }
                    else
                    {
                        if ( (board.tiles[i][j].getRow() == players[1].piece.get(key).getX() && board.tiles[i][j].getCol() == players[1].piece.get(key).getY()) && !bMoveDone && !players[1].piece.get(key).getCaptureStatus())
                        {
                            move.setSrcPiece(players[1].piece.get(key));

                            if (strChooseMove.equals("W"))
                                    bMoveDone = move.moveNorth (board, players);
                            else if (strChooseMove.equals("A"))
                                    bMoveDone = move.moveWest (board,  players);
                            else if (strChooseMove.equals("S"))
                                    bMoveDone = move.moveSouth (board,  players);
                            else if (strChooseMove.equals("D"))
                                    bMoveDone = move.moveEast (board, players);
                            else
                                    System.out.println("Invalid input");
                        }
                    }
                }
            }
        }

        if (bMoveDone){
            if (bCurrentTurn)
                bCurrentTurn = false;
            else
                bCurrentTurn = true;
        }

        return bMoveDone;
    }

    /**
     * Finds the chosen piece index for the current player.
     * Returns respective teams' piece in an int number
     * @param player current state of the game board
     * @param nChooseAnimal animal piece that will be chosen
     * @return int of a piece index
     */
    public int findPieceIndex (Player player, int nChooseAnimal)
    {
        for (int i = 0; i  < player.piece.size(); i++)
        {
            if (nChooseAnimal == 8){
                if (player.piece.get(i) instanceof Elephant)
                    return i;
            }
            else if (nChooseAnimal == 7){
                if (player.piece.get(i) instanceof Lion)
                    return i;
            }
            else if (nChooseAnimal == 6){
                if (player.piece.get(i) instanceof Tiger)
                    return i;
            }
            else if (nChooseAnimal == 5){
                if (player.piece.get(i) instanceof Leopard)
                    return i;
            }
            else if (nChooseAnimal == 4){
                if (player.piece.get(i) instanceof Dog)
                    return i;
            }
            else if (nChooseAnimal == 3){
                if (player.piece.get(i) instanceof Wolf)
                    return i;
            }
            else if (nChooseAnimal == 2){
                if (player.piece.get(i) instanceof Cat)
                    return i;
            }
            else if (nChooseAnimal == 1)
            {
                if (player.piece.get(i) instanceof Mouse)
                    return i;
            }
        }
        return -1;
    }

    /* Method starts the game and will only end if the game is finished also.
    It contains a scanner variable, a Board object, and four booleans 
    which will determine the state of the game.
    public void startGame() {
        //Scanner kb - new Scanner(System.in); //Board board = new Board(); //boolean bCurrentTurn = true, bEndGame = false, bResult = false;
        board.initializeBoard();
        
        while (bEndGame == false) {
            //Red Player's Turn
            if (bCurrentTurn == true) {
                bCurrentTurn = gameMove(bCurrentTurn, board, players[0]);
            }
            else {
                System.out.println("--------------------------------------------------"); //Blue Player Turn
                bCurrentTurn = gameMove(bCurrentTurn, board, players[1]); //Can be removed for testing only Player 1
            }
            bEndGame = checkWinConditions();
        }

        if (bResult == true) System.out.print("Player Red Wins!");
        else                  System.out.print("Player Blue Wins!");

        //remove: kb.close();
    } */

    /**
     * Checks if a winner can be determined 
     * Returns a boolean (bEndGame) whether a winning condition is found or not (when a winner is found or not)
     * @return boolean (bEndGame)
     */
    public boolean checkWinConditions()
    {
        for (int i = 0; i < players.length; i++)
        {
                for (int j = 0; j < players[i].piece.size(); j++) {
                // If statement checks if any red piece is on the blue den tile
                    if ( (board.tiles[0][3].getRow() == players[i].piece.get(j).getX() && board.tiles[0][3].getCol() == players[i].piece.get(j).getY()) && (players[i].piece.get(j).getPieceColor()) ) {
                    bResult = true;
                    board.displayBoard(players);
                    bEndGame = true;
                }
                // Else if statement checks if any blue piece is on the red den tile
                else if ( (board.tiles[8][3].getRow() == players[i].piece.get(j).getX() && board.tiles[8][3].getCol() == players[i].piece.get(j).getY()) && (!players[i].piece.get(j).getPieceColor()) ) {
                    bResult = false;
                    board.displayBoard(players);
                    bEndGame = true;
                }
                // Else If statement checks if all blue pieces are captured
                else if (  players[1].piece.isEmpty()) {
                    bResult = true;
                    board.displayBoard(players);
                    bEndGame = true;
                }
                // Else If statement checks if all red pieces are captured
                else if ( players[0].piece.isEmpty() )
                    {
                    bResult = false;
                    board.displayBoard(players);
                    bEndGame = true;
                    }
                }
        }

        return bEndGame;
    }

    // Setters
    /**
     * Sets an animal using an integer
     * @param chooseAnimal animal integer
     */
    public void setChooseAnimal(int chooseAnimal){
        nChooseAnimal = chooseAnimal;
    }

    /**
     * Sets a move using a String
     * @param chooseMove performed move
     */
    public void setChooseMove(String chooseMove){
        strChooseMove = chooseMove;
    }

    // Getters
    /**
     * Gets board through Board type
     * @return type Board containing board
     */
    public Board getBoard(){
        return board;
    }

    /*
    Gets current turn of game using boolean @return boolean that contains a current turn of the game
    public boolean getCurrentTurn(){
        return bCurrentTurn;
    }
    */

    /**
     * Gets result of game using boolean
     * @return boolean that contains result (winner) of the game
     */
    public boolean getResult() {
        return bResult;
    }
}