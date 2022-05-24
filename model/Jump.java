package model;
/**
 * This interface class allows the board pieces to jump across the river terain
 * 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */

public interface Jump
{
    /**
     * Checks if there is an enemy or ally in the river towards North
     * Return value: 1 - enemy for capture, 2 - found enemy with higher rank or ally (invalid), 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    public int isEnemyOnNorthRiver (Board board, Player[] players);

    /**
     * Checks if there is an enemy or ally in the river towards South 
     * Return value: 1 - enemy for capture, 2 - found enemy with higher rank or ally (invalid), 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    public int isEnemyOnSouthRiver (Board board, Player[] players);

    /**
     * Checks if there is an enemy or ally in the river towards East 
     * Return value: 1 - enemy for capture, 2 - found enemy with higher rank or ally (invalid), 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    public int isEnemyOnEastRiver (Board board, Player[] players);

    /**
     * Checks if there is an enemy or ally in the river towards West
     * Return value: 1 - enemy for capture, 2 - found enemy with higher rank or ally (invalid), 3 - No piece is on the square
     * @param board current status of the board
     * @param players player
     * @return int values 1, 2 or 3
     */
    public int isEnemyOnWestRiver (Board board, Player[] players);
}