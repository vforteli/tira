/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 *
 * @author vforteli
 */
public class AstarGUI extends javax.swing.JFrame
{
    private Board board = null; 
    
    /**
     * Creates new form AstarGUI
     */
    public AstarGUI()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        NewGameFrame = new javax.swing.JFrame();
        BoardSizeChoice = new java.awt.Choice();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        ObstaclePercentageChoice = new java.awt.Choice();
        StartGameButton = new java.awt.Button();
        BoardPanel = new javax.swing.JPanel();
        jlabel2 = new javax.swing.JLabel();
        ScoreLabel = new javax.swing.JLabel();
        MainMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        NewGameButton = new javax.swing.JMenuItem();

        NewGameFrame.setTitle("Start game");
        NewGameFrame.setAlwaysOnTop(true);
        NewGameFrame.setMinimumSize(new java.awt.Dimension(250, 144));
        NewGameFrame.setResizable(false);

        label1.setAlignment(java.awt.Label.RIGHT);
        label1.setText("Size");

        label2.setAlignment(java.awt.Label.RIGHT);
        label2.setText("Obstacle %");

        StartGameButton.setActionCommand("StartGameButton");
        StartGameButton.setLabel("Start");
        StartGameButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                StartGameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NewGameFrameLayout = new javax.swing.GroupLayout(NewGameFrame.getContentPane());
        NewGameFrame.getContentPane().setLayout(NewGameFrameLayout);
        NewGameFrameLayout.setHorizontalGroup(
            NewGameFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewGameFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NewGameFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NewGameFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(StartGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(BoardSizeChoice, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(ObstaclePercentageChoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        NewGameFrameLayout.setVerticalGroup(
            NewGameFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewGameFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NewGameFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoardSizeChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NewGameFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ObstaclePercentageChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StartGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Astar");

        javax.swing.GroupLayout BoardPanelLayout = new javax.swing.GroupLayout(BoardPanel);
        BoardPanel.setLayout(BoardPanelLayout);
        BoardPanelLayout.setHorizontalGroup(
            BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );
        BoardPanelLayout.setVerticalGroup(
            BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );

        jlabel2.setText("Score");

        ScoreLabel.setMaximumSize(new java.awt.Dimension(34, 14));
        ScoreLabel.setMinimumSize(new java.awt.Dimension(34, 14));
        ScoreLabel.setPreferredSize(new java.awt.Dimension(34, 14));

        jMenu1.setText("File");

        NewGameButton.setText("New game");
        NewGameButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                NewGameButtonActionPerformed(evt);
            }
        });
        jMenu1.add(NewGameButton);

        MainMenuBar.add(jMenu1);

        setJMenuBar(MainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jlabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 329, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel2)
                    .addComponent(ScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Start a new game
     */
    private void NewGameButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_NewGameButtonActionPerformed
    {//GEN-HEADEREND:event_NewGameButtonActionPerformed
        // If a game is already running prompt the user for confirmation
        if (board != null && board.getIsRunning())
        {
            int selection = JOptionPane.showConfirmDialog(
                        null
                        , "Do you want to discard the current game?"
                        , "New game"
                        , JOptionPane.OK_CANCEL_OPTION
                        , JOptionPane.WARNING_MESSAGE);
            
            if (selection != 0)
            {
                return;
            }    
        }
             
        BoardSizeChoice.removeAll();
        BoardSizeChoice.add("20");
        BoardSizeChoice.add("50");
        BoardSizeChoice.add("100");
        
        ObstaclePercentageChoice.removeAll();
        ObstaclePercentageChoice.add("10");
        ObstaclePercentageChoice.add("20");
        ObstaclePercentageChoice.add("30");
        
        NewGameFrame.setVisible(true);
    }//GEN-LAST:event_NewGameButtonActionPerformed

    
    /**
     * Draw the board to screen
     * @param cells 
     */
    private void DrawBoard(Board.cellstate[][] cells)
    { 
        BoardPanel.removeAll();
        BoardPanel.setLayout(new GridLayout(cells.length, cells.length));
        BoardPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        int x = 0; 
        int y = 0;
        for (Board.cellstate[] row : cells)
        {
            for (Board.cellstate cell : row)
            {
                JPanel cellpanel = new JPanel();
                cellpanel.setEnabled(true);
                cellpanel.setName(new Coordinates(x, y).getHumanCoordinates());
                cellpanel.setPreferredSize(new Dimension(3, 3));
                cellpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
                // This is probably insane... but whatever
                cellpanel.addMouseListener(new MouseAdapter() 
                {
                    @Override
                    public void mousePressed(MouseEvent e) 
                    {  
                        JPanel cell =(JPanel)e.getSource();
                        Coordinates c = Coordinates.ParseCoordinates(cell.getName());

                        try
                        {
                            ClickBoard(c);
                        } 
                        catch (Exception ex)
                        {
                            Logger.getLogger(AstarGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                if (cell == Board.cellstate.Obstacle)         
                {
                    cellpanel.setBackground(Color.black);
                }
                else if (cell == Board.cellstate.Hit)         
                {
                    cellpanel.setBackground(Color.red);
                }
                else if (cell == Board.cellstate.Miss)         
                {
                    cellpanel.setBackground(Color.gray);
                }
                else if (cell == null)
                {
                    cellpanel.setBackground(Color.blue);
                }  
           
                BoardPanel.add(cellpanel);
                x++;
            }
            x = 0;
            y++;
        }
        BoardPanel.revalidate();        
    }
    
    
       

    

    
    
    private Coordinates previousCoordinates;
       
    private void ClickBoard(Coordinates c)
    {
        if (board != null && board.getIsRunning())
        {
            try
            {
                if (previousCoordinates != null)
                {
                    int distance = HeuristicDistance.CalculateOptimalDistance(previousCoordinates, c);
                    System.out.println("Distance from previously clicked cell: " + distance);
                }
                previousCoordinates = c;
                        
                board.Fire(c.x, c.y);
               
                
                DrawBoard(board.GetBoard());

               
                if (board.getShipcells() == 0)
                {
                    GameCompleted();
                }
            } 
            catch (Exception ex)
            {
                // Notify the user? although, with a clickable GUI this should not be an issue anyway...
                Logger.getLogger(AstarGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
    /**
     * Handle start game clicks
     * @param evt 
     */
    private void StartGameButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_StartGameButtonActionPerformed
    {//GEN-HEADEREND:event_StartGameButtonActionPerformed
        NewGameFrame.dispose();
        
        board = new Board(Integer.parseInt(BoardSizeChoice.getSelectedItem()));
        
        try 
        {
            board.AddRandomShip(5);
            board.AddRandomShip(4);
            board.AddRandomShip(3);
            board.AddRandomShip(3);
            board.AddRandomShip(2);
            
            int ships = Integer.parseInt(ObstaclePercentageChoice.getSelectedItem());
            if (ships == 7 || ships == 9)
            {
                board.AddRandomShip(2);
                board.AddRandomShip(3);
            }
            if (ships == 9)
            {
                board.AddRandomShip(5);
                board.AddRandomShip(1); // :p         
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(AstarGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        board.StartGame();
        DrawBoard(board.GetBoard());
    }//GEN-LAST:event_StartGameButtonActionPerformed

    
    /**
     * Called when a game is completed
     */
    private void GameCompleted()
    {
        
    }    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(AstarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(AstarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(AstarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(AstarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new AstarGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoardPanel;
    private java.awt.Choice BoardSizeChoice;
    private javax.swing.JMenuBar MainMenuBar;
    private javax.swing.JMenuItem NewGameButton;
    private javax.swing.JFrame NewGameFrame;
    private java.awt.Choice ObstaclePercentageChoice;
    private javax.swing.JLabel ScoreLabel;
    private java.awt.Button StartGameButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel jlabel2;
    private java.awt.Label label1;
    private java.awt.Label label2;
    // End of variables declaration//GEN-END:variables

}
