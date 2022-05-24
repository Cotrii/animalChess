package model;
/**
 * Contains variables of row, col, terrain
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */
public class Square 
{
    /**
     * row index for Square
     */
    private int row;
    /**
     * column index for Square
     */
    private int col;
    /**
     * Terrain object for terrain
     */
    public Terrain terrain;
    
    // Constructors 

    /**
     * Sets a square with an int row and column and also, a String terrain
     * @param row row of Square
     * @param col col of Square
     * @param terrain terrain name of Square
     */
    public Square (int row, int col, Terrain terrain){
        this.setRow(row);
        this.setCol(col);
        this.setTerrain(terrain);
    }
    
    // Setters

    /**
     * Sets a new row index for a square
     * @param row index for row
     */
    public void setRow(int row){
        this.row = row;
    }

    /**
     * Sets a new column index for a square
     * @param col index for col
     */
    public void setCol(int col){
        this.col = col;
    }

    /**
     * Sets a terrain using a string
     * @param terrain string for terrain
     */
    public void setTerrain(Terrain terrain){
        this.terrain = terrain;
    }

    // Getters
    /**
     * Get row of a square
     * @return int that contains row index
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Gets row of a square
     * @return int that contains column index
     */
    public int getCol(){
        return this.col;
    }

    /**
     * Gets terrain using a string
     * @return string that contains a terrain's name
     */
    public Terrain getTerrain(){
        return this.terrain;
    }
}