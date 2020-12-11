import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class Board implements ActionListener
{
    private JButton gridBox;
    private JPanel boardPanel;
    private String currentPlayer;
    private JButton selectedButton;
    private int button_x_pos;
    private int button_y_pos;
    /**
     * Constructor for the board class
     * Constructor is used to create the board which is an 8*8 grid
     * The constructor also sets the initial 2 black and 2 white pieces
     * The board is able to resize along with the GUI
     */
    public Board(){
       boardPanel = new JPanel();
       boardPanel.setLayout(new GridLayout(8,8));
       boardPanel.setSize(600,600);
       gridBox = new JButton();
       currentPlayer = "B";
       for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                gridBox = new JButton("");
                gridBox.setBorder(new LineBorder(Color.BLACK));
                if((x == 3 && y == 3) || (x == 4 && y == 4)){
                    gridBox.setBackground(Color.WHITE);
                }
                else if((x == 4 && y == 3) || (x == 3 && y == 4)){
                    gridBox.setBackground(Color.BLACK);
                }
                else{
                    gridBox.setBackground(Color.GREEN.darker());
                    gridBox.addActionListener(this);
                }
                boardPanel.add(gridBox);
            }
       }
    }
   
    /**
     * Override method for the actionListener
     * checks which button was pressed and runs the move method on it
     */
    public void actionPerformed(ActionEvent e){
        selectedButton = (JButton) e.getSource();
        move();
    }
    
    /**
     * move method checks which players turn it is and sets the color of the selected button/box to the players color
     * Once a square has been selected and the color had changed, it cannot be changed again
     */
    public void move(){
        if(currentPlayer == "B"){
            selectedButton.setBackground(Color.BLACK);
            currentPlayer = "W";
        }
        else{
            selectedButton.setBackground(Color.WHITE);
            currentPlayer = "B";
        }
        button_x_pos = selectedButton.getLocation().x;
        button_y_pos = selectedButton.getLocation().y;
        selectedButton.setEnabled(false);
    }
    
    /**
     * Capture method is used to flip all enemy pieces inbetween the players two pieces if they have been captured
     */
    public void capture(){
        int current_y = button_y_pos;
        while(current_y == button_y_pos){
            button_x_pos = button_x_pos + 124;
        }
    }
    
    /**
     * Method used to check if the move can be made
     */
    public void isMoveLegal(){}
    
    /**
     * Method used to check if there is a move that the player can make
     */
    public void canMove(){}
  
    /**
     * getPanel method used to return the boardPanel(Game board), that will be used in the reversi class.
     */
    public JPanel getPanel(){
        return boardPanel;
    }
    
    public String getCurrentPlayer(){
        return currentPlayer;
    }
}
