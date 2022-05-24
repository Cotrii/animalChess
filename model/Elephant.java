package model;
/**
 * Elephant Subclass for Piece 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */
public class Elephant extends Piece
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
    Elephant (String animal, int nX, int nY, boolean bPieceColor, int nPieceRank, boolean bCaptureStatus) {
        super(animal, nX, nY, bPieceColor, nPieceRank, bCaptureStatus);
    }

    /**
     * Boolean method that checks if srcPiece is an elephant piece and if targetPiece is a mouse piece
     * @param srcPiece chosen piece
     * @param targetPiece piece of destination tile
     * @return boolean - true pieces are both elephant and mouse pieces, false if otherwise.
     */
    public static boolean srcElephantAndMouse(Piece srcPiece, Piece targetPiece)
    {
        if (srcPiece instanceof Elephant)
        {
            if (targetPiece instanceof Mouse)
            {
                return true;
            }
        }
        return false;
    }
}