package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

/**
 * Layout Class that creates a new chess board window that contains
 * the actual game and its starting layout.
 * 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */

public class Layout
{
    /** public JFrame for the Chess Board Window */
    public JFrame frame = new JFrame("Chess Board");
    /** public JPanel for the header of the Chess Board Window */
    public JPanel title_panel = new JPanel();
    /** public JPanel for the main panel of the Chess Board Window */
    public JPanel panel = new JPanel();

    /** public JButton for the initialization of a new board or resets current board in the Chess Board Window */
    public JButton new_reset = new JButton("New / Reset");
    /** public JButton for the verification of each player's move during the game in the Chess Board Window */
    public JButton end_turn = new JButton("End Turn");
    /** public JTextArea for the visual reference of who's turn is it during the game in the Chess Board Window*/
    public JTextArea dialogue_box = new JTextArea("ANIMAL CHESS GAME");

    /** public array of JButtons for each of the tiles of the board in the Chess Board Window */
    public JButton[][] squares = new JButton[9][7];
    /** public array of JLabels for each of the pieces of the board in the Chess Board Window */
    public JLabel[][] pieces = new JLabel[2][8];

    /** private row variable with integer data type for determining i or row index */
    private int row;
    /** private col variable with integer data type for determining j or col index */
    private int col;
    /** private isFirstMove variable with boolean data type for determining which player/team moves first */
    private boolean isFirstMove = true;

    /** private bIsValid variable with boolean data type for determining if move performed is valid */
    private boolean bIsValid = false;
    /** private pColorTurn variable with boolean data type for determining if piece can be played/moved by player */
    private boolean pColorTurn = true;
    /** private numAnimal variable with integer data type for determining the numerical equivalent of the animal piece */
    private int numAnimal = 0;
    /** private stringMove variable with String data type for determining the string equivalent of the move performed */
    private String stringMove = null; //this should be set to null after every successful move
    /** private isOwnPiece variable with boolean data type for determining if chosen piece is the for the player or not */
    private boolean isOwnPiece = true; //check if own piece or not

    /** private chosenSquare variable with JButton data type for the target square chosen when making a move */
    private JButton chosenSquare = new JButton();  

    /** ImageIcon for the Land terrain */
    ImageIcon land = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/terrain/land.png")));
    /** ImageIcon for the River terrain */
    ImageIcon river = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/terrain/river.png")));
    /** ImageIcon for the Trap terrain */
    ImageIcon trap = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/terrain/trap.png")));
    /** ImageIcon for the Den terrain */
    ImageIcon den = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/terrain/den.jpg")));

    /** ImageIcon for the Red Mouse piece*/
    ImageIcon rMouse = new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Red-Mouse.png")));
    /** ImageIcon for the Red Cat piece */
    ImageIcon rCat = new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Red-Cat.png")));
    /** ImageIcon for the Red Wolf piece */
    ImageIcon rWolf = new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Red-Wolf.png")));
    /** ImageIcon for the Red Dog piece */
    ImageIcon rDog = new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Red-Dog.png")));
    /** ImageIcon for the Red Leopard piece */
    ImageIcon rLeopard = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Red-Leopard.png")));
    /** ImageIcon for the Red Tiger piece */
    ImageIcon rTiger = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Red-Tiger.png")));
    /** ImageIcon for the Red Lion piece */
    ImageIcon rLion = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Red-Lion.png")));
    /** ImageIcon for the Red Elephant piece */
    ImageIcon rElephant = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Red-Elephant.png")));

    /** ImageIcon for the Blue Mouse piece */
    ImageIcon bMouse = new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Blue-Mouse.png")));
    /** ImageIcon for the Blue Cat piece */
    ImageIcon bCat = new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Blue-Cat.png")));
    /** ImageIcon for the Red Wolf piece */
    ImageIcon bWolf = new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Blue-Wolf.png")));
    /** ImageIcon for the Blue Dog piece */
    ImageIcon bDog = new ImageIcon (Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Blue-Dog.png")));
    /** ImageIcon for the Blue Leopard piece */
    ImageIcon bLeopard = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Blue-Leopard.png")));
    /** ImageIcon for the Blue Tiger piece */
    ImageIcon bTiger = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Blue-Tiger.png")));
    /** ImageIcon for the Blue Lion piece */
    ImageIcon bLion = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Blue-Lion.png")));
    /** mageIcon for the Blue Elephant piece */
    ImageIcon bElephant = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Blue-Elephant.png")));

    /**ButtonHandler for the movement of the pieces through the JButtons and Action Listener */
    public ButtonHandler buttonHandler = new ButtonHandler();

    /**
    * Creates a new Chess Board Window
    */
    public Layout()
    {
        createHeader();
        panel.setLayout(new GridLayout(9, 7));

        initializeBoard();

        setInitialImageforPiece();

        startPiece();

        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 800);
        frame.setVisible(true);

        frame.setResizable(false);
    }

    /**
    * Creates header of the Chess Board Window
    */
    public void createHeader() {
        title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(20,20,800,100);

        new_reset.setFont(new Font("Cooper Black", Font.BOLD, 16));
        end_turn.setFont(new Font("Cooper Black", Font.BOLD, 16));
        new_reset.setBackground(new Color(255, 153, 153));
        end_turn.setBackground(new Color(217, 255, 179));
        dialogue_box.setEditable(false);

        title_panel.add(new_reset, BorderLayout.WEST);
        title_panel.add(end_turn, BorderLayout.CENTER);

        JScrollPane scroll;
        scroll = new JScrollPane(dialogue_box);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(200, 100));

        title_panel.add(scroll, BorderLayout.EAST);
    }

    /** 
    * Initializes the animal pieces (images) to its respective JLabel
    */
    public void setInitialImageforPiece()
    {
        pieces[0][0] = new JLabel(rMouse);      pieces[0][1] = new JLabel(rCat);
        pieces[0][2] = new JLabel(rWolf);       pieces[0][3] = new JLabel(rDog);
        pieces[0][4] = new JLabel(rLeopard);    pieces[0][5] = new JLabel(rTiger);
        pieces[0][6] = new JLabel(rLion);       pieces[0][7] = new JLabel(rElephant);

        pieces[1][0] = new JLabel(bMouse);      pieces[1][1] = new JLabel(bCat);
        pieces[1][2] = new JLabel(bWolf);        pieces[1][3] = new JLabel(bDog);
        pieces[1][4] = new JLabel(bLeopard);    pieces[1][5] = new JLabel(bTiger);
        pieces[1][6] = new JLabel(bLion);       pieces[1][7] = new JLabel(bElephant);
    }

    /** 
    * Initializes the animal pieces (images) to its respective JLabel
    * @param colorFP boolean value determined from the result in the Menu Window
    */
    public void setFinalImageforPiece(boolean colorFP)
    {
        //if the winner player picks red
        if (colorFP)
        {
            pieces[0][0].setIcon(rMouse);      pieces[0][1].setIcon(rCat);
            pieces[0][2].setIcon(rWolf);       pieces[0][3].setIcon(rDog);
            pieces[0][4].setIcon(rLeopard);    pieces[0][5].setIcon(rTiger);
            pieces[0][6].setIcon(rLion);       pieces[0][7].setIcon(rElephant);

            pieces[1][0].setIcon(bMouse);      pieces[1][1].setIcon(bCat);
            pieces[1][2].setIcon(bWolf);        pieces[1][3].setIcon(bDog);
            pieces[1][4].setIcon(bLeopard);    pieces[1][5].setIcon(bTiger);
            pieces[1][6].setIcon(bLion);       pieces[1][7].setIcon(bElephant);
        }
        //if the winner player picks blue
        else
        {
            pieces[0][0].setIcon(bMouse);      pieces[0][1].setIcon(bCat);
            pieces[0][2].setIcon(bWolf);       pieces[0][3].setIcon(bDog);
            pieces[0][4].setIcon(bLeopard);    pieces[0][5].setIcon(bTiger);
            pieces[0][6].setIcon(bLion);       pieces[0][7].setIcon(bElephant);

            pieces[1][0].setIcon(rMouse);      pieces[1][1].setIcon(rCat);
            pieces[1][2].setIcon(rWolf);        pieces[1][3].setIcon(rDog);
            pieces[1][4].setIcon(rLeopard);    pieces[1][5].setIcon(rTiger);
            pieces[1][6].setIcon(rLion);       pieces[1][7].setIcon(rElephant);
        }
    }

    /** 
    * Sets the pieces in their respective places
    * @param i i or row index
    * @param j j or col index
    * @return boolean 
    */
    public boolean putPieceinStartingSquare(int i, int j)
    {
        //Blue first 
        if ( (i == 0 && (j == 0 || j == 6)) ||
             (i == 1 && (j == 1 || j == 5)) ||
             (i == 2 && (j % 2 == 0))       )
        {
                return true;
        }
        else return (i == 6 && (j % 2 == 0)) || (i == 7 && (j == 1 || j == 5)) || (i == 8 && (j == 0 || j == 6));
    }

    /** 
    * Adds the JLabel (pieces) in their respective starting JButton (square) positions
    * @param square JButton square/tile for the board
    * @param i i or row index
    * @param j j or col index
    */
    public void addImagePieces(JButton square, int i, int j)
    {
        //Player 2 Pieces
        if (i == 0)
        {
            if (j == 0)         square.add(pieces[1][6]); //Lion
            else if (j == 6)    square.add(pieces[1][5]); //Tiger
        }
        else if (i == 1) 
        {
            if  (j == 1)        square.add(pieces[1][3]); //Dog
            else if (j == 5)    square.add(pieces[1][1]); //Cat
        }
        else if (i == 2)
        {
            if (j == 0)         square.add(pieces[1][0]); //Mouse
            else if (j == 2)    square.add(pieces[1][4]); //Leopard
            else if (j == 4)    square.add(pieces[1][2]); //Wolf
            else if (j == 6)    square.add(pieces[1][7]); //Elephant
        }
        //Player 1 Pieces
        else if (i == 8)
        {
            if (j == 0)         square.add(pieces[0][5]); //Tiger
            else if (j == 6)    square.add(pieces[0][6]); //LIon
        }
        else if (i == 7) 
        {
            if  (j == 1)        square.add(pieces[0][1]); //Cat
            else if (j == 5)    square.add(pieces[0][3]); //Dog 
        }
        else if (i == 6)
        {
            if (j == 0)         square.add(pieces[0][7]); //Elephant
            else if (j == 2)    square.add(pieces[0][2]); //Wolf
            else if (j == 4)    square.add(pieces[0][4]); //Leopard
            else if (j == 6)    square.add(pieces[0][0]); //Mouse
        }
    }

    /** 
    * Initializes the board (JButton) to its respective JButtons type
    * and adds an action listener for each
    */
    public void initializeBoard()
    {
        for (int i = 0; i < squares.length; i++)
        {
            for (int j = 0; j < squares[i].length; j++)
            {
                squares[i][j] = new JButton();

                if ( (i >= 3 && i <= 5) && (j == 1 || j == 2 || j == 4 || j == 5 ) ){
                    squares[i][j] = new JButton(river);
                }
                else if ( (i == 0 && (j == 2 || j == 4)) || (i == 1 && j == 3) || (i == 8 && (j == 2 || j == 4)) || (i == 7 && j == 3) )
                {
                    squares[i][j] = new JButton(trap);
                }
                else if ( (i == 0 || i == 8) &&  j == 3){
                    squares[i][j] = new JButton(den);
                }
                else
                {
                    squares[i][j] = new JButton(land);
                }

                squares[i][j].addActionListener(buttonHandler);
                panel.add(squares[i][j]);
            }
        }
    }

    /** 
    * Resets the pieces back to their starting positions
    */
    public void resetPieces()
    {
        for (JButton[] square : squares) {
            for (int j = 0; j < square.length; j++) {
                Component[] components = square[j].getComponents();

                if (components.length > 0) {
                    square[j].remove(square[j].getComponent(0));
                    square[j].requestFocus();
                }
            }
        }
    }

    /** 
    * Initialize pieces in their respective starting positions
    */
    public void startPiece()
    {
        for (int i = 0; i < squares.length; i++){
            for (int j = 0; j < squares[i].length; j++){

                if (putPieceinStartingSquare(i, j)){
                    addImagePieces(squares[i][j], i, j);
                    squares[i][j].requestFocus();   
                }
            }
        }
    }

    /** 
    * Method that checks if move is valid
    * @param i i or row index
    * @param j j or col index
    * @return boolean
    */
    private boolean isValidMove(int i, int j)
    {
        int rowDelta = Math.abs(i - row); //7 - 6 = 1
        int colDelta = Math.abs(j - col); //1 - 1 = 0

        if ((rowDelta == 1) && (colDelta == 0))
        {
            if (bVerticalNormalMove(squares[row][col].getComponent(0), i-row))
                    return true;
        }
        if ((rowDelta == 0) && (colDelta == 1))
        {
            if (bHorizontalNormalMove(squares[row][col].getComponent(0), j - col))
                return true;
        }
        if ((rowDelta == 0) && (colDelta == 3)) //Jump River
        {
            if (squares[row][col].getComponents().length > 0)
            {
                if (bAnimalCanJumpSide(squares[row][col].getComponent(0), j-col))
                      return true;
            }
        }
        if ((rowDelta == 4) && (colDelta == 0)) //Jump River
        {
            if (squares[row][col].getComponents().length > 0)
            {
                return bAnimalCanJumpUD(squares[row][col].getComponent(0), i - row);
            }
        }

        return false; //if move is not valid
    }

    /** 
    * Method that processes the click on the JButtons made by the player
    * @param i i or row index
    * @param j j or col index
    */
    public void processClick(int i, int j)
    {
        if (isValidMove(i, j))
        {      
            if (squares[i][j].getComponents().length == 0 )
            {
                squares[i][j].add(squares[row][col].getComponent(0));
            }
            else
            {
                squares[i][j].add(squares[row][col].getComponent(0));
                squares[i][j].remove(squares[i][j].getComponent(0));
            }

            squares[row][col].requestFocus();   
            row = i; 
            col = j;
        }

        isFirstMove = true;
    }

    /** 
    * Method that checks if move made northward or southward is valid or not
    * @param piece component or source Piece that is being moved
    * @param distance how far the target tile from the source tile
    * @return boolean
    */
    public boolean bVerticalNormalMove(Component piece, int distance)
    {
        if (pieces[0][0].equals(piece) || pieces[1][0].equals(piece) ){
            if (distance > 0){
                setStringMove("S"); System.out.println("S (South)"); 
            }
            else {
                setStringMove("W"); System.out.println("W (North)"); 
            }

            return true;
        }
        else
        {
            if (distance > 0){
                if ( !(squares[row + 1][col].getIcon()).equals(river)){
                    setStringMove("S"); System.out.println("S (South)"); return true; 
                }
            }
            else {
                if ( !(squares[row - 1][col].getIcon()).equals(river)){
                    setStringMove("W"); System.out.println("W (North)"); return true; 
                }
            }
        }

        return false;
    }

    /** 
    * Method that checks if move made eastward or westward is valid or not
    * @param piece component or source Piece that is being moved
    * @param distance how far the target tile from the source tile
    * @return boolean valid or not
    */
    public boolean bHorizontalNormalMove(Component piece, int distance)
    {
        if (pieces[0][0].equals(piece) || pieces[1][0].equals(piece) ){
            if (distance > 0)
            {
                setStringMove("D"); System.out.println("D (East)");
            }
            else 
            {
                setStringMove("A"); System.out.println("A (West)");
            }
            return true;
        }
        else
        {
            if (distance > 0)
            {
                if ( !(squares[row][col + 1].getIcon()).equals(river)){
                    setStringMove("D"); System.out.println("D (East)");
                    return true; 
                }
            }
            else 
            {
                if ( !(squares[row][col - 1].getIcon()).equals(river)){
                    setStringMove("A"); System.out.println("A (West)"); return true; 
                }
            }
        }

        return false;
    }


    /** 
    * Method that checks if move made is a jump eastward or westward and if it is valid or not
    * @param piece component or source Piece that is being moved
    * @param distance how far the target tile from the source tile
    * @return boolean
    */
    public boolean bAnimalCanJumpSide(Component piece, int distance)
    {
        //A
        if (pieces[0][5].equals(piece) || pieces[0][6].equals(piece) || pieces[1][5].equals(piece) || pieces[1][6].equals(piece) )
        {
            if (distance > 0)
            {
                if ( (squares[row][col + 1].getIcon()).equals(river) && (squares[row][col + 2].getIcon()).equals(river) ){
                    setStringMove("D"); return true;
                }
            }
            else
            {
                if (squares[row][col - 1].getIcon().equals(river) && squares[row][col - 2].getIcon().equals(river) ){
                    setStringMove("A"); return true;
                } 
            }
            

        }

        return false;
    }

    /** 
    * Method that checks if move made is a jump northward or southward and if it is valid or not
    * @param piece component or source Piece that is being moved
    * @param distance how far the target tile from the source tile
    * @return boolean valid or not
    */
    public boolean bAnimalCanJumpUD(Component piece, int distance)
    {
        //A
        if (pieces[0][5].equals(piece) || pieces[0][6].equals(piece) || pieces[1][5].equals(piece) || pieces[1][6].equals(piece) )
        {
            if (distance > 0)
            {
                if ( (squares[row + 1][col].getIcon()).equals(river) && (squares[row + 2][col].getIcon()).equals(river) && (squares[row + 3][col].getIcon()).equals(river) ){
                    setStringMove("S"); return true;
                }
            }
            else
            {
                if ( (squares[row - 1][col].getIcon()).equals(river) && (squares[row - 2][col].getIcon()).equals(river) && (squares[row - 3][col].getIcon()).equals(river) ){
                    setStringMove("W"); return true;
                } 
            }
            

        }

        return false;
    }

    /**
     * Finds the chosen piece Component for the current player.
     * Sets the animal piece chosen
     * @param piece component or source Piece that is being moved
     * @return boolean
     */
    public boolean findPiece(Component piece)
    {
        if (pColorTurn)
        {
             //Shortened code: 
             for (int i = 0; i < pieces[0].length; i++ )
             {
                 if (pieces[0][i].equals(piece)){
                    setNumAnimal(i + 1); 
                    System.out.println(getNumAnimal());
                    return true;
                 }
             }
        }
        else 
        {
            for (int i = 0; i < pieces[0].length; i++ )
            {
                if (pieces[1][i].equals(piece)){
                   setNumAnimal(i + 1); return true;
                }
            }
        }

        return false;

    }

    /** Nested interface class that implements action listener for the JButtons or the board of the actual game.
    */
    private class ButtonHandler implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
            Object source = e.getSource(); //for the target position

            if (isFirstMove)
            {
                for (int i = 0; i < 9; i++){
                    for (int j = 0; j < 7; j++){
                        if (source == squares[i][j] && squares[i][j].getComponents().length > 0){
                            if (squares[i][j].getComponent(0) instanceof JLabel){
                                isOwnPiece = findPiece(squares[i][j].getComponent(0)); //Use boolean to check if its p1 and p2
                                
                                if (isOwnPiece)
                                {
                                    row = i;
                                    col = j;
                                    isFirstMove = false;
                                }
                            }
                        }

                    }
                }
            }
            else 
            {
                for (int i = 0; i < 9; i++)
                {
                    for (int j = 0; j < 7; j++)
                    {
                        if (source == squares[i][j]) //gets the next i and j
                        {
                            bIsValid = isValidMove(i, j);
                            if (bIsValid)
                            {
                                setChosenSquare(squares[i][j]);
                            }
                            //processClick(i, j); //add row col does the move
                        }
                    }
                }
            }
        }
    }

    /**
    * Gets the boolean value whether move made is valid or not
    * @return boolean that contains the validity of performed move
    */
    public boolean getbValid(){
        return bIsValid;
    }

    /**
    * Gets the JButton value for the chosen target square when making a move
    * @return JButton that contains the target square
    */
    public JButton getChosenSquare(){
        return chosenSquare;
    }

    /**
    * Sets the value for the chosen target square
    * @param chosenSquare JButton that contains the target square
    */
    public void setChosenSquare(JButton chosenSquare){
        this.chosenSquare = chosenSquare;
    }

    /**
    * Updates the JTextArea Dialogue Box within the Chess Board Window
    * and shows who's turn it is and if move made is valid
    * @param color team color of the players
    * @param isMoveValid if move performed is valid
    */
    public void updateDialogueBox(boolean color, boolean isMoveValid) {
        String player1 = " Player 1's ";
        String player2 = " Player 2's ";

        if (color && isMoveValid) {
            dialogue_box.setText(dialogue_box.getText() + "\n" + player1 + "turn");
        }
        else if (!color && isMoveValid) {
            dialogue_box.setText(dialogue_box.getText() + "\n" + player2 + "turn");
        }
        else  { //if (!isMoveValid)
            dialogue_box.setText(dialogue_box.getText() + "\n" + "Invalid Move");
        }
    }

    /**
    * Updates the JTextArea Dialogue Box within the Chess Board Window
    * and shows who the winner is (if a winner is found)
    * @param winner winner of the game
    */
    public void updateDialogueBoxWinner(boolean winner) {
        String player1 = " Player 1 ";
        String player2 = " Player 2 ";

        if (winner) {
            dialogue_box.setText(player1 + "Wins!");
        }
        else {
            dialogue_box.setText(player2 + "Wins!");
        }
    }

    /**
    * Gets the integer value of the chosen animal piece
    * @return integer value of animal
    */
    public int getNumAnimal(){
        return numAnimal;
    }

    /**
    * Gets the String value of the move performed
    * @return String value of the move
    */
    public String getStringMove(){
        return stringMove;
    }

    /**
    * Gets the boolean value of player's color turn (whether the piece can be played by the player)
    * @return boolean value of the player's color turn
    */
    public boolean getColorTurn(){
        return pColorTurn;
    }

    /**
    * Gets the integer value of i or row index
    * @return String value of the move
    */
    public int getRow()
    {
        return row;
    }

    /**
    * Gets the integer value of j or col index
    * @return String value of the move
    */
    public int getCol()
    {
        return col;
    }

    /**
    * Sets the integer value of the chosen animal piece
    * @param num value of the chosen animal piece
    */
    public void setNumAnimal(int num){
        numAnimal = num;
    }

    /**
    * Sets the String value of the move performed
    * @param input String value of the move
    */
    public void setStringMove(String input)
    {
        stringMove = input;
    }
    
    /**
    * Sets the boolean value of player's color turn (whether the piece can be played by the player)
    * @param colorTurn boolean value of the player's color turn
    */
    public void setColorTurn(boolean colorTurn) {
        pColorTurn = colorTurn;
    }

    /**
    * Sets the boolean value of the player who would move first
    * @param turn boolean value of the player moving first
    */
    public void setIsFirstMove(boolean turn)
    {
        isFirstMove = turn;
    }
}