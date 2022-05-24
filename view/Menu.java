package view;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Menu Class that creates a new menu window that would determine
 * which of the two players would go first in the actual game and
 * the team color of the first player making a move.
 * 
 * @author : Aedan Matienzo
 * @author : Carlo Cortez
 * @version : 3.0
 */

public class Menu
{
        /**public JFrame for the Menu Window */
        public JFrame frame = new JFrame("Menu");
        /** private JPanel for the Menu Window */
        private JPanel panel = new JPanel();
        /** private box variable with Box data type for Player 1's cards in the Menu Window */
        private Box box = Box.createHorizontalBox();
        /** private box1 variable with Box data type for Player 2's cards in the Menu Window */
        private Box box1 = Box.createHorizontalBox();
        /** private box2 variable with Box data type for choosing color cards (for the winner) in the Menu Window */
        private Box box2 = Box.createHorizontalBox();

        /** ImageIcon for the initial Gold card */
        ImageIcon Gold = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/menupieces/Gold.png")));
        /** ImageIcon for the Gold Mouse Card */
        ImageIcon Mouse = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/menupieces/1-Mouse.png")));
        /** ImageIcon for the Gold Cat Card */
        ImageIcon Cat = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/menupieces/2-Cat.png")));
        /** ImageIcon for the Gold Wolf Card */
        ImageIcon Wolf = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/menupieces/3-Wolf.png")));
        /** ImageIcon for the Gold Dog Card */
        ImageIcon Dog = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/menupieces/4-Dog.png")));
        /** ImageIcon for the Gold Leopard Card */
        ImageIcon Leopard = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/menupieces/5-Leopard.png")));
        /** ImageIcon for the Gold Tiger Card */
        ImageIcon Tiger = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/menupieces/6-Tiger.png")));
        /** ImageIcon for the Gold Lion Card */
        ImageIcon Lion = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/menupieces/7-Lion.png")));
        /** ImageIcon for the Gold Elephant Card */
        ImageIcon Elephant = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/menupieces/8-Elephant.png")));
        /** ImageIcon for the Red Team Color Card */
        ImageIcon Red = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Red.png")));
        /**  ImageIcon for the Blue Team Color Card */
        ImageIcon Blue = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/pieces/Blue.png")));

        /** JButton data type for one of the animal cards with the initial gold card */
        private JButton button1 = new JButton(Gold);
        /** JButton data type for one of the animal cards with the initial gold card */
        private JButton button2 = new JButton(Gold);
        /**J Button data type for one of the animal cards with the initial gold card */
        private JButton button3 = new JButton(Gold);
        /** private button4 variable with JButton data type for one of the animal cards with the initial gold card */
        private JButton button4 = new JButton(Gold);
        /** button5 variable with JButton data type for one of the animal cards with the initial gold card*/
        private JButton button5 = new JButton(Gold); 
        /** * private button6 variable with JButton data type for one of the animal cards with the initial gold card*/
        private JButton button6 = new JButton(Gold);
        /** private button7 variable with JButton data type for one of the animal cards with the initial gold card */
        private JButton button7 = new JButton(Gold); 
        /** private button8 variable with JButton data type for one of the animal cards with the initial gold card */
        private JButton button8 = new JButton(Gold);
        /** private button9 variable with JButton data type for one of the animal cards with the initial gold card */
        private JButton button9 = new JButton(Gold); 
        /** private button10 variable with JButton data type for one of the animal cards with the initial gold card */
        private JButton button10 = new JButton(Gold);
        /** private button11 variable with JButton data type for one of the animal cards with the initial gold card */
        private JButton button11 = new JButton(Gold); 
        /** private button12 variable with JButton data type for one of the animal cards with the initial gold card */
        private JButton button12 = new JButton(Gold);
        /** private button13 variable with JButton data type for one of the animal cards with the initial gold card */
        private JButton button13 = new JButton(Gold); 
        /** private button14 variable with JButton data type for one of the animal cards with the initial gold card */
        private JButton button14 = new JButton(Gold);
        /** private button15 variable with JButton data type for one of the animal cards with the initial gold card */
        private JButton button15 = new JButton(Gold); 
        /** private button16 variable with JButton data type for one of the animal cards with the initial gold card */
        private JButton button16 = new JButton(Gold);

        /** private button17 variable with JButton data type for one of the team color cards with the red team color initialized */
        private JButton button17 = new JButton(Red); 
        /** private button18 variable with JButton data type for one of the team color cards with the blue team color initialized */
        private JButton button18 = new JButton(Blue);

        /**private nextFrame variable with JButton data type for proceeding to the main game */
        public JButton nextFrame = new JButton("Head to the game");

        /**  private presses variable with integer data type for checking if both player's chose their cards */
        private int presses = 0;
        /** private Player1 variable with integer data type for numeric value of player 1's chosen card */
        public int Player1 = 0;
        /** private Player2 variable with integer data type for numeric value of player 2's chosen card */
        public int Player2 = 0;
        /** public status variable with boolean data type that determines if team colors have been assigned */
        public boolean status = true;
        /** public colorFP variable with boolean data type that determines which team color move first (colorFirstPlayer) */
        public boolean colorFP;

        /**
        * Creates a new Menu Window
        */
        public Menu()
        {
                createHeader();
                initializeCards();

                frame.add(panel);
                panel.setAlignmentX(Component.CENTER_ALIGNMENT);

                button1.addActionListener(e -> generateNumber(1)); button2.addActionListener(e -> generateNumber(2));
                button3.addActionListener(e -> generateNumber(3)); button4.addActionListener(e -> generateNumber(4));
                button5.addActionListener(e -> generateNumber(5)); button6.addActionListener(e -> generateNumber(6));
                button7.addActionListener(e -> generateNumber(7)); button8.addActionListener(e -> generateNumber(8));

                button9.addActionListener(e -> generateNumber(9)); button10.addActionListener(e -> generateNumber(10));
                button11.addActionListener(e -> generateNumber(11)); button12.addActionListener(e ->  generateNumber(12));
                button13.addActionListener(e ->  generateNumber(13)); button14.addActionListener(e -> generateNumber(14));
                button15.addActionListener(e ->  generateNumber(15)); button16.addActionListener(e -> generateNumber(16));

                button17.addActionListener(e -> colorFP = assignColor(true)); //choose Red
                button18.addActionListener(e -> colorFP = assignColor(false)); //choose Blue

                panel.setBackground(new Color(255,252,187));
                frame.pack();
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setSize(900, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Player1 = 0;
                Player2 = 0;

                chooseCard();
        }

        /**
        * Gets the team color of the first player 
        * @return boolean that contains the team color that would move first
        */
        public boolean getColorFP() {
	        return colorFP;
        }

        /**
        * Gets the status
        * @return boolean that contains status of the color picking
        */
        public boolean getStatus(){
                return status;
        }

        /**
        * Sets the status using a boolean value
        * @param stat status of the card/color picking
        */
        public void setStatus(boolean stat){
                this.status = stat;
        }

        /** 
        * Initializes the animal pieces (cards) to its respective buttons and boxes
        */
        public void initializeCards() {
                box.add(button1, BorderLayout.CENTER); box.add(button2, BorderLayout.CENTER); box.add(button3, BorderLayout.CENTER); box.add(button4, BorderLayout.CENTER);
                box.add(button5, BorderLayout.CENTER); box.add(button6, BorderLayout.CENTER); box.add(button7, BorderLayout.CENTER); box.add(button8, BorderLayout.CENTER);
                box1.add(button9, BorderLayout.CENTER); box1.add(button10, BorderLayout.CENTER); box1.add(button11, BorderLayout.CENTER); box1.add(button12, BorderLayout.CENTER);
                box1.add(button13, BorderLayout.CENTER); box1.add(button14, BorderLayout.CENTER); box1.add(button15, BorderLayout.CENTER); box1.add(button16, BorderLayout.CENTER);
                box2.add(button17, BorderLayout.CENTER); box2.add(button18, BorderLayout.CENTER);
                
                button1.setBackground(Color.white); button2.setBackground(Color.white); button3.setBackground(Color.white); button4.setBackground(Color.white);
                button5.setBackground(Color.white); button6.setBackground(Color.white); button7.setBackground(Color.white); button8.setBackground(Color.white);
                button9.setBackground(Color.white); button10.setBackground(Color.white); button11.setBackground(Color.white); button12.setBackground(Color.white);
                button13.setBackground(Color.white); button14.setBackground(Color.white); button15.setBackground(Color.white); button16.setBackground(Color.white);
                button17.setBackground(Color.white); button18.setBackground(Color.white);
        }

        /**
        * Creates header of the Menu Window
        */
        public void createHeader() {
                JLabel header = new JLabel("ANIMAL CHESS MACHINE PROJECT");
                header.setFont(new Font("Verdana", Font.BOLD, 28));
                panel.add(header, BorderLayout.NORTH);
        }

        /**
        * Method that allows the players to choose a card 
        */
        public void chooseCard() {
                JLabel choose = new JLabel("Player 1 choose a random card:");
                choose.setFont(new Font("Verdana", Font.BOLD, 28));
                panel.add(choose);
                panel.add(box, BorderLayout.CENTER);
                frame.setVisible(true);

                JLabel warning = new JLabel("(Warning: Do not pick in the same order, or P1 Wins)");
                warning.setFont(new Font("Verdana", Font.BOLD, 20));
                panel.add(warning);
                frame.setVisible(true);

                JLabel choose1 = new JLabel("Player 2 choose a random card:");
                choose1.setFont(new Font("Verdana", Font.BOLD, 28));
                panel.add(choose1);
                panel.add(box1, BorderLayout.CENTER);
                frame.setVisible(true);
        }

        /**
        * Method that generates the number of the chosen card
        * @param number integer value within the JButtons' action listener respectively
        */
        private void generateNumber(int number) {
                JLabel Player = new JLabel();
                disableButtons(number);

                if (number > 0 && number < 9)
                        Player.setText("You got a " + number);
                else
                        Player.setText("You got a " + (number - 8));

                switch (number) {
                        case 1: button1.setIcon(Mouse);  Player1 = 1; break;
                        case 2:  button2.setIcon(Cat); Player1 = 2; break;
                        case 3: button3.setIcon(Wolf); Player1 = 3; break;
                        case 4:  button4.setIcon(Dog); Player1 = 4; break;
                        case 5: button5.setIcon(Leopard); Player1 = 5; break;
                        case 6: button6.setIcon(Tiger); Player1 = 6; break;
                        case 7: button7.setIcon(Lion); Player1 = 7; break;
                        case 8: button8.setIcon(Elephant); Player1 = 8; break;
                        case 9: button9.setIcon(Mouse); Player2 = 1; break;
                        case 10: button10.setIcon(Cat); Player2 = 2; break;
                        case 11: button11.setIcon(Wolf); Player2 = 3; break;
                        case 12:button12.setIcon(Dog);Player2 = 4; break;
                        case 13: button13.setIcon(Leopard); Player2 = 5; break;
                        case 14: button14.setIcon(Tiger);Player2 = 6; break;
                        case 15: button15.setIcon(Lion);Player2 = 7; break;
                        case 16: button16.setIcon(Elephant); Player2 = 8; break;
                }
                Player.setFont(new Font("Verdana", Font.BOLD, 28));
                panel.add(Player);
                frame.setVisible(true);

                if (presses == 2)
                        showWinner();
        }

        /**
        * Method that disables player's buttons if player already chose a card
         * @param number pick
        */
        private void disableButtons(int number){
                if (number >= 1 && number <= 8){
                        button1.setEnabled(false); button2.setEnabled(false); button3.setEnabled(false); button4.setEnabled(false);
                        button5.setEnabled(false); button6.setEnabled(false); button7.setEnabled(false); button8.setEnabled(false);
                }
                else{
                        button9.setEnabled(false); button10.setEnabled(false); button11.setEnabled(false); button12.setEnabled(false);
                        button13.setEnabled(false); button14.setEnabled(false); button15.setEnabled(false); button16.setEnabled(false);
                }
                presses++;
        }

        /**
        * Displays the winner of the card picking determined by showResult method
        */
        public void showWinner() {
                boolean resultPlayer = showResult(Player1, Player2);
                chooseColor(resultPlayer);
                frame.setVisible(true);
        }

        /**
        * Determines the player with a higher integer value based on the chosen cards
        * @param Player1 integer value of the card chosen by player 1
        * @param Player2 integer value of the card chosen by player 2
        * @return boolean value that contains result (true == p1 wins, false == p2 wins)
        */
        public boolean showResult(int Player1, int Player2) {
                boolean rp;
                JLabel Result = new JLabel();
                if (Player1 >= Player2) {
                        Result.setText("Player 1 goes first!");
                        rp = true;
                }
                else {
                        Result.setText("Player 2 goes first!");
                        rp = false;
                }
                Result.setFont(new Font("Verdana", Font.BOLD, 28));
                panel.add(Result);
                frame.setVisible(true);

                return rp;
        }

        /**
        * Winning chooses a team color that would move first in the actual game
        * @param resultPlayer winner of the color picking (true == p1 wins, false == p2 wins)
        */
        public void chooseColor(boolean resultPlayer) {
                JLabel choose2 = new JLabel();
                if (resultPlayer) {
                        choose2.setText("Player 1 choose a team color:");
                }
                else if (!resultPlayer) {
                        choose2.setText("Player 2 choose a team color:"); 
                }
                choose2.setFont(new Font("Verdana", Font.BOLD, 28));
                panel.add(choose2);
                panel.add(box2, BorderLayout.CENTER);
                frame.setVisible(true);
        }

        /**
        * Assigns color of the player who would move first in the actual game
        * @param color boolean value (true == red moves first, false == blue moves first)
        * @return boolean that contains who will move first
        */
        public boolean assignColor(boolean color) {
                JLabel FirstMove = new JLabel();
                if (color) {
                        FirstMove.setText("Red moves first!");
                        panel.add(nextFrame);
                        button18.setEnabled(false);
                }
                else {
                        FirstMove.setText("Blue moves first!");
                        panel.add(nextFrame);
                        button17.setEnabled(false);
                }
                FirstMove.setFont(new Font("Verdana", Font.BOLD, 28));
                panel.add(FirstMove);
                frame.setVisible(true);

                return color;
        }
}