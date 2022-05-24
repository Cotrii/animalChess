package model;
import java.util.ArrayList;
/**
 * Player Class for the two players of the game
 * 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */

class Player
{
    /**
     * An arraylist of pieces for each player
     */
    public ArrayList<Piece> piece = new ArrayList<> ();
    
    /**
     * Sets a player's pieces depending on player's team color
     * @param color red team if it is true, blue team if false.
     */
    Player (boolean color)
    {
        if (color)
        {
            piece.add(0, new Elephant ("1-Ele", 6, 0, color, 8, false));
            piece.add(1, new Lion ("1-Lio", 8, 6, color, 7, false));
            piece.add(2, new Tiger ("1-Tig", 8, 0, color, 6, false));
            piece.add(3, new Leopard ("1-Leo", 6, 4, color, 5, false));
            piece.add(4, new Dog ("1-Dog", 7, 5, color, 4, false));
            piece.add(5, new Wolf ("1-Wlf", 6, 2, color, 3, false));
            piece.add(6, new Cat ("1-Cat", 7, 1, color, 2, false));
            piece.add(7, new Mouse ("1-Mou", 6, 6, color, 1, false));
        }
        else 
        {
            piece.add(0, new Elephant ("2-Ele", 2, 6, color, 8, false));
            piece.add(1, new Lion("2-Lio", 0, 0, color, 7, false));
            piece.add(2, new Tiger("2-Tig", 0, 6, color, 6, false));
            piece.add(3, new Leopard ("2-Leo", 2, 2, color, 5, false));
            piece.add(4, new Dog ("2-Dog", 1, 1, color, 4, false));
            piece.add(5, new Wolf ("2-Wlf", 2, 4, color, 3, false));
            piece.add(6, new Cat ("2-Cat", 1, 5, color, 2, false));
            piece.add(7, new Mouse ("2-Mou", 2, 0, color, 1, false)); 
        }
    }
}