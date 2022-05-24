package model;
/**
 * Each piece of the game has it's own name (String), X and Y 
 * coordinate (both are Integers) which corresponds to the 9x7 
 * board, piece team color (Boolean), rank among other pieces
 * (Integer), and it's capture status (Boolean)
 * 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */
public abstract class Piece
{
    /**
     * animal's name in String
     */
    private String animal;

    /**
     * X location of piece
     */
    private int nX;

    /**
     * Y location of piece
     */
    private int nY;

    /**
     * color of piece
     */
    private boolean bPieceColor;

    /**
     * Rank of piece
     */
    private int nPieceRank;

    /**
     * capture status of Piece
     */
    private boolean bCaptureStatus;

    // Constructor

    /**
     * Constructor that contains an String animal, nX and nY for respective row and column values,
     * a boolean for color, a ranking of an animal and also a capture status in boolean form (true for captured,
     * false for not captured)
     * @param animal String of animal's name
     * @param nX location of piece in X
     * @param nY location of piece in Y
     * @param bPieceColor color of piece
     * @param nPieceRank rank of piece
     * @param bCaptureStatus true for captured, false for not captured
     */
    public Piece(String animal, int nX, int nY, boolean bPieceColor, int nPieceRank, boolean bCaptureStatus) {
        this.animal = animal;
        this.setX(nX);
	    this.setY(nY);
        this.bPieceColor = bPieceColor;
	    this.nPieceRank = nPieceRank;
        this.setCaptureStatus(bCaptureStatus);
    }

    // Getters

    /**
     * Returns a string containing an animal name
     * @return a String containing an animal's name
     */
    public String getAnimalName () {
        return this.animal;
    }
    
    /**
     * Returns an int of X
     * @return an int containing X value
     */
    public int getX () {
	    return this.nX;
    }
  
    /**
     * Returns an int of Y
     * @return an int containing Y value
     */
    public int getY () {
	    return this.nY;
    }

    /**
     * Returns a boolean of a color
     * @return boolean: true for Red, false for Blue
     */
    public boolean getPieceColor () {
        return this.bPieceColor;
    }

    /**
     * Return an int
     * @return int: that contains ranking of an animal
     */
    public int getPieceRank () {
        return this.nPieceRank;
    }

    /**
     * Return an boolean if it's captured or not
     * @return boolean: true for Captured, false for Not Captured
     */
    public boolean getCaptureStatus () {
        return this.bCaptureStatus;
    }
  
    // Setters

    /**
     * Set X to a value
     * @param nX sets a value for X
     */
    public void setX(int nX){
        this.nX = nX;
    }
    
    /**
     * Set Y to a value
     * @param nY sets a value for Y
     */
    public void setY(int nY){
        this.nY = nY;
    }

    /**
     * Sets a capture status
     * @param bCaptureStatus sets a capture status of true or false
     */
    public void setCaptureStatus(boolean bCaptureStatus){
        this.bCaptureStatus = bCaptureStatus;
    }
    
    /**
     * Sets a new rank for a piece
     * @param nPieceRank sets a new rank for Piece
     */
    public void setPieceRank(int nPieceRank){
        this.nPieceRank = nPieceRank;
    }

    /**
     * Void method that removes piece if it is captured
     * @param player player
     * @param j player's piece
     */
    public static void removePiece(Player player, int j){
        player.piece.get(j).setCaptureStatus(true);
        player.piece.remove(j);
    }
}