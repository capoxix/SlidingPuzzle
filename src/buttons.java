import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*Name: Garbo Cheng (gc2bt) & Marbo Cheng (mc2cy)
 * 
 */

public class buttons extends JFrame {
	
	public JButton img;
	public int index;
	public Image image;
	
	buttons(){
		img = new JButton();
		index = 0;
	}
	
	buttons(Image x, int y){		
		img = new JButton();
		img.setIcon(new ImageIcon(x));
		image = x;
		index = y;		
	}
	 
	public JButton getButton(){
		return img;
	}
	public Image getImage(){
		return image;
	}
	public int getIndex(){
		return index;
	}
	public void setIndex(int x){
		index = x;
	}
}
