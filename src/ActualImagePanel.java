import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*Name: Garbo Cheng (gc2bt) & Marbo Cheng (mc2cy)
 * 
 */
public class ActualImagePanel extends JFrame{
public JPanel finalPanel;
int width,height;
private Image source,image;

    public ActualImagePanel(){

    	  try {                 
    	      BufferedImage image = ImageIO.read(new File("happy.jpg")); 
    	      JLabel picLabel = new JLabel(new ImageIcon(image));
    	      add( picLabel );
    	      add(Box.createRigidArea(new Dimension(0,0)), BorderLayout.NORTH);    
    	      add(picLabel, BorderLayout.CENTER);
    	      setSize(330, 280);
    	      setTitle("Solution Image");  
    	      setResizable(false);
    	    
    	      setLocation(160,275);
    	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	      setVisible(true);
    	   } catch (IOException ex) { 
    	       System.out.println("Image does not exist...");
    	   } 
   
        }

}
