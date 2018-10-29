/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.util.List;
import java.util.Arrays;
/**
 *
 * @author super0
 */
public class Node implements Comparable<Node> {
    
        private Integer [] state; // Represents an n-puzzle current state
        private final int dimension; // Row column dimension of the nxn matrix
        private int cost; // To implement A* search
        private List<Node> children; // Expanded children of this state
        private Node parentNode; // To track path from initial state to goal state 


        // Main constructor
        public Node(Integer [] state, int dimension) {
            this.dimension = dimension;
            this.state = state.clone();
            cost = 0;
            parentNode = null;            
        } 

        // Returns this node's current state
        public Integer [] getCurrentState(){
            Integer [] currentState = state.clone();
            return currentState;
        }
        
        // Changes this nodes current state 
        public void setCurrentState(Integer [] state){                        
            this.state = state.clone();                                  
        }
        
        // Gets the current cost to get to this state
        public int currentCost(){
            return cost;
        }
        
        // Sets the cost for getting to this state
        public void setCost(int newCost){
            cost = newCost;
        }
        
        // Get the matrix dimension
        public int getDimension(){
            return this.dimension;
        }
        
        // Returns the parent state for this state
        public void setParentNode(Node daddy){
            parentNode = daddy;
        }
        
        // Returns the parent state for this state
        public Node getParentNode(){
            return parentNode;
        }
        
        // Returns the expanded states for this state
        public List<Node> getChildren(){
            return children;
        }
        
        // Add a child
        public void addChild(Node child){
            children.add(child);
        }
        
        // Print the state
        public void printState(){
            int size = this.state.length;
            for(int i = 0; i < size; i++){
                if(i%this.dimension == 0)
                    System.out.println("\n");
                System.out.print(state[i].toString() + " ");                
            }
            System.out.println("\n");
        }
        
        @Override
        public String toString() {
            String contents = Arrays.toString(state) + "\tPuzzle dimension:\t" + 
                    String.valueOf(this.dimension) + " " +
                    String.valueOf(cost); 
            return contents;
        }
      
        @Override
        public int compareTo(Node state1) {
            return (this.cost - state1.cost);
        }    
}
