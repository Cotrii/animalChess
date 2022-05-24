package model;
/**
 * Class that contains the different moves that could happen in the board
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */
public class Move {

    /** A move needs to consist of a piece in order for it to process*/
    Piece srcPiece;

    /**
     * Constructor for Move
     */
    public Move(){
        srcPiece = null;
    }
    
    /**
     * Boolean method that checks if the move is valid or not for an animal's den
     * @param board board object
     * @param location desired movement or destination of the piece
     * @return boolean - true it is not its den, false if it is. 
     */
    public boolean bNotOwnDen(Board board, String location)
    {
        if (srcPiece.getPieceColor()) {
            if ( (location.equals("S") && (board.tiles[(srcPiece.getX())+1][srcPiece.getY()].terrain.getTerrain()).equals("R-Den"))   ||
                 (location.equals("A") && (board.tiles[(srcPiece.getX())][srcPiece.getY() - 1].terrain.getTerrain()).equals("R-Den")) ||
                 (location.equals("D") && (board.tiles[(srcPiece.getX())][srcPiece.getY() + 1].terrain.getTerrain()).equals("R-Den"))  )
                    return false; //South , West, East for Red Team
        }
        else //Blue Team - North, West, East
        {
            if ( (location.equals("W") && (board.tiles[(srcPiece.getX())-1][srcPiece.getY()].terrain.getTerrain()).equals("B-Den"))   ||
                 (location.equals("A") && (board.tiles[(srcPiece.getX())][srcPiece.getY() - 1].terrain.getTerrain()).equals("B-Den")) ||
                 (location.equals("D") && (board.tiles[(srcPiece.getX())][srcPiece.getY() + 1].terrain.getTerrain()).equals("B-Den"))  )
                    return false;
        }
        return true; //if not own den
    }

    /**
     * Boolean method that checks if the move is valid or out of bounds
     * @param location desired movement or destination of the piece
     * @return boolean - true it is out of bounds, false if move is valid
     */
    public boolean bIsMoveOutOfBounds (String location)
    {
        if (location.equals("W") && srcPiece.getX() == 0) 
            return true;    // If piece is on one of the northern-edge tile 
        else if (location.equals("S") && srcPiece.getX() == 8) 
            return true;    // If piece is on one of the southern-edge tile
        else if (location.equals("A") && srcPiece.getY() == 0) 
            return true;    // If piece is on one of the western-edge tile
        else return location.equals("D") && srcPiece.getY() == 6;    // If piece is on one of the eastern-edge tile
//piece is not on the edge of the board
    }


    /**
     * Initialize a new northward move with respect to the piece played, includes only the source piece and the initialized board.  
     * @param board current state of the board display
     * @param players player
     * @return boolean if move is valid (true) or not (false)
     */
    public boolean moveNorth ( Board board, Player[] players) {
        int val;

        // Checks if move is out of bounds
        if (!bIsMoveOutOfBounds("W")) {
            if ( (board.tiles[(srcPiece.getX())-1][srcPiece.getY()].terrain.getTerrain() ).equals("River")  ) // if next tiles is a river
            {
                // Checks if source piece is a tiger or lion (can jump on river) or mouse (can move on river)
                if ((srcPiece instanceof Tiger) || (srcPiece instanceof Lion) || (srcPiece instanceof Mouse))
                {
                    if (srcPiece instanceof Mouse){
                        val = ((Mouse) srcPiece).moveUpInsideRiver(players, board);
                    }
                    else{
                        if (srcPiece instanceof Tiger)
                            val = ((Tiger) srcPiece).isEnemyOnNorthRiver(board, players);
                        else //if (srcPiece instanceof Lion)
                            val = ((Lion) srcPiece).isEnemyOnNorthRiver(board, players);
                    }

                    if (val % 2 == 0)   return false;
                    else if (val == 3) {
                        if (srcPiece instanceof Mouse)
                            srcPiece.setX(srcPiece.getX() - 1); 
                        else    
                            srcPiece.setX(srcPiece.getX() - 4);

                        return true;
                    }
                    else /* val == 1 */ return true;
                }
                else {
                    return false; //System.out.println("Invalid Move");
                }
            }
            else {

                val = isEnemyOnNorthLand(board, players);

                //if val == 2
                if (val == 3) {
                    if (bNotOwnDen(board, "W")){
                        srcPiece.setX(srcPiece.getX() - 1);
                        board.tiles[(srcPiece.getX())][srcPiece.getY()].terrain.EffectOnTerrain(srcPiece, board);
                        return true;
                    }
                    else
                        return false;
                }
                else return val == 1; //
            }
        }
        // Move is out of bounds
        else {
            return false;
        }
    }


    /**
     * Checks if there is an enemy or ally towards North in Land
     * Return value: 1 - enemy for capture, 2 - found enemy with higher rank or ally (invalid), 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    public int isEnemyOnNorthLand (Board board, Player[] players)
    {
        for (Player player : players) {
            for (int j = 0; j < player.piece.size(); j++) {
                //If statement - checks If there is a piece or not (whether ally or enemy on next if statement)
                if ((srcPiece.getX()) - 1 == (player.piece.get(j).getX()) && (srcPiece.getY()) == (player.piece.get(j).getY())) {
                    if (srcPiece.getPieceColor() != player.piece.get(j).getPieceColor()
                            && !board.tiles[(srcPiece.getX())][srcPiece.getY()].terrain.getTerrain().equals("River")) {
                        if (Elephant.srcElephantAndMouse(srcPiece, player.piece.get(j))) {
                            return 2; // Source Piece is Elephant and Target Piece is Mouse = Invalid
                        } else if (srcPiece.getPieceRank() >= player.piece.get(j).getPieceRank() || Mouse.isSrcMouseCanCaptureElephant(srcPiece, player.piece.get(j))) {
                            player.piece.get(j).setCaptureStatus(true);
                            player.piece.remove(j);
                            srcPiece.setX(srcPiece.getX() - 1);
                            return 1;
                        } else return 2; // Found piece is higher ranking
                    } else // (srcPiece.Color() == players[i].piece[j]PieceColor() )
                        return 2; //If piece is same color return invalid move (2)
                }
            }
        }
        return 3; //No piece found, free to move
    }

    /**
     * Initialize a new southward move with respect to the piece played, includes only the source piece and the initialized board. 
     * @param board current status of the board
     * @param players player
     * @return boolean if move is valid (true) or not (false)
     */
    public boolean moveSouth (Board board, Player[] players) {
        int val;

        // Checks if move is out of bounds
        if (!bIsMoveOutOfBounds("S")) {
            if ( (board.tiles[(srcPiece.getX())+1][srcPiece.getY()].terrain.getTerrain() ).equals("River")   ) // // if next tiles is a river
            {
                // Check if source piece is a tiger or lion (can jump on river)
                if ((srcPiece instanceof Tiger) || (srcPiece instanceof Lion) || (srcPiece instanceof Mouse))
                {
                    if (srcPiece instanceof Mouse){
                        val = ((Mouse) srcPiece).moveDownInsideRiver(players, board);
                    }
                    else{
                        if (srcPiece instanceof Tiger)
                            val = ((Tiger) srcPiece).isEnemyOnSouthRiver(board, players);
                        else //if (srcPiece instanceof Lion)
                            val = ((Lion) srcPiece).isEnemyOnSouthRiver(board, players);
                    }

                    if (val % 2 == 0)   return false;
                    else if (val == 3) {
                        if (srcPiece instanceof Mouse)
                            srcPiece.setX(srcPiece.getX() + 1); 
                        else    
                            srcPiece.setX(srcPiece.getX() + 4);

                        return true;
                    }
                    else /*val == 1*/ return true; 
                }
                else {
                    System.out.println("Invalid Move");
                    return false;
                }
            }
            else {
                val = isEnemyOnSouthLand(board, players);

                //val == 2
                if (val == 3) {
                    if (bNotOwnDen(board, "S")) {
                        srcPiece.setX(srcPiece.getX() + 1); 
                        board.tiles[(srcPiece.getX())][srcPiece.getY()].terrain.EffectOnTerrain(srcPiece, board);
                        return true;
                    }
                    else
                        return false;
                }
                else return val == 1; //else if (val == 1) return true; else /* val == 2 */ return false;
            }
        }
        // Move is out of bounds
        else {
            return false;
        }
    }


    /**
     * Checks if there is an enemy or ally towards South in Land
     * Return value: 1 - enemy for capture, 2 - found enemy with higher rank or ally (invalid), 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    public int isEnemyOnSouthLand (Board board, Player[] players)
    {
        for (Player player : players) {
            for (int j = 0; j < player.piece.size(); j++) {
                //If statement - checks If there is a piece or not (whether ally or enemy on next if statement)
                if ((srcPiece.getX()) + 1 == (player.piece.get(j).getX()) && (srcPiece.getY()) == (player.piece.get(j).getY())) {
                    if (srcPiece.getPieceColor() != player.piece.get(j).getPieceColor() && !board.tiles[(srcPiece.getX())][srcPiece.getY()].terrain.getTerrain().equals("River")) {
                        if (Elephant.srcElephantAndMouse(srcPiece, player.piece.get(j))) {
                            return 2; // Source Piece is Elephant and Target Piece is Mouse = Invalid
                        } else if (srcPiece.getPieceRank() >= player.piece.get(j).getPieceRank() || Mouse.isSrcMouseCanCaptureElephant(srcPiece, player.piece.get(j))) {
                            player.piece.get(j).setCaptureStatus(true);
                            player.piece.remove(j);
                            srcPiece.setX(srcPiece.getX() + 1);
                            return 1;
                        } else return 2; // Found piece is higher ranking
                    } else // (srcPiece.Color() == players[i].piece[j]PieceColor() )
                        return 2; //If piece is same color return invalid move (2)
                }
            }
        }
        return 3; //No piece found, free to move
    }


    /**
     * Initialize a new eastward move with respect to the piece played, includes only the source piece and the initialized board.  
     * @param board current status of the board
     * @param players player
     * @return boolean if move is valid (true) or not (false)
     */
    public boolean moveEast (Board board, Player[] players) {
        int val;

        // Checks if move is out of bounds
        if (!bIsMoveOutOfBounds("D")) {
            if ( (board.tiles[(srcPiece.getX())][srcPiece.getY()+1].terrain.getTerrain() ).equals("River")  ) // checks if next tiles is a river
            {
                //  checks if source piece is a tiger (can jump on river)
                if ((srcPiece instanceof Tiger) || (srcPiece instanceof Lion) || (srcPiece instanceof Mouse))
                {
                    if (srcPiece instanceof Mouse){
                        val = ((Mouse) srcPiece).moveRightInsideRiver(players, board); //orig: moveRightInsideRiver(srcPiece, players, board);
                    }
                    else{
                        if (srcPiece instanceof Tiger)
                            val = ((Tiger) srcPiece).isEnemyOnEastRiver(board, players);
                        else //if (srcPiece instanceof Lion)
                            val = ((Lion) srcPiece).isEnemyOnEastRiver(board, players);
                    }

                    if (val % 2 == 0) return false;
                    else if (val == 3) {
                        if (srcPiece instanceof Mouse)
                            srcPiece.setY(srcPiece.getY() + 1);
                        else
                            srcPiece.setY(srcPiece.getY() + 3);

                        return true;
                    }
                    else /*val == 1*/ return true;
                }
                else {
                    return false; //System.out.println("Invalid Move");
                }
            }
            else {
                val = isEnemyOnEastLand(board, players);

                //val == 2
                if (val == 3){
                    if (bNotOwnDen(board, "D")){
                        srcPiece.setY(srcPiece.getY() + 1); //new code
                        board.tiles[(srcPiece.getX())][srcPiece.getY()].terrain.EffectOnTerrain(srcPiece, board);
                        return true;
                    }
                    else
                        return false;
                }
                else return val == 1;  //else if (val == 1) return true; else /* val == 2 */ return false;
            }
        }
        // Move is out of bounds
        else {
            return false;
        }
    }

    /**
     * Checks if there is an enemy or ally towards East in Land
     * Return value: 1 - enemy for capture, 2 - found enemy with higher rank or ally (invalid), 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    public int isEnemyOnEastLand (Board board, Player[] players)
    {
        for (Player player : players) {
            for (int j = 0; j < player.piece.size(); j++) {
                //If statement - checks If there is a piece or not (whether ally or enemy on next if statement)
                if ((srcPiece.getX()) == (player.piece.get(j).getX()) && (srcPiece.getY()) + 1 == (player.piece.get(j).getY())) {
                    if (srcPiece.getPieceColor() != player.piece.get(j).getPieceColor() && !board.tiles[(srcPiece.getX())][srcPiece.getY()].terrain.getTerrain().equals("River")) {
                        if (Elephant.srcElephantAndMouse(srcPiece, player.piece.get(j))) {
                            return 2; // Source Piece is Elephant and Target Piece is Mouse = Invalid
                        } else if (srcPiece.getPieceRank() >= player.piece.get(j).getPieceRank() || Mouse.isSrcMouseCanCaptureElephant(srcPiece, player.piece.get(j))) {
                            player.piece.get(j).setCaptureStatus(true);
                            player.piece.remove(j);
                            srcPiece.setY(srcPiece.getY() + 1);
                            return 1;
                        } else return 2; // Found piece is higher ranking
                    } else // (srcPiece.Color() == players[i].Color() )
                        return 2; //If piece is same color, return invalid move (2)
                }
            }
        }
        return 3; //No piece found, free to move
    }

    /**
     * Initialize a new westward move with respect to the piece played, includes only the source piece and the initialized board.  
     * @param board current status of the board
     * @param players player
     * @return boolean if move is valid (true) or not (false)
     */
    public boolean moveWest (Board board, Player[] players) {
        int val;

        // Checks if move is out of bounds
        if (!bIsMoveOutOfBounds("A")) {
            if ( (board.tiles[(srcPiece.getX())][srcPiece.getY()-1].terrain.getTerrain() ).equals("River") )  // if next tiles is a river
            {
                // if statement that checks if source piece is a tiger (can jump on river)
                if ((srcPiece instanceof Tiger) || (srcPiece instanceof Lion) || (srcPiece instanceof Mouse))
                {
                    if (srcPiece instanceof Mouse){
                        val = ((Mouse) srcPiece).moveLeftInsideRiver( players, board);
                    }
                    else{
                        if (srcPiece instanceof Tiger)
                            val = ((Tiger) srcPiece).isEnemyOnWestRiver(board, players);
                        else //if (srcPiece instanceof Lion)
                            val = ((Lion) srcPiece).isEnemyOnWestRiver(board, players);
                    }

                    if (val % 2 == 0)
                        return false;
                    else if (val == 3) {
                        if (srcPiece instanceof Mouse)
                            srcPiece.setY(srcPiece.getY() - 1); 
                        else    
                            srcPiece.setY(srcPiece.getY() - 3);

                        return true;
                    }
                    else /*val == 1*/ return true; 
                }
                else {
                    System.out.println("Invalid Move");
                    return false;
                }
            }
            else {
                val = isEnemyOnWestLand(board, players);

                /* val == 2 */
                if (val == 3) {
                    if (bNotOwnDen(board, "A")) {
                        srcPiece.setY(srcPiece.getY() - 1);
                        board.tiles[(srcPiece.getX())][srcPiece.getY()].terrain.EffectOnTerrain(srcPiece, board);
                        return true;
                    }
                    else    return false;
                }
                else return val == 1;  // else if (val == 1) return true; else /* val == 2 */ return false;
            }
        }
        // Move is out of bounds
        else {
            return false;
        }
    }

    /**
     * Checks if there is an enemy or ally towards West in Land
     * Return value: 1 - enemy for capture, 2 - found enemy with higher rank or ally (invalid), 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    public int isEnemyOnWestLand (Board board, Player[] players)
    {
        for (Player player : players) {
            for (int j = 0; j < player.piece.size(); j++) {
                //If statement - checks If there is a piece or not (whether ally or enemy on next if statement)
                if ((srcPiece.getX()) == (player.piece.get(j).getX()) && (srcPiece.getY()) - 1 == (player.piece.get(j).getY())) {
                    if (srcPiece.getPieceColor() != player.piece.get(j).getPieceColor() && !board.tiles[(srcPiece.getX())][srcPiece.getY()].terrain.getTerrain().equals("River")) {
                        if (Elephant.srcElephantAndMouse(srcPiece, player.piece.get(j))) {
                            return 2; // Source Piece is Elephant and Target Piece is Mouse = Invalid
                        } else if (srcPiece.getPieceRank() >= player.piece.get(j).getPieceRank() || Mouse.isSrcMouseCanCaptureElephant(srcPiece, player.piece.get(j))) {
                            player.piece.get(j).setCaptureStatus(true);
                            player.piece.remove(j);
                            srcPiece.setY(srcPiece.getY() - 1);
                            return 1;
                        } else return 2; // Found piece is higher ranking
                    } else // (srcPiece.Color() == players[i].Color() )
                        return 2; //If piece is same color, return invalid move (2)
                }
            }
        }
        return 3; //No piece found, free to move
    }

    /**
     * sets a piece obj for Move
     * @param piece obj for class
     */
    public void setSrcPiece(Piece piece)
    {
        srcPiece = piece;
    }
}