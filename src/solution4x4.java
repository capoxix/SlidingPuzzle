
import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;


//uses idps to find lower bound on solution and find whether the correct answer in the bound
//if a solution is not found, the bound is too low and has to be increased and repeat iterate process
//with increase bound, since solution requires more steps
public class solution4x4 {

	InstructionsFrame instructionFrame= new InstructionsFrame(false, false, null, 300, 300, JFrame.DISPOSE_ON_CLOSE);

    private int[][] beginningState; // Beginning state  
    // Blank position
    private int parentX;
    private int paretY;
    public static boolean finish; // has a solution been found

    private HashMap<Integer,String> steps;
  
    public solution4x4(int[][] puzzle) throws Exception {
 
        this.beginningState = puzzle;
        finish = false;
        steps = new HashMap <Integer, String>();
        
    }
    private static int[][][] manhattanDistance; //manhattan distance for all position

 
    //calculate manhattanDistance for all position
    static {
        manhattanDistance = new int[16][4][4];
        for (int value = 0; value <= 15; value++) {
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    manhattanDistance[value][x][y] = Math.abs(value / 4 - x) + Math.abs(value % 4 - y);
                }
            }
        }
    }

    //return the blank position
    public static java.awt.Point blankPos(int[][] puzzle) {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (puzzle[x][y] == 0) {
                    return new java.awt.Point(x, y);
                }
            }
        }
        return null;
    }
   
    //calculate the total cost to reach solution from current state
    public int cost(int distanceFromRoot, int[][] puzzle) {
 
    	StringBuffer str = new StringBuffer();
    	
    	//place latest step into hashmap so thatwe can shows steps to solution
    	for ( int i = 0; i < 4; i ++) {
        	for ( int j =0 ; j < 4; j++) {
        		
        		str.append(Integer.toHexString(puzzle[i][j]));
     
        	}
        
        }
    	steps.put(distanceFromRoot, str.toString());

        return distanceFromRoot + manhattanDist(puzzle);
    }
    //get the manhattandistance
    public int manhattanDist(int[][] puzzle) {
    	 int distance = 0;
         int[] puzzleRaw;
         int value;
         for (int i = 0; i < puzzle.length; i++) {
             puzzleRaw = puzzle[i];
             for (int j = 0; j < puzzleRaw.length; j++) {
                 value = puzzle[i][j];
                 if (value != 0) {
                     distance += manhattanDistance[value][i][j];
                 }
             }
         }
         return distance;
        
    }

    public void solvePuzzle() {
        Point spacePoint = blankPos(this.beginningState);
        solve(this.beginningState, spacePoint.x, spacePoint.y);
       
    }

    //uses iterative depth search to solve problem 
    public int solve(int[][] root, int blankX, int blankY) {
        finish = false;
        int depthBound = manhattanDist(root);
        int temp;
       
        //long runtime = System.currentTimeMillis();
        while (!finish && depthBound < 100) {
            parentX = blankX;
            paretY = blankY;
            temp = IterDepthSearch(root, blankX, blankY, 0, depthBound);
            int zerocurrpos = steps.get(steps.size()-1).indexOf("0");
            String movement ="";
            if (finish) {
        
            	//System.out.println("Solution Depth: "+temp);
            	for ( int k = steps.size()-1 ; k >= 0;k--) {
            		
            		int zeronextpos = steps.get(k).indexOf("0");
            		
            		//down if it is greater than zeropasspos+
            		if (zeronextpos > zerocurrpos && zeronextpos > (zerocurrpos+1)) {
            			movement = "UP" ;
            		}
            		else if (zeronextpos < zerocurrpos && zeronextpos < (zerocurrpos-1)) {
            			movement = "DOWN";
            		}
            		else if ( zeronextpos > zerocurrpos) {
            			movement = "LEFT";
            		}
            		else if ( zeronextpos < zerocurrpos) {
            			movement = "RIGHT";
            		}
            		zerocurrpos = zeronextpos;
            		String puzz = steps.get(k);
            		
            		System.out.println();
            		
            		if (k == steps.size()-1) {
            			movement = "Done!";
            		}
            		System.out.println( "Step "+ (k+1) + " : " + movement );
            		
            		int x = 0;
            		for ( int i= 0 ; i < puzz.length(); i++) {
            			System.out.print(puzz.charAt(i)+ " ");
            			x++;
            			if ( x == 4) {
            				System.out.println();
            				x=0;
            			}	
            		}
                }
            			System.out.println();
    
                 // A solution has been found! End the iterations and return the depth of solution. 
               // long endAlgoTime = System.currentTimeMillis();
                System.out.println("Steps to solution is " + (temp+1));
               // System.out.println("Run Time : " + (endAlgoTime - runtime)+" millisec");
                
                               
                return temp;
            }
            
             //No solution for current depth bound increase depth bound 
             //  to the minimal cost of the last iteration
     
            depthBound = temp;
        }
        return -1;
    }

    //iter depth search function
    
    public int IterDepthSearch(int[][] puzzle, int blankX, int blankY, int distFromRoot, int depthBound) {
    
        if (cost(distFromRoot, puzzle) > depthBound) {
            /* State cannot be solved in this iteration. No further
             * search effort is needed. */
            return cost(distFromRoot, puzzle);
        }
        
        // Checks if a solution has been found
        if (manhattanDist(puzzle) == 0) {
            finish = true;
            return cost(distFromRoot, puzzle);
        }        
        int iterNumber = Integer.MAX_VALUE;
        int temp;
       
        // finds all possible next moves
        Vector<Point> validBlankPos = possibleNextMove(blankX, blankY);
  //      if (validBlankPos.size() > 0) {
          
   //     }
        for (Point validBlankPosition : validBlankPos) {
            parentX = blankX;
            paretY = blankY;
            
            nextState(puzzle, blankX, blankY, validBlankPosition.x, validBlankPosition.y);
            /* Recurse on the next state so do distFromRoot + 1 to do next iter depth search
     			if finish is true then a solution is found and stop search
             */
            temp = IterDepthSearch(puzzle, validBlankPosition.x, validBlankPosition.y, (distFromRoot + 1), depthBound);
           
            if (finish) {
            	//show you number of steps
                return temp; 
            }
            if (temp < iterNumber) { //update the minimum state cost that have been generated but not expanded.
                iterNumber = temp;
            }
            // Modify state by undoing the move 
            nextState(puzzle, validBlankPosition.x, validBlankPosition.y, blankX, blankY);  
        }
    
        //no solution found return the min iter number for next iteration
        return iterNumber;
    }
      
    private Vector<Point> possibleNextMove(int x, int y) {

       //checks all possible move for blank space in current position
        Vector<Point> result = new Vector<Point>();
        Point spacePoint;

        if (x - 1 >= 0 && !(parentX == x - 1 && paretY == y)) {
            spacePoint = new Point(x - 1, y);
            result.add(spacePoint);
        }
        if (x + 1 < 4 && !(parentX == x + 1 && paretY == y)) {
            spacePoint = new Point(x + 1, y);
            result.add(spacePoint);
        }
        if (y - 1 >= 0 && !(parentX == x && paretY == y - 1)) {
            spacePoint = new Point(x, y - 1);
            result.add(spacePoint);
        }
        if (y + 1 < 4 && !(parentX == x && paretY == y + 1)) {
            spacePoint = new Point(x, y + 1);
            result.add(spacePoint);
        }
        
        return result;
    }
    //moves blank spot to its next state
    private void nextState(int[][] puzzle, int prevX, int prevY, int nextX, int nextY) {
        puzzle[prevX][prevY] = puzzle[nextX][nextY];
        puzzle[nextX][nextY] = 0;
        
    }

}