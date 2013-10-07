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
import java.io.IOException;
import java.util.Locale;
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
    private transient Board board; 
    private JPanel[][] cellmap;
    private transient Coordinates previousCoordinates;
    private transient Coordinates clickedCoordinates;
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

        newBoardFrame = new javax.swing.JFrame();
        initBoardButton = new java.awt.Button();
        label3 = new java.awt.Label();
        chooseFileButton = new java.awt.Button();
        filenamelabel = new java.awt.Label();
        terrainMaxWeight = new java.awt.TextField();
        terrainMinWeight = new java.awt.TextField();
        label4 = new java.awt.Label();
        boardPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        newBoard = new java.awt.Button();
        label5 = new java.awt.Label();
        heuristicMultipliertextField = new java.awt.TextField();
        recalculatebutton = new java.awt.Button();
        label2 = new java.awt.Label();
        pathlengthlabel = new java.awt.Label();
        coordinatesLabel = new java.awt.Label();

        newBoardFrame.setTitle("Start game");
        newBoardFrame.setAlwaysOnTop(true);
        newBoardFrame.setMinimumSize(null);
        newBoardFrame.setResizable(false);

        initBoardButton.setActionCommand("StartGameButton");
        initBoardButton.setLabel("Start");
        initBoardButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                initBoardButtonActionPerformed(evt);
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

        javax.swing.GroupLayout newBoardFrameLayout = new javax.swing.GroupLayout(newBoardFrame.getContentPane());
        newBoardFrame.getContentPane().setLayout(newBoardFrameLayout);
        newBoardFrameLayout.setHorizontalGroup(
            newBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newBoardFrameLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(newBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(initBoardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filenamelabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chooseFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                    .addGroup(newBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(terrainMaxWeight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                        .addComponent(terrainMinWeight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        newBoardFrameLayout.setVerticalGroup(
            newBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newBoardFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newBoardFrameLayout.createSequentialGroup()
                        .addGroup(newBoardFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addComponent(initBoardButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Astar");

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        newBoard.setLabel("New board");
        newBoard.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newBoardActionPerformed(evt);
            }
        });
        jToolBar1.add(newBoard);

        label5.setAlignment(java.awt.Label.RIGHT);
        label5.setText("Heuristic *");
        jToolBar1.add(label5);

        heuristicMultipliertextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        heuristicMultipliertextField.setMaximumSize(new java.awt.Dimension(14, 18));
        heuristicMultipliertextField.setPreferredSize(new java.awt.Dimension(40, 20));
        heuristicMultipliertextField.setText("1");
        jToolBar1.add(heuristicMultipliertextField);

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

        pathlengthlabel.setMinimumSize(new java.awt.Dimension(70, 20));
        jToolBar1.add(pathlengthlabel);

        coordinatesLabel.setPreferredSize(new java.awt.Dimension(100, 20));
        jToolBar1.add(coordinatesLabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
            .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    /**
     * Draw the board to screen
     * @param cells 
     */
    private void drawBoard(PathInfo path)
    {        
        for (int y = 0; y < board.getHeight(); y++)
        {
            for (int x = 0; x < board.getWidth(); x++)
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
                
                drawCell(c, board.getCellValue(c), highlight, visited);
            }
            
        }
       
    }
    
    private void drawCell(Coordinates c, TerrainCell cell, boolean highlight, boolean visited)
    {      
        JPanel cellpanel = cellmap[c.x][c.y];
        if (cell.terrainType == TerrainTypes.Impassible)    // Obstacle        
        {
            cellpanel.setBackground(Color.black);
        } 
        else if(clickedCoordinates != null && clickedCoordinates.equals(c)) // End
        {
            cellpanel.setBackground(Color.red);
        }
        else if(previousCoordinates != null && previousCoordinates.equals(c))   // Start
        {
            cellpanel.setBackground(Color.green);
        }
        else if (highlight == true)     // Path
        {
            cellpanel.setBackground(Color.ORANGE);
        }
        else
        {   
            // Else try to figure out the color somehow...
            cellpanel.setBackground(calculateColor(cell, visited));
        }
    } 
    
    
    private void createBoard(int height, int width)
    {
        // Only recreate the panels if the board size doesnt match. 10x5 = 50... 5x10 = 50.. oh shit
        if (boardPanel.getComponentCount() != height * width)
        {
            if (boardPanel.getComponentCount() != 0)
            {
                boardPanel.removeAll();     // Insanely slow...
            }
            boardPanel.setLayout(new GridLayout(board.getHeight(), board.getWidth(), -1, -1));
            boardPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            Color bordercolor = new Color(0f,0f,0f, 0.3f);
            cellmap = new JPanel[height][width];

            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    Coordinates c = new Coordinates(x, y);
                    JPanel cellpanel = createCell(c, bordercolor);

                    boardPanel.add(cellpanel);
                    cellmap[x][y] = cellpanel;
                }
            }
        }
    }
       
    
    private JPanel createCell(Coordinates c, Color bordercolor)
    {
        String name = c.toString();
        JPanel cellpanel = new JPanel();
        cellpanel.setName(name);
        cellpanel.setMinimumSize(new Dimension(1,1));
        cellpanel.setBorder(BorderFactory.createLineBorder(bordercolor, 1));
        cellpanel.addMouseListener(new BoardClickMouseAdapter());   // This is probably insane... but whatever     
        return cellpanel;
    }
  

    
    private void clickBoard(Coordinates c, MouseEvent e)
    {
        clickedCoordinates = c;
        
        if (board != null)
        {
            PathInfo path = null;
            if (previousCoordinates != null)
            {
                path = board.findPath(previousCoordinates, clickedCoordinates, Integer.parseInt(heuristicMultipliertextField.getText()));
                if (path.pathlength != null)
                    pathlengthlabel.setText(path.pathlength.toString());
            }
            
            drawBoard(path); 
                        
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
            PathInfo path = board.findPath(previousCoordinates, clickedCoordinates, Integer.parseInt(heuristicMultipliertextField.getText()));
            drawBoard(path); 
            if (path.pathlength != null)
                pathlengthlabel.setText(path.pathlength.toString());
        }
    }//GEN-LAST:event_recalculatebuttonActionPerformed

    
    private void chooseFileButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chooseFileButtonActionPerformed
    {//GEN-HEADEREND:event_chooseFileButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new BitmapFileFilter());

        int returnVal = fc.showOpenDialog(newBoardFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            bitmapfile = fc.getSelectedFile();
            filenamelabel.setText(bitmapfile.getName());
            initBoardButton.setEnabled(true);
        }
    }//GEN-LAST:event_chooseFileButtonActionPerformed

    /**
     * Handle start game clicks
     * @param evt 
     */
    private void initBoardButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_initBoardButtonActionPerformed
    {//GEN-HEADEREND:event_initBoardButtonActionPerformed
        if (bitmapfile == null)
        {
            return;
        }
        
        newBoardFrame.dispose();
        int size = 100;
        int terrainMaxValue = Integer.parseInt(terrainMaxWeight.getText());
        int terrainMinValue = Integer.parseInt(terrainMinWeight.getText());
        
        clickedCoordinates = null;
        previousCoordinates = null;
        try
        {
            board = new Board(size, terrainMinValue, terrainMaxValue, bitmapfile);
            createBoard(board.getHeight(), board.getWidth());
            drawBoard(null);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(AstarGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_initBoardButtonActionPerformed

    
    private void newBoardActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newBoardActionPerformed
    {//GEN-HEADEREND:event_newBoardActionPerformed
        bitmapfile = null;
        filenamelabel.setText("");
        newBoardFrame.pack();
        newBoardFrame.setLocationRelativeTo(this);
        newBoardFrame.setVisible(true);
        initBoardButton.setEnabled(false);
    }//GEN-LAST:event_newBoardActionPerformed

    
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
    private javax.swing.JPanel boardPanel;
    private java.awt.Button chooseFileButton;
    private java.awt.Label coordinatesLabel;
    private java.awt.Label filenamelabel;
    private java.awt.TextField heuristicMultipliertextField;
    private java.awt.Button initBoardButton;
    private javax.swing.JToolBar jToolBar1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Button newBoard;
    private javax.swing.JFrame newBoardFrame;
    private java.awt.Label pathlengthlabel;
    private java.awt.Button recalculatebutton;
    private java.awt.TextField terrainMaxWeight;
    private java.awt.TextField terrainMinWeight;
    // End of variables declaration//GEN-END:variables

    
    private Color calculateColor(TerrainCell cell, boolean visited)
    {   
        float hue = cell.hsbcolor[0];
        float saturation = cell.hsbcolor[1];
        float brightness = cell.hsbcolor[2];
        
        if (visited)
            brightness -= 0.2f;
        
        brightness = brightness < 0 ? 0 : brightness;
        Color color = Color.getHSBColor(hue, saturation, brightness);
        return color;
    }

    /**
     * Filters files to only bmps files
     */
    private static class BitmapFileFilter extends FileFilter
    {
        @Override
        public boolean accept(File file)
        {
            return (file.isDirectory()||file.getName().toLowerCase(Locale.ENGLISH).endsWith(".bmp"));
        }

        @Override
        public String getDescription()
        {
            return "Bitmaps";
        }
    }

    
    /**
     * Class which handles cell mouse events
     */
    private class BoardClickMouseAdapter extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e) 
        {  
            JPanel cell =(JPanel)e.getSource();
            Coordinates c = Coordinates.parseCoordinates(cell.getName());
            
            try
            {
                clickBoard(c, e);
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
            Coordinates c = Coordinates.parseCoordinates(cell.getName());
            String weight = "";
            if (board != null)
            {
                weight = String.valueOf(board.getCellValue(c).weight);
            }
            coordinatesLabel.setText(c.x + ", " + c.y + " w: " + weight);
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            coordinatesLabel.setText("");
        }
    }
}