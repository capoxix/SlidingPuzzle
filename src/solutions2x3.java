import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JFrame;
/*Name: Garbo Cheng (gc2bt) & Marbo Cheng (mc2cy)
 * 
 */
class solutions2x3 {
	
		boolean foundSolution = false;
	    Queue<String> stepsTree = new LinkedList<String>();    // Use of Queue Implemented using LinkedList to store all nodes in BFS.
	    //Stack<String> stepsTree = new Stack<String>();
	    Map<String,Integer> stepNumber = new HashMap<String, Integer>(); // Keep track of step number, and HashMap is used to ignore repeated steps
	    Map<String,String> stepHistory = new HashMap<String,String>(); // Keep tracks of string at each step
	    InstructionsFrame instructionFrame= new InstructionsFrame(false, false, null, 300, 300, JFrame.DISPOSE_ON_CLOSE);
	
	    boolean findSolution(solutions2x3 steps){
	    	//if queue is not empty means it has not reached solve state
	    	//breadth-first search using the queue
	    	 
	        while(!steps.stepsTree.isEmpty()){
	        	//current state 
	            String currentState = steps.stepsTree.remove();
	        	//String currentState = steps.stepsTree.pop();
	            //move tile up and place new state into a queue

	            if(foundSolution!=true){
	            	steps.up(currentState);
	            }

	            // Move to left and update state
	            if(foundSolution!=true){
	            	steps.left(currentState);
	            }
	            //move blank tile down
	            if(foundSolution!=true){
	            	steps.down(currentState);

	            }
	            // Move right and remove the current node from Queue
	            if(foundSolution!=true){
	            	steps.right(currentState);
	            }
	        }
	        
	        if(foundSolution==false){
	        	return foundSolution;
	        }
	        return foundSolution;
	    }
	    
	    private void completed(String previousStep, String newStep) {
	    	//add newstate to stepsTree
	    	add(newStep, previousStep);
	        if(newStep.equals("012345")) {
	        	System.out.println("Solution found at depth: "+stepNumber.get(newStep));
	            while (newStep != null) {
	            	if(stepNumber.get(newStep)!=0){
	            		System.out.println("Step "+ stepNumber.get(newStep) + " :");
	            		System.out.println(newStep.substring(0,2));
	            		System.out.println(newStep.substring(2,4));
	            		System.out.println(newStep.substring(4,6)+"\n");
	            		newStep = stepHistory.get(newStep);
	            	}else{
	            		System.out.println("The beginning state : ");
	            		System.out.println(newStep.substring(0,2));
	 	                System.out.println(newStep.substring(2,4));
	 	                System.out.println(newStep.substring(4,6) +"\n");
	 	                newStep = stepHistory.get(newStep);	
	            	}
	            }
	            foundSolution = true;
	        } 
	    }
	   
	    //Add method to add the new string to the Map and Queue
	    void add(String newStep, String prevStep){
	    	//only add current state if it stateDepth does not contain the new state
	        if(!stepNumber.containsKey(newStep)){
	        	int stepCounter = 0;
	        	if (prevStep!= null) {
	        		stepCounter = stepNumber.get(prevStep)+1;
	        	}
	            
	            //state depth keeps track of record
	            stepNumber.put(newStep, stepCounter);
	            //newstate added to stepsTree
	            stepsTree.add(newStep);
	            //hashmap of new step connected to previous step
	            stepHistory.put(newStep, prevStep);
	        }
	    }

	  //update string positions
	    void up(String currentState){
	        int blankPos = currentState.indexOf("0");
	        
	        //if current position bigger than 2 then blank tile can be move up
	        if(blankPos>1){
	            String nextState = currentState.substring(0,blankPos-2)+"0"
	        +currentState.substring(blankPos-1,blankPos)
	        +currentState.charAt(blankPos-2)
	        +currentState.substring(blankPos+1);
	            //checks it solution completed
	            completed(currentState, nextState);
	        }
	    }

	    void down(String currentState){
	        int blankPos = currentState.indexOf("0");
	        //if tile is not on last row then it can be move down
	        if(blankPos<4){
	            String nextState = currentState.substring(0,blankPos)
	            		+currentState.substring(blankPos+2,blankPos+3)
	            		+currentState.substring(blankPos+1,blankPos+2)+"0"
	            		+currentState.substring(blankPos+3);
	            //checks it to see if completed
	            completed(currentState, nextState);
	        }
	    }
	    void left(String currentState){
	    	
	        int blankPos = currentState.indexOf("0");
	        //blank tile can be moved left when it is not on left corner of puzzle
	        if(blankPos!=0 && blankPos!=2 && blankPos!=4){
	            String nextState = currentState.substring(0,blankPos-1)+"0"
	        +currentState.charAt(blankPos-1)
	        +currentState.substring(blankPos+1);
	            //checks if completed
	            completed(currentState, nextState);
	        }
	    }
	    void right(String currentState){
	        int blankPos = currentState.indexOf("0");
	        //blank tile can  move right if not in right corner to puzzle
	        if(blankPos!=1 && blankPos!=3 && blankPos!=5){
	            String nextState = currentState.substring(0,blankPos)
	            		+currentState.charAt(blankPos+1)+"0"+currentState.substring(blankPos+2);
	            //check if completed
	            completed(currentState, nextState);
	        }
	    }
}
