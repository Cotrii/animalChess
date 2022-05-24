package model;
/**
 * It has a variable named tiles (array of Object Square)
 * that each element represents each square tile of the board.
 * The different types of terrain are also initialized with
 * Terrain as its data type.
 * 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */
public class Board
{
    /**
     * public tiles or square of a board
     */
    public Square[][] tiles = new Square[9][7];
    
    /**
     * public TrapTerrain variable with Terrain data type. Initializes new Trap Terrain.
     */
    public Terrain TrapTerrain = new Terrain("Trap ");
    /**
     * public LandTerrain variable with Terrain data type. Initializes new Land Terrain.
     */
    public Terrain LandTerrain = new Terrain("Land ");
    /**
     * public RiverTerrain variable with Terrain data type. Initializes new River Terrain.
     */
    public Terrain RiverTerrain = new Terrain("River");
    /**
     * public BlueDenTerrain variable with Terrain data type. Initializes new Blue Den Terrain.
     */
    public Terrain BlueDenTerrain = new Terrain("B-Den");
    /**
     * public RedDenTerrain variable with Terrain data type. Initializes new Red Den Terrain.
     */
    public Terrain RedDenTerrain = new Terrain("R-Den");

    /** 
     * Initializes the board (tiles) to its respective groups or terrain
     * and also piece elements that contains different values
     */
    public void initializeBoard () {
    
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[i].length; j++)
            {
                //new code - //River tiles containing values from x: 3 to 5 and y: 1 to 5 excluding 3
                if ( (i >= 3 && i <= 5) && (j == 1 || j == 2 || j == 4 || j == 5 ) ){
                    tiles[i][j] = new Square (i, j, RiverTerrain);
                }
                else
                    tiles[i][j] = new Square(i, j, LandTerrain); //Check spacing which could disrupt when comparing it to other variables/values
            }
        }

        tiles[0][2] = new Square(0, 2, TrapTerrain); // Trap near Blue Den
        tiles[0][4] = new Square(0, 4, TrapTerrain); // Trap near Blue Den
        tiles[1][3] = new Square(1, 3, TrapTerrain); // Trap near Blue Den

        tiles[8][2] = new Square(8, 2, TrapTerrain); // Trap near Red Den
        tiles[8][4] = new Square(8, 4, TrapTerrain); // Trap near Red Den
        tiles[7][3] = new Square(7, 3, TrapTerrain); // Trap near Red Den

        tiles[0][3] = new Square(0, 3, BlueDenTerrain); // Blue Den
        tiles[8][3] = new Square(8, 3, RedDenTerrain); //Red Den
    }

    /** 
     * Checks if a piece is in a certain spot/ square of the board
     * Returns a string Animal if i and j matches x and y then null if false
     * 
     * @param i i or row index of Piece
     * @param j j or col index of Piece
     * @param player player
     * @return a String containing an animal's name
     */
    public String pieceinPos(int i, int j, Player player)
    {
        for (int k = 0; k < player.piece.size(); k++) 
        {
            if ((i == player.piece.get(k).getX()) && (j == player.piece.get(k).getY()) && player.piece.get(k).getCaptureStatus() != true ) 
            {
                return player.piece.get(k).getAnimalName();
            }
        }

        return null;
    }
    
    /**
     * It displays every element in a format that tries to represent the chess Board
     * @param players player
     */
    public void displayBoard (Player[] players) 
    {
        System.out.println(); //to avoid confusion
        
        for (int i = 0; i < tiles.length; i++) 
        {
            for (int j = 0; j < tiles[i].length; j++) 
            {
                System.out.print(" " + tiles[i][j].terrain.getTerrain() + " |");
            }

            System.out.println();

            for (int j = 0; j < tiles[i].length; j++)
            {
                if (pieceinPos(i, j, players[0]) != null)
                { 
                        System.out.print(" " + pieceinPos(i, j, players[0]) + " |");
                }
                else if (pieceinPos(i, j, players[1]) != null)
                {
                    System.out.print(" " + pieceinPos(i, j, players[1]) + " |");
                }
                else
                    System.out.print("       |");
            }
        
            System.out.println("\n----------------------------------------------------------");
        }
    }
}