

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

/*Name: Garbo Cheng (gc2bt) & Marbo Cheng (mc2cy)
 * 
 */
public class puzzleFrame extends JFrame {

	private JPanel contentPane;
	private Puzzle puzzle;
	private puzzle4x4 puzzle4;
	private puzzle2x3 puzzle2x3;
	private puzzle2x2 puzzle2x2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					puzzleFrame frame = new puzzleFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public puzzleFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		JButton newPuzzleButton = new JButton("3x3 Puzzle");
		newPuzzleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			puzzle = new Puzzle(3);
				
			}
		});
		
		JButton fourButton = new JButton("4x4 Puzzle");
		fourButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				puzzle4 = new puzzle4x4(4);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
				
			}
		});
		
		JButton button23 = new JButton("2x3 Puzzle");
		button23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			puzzle2x3 = new puzzle2x3();	
				
			}
		});
		
		JButton button22 = new JButton("2x2 Puzzle");
		button22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			puzzle2x2 = new puzzle2x2(2);	
				
			}
		});

		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(puzzle!=null){
					puzzle.restartPuzzle();
				}
				if (puzzle2x2!=null){
					puzzle2x2.restartPuzzle();
				}

				if (puzzle2x3 != null){
					puzzle2x3.restartPuzzle();
				}

				if (puzzle4 != null){
					puzzle4.restartPuzzle(4);
					}
				if(puzzle2x3 == null && puzzle4 == null && puzzle ==null && puzzle2x2 ==null){
					JOptionPane.showMessageDialog(contentPane, "Error, puzzle does not exist. Please create a puzzle.","Error!",JOptionPane.ERROR_MESSAGE);
			
				}


			}
		});
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GroupLayout menu = new GroupLayout(contentPane);
		menu.setHorizontalGroup(
			menu.createParallelGroup(Alignment.LEADING)
				.addGroup(menu.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addGroup(menu.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(closeButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fourButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(newPuzzleButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button23, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button22, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(resetButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addGap(45))
		);
		menu.setVerticalGroup(
			menu.createParallelGroup(Alignment.LEADING)
				.addGroup(menu.createSequentialGroup()
					.addGap(23)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button22)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button23)
					.addComponent(newPuzzleButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fourButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(resetButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(closeButton)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		contentPane.setLayout(menu);
		setTitle("Game Menu");
		setLocation(200,000);
	}
}

