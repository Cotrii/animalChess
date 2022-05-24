package model;
/**
 * Lion Subclass for Piece 
 * 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */
public class Lion extends Piece implements Jump
{
    /**
     * Inheritance method for Lion piece
     * @param animal name of animal
     * @param nX row index
     * @param nY col index
     * @param bPieceColor color of piece
     * @param nPieceRank piece's ranking
     * @param bCaptureStatus if true, then it is captured. if false it has not been captured
     */
    Lion (String animal, int nX, int nY, boolean bPieceColor, int nPieceRank, boolean bCaptureStatus) {
        super(animal, nX, nY, bPieceColor, nPieceRank, bCaptureStatus);
    }

    /**
     * Checks if there is an enemy or ally in the river towards North 
     * Return value: 1 - enemy for capture, 2 - found enemy with higher rank or ally (invalid), 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    @Override
    public int isEnemyOnNorthRiver (Board board, Player[] players)
    {
        if (Mouse.isMouseInsideRiver("W", this, players))
            return 2;

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].piece.size(); j++)  {
                //If statement - checks If there is a piece or not (whether ally or enemy on next if statement)
                    
                if  ((this.getX()) - 4 == (players[i].piece.get(j).getX()) &&  (this.getY()) == (players[i].piece.get(j).getY())  ) 
                //add Mouse movement here as part of the if condition
                {

                    if (this.getPieceColor() != players[i].piece.get(j).getPieceColor() ) 
                    {
                        if(this.getPieceRank() >= players[i].piece.get(j).getPieceRank() ) { 
                            players[i].piece.get(j).setCaptureStatus(true);
                            players[i].piece.remove(j); 
                            this.setX(this.getX() - 4);
                            return 1;
                        }
                        else return 2; // Found piece is higher ranking
                    }
                    else // (srcPiece.Color() == players[i].Color() )
                        return 2; //If piece is same color, return invalid move (2)
                }
            }
        }
        return 3; //No piece found, free to move
    }

    /**
     * Checks if there is an enemy or ally in the river towards South 
     * Return value: 1 - enemy for capture, 2 - found enemy with higher rank or ally (invalid), 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    @Override
    public int isEnemyOnSouthRiver (Board board, Player[] players)
    {
        if (Mouse.isMouseInsideRiver("S", this, players))
            return 2;

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].piece.size(); j++)  {
                //If statement - checks If there is a piece or not (whether ally or enemy on next if statement)
                if  ((this.getX()) + 4 == (players[i].piece.get(j).getX()) &&  (this.getY()) == (players[i].piece.get(j).getY()) )
                {
                    if (this.getPieceColor() != players[i].piece.get(j).getPieceColor() ) 
                    {
                        if(this.getPieceRank() >= players[i].piece.get(j).getPieceRank() ) { 
                            players[i].piece.get(j).setCaptureStatus(true);
                            players[i].piece.remove(j); //players[i].piece.get(j).setX(-100);
                            this.setX(this.getX() + 4);
                            return 1;
                        }
                        else return 2; // Found piece is higher ranking
                    }
                    else // (srcPiece.Color() == players[i].Color() )
                        return 2; //If piece is same color, return invalid move (2)
                }
            }
        }
        return 3; //No piece found, free to move
    }

        /**
     * Checks if there is an enemy or ally in the river towards East 
     * Return value: 1 - enemy for capture, 2 - found enemy with higher rank or ally (invalid), 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    @Override
    public int isEnemyOnEastRiver (Board board, Player[] players)
    {
        if (Mouse.isMouseInsideRiver("D", this, players))
        return 2;

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].piece.size(); j++)  {
                //If statement - checks If there is a piece or not (whether ally or enemy on next if statement)
                if  ((this.getX()) == (players[i].piece.get(j).getX()) &&  (this.getY()) + 3 == (players[i].piece.get(j).getY()) )
                {
                    if (this.getPieceColor() != players[i].piece.get(j).getPieceColor() ) 
                    {
                        if(this.getPieceRank() >= players[i].piece.get(j).getPieceRank() ) { 
                            players[i].piece.get(j).setCaptureStatus(true);
                            players[i].piece.remove(j); //players[i].piece.get(j).setX(-100);
                            this.setY(this.getY() + 3);
                            return 1;
                        }
                        else return 2; // Found piece is higher ranking
                    }
                    else // (srcPiece.Color() == players[i].Color() )
                        return 2; //If piece is same color, return invalid move (2)
                }
            }
        }
        return 3; //No piece found, free to move
    }

    /**
     * Checks if there is an enemy or ally in the river towards West
     * Return value: 1 - found enemy and is available for capture, 2 - found enemy with higher ranking so invalid move or 
     *               found ally piece, therefore invalid move, 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    @Override
    public int isEnemyOnWestRiver ( Board board, Player[] players)
    {
        if (Mouse.isMouseInsideRiver("A", this, players))
            return 2;

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].piece.size(); j++)  {
                //If statement - checks If there is a piece or not (whether ally or enemy on next if statement)
                if  ((this.getX()) == (players[i].piece.get(j).getX()) &&  (this.getY()) - 3 == (players[i].piece.get(j).getY()) )
                {
                    if (this.getPieceColor() != players[i].piece.get(j).getPieceColor() ) 
                    {
                        if(this.getPieceRank() >= players[i].piece.get(j).getPieceRank() ) { 
                            players[i].piece.get(j).setCaptureStatus(true);
                            players[i].piece.remove(j); //players[i].piece.get(j).setX(-100);
                            this.setY(this.getY() - 3);
                            return 1;
                        }
                        else return 2; // Found piece is higher ranking
                    }
                    else // (srcPiece.Color() == players[i].Color() )
                        return 2; //If piece is same color, return invalid move (2)
                }
            }
        }
        return 3;
    }
}