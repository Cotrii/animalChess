package model;
/**
 * Wolf Subclass for Piece 
 * 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */
public class Wolf extends Piece
{
    /**
     * Inheritance method for Wolf piece
     * @param animal name of animal
     * @param nX row index
     * @param nY col index
     * @param bPieceColor color of piece
     * @param nPieceRank piece's ranking
     * @param bCaptureStatus if true, then it is captured. if false it has not been captured
     */
    Wolf (String animal, int nX, int nY, boolean bPieceColor, int nPieceRank, boolean bCaptureStatus) {
        super(animal, nX, nY, bPieceColor, nPieceRank, bCaptureStatus);
    }
}