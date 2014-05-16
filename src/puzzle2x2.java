import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*Name: Garbo Cheng (gc2bt) & Marbo Cheng (mc2cy)
 * 
 */
public class puzzle2x2 extends JFrame implements ActionListener {

	final JPanel centerPanel;
    private Image source;
    private Image image;
    
    int width, height;
    int[][] positions;
    private buttons tiles;
    private buttons blank;
    private int counter=0;
    buttons[] holder = new buttons[4];
    int[] layout = new int[4];
    String state = null;
    private boolean solvable;
    
    
    public puzzle2x2(int x) {
    	
        positions = new int[][] {
                {0,1},
                {2,3},
        	};
      

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2,2,0,0));
    
        ImageIcon img = new ImageIcon(puzzle2x2.class.getResource("happy.jpg"));
        source = img.getImage();

            
        width = img.getIconWidth();
        height = img.getIconHeight();
        
        add(Box.createRigidArea(new Dimension(0,0)), BorderLayout.NORTH);    
        add(centerPanel, BorderLayout.CENTER);

        solutions2x2 solved = new solutions2x2();
        
        
        for ( int i = 0; i < x; i++) {
            for ( int j = 0; j < x; j++) {
                if ( j == 0 && i == 0) {
                	//blank or hole in the bottom right
                	blank = new buttons();
                	blank.getButton().setRolloverEnabled(false);
                	blank.getButton().setEnabled(false);
                	holder[counter++]=blank;
                } else {
                	//put parts of image into buttons
                    image = createImage(new FilteredImageSource(source.getSource(),
                        new CropImageFilter(j*width/x, i*height/x, 
                            (width/x)+x, height/x)));
                    tiles = new buttons(image, counter);
                    tiles.getButton().addActionListener(this);
                    holder[counter]=tiles;

                    counter++;
                  }
            }
        }
        //First randomized sequence
    
        Collections.shuffle(Arrays.asList(holder));
        for(int i = 0; i<x*x; i++){
        	layout[i]=holder[i].getIndex();
        }
        StringBuffer buffer = new StringBuffer();
        for(int i = 0;i<layout.length;i++){
        buffer.append(layout[i]);
        }
        
        state = buffer.toString();
        //System.out.println("in puzzle2x2 state: " + state );
   
        //System.out.println("1023");
        solved.add(state, null);
        //checks if randomized sequence is legal for puzzle
        solvable = solved.findSolution(solved);
        
        //Making game
        
       //create a puzzle by constantly moving tiles until we arrive at a legal puzzle
 
        while(solvable!=true){
            Collections.shuffle(Arrays.asList(holder));
            for(int i = 0; i<4; i++){
            	layout[i]=holder[i].getIndex();
            }
            buffer.delete(0,4);
            for(int i = 0;i<layout.length;i++){
            buffer.append(layout[i]);
            }
            state = buffer.toString();
            solved.add(state, null);
            solvable = solved.findSolution(solved);      	
        }
     

      //once we arrive to a legal puzzle, insert into panel for player to solve
      if(solvable==true){
    	  for(int i = 0;i<4;i++){
          centerPanel.add(holder[i].getButton());
    	  }
      }
      //Create puzzle game
      setSize(345, 295);
        setTitle("Tile Puzzle Game");
        setResizable(false);
        setLocation(525,250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
       ActualImagePanel imageSolution = new ActualImagePanel();
    }
    
    //Restarts the tile puzzle
    public void restartPuzzle(){
    	setVisible(false);
    	centerPanel.removeAll();
    	centerPanel.setLayout(new GridLayout(2,2,0,0));
        
        add(Box.createRigidArea(new Dimension(0,0)), BorderLayout.NORTH);    
        add(centerPanel, BorderLayout.CENTER);
        
    	for(int i = 0;i<4;i++){
  	  		centerPanel.add(holder[i].getButton());
  	  	}
    	setVisible(true);
    }
    

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Dimension size = button.getSize();

        int blankX = blank.getButton().getX();
        int blankY = blank.getButton().getY();
        int buttonX = button.getX();
        int buttonY = button.getY();
        int buttonPosX = buttonX / size.width;
        int buttonPosY = buttonY / size.height;
        int buttonIndex = positions[buttonPosY][buttonPosX];
        
        //if blank is above you, when you are clicked you swap and go up
        if (blankX == buttonX && (blankY - buttonY) == size.height ) {
            
             int labelIndex = buttonIndex + 2;
             centerPanel.getComponentAt(0,0);
              centerPanel.remove(buttonIndex);
             centerPanel.add(blank.getButton(), buttonIndex); 
             centerPanel.add(button,labelIndex); 
             centerPanel.validate();
        }

        //if blank is below you, swap with it go down
        if (blankX == buttonX && (blankY - buttonY) == -size.height ) {
             
        	int labelIndex = buttonIndex - 2;
   
             centerPanel.remove(labelIndex);
             centerPanel.add(button,labelIndex);
             centerPanel.add(blank.getButton(), buttonIndex);
             centerPanel.validate();
            		 
        }
        //if blank is to your right, swap with it when you are clicked go right
        if (blankY == buttonY && (blankX - buttonX) == size.width ) {

             int labelIndex = buttonIndex + 1;
           
             centerPanel.remove(buttonIndex);
             centerPanel.add(blank.getButton(), buttonIndex);
             centerPanel.add(button,labelIndex);
             centerPanel.validate();
              
        }
        //if blank is to your left, swap with it when you are clicked go left
        if (blankY == buttonY && (blankX - buttonX) == -size.width ) {
        	
             int labelIndex = buttonIndex - 1;

             centerPanel.remove(buttonIndex);
             centerPanel.add(blank.getButton(), labelIndex);
             centerPanel.add(button,labelIndex);
             centerPanel.validate();
   
        }
    }
}

