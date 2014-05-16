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
public class puzzle4x4 extends JFrame implements ActionListener {

	final JPanel centerPanel;
    private Image source;
    private Image image;
    
    int width, height;
    int[][] positions;
    private buttons tiles;
    private buttons blank;
    private int counter=0;
    buttons[] holder = new buttons[16];
    buttons[] actualPuzzle = new buttons[16];
    int[] layout = new int[16];
    String state = null;
   
    
    
    public puzzle4x4(int x) throws Exception {
    	
        positions = new int[][] {
                {0,1,2,3},
                {4,5,6,7},
                {8,9,10,11},
                {12,13,14,15}
        	};
    
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(x,x,0,0));
    
        ImageIcon img = new ImageIcon(puzzle4x4.class.getResource("happy.jpg"));
        source = img.getImage();

            
        width = img.getIconWidth();
        height = img.getIconHeight();
        
        add(Box.createRigidArea(new Dimension(0,0)), BorderLayout.NORTH);    
        add(centerPanel, BorderLayout.CENTER);

      //  solutions4x4 solved = new solutions4x4();
        

        
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

        //System.out.println(holder[0].getIndex());
        buttons  temp = holder[15];
        buttons blankbutton = holder[0];
        
        String currentState = "`abcdefghijklmno";
        int NUM_OF_STEPS = 200;
        int steps = 0;
        int blankPos = 0;
        
        while (steps < NUM_OF_STEPS)
        {
          int rnd = (int)(Math.random() * 4);    ///* generated random value of 0,1,2 or 3 
          switch (rnd)
          {
            case 0: blankPos = currentState.indexOf("`");
  	       
  	        //if current position bigger than 2 then blank tile can be move up
  	        if(blankPos>3){
  	        	//System.out.println("Up");
  	        	temp = holder[blankPos-4];
  	        	blankbutton = holder[blankPos];
  	        	
  	        	holder[blankPos] = temp;
  	        	holder[blankPos-4]= blankbutton;
  	        	String nextState = currentState.substring(0,blankPos-4)+"`"
  	      	        +currentState.substring(blankPos-3,blankPos)
  	      	        +currentState.charAt(blankPos-4)
  	      	        +currentState.substring(blankPos+1);
  	            //checks it solution completed
  	            currentState = nextState;
  	        }; break;
            case 1:  blankPos = currentState.indexOf("`");
  	        //if tile is not on last row then it can be move down
  	        if(blankPos<12){
  	        	//System.out.println("Down");
  	        	temp = holder[blankPos+4];
  	        	blankbutton = holder[blankPos];
  	        	
  	        	holder[blankPos] = temp;
  	        	holder[blankPos+4]= blankbutton;
  	        	String nextState = currentState.substring(0,blankPos)
	            		+currentState.substring(blankPos+4,blankPos+5)
	            		+currentState.substring(blankPos+1,blankPos+4)+"`"
	            		+currentState.substring(blankPos+5);
  	            //checks it to see if completed
  	            currentState = nextState;
  	            
  	        }; break;
            case 2: blankPos = currentState.indexOf("`");
  	        //blank tile can be moved left when it is not on left corner of puzzle
  	        if(blankPos!=0 && blankPos!=4 && blankPos!=8 && blankPos!=12){
  	        	//System.out.println("Left");
  	        	temp = holder[blankPos-1];
  	        	blankbutton = holder[blankPos];
  	        	
  	        	holder[blankPos] = temp;
  	        	holder[blankPos-1]= blankbutton;
  	            String nextState = currentState.substring(0,blankPos-1)+"`"
  	        +currentState.charAt(blankPos-1)
  	        +currentState.substring(blankPos+1);
  	            //checks if completed
  	          currentState = nextState;
  	          
  	        }; break;
            case 3:  blankPos = currentState.indexOf("`");
  	        //blank tile can  move right if not in right corner to puzzle
  	        if(blankPos!=3 && blankPos!=7 && blankPos!=11 && blankPos!=15){
  	        	//System.out.println("Right");
  	        	temp = holder[blankPos+1];
  	        	blankbutton = holder[blankPos];
  	        	
  	        	holder[blankPos] = temp;
  	        	holder[blankPos+1]= blankbutton;
  	            String nextState = currentState.substring(0,blankPos)
  	            		+currentState.charAt(blankPos+1)+"`"+currentState.substring(blankPos+2);
  	            //check if completed
  	            currentState= nextState;
  	        }; break;
            default: break;    // should not happen 
          }
          steps++;
        }
       
        int [][] puzzle = new int [4][4];
        int count =0;
        for (int j = 0; j < 4 ; j++) {
    		for (int k=0; k <4 ; k++) {
    			puzzle[j][k]= (currentState.charAt(count)-96);
    			count++;
    		}
    	}

        state = currentState;
	
    	  for(int i = 0;i<x*x;i++){
          centerPanel.add(holder[i].getButton());
    	  }
 
      //Create puzzle game
    	solution4x4 solver = new solution4x4(puzzle);
    	solver.solvePuzzle();
    	setSize(350, 300);
        setTitle("Tile Puzzle Game");
        setResizable(false);
        setLocation(520,250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
       ActualImagePanel imageSolution = new ActualImagePanel();
    }

    //Restarts the tile puzzle
    public void restartPuzzle(int x){
    	setVisible(false);
    	centerPanel.removeAll();
    	centerPanel.setLayout(new GridLayout(x,x,0,0));
        
        add(Box.createRigidArea(new Dimension(0,0)), BorderLayout.NORTH);    
        add(centerPanel, BorderLayout.CENTER);
        
    	for(int i = 0;i<x*x;i++){
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
        //char buttonValue = positions[buttonPosY][buttonPosX];
        
        //if blank is above you, when you are clicked you swap and go up
        if (blankX == buttonX && (blankY - buttonY) == size.height ) {
            //char to integer - the change
             int labelIndex = buttonIndex + 4;
             centerPanel.getComponentAt(0,0);
              centerPanel.remove(buttonIndex);
             centerPanel.add(blank.getButton(), buttonIndex); 
             centerPanel.add(button,labelIndex); 
             centerPanel.validate();
        }

        //if blank is below you, swap with it go down
        if (blankX == buttonX && (blankY - buttonY) == -size.height ) {
             
        	int labelIndex = buttonIndex - 4;
   
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

