package model;
/**
 * Contains variables of terrain
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */
public class Terrain {
    /**
     * String name for a terrain
     */
    public String terrain;

    /**
     * Sets a terrain with a String
     * @param terrain terrain name
     */
    public Terrain (String terrain) {
        this.setTerrain(terrain);
    }

    //Setter
    /**
     * Sets a terrain using a string
     * @param terrain string for terrain
     */
    public void setTerrain(String terrain){
        this.terrain = terrain;
    }

    //Getter
    /**
     * Gets terrain using a string
     * @return string that contains a terrain's name
     */
    public String getTerrain(){
        return this.terrain;
    }

    /**
     * Void method that modifies the piece's rank when on terrain
     * @param srcPiece chosen piece
     * @param board board object
     */
    public void EffectOnTerrain(Piece srcPiece, Board board)
    {
        // if statement that checks if source piece is on Trap terrain
        if ( board.tiles[(srcPiece.getX())][srcPiece.getY()].terrain.getTerrain().equals("Trap ") ) {

            if ( srcPiece.getX() == 0 && (srcPiece.getY() == 2 || srcPiece.getY() == 4) || (srcPiece.getX() == 1 && srcPiece.getY() == 3) )
            {
                if (srcPiece.getPieceColor() == true )
                    srcPiece.setPieceRank(0); 
            }
            else if ( srcPiece.getX() == 8 && (srcPiece.getY() == 2 || srcPiece.getY() == 4) || (srcPiece.getX() == 7 && srcPiece.getY() == 3) )
            {
                if (srcPiece.getPieceColor() == false )
                    srcPiece.setPieceRank(0); 
            }
        }

        // if statement that checks if source piece is on Land terrain
        if ( board.tiles[(srcPiece.getX())][srcPiece.getY()].terrain.getTerrain().equals("Land ") ) {
            if (srcPiece instanceof Elephant) {
                srcPiece.setPieceRank(8); 
            }
            else if (srcPiece instanceof Lion) {
                srcPiece.setPieceRank(7); 
            }
            else if (srcPiece instanceof Tiger) {
                srcPiece.setPieceRank(6); 
            }
            else if (srcPiece instanceof Leopard) {
                srcPiece.setPieceRank(5); 
            }
            else if (srcPiece instanceof Dog) {
                srcPiece.setPieceRank(4); 
            }
            else if (srcPiece instanceof Wolf) {
                srcPiece.setPieceRank(3); 
            }
            else if (srcPiece instanceof Cat) {
                srcPiece.setPieceRank(2); 
            }
            else if (srcPiece instanceof Mouse) {
                srcPiece.setPieceRank(1); 
            }
        }
    }
}