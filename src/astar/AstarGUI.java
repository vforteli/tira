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
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;



/**
 *
 * @author vforteli
 */
public class AstarGUI extends javax.swing.JFrame
{
    private Board board; 
    private JPanel[][] cellmap;
    private Coordinates previousCoordinates;
    private Coordinates clickedCoordinates;
    private File bitmapfile; 
    
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

        NewBoardFrame = new javax.swing.JFrame();
        InitBoardButton = new java.awt.Button();
        label3 = new java.awt.Label();
        chooseFileButton = new java.awt.Button();
        filenamelabel = new java.awt.Label();
        terrainMaxWeight = new java.awt.TextField();
        terrainMinWeight = new java.awt.TextField();
        label4 = new java.awt.Label();
        BoardPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        NewBoard = new java.awt.Button();
        label5 = new java.awt.Label();
        HeuristicMultipliertextField = new java.awt.TextField();
        recalculatebutton = new java.awt.Button();
        label2 = new java.awt.Label();
        pathlengthlabel = new java.awt.Label();
        coordinatesLabel = new java.awt.Label();

        NewBoardFrame.setTitle("Start game");
        NewBoardFrame.setAlwaysOnTop(true);
        NewBoardFrame.setMinimumSize(null);
        NewBoardFrame.setResizable(false);

        InitBoardButton.setActionCommand("StartGameButton");
        InitBoardButton.setLabel("Start");
        InitBoardButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                InitBoardButtonActionPerformed(evt);
            }
        });

        label3.setAlignment(java.awt.Label.RIGHT);
        label3.setText("Terrain minimum weight");

        chooseFileButton.setLabel("Choose bitmap");
        chooseFileButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                chooseFileButtonActionPerformed(evt);
            }
        });

        terrainMaxWeight.setText("10");

        terrainMinWeight.setText("1");

        label4.setAlignment(java.awt.Label.RIGHT);
        label4.setText("Terrain maximum weight");

        javax.swing.GroupLayout NewBoardFrameLayout = new javax.swing.GroupLayout(NewBoardFrame.getContentPane());
        NewBoardFrame.getContentPane().setLayout(NewBoardFrameLayout);
        NewBoardFrameLayout.setHorizontalGroup(
            NewBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewBoardFrameLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(NewBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NewBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NewBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(InitBoardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filenamelabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chooseFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                    .addGroup(NewBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(terrainMaxWeight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                        .addComponent(terrainMinWeight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        NewBoardFrameLayout.setVerticalGroup(
            NewBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewBoardFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NewBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NewBoardFrameLayout.createSequentialGroup()
                        .addGroup(NewBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(terrainMinWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(terrainMaxWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chooseFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filenamelabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InitBoardButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Astar");

        javax.swing.GroupLayout BoardPanelLayout = new javax.swing.GroupLayout(BoardPanel);
        BoardPanel.setLayout(BoardPanelLayout);
        BoardPanelLayout.setHorizontalGroup(
            BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        BoardPanelLayout.setVerticalGroup(
            BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        NewBoard.setLabel("New board");
        NewBoard.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                NewBoardActionPerformed(evt);
            }
        });
        jToolBar1.add(NewBoard);

        label5.setAlignment(java.awt.Label.RIGHT);
        label5.setText("Heuristic multiplier");
        jToolBar1.add(label5);

        HeuristicMultipliertextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        HeuristicMultipliertextField.setMaximumSize(new java.awt.Dimension(14, 18));
        HeuristicMultipliertextField.setPreferredSize(new java.awt.Dimension(40, 20));
        HeuristicMultipliertextField.setText("1");
        jToolBar1.add(HeuristicMultipliertextField);

        recalculatebutton.setLabel("Redraw");
        recalculatebutton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                recalculatebuttonActionPerformed(evt);
            }
        });
        jToolBar1.add(recalculatebutton);

        label2.setAlignment(java.awt.Label.RIGHT);
        label2.setText("Path length");
        jToolBar1.add(label2);
        jToolBar1.add(pathlengthlabel);

        coordinatesLabel.setPreferredSize(new java.awt.Dimension(70, 20));
        jToolBar1.add(coordinatesLabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
            .addComponent(BoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    /**
     * Draw the board to screen
     * @param cells 
     */
    private void DrawBoard(int[][] cells, PathInfo path)
    {        
        for (int y = 0; y < cells.length; y++)
        {
            for (int x = 0; x < cells.length; x++)
            {
                Coordinates c = new Coordinates(x, y);
                boolean highlight = false;
                boolean visited = false;
                if (path != null)
                {
                    if (path.coordinates != null && path.coordinates.containsKey(c))
                        highlight = true;
                    
                    if (path.closedset != null && path.closedset.containsKey(c))
                        visited = true;
                }
                
                DrawCell(cellmap[x][y], c, cells[y][x], highlight, visited);
            }
        }
    }
    
    private void DrawCell(JPanel cellpanel, Coordinates c, int cellweight, boolean highlight, boolean visited)
    {      
        if (cellweight == -1)         
        {
            cellpanel.setBackground(Color.black);
        } 
        else if(clickedCoordinates != null && clickedCoordinates.equals(c))
        {
            cellpanel.setBackground(Color.red);
        }
        else if(previousCoordinates != null && previousCoordinates.equals(c))
        {
            cellpanel.setBackground(Color.green);
        }
        else if (highlight == true)
        {
            cellpanel.setBackground(Color.ORANGE);
        }
        else
        {   
            float saturation = 0;
            float brightness = CalculateBrightness(cellweight, board.getTerrainMinWeight(), board.getTerrainMaxWeight());
            if (visited)
            {
                saturation = 0.5f;
                brightness -= 0.1f;
            }
            cellpanel.setBackground(Color.getHSBColor(0.125f, saturation, brightness));
        }
    }
      
    private float CalculateBrightness(int value, int min, int max)
    {
        if (max == min)
        {
            return 1;
        }
        float outmax = 0.3f;
        float outmin = 1;
        float brightness = outmin + (value - min) * (outmax - outmin) / (max - min);
        return brightness;
    }
    
    
    
    
    
    private void CreateBoard(int height, int width)
    {
        // Only recreate the panels if the board size doesnt match  10x5 = 50... 5x10 = 50.. oh shit
        if (BoardPanel.getComponentCount() != height * width)
        {
            if (BoardPanel.getComponentCount() != 0)
            {
                BoardPanel.removeAll();
            }
            BoardPanel.setLayout(new GridLayout(board.getHeight(), board.getWidth(), -1, -1));
            BoardPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            cellmap = new JPanel[height][width];

            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    Coordinates c = new Coordinates(x, y);
                    JPanel cellpanel = CreateCell(c.toString());

                    BoardPanel.add(cellpanel);
                    cellmap[x][y] = cellpanel;
                }
            }
        }
    }
       
    private JPanel CreateCell(String name)
    {
        JPanel cellpanel = new JPanel();
        cellpanel.setEnabled(true);
        cellpanel.setName(name);
        cellpanel.setMinimumSize(new Dimension(1,1));
        cellpanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        
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
                    ClickBoard(c, e);
                } 
                catch (Exception ex)
                {
                    Logger.getLogger(AstarGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                JPanel cell =(JPanel)e.getSource();
                Coordinates c = Coordinates.ParseCoordinates(cell.getName());
                String weight = "";
                if (board != null)
                {
                    weight = String.valueOf(board.getCellValue(c));
                }
                coordinatesLabel.setText(c.x + ", " + c.y + " w: " + weight);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                coordinatesLabel.setText("");
            }
        });
        
        return cellpanel;
    }
  

    
    private void ClickBoard(Coordinates c, MouseEvent e)
    {
        clickedCoordinates = c;
        
        if (board != null)
        {
            PathInfo path = null;
            if (previousCoordinates != null)
            {
                path = board.FindPath(previousCoordinates, clickedCoordinates, Integer.parseInt(HeuristicMultipliertextField.getText()));
                if (path.pathlength != null)
                    pathlengthlabel.setText(path.pathlength.toString());
            }
            
            DrawBoard(board.GetBoard(), path); 
                        
            if (e.getButton() == 3 || previousCoordinates == null)
            {        
                previousCoordinates = clickedCoordinates;
            }
        }
    }  
    
    
    private void recalculatebuttonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_recalculatebuttonActionPerformed
    {//GEN-HEADEREND:event_recalculatebuttonActionPerformed
        if (board != null && previousCoordinates != null && clickedCoordinates != null)
        {
            PathInfo path = board.FindPath(previousCoordinates, clickedCoordinates, Integer.parseInt(HeuristicMultipliertextField.getText()));
            DrawBoard(board.GetBoard(), path); 
        }
    }//GEN-LAST:event_recalculatebuttonActionPerformed

    
    private void chooseFileButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chooseFileButtonActionPerformed
    {//GEN-HEADEREND:event_chooseFileButtonActionPerformed
        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileFilter()
            {
                @Override
                public boolean accept(File file)
                {
                    return (file.isDirectory()||file.getName().toLowerCase().endsWith(".bmp"));
                }

                @Override
                public String getDescription()
                {
                    return "Bitmaps";
                }
            });

        int returnVal = fc.showOpenDialog(NewBoardFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            bitmapfile = fc.getSelectedFile();
            filenamelabel.setText(bitmapfile.getName());
        }
    }//GEN-LAST:event_chooseFileButtonActionPerformed

    /**
     * Handle start game clicks
     * @param evt 
     */
    private void InitBoardButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_InitBoardButtonActionPerformed
    {//GEN-HEADEREND:event_InitBoardButtonActionPerformed
        NewBoardFrame.dispose();
        int size = 100;
        int terrainMaxValue = Integer.parseInt(terrainMaxWeight.getText());
        int terrainMinValue = Integer.parseInt(terrainMinWeight.getText());
        
        clickedCoordinates = null;
        previousCoordinates = null;
        board = new Board(size, terrainMinValue, terrainMaxValue, bitmapfile);

        CreateBoard(board.getHeight(), board.getWidth());
        DrawBoard(board.GetBoard(), null);
    }//GEN-LAST:event_InitBoardButtonActionPerformed

    
    private void NewBoardActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_NewBoardActionPerformed
    {//GEN-HEADEREND:event_NewBoardActionPerformed
        bitmapfile = null;
        filenamelabel.setText("");
        NewBoardFrame.pack();
        NewBoardFrame.setLocationRelativeTo(this);
        NewBoardFrame.setVisible(true);
    }//GEN-LAST:event_NewBoardActionPerformed

    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(AstarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new AstarGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoardPanel;
    private java.awt.TextField HeuristicMultipliertextField;
    private java.awt.Button InitBoardButton;
    private java.awt.Button NewBoard;
    private javax.swing.JFrame NewBoardFrame;
    private java.awt.Button chooseFileButton;
    private java.awt.Label coordinatesLabel;
    private java.awt.Label filenamelabel;
    private javax.swing.JToolBar jToolBar1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label pathlengthlabel;
    private java.awt.Button recalculatebutton;
    private java.awt.TextField terrainMaxWeight;
    private java.awt.TextField terrainMinWeight;
    // End of variables declaration//GEN-END:variables
}