import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class Reversi
{
    private JPanel playerPanel;
    private Board gameBoard;
    private JLabel message;
    private Board currentPlayer;
    /**
     * Constructor for Reversi class
     * runs the makeFrame method
     */
    public Reversi()
    {
        makeFrame();
    }
    
    /**
     * makeFrame method creates the frame
     * Frame is split into 3 sections (Game board on the left, player/score on the right and a message at the bottom)
     * A menu bar is created that allows the player to start a new game, quit the current game, save a game [not working] or open a saved game [not working]
     */
    private void makeFrame()
    {
        JFrame frame = new JFrame("Reversi");
        frame.setSize(700,300);
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //MENUBAR
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem openGame = new JMenuItem("Open Game");
        JMenuItem saveGame = new JMenuItem("Save Game");
        JMenuItem quitGame = new JMenuItem("Quit Game");
        
        quitGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        
        newGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.getContentPane().removeAll();
                frame.repaint();
                makeFrame();
            }
        });
        
        file.add(newGame);
        file.add(openGame);
        file.add(saveGame);
        file.add(quitGame);
        bar.add(file);
        frame.setJMenuBar(bar);
        
        //CONTAINER
        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        
        //BOARD PANEL
        gameBoard = new Board();
        frame.add(gameBoard.getPanel(),BorderLayout.CENTER);
        
        //PLAYER PANEL
        JPanel midPanel = new JPanel();
        midPanel.setBorder(new LineBorder(Color.BLACK, 3));
        midPanel.setLayout(new FlowLayout(4,4,4));
        midPanel.setBackground(Color.GRAY);
        
        makePlayerPanel();
        
        midPanel.add(playerPanel);
        mainContainer.add(midPanel, BorderLayout.EAST);
        
        //MESSAGE PANEL
        JPanel statusBar = new JPanel();
        statusBar.setBackground(Color.RED);
        message = new JLabel("Enter name and press start");
        statusBar.add(message);
        frame.add(statusBar,BorderLayout.SOUTH);
        
        //FRAME
        frame.setVisible(true);
    }
    
    /**
     * makePlayerPanel method creates the player/score panel
     * It includes input fields for both players names, and a counter for each players scores and no of disks.
     * There is validation for the name inputs
     */
    public void makePlayerPanel()
    {
        playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(6,1,5,5));
        playerPanel.setBackground(Color.GRAY);
        
        JTextField playerName1 = new JTextField("Player 1");
        playerName1.setPreferredSize(new Dimension(200,30));
      
        JTextField playerName2 = new JTextField("Player 2");
        playerName2.setPreferredSize(new Dimension(200,30));
      
        JLabel player1Score = new JLabel("P1 Score:       ");
        JLabel player2Score = new JLabel("P2 Score:       ");
        JLabel P1Disks = new JLabel("P1 Remaining Disks:      ");
        JLabel P2Disks = new JLabel("P2 Remaining Disks:      ");
        JLabel noNameError = new JLabel();
        
        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
                if(playerName1.getText().equals("") == false && playerName2.getText().equals("") == false){
                    player1Score.setText(playerName1.getText()+"'s Score:       ");
                    player2Score.setText(playerName2.getText()+"'s Score:       ");
                    P1Disks.setText(playerName1.getText()+"'s Remaining Disks:      ");
                    P2Disks.setText(playerName2.getText()+"'s Remaining Disks:      ");
                    playerName1.setEditable(false);
                    playerName2.setEditable(false);
                    noNameError.setText("");
                    message.setText("");
                }
                else{
                    noNameError.setText("Please enter a name");
                    noNameError.setForeground(Color.RED);
                }
            }
        });
        
        playerPanel.add(player1Score);
        playerPanel.add(P1Disks);
        playerPanel.add(player2Score);
        playerPanel.add(P2Disks);
        playerPanel.add(playerName1);
        playerPanel.add(playerName2);
        playerPanel.add(playButton);
        playerPanel.add(noNameError);
    }
}