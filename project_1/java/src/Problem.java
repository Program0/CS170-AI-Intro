/**
 * @author Marlo Zeroth
 * @date October 25, 2018
 */

package project_1;

import java.util.Arrays;

/**
 * The problem defines the initial and goal state and the operators that 
 * can change one state into another state
 */

public class Problem {
    
    private Integer [] initial; // Initial state of the problem
    private Integer [] goal; // Goal state or solution to the problem
    private final int matrixDimension; // The matrixDimension of the nxn matrix n-puzzle 
    private final Operators operator; // Operator object to manipulate nodes 
    
    // Main constructor
    public Problem(Integer [] initialState, Integer [] goalState, int dimension) throws IllegalArgumentException {
        // Make sure we have an nxn matrix
        int goalDimension = new Double(Math.sqrt(goalState.length)).intValue();
        int puzzleDimension = new Double(Math.sqrt(initialState.length)).intValue();
        
        if( goalDimension != dimension || puzzleDimension != dimension){
            throw new IllegalArgumentException();            
        }
        this.initial = initialState.clone();
        this.goal = goalState.clone();
        this.matrixDimension = dimension;
        this.operator = new Operators();
    }
    
    public Operators operators(){
        return this.operator;
    }
    
    /**
     * @return A copy of the initial state
     */
    public Integer [] getInitialState(){
        return initial.clone();
    }
    
    /**
     * @param initialGoal The starting state for the problem.
     */
    public void setInitialState(Integer [] initialGoal) throws IllegalArgumentException {
        // Make sure we have an nxn matrix
        int dimension = initialGoal.length;
        int calcDimension = new Double(Math.sqrt(dimension)).intValue();
        
        if( calcDimension != dimension ){
            throw new IllegalArgumentException();            
        }
        this.initial = Arrays.copyOf(initial, dimension);
    }
    
    /**
     * @param goalState The goal state or solution to the problem.
     */
    public void setGoalState(Integer [] goalState) throws IllegalArgumentException {
              // Make sure we have an nxn matrix
        int dimension = goalState.length;
        int calcDimension = new Double(Math.sqrt(dimension)).intValue();
        
        if( calcDimension != dimension ){
            throw new IllegalArgumentException();            
        }
        this.goal = Arrays.copyOf(goalState, dimension);
    }
    
    /**
     * @return A copy of the goal state
     */
    public Integer [] getGoalState(){
        return Arrays.copyOf(goal, goal.length);        
    }
    
    /**
     * @return The size of the nxn puzzle matrix
     */
    public int getPuzzleDimension(){
        return matrixDimension;
    }
    
    /**
     * 
     * @param state The state to test
     * @return True if the state is equal to the goal state. False otherwise.
     */
    public boolean goalTest(Integer [] state){
        return Arrays.deepEquals(this.goal, state);
    }
    
    /**
     * A class of operators to modify or change from one state to another state.
     */
    public class Operators{
        // Cost for using the operators
        private static final int COST_MOVE_UP = 1;
        private static final int COST_MOVE_DOWN = 1;
        private static final int COST_MOVE_LEFT = 1;
        private static final int COST_MOVE_RIGHT = 1;
         /**
         * Returns a new node with a new state if the blank tile is able to be 
         * moved up. Returns null otherwise.
         * @param node Node holding current state to be modified by operator.
         * @return newNode New node created by operator. 
         * Node is null if not modified.
         */   
        public Node moveBlankUp(Node node){
            Node newNode = null;
            Integer [] newState = node.getCurrentState();
            int dimension = matrixDimension;
            int blankPosition = Arrays.asList(newState).indexOf(0);
            int row = blankPosition/dimension;
            int col = blankPosition % dimension;
            if(row > 0){                
                // Swap with number in row above                
                newState[blankPosition] = newState[dimension*(row-1) + col];
                newState[dimension*(row-1) + col] = 0;
                
                // Createa new node with the new state and cost
                newNode = new Node(newState);                
                // Set the cost                             
                newNode.setCost(COST_MOVE_UP);                
            }            
            return newNode;
        }
        
         /**
         * Returns a new node with a new state if the blank tile is able to be 
         * moved down. Returns null otherwise.
         * @param node Node holding current state to be modified by operator.
         * @return newNode New node created by operator. 
         * Node is null if not modified.
         */   
        public Node moveBlankDown(Node node){
            Node newNode = null;
            Integer [] newState = node.getCurrentState();
            int dimension = matrixDimension;
            int blankPosition = Arrays.asList(newState).indexOf(0);
            int row = blankPosition/dimension;
            int col = blankPosition % dimension;
            if(row < dimension - 1 ){                
                // Swap with number in row below
                newState[blankPosition] = newState[dimension*(row+1) + col];
                newState[dimension*(row+1) + col] = 0;
                
                // Createa new node with the new state and cost
                newNode = new Node(newState);                
                // Set the new cost                
                newNode.setCost(COST_MOVE_DOWN);                
            }            
            return newNode;
        }
        
         /**
         * Returns a new node with a new state if the blank tile is able to be 
         * moved left. Returns null otherwise.
         * @param node Node holding current state to be modified by operator.
         * @return newNode New node created by operator. 
         * Node is null if not modified.
         */   
        public Node moveBlankLeft(Node node){
            Node newNode = null;
            Integer [] newState = node.getCurrentState();
            int dimension = matrixDimension;
            int blankPosition = Arrays.asList(newState).indexOf(0);
            int row = blankPosition/dimension;
            int col = blankPosition % dimension;
            if(col > 0){                
                // Swap with number in column to the left 
                newState[blankPosition] = newState[dimension*row + (col - 1)];
                newState[dimension*row + (col - 1)] = 0;
                
                // Createa new node with the new state and cost
                newNode = new Node(newState);                
                // Set the new cost                
                newNode.setCost(COST_MOVE_LEFT);                
            }            
            return newNode;
        }
        
         /**
         * Returns a new node with a new state if the blank tile is able to be 
         * moved right. Returns null otherwise.
         * @param node Node holding current state to be modified by operator.
         * @return newNode New node created by operator. 
         * Node is null if not modified.
         */   
        public Node moveBlankRight(Node node){
            Node newNode = null;
            Integer [] newState = node.getCurrentState();
            int dimension = matrixDimension;
            int blankPosition = Arrays.asList(newState).indexOf(0);
            int row = blankPosition/dimension;
            int col = blankPosition % dimension;
            if( col < (dimension -1) ) {                
                // Swap with number in column to the right 
                newState[blankPosition] = newState[dimension*row + (col + 1)];
                newState[dimension*row + (col + 1)] = 0;
                
                // Createa new node with the new state and cost
                newNode = new Node(newState);                
                // Set the new cost                
                newNode.setCost(COST_MOVE_RIGHT);                
            }            
            return newNode;
        }
    }
}
