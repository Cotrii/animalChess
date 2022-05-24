package model;
/**
 * Mouse Subclass for Piece 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */
public class Mouse extends Piece
{
    /**
     * Inheritance method for Tiger piece
     * @param animal name of animal
     * @param nX row index
     * @param nY col index
     * @param bPieceColor color of piece
     * @param nPieceRank piece's ranking
     * @param bCaptureStatus if true, then it is captured. if false it has not been captured
     */
    Mouse (String animal, int nX, int nY, boolean bPieceColor, int nPieceRank, boolean bCaptureStatus) {
        super(animal, nX, nY, bPieceColor, nPieceRank, bCaptureStatus);
    }

    /**
     * Boolean method that verifies if the direction has a piece for the following squares
     * @param move player's move based on input
     * @param srcPiece chosen piece
     * @param players player
     * @return boolean - true it is not mouse is inside river, false if it is not. 
     */
    public static boolean isMouseInsideRiver(String move, Piece srcPiece, Player[] players)
    {
        for (int i = 0; i < players.length; i++){
            for (int j = 0; j < players[i].piece.size(); j++)  {

                if (move.equals("W")){
                    for (int k = 1; k <= 3; k++) //If statement - checks If there is a piece or not (whether ally or enemy on next if statement)
                        if  ((srcPiece.getX()) - k == (players[i].piece.get(j).getX()) &&  (srcPiece.getY()) == (players[i].piece.get(j).getY()) )
                            return true;
                }
                else if (move.equals("S")){
                    for (int k = 1; k <= 3; k++)
                        if  ((srcPiece.getX()) + k == (players[i].piece.get(j).getX()) &&  (srcPiece.getY()) == (players[i].piece.get(j).getY()) )
                            return true;
                }
                else if (move.equals("A")){
                    for (int k = 1; k <= 2; k++)
                        if  ((srcPiece.getX()) == (players[i].piece.get(j).getX()) &&  (srcPiece.getY()) - k == (players[i].piece.get(j).getY()) )
                            return true;
                }
                else if (move.equals("D")) {
                    for (int k = 1; k <= 2; k++)
                        if  ((srcPiece.getX())== (players[i].piece.get(j).getX()) &&  (srcPiece.getY()) + k == (players[i].piece.get(j).getY()) )
                            return true;
                }
            }
        }
        return false;
    }

    /**
     * Boolean method that checks if srcPiece is a mouse piece and if targetPiece is an elephant piece
     * @param srcPiece chosen piece
     * @param targetPiece piece of destination tile
     * @return boolean - true it is mouse and elephant pieces, false if otherwise.
     */
    public static boolean isSrcMouseCanCaptureElephant(Piece srcPiece, Piece targetPiece)
    {
        if (srcPiece instanceof Mouse)
        {
            return targetPiece instanceof Elephant;
        }
        return false;
    }

    /**
     * Northward move for mouse piece only
     * @param players player
     * @param board current state of the board display
     * @return int for moveNorth method
     */
    public int moveUpInsideRiver (Player[] players, Board board)
    {
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].piece.size(); j++)  {
                if  ((this.getX()) - 1 == (players[i].piece.get(j).getX()) &&  (this.getY()) == (players[i].piece.get(j).getY()) ){
                    if (board.tiles[(this.getX())][this.getY()].terrain.getTerrain().equals("River"))
                    {
                        if (board.tiles[(this.getX() - 1)][this.getY()].terrain.getTerrain().equals("Land") ) { 
                            return 2; //Not needed: add srcPiece.getPieceColor() == players[i].piece.get(j).getPieceColor()
                        }
                        else {
                            Piece.removePiece(players[i], j);
                            this.setX(this.getX() - 1); 
                            return 1;
                        }
                    }
                    else
                        return 2;
                }
            }
        }
        return 3;
    }

    /**
     * Southward move for mouse piece only
     * @param players player
     * @param board current state of the board display
     * @return int for moveSouth method
     */
    public int moveDownInsideRiver (Player[] players, Board board)
    {
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].piece.size(); j++)  {
                if  ((this.getX()) + 1 == (players[i].piece.get(j).getX()) &&  (this.getY()) == (players[i].piece.get(j).getY()) ){
                    if (board.tiles[(this.getX())][this.getY()].terrain.getTerrain().equals("River"))
                    {
                        if (board.tiles[(this.getX() + 1)][this.getY()].terrain.getTerrain().equals("Land") )    { 
                            return 2;
                        }
                        else {
                            Piece.removePiece(players[i], j);
                            this.setX(this.getX() + 1); 
                            return 1;
                        }
                    }
                    else
                        return 2;
                }
            }
        }
        return 3;
    }

    /**
     * Eastward move for mouse piece only
     * @param players player
     * @param board current state of the board display
     * @return int for moveEast method
     */
    public int moveRightInsideRiver (Player[] players, Board board)
    {
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].piece.size(); j++)  {
                if  ((this.getX()) == (players[i].piece.get(j).getX()) &&  (this.getY()) + 1 == (players[i].piece.get(j).getY()) ){
                    if (board.tiles[(this.getX())][this.getY()].terrain.getTerrain().equals("River") )
                    {
                        if (board.tiles[(this.getX())][(this.getY() + 1)].terrain.getTerrain().equals("Land") )    
                        { //  not sure if I need to add srcPiece.getPieceColor() == players[i].piece.get(j).getPieceColor()
                            return 2;
                        }
                        else {
                            Piece.removePiece(players[i], j);
                            this.setY(this.getY() + 1); 
                            return 1;
                        }
                    }
                    else
                        return 2;
                }
            }
        }
        return 3;
    }

    
    /**
     * Westward move for mouse piece only
     * @param players player
     * @param board current state of the board display
     * @return int for moveWest method
     */
    public int moveLeftInsideRiver (Player[] players, Board board)
    {
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].piece.size(); j++)  {
                if  ((this.getX()) == (players[i].piece.get(j).getX()) &&  (this.getY()) - 1 == (players[i].piece.get(j).getY()) ){
                    if (board.tiles[(this.getX())][this.getY()].terrain.getTerrain().equals("River"))
                    {
                        if (board.tiles[(this.getX())][(this.getY() - 1)].terrain.getTerrain().equals("Land") )    
                        { //  not sure if I need to add srcPiece.getPieceColor() == players[i].piece.get(j).getPieceColor()
                            return 2;
                        }
                        else {
                            Piece.removePiece(players[i], j);
                            this.setY(this.getY() - 1); 
                            return 1;
                        }
                    }
                    else
                        return 2;
                }
            }
        }
        return 3;
    }

}
