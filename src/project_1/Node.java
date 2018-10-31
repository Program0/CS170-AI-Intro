/**
 * @author Marlo Zeroth
 * @date October 25, 2018
 * 
 */

package project_1;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Node implements Comparable<Node> {
    
        private Integer [] state; // Represents an n-puzzle current state matrix
        private int cost; // To implement A* search
        private final List<Node> children; // Expanded children of this state
        private Node parentNode; // To track path from initial state to goal state 


        // Main constructor
        public Node(Integer [] state) {
            this.state = state.clone();
            cost = 0;
            parentNode = null;
            children = new ArrayList<>();
            
        } 

        // Returns this node's current state
        public Integer [] getCurrentState(){
            return state.clone();            
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
            List<Node> nodes = new ArrayList<>(children);
            return nodes;
        }
        
        // Add a child
        public void addChild(Node child){
            children.add(child);
        }
        
        // Print the state
        public void printState(){
            int size = this.state.length;
            int dimension = new Double(Math.sqrt(size)).intValue();
            for(int i = 0; i < size; i++){
                if(i%dimension == 0)
                    System.out.println("\n");
                System.out.print(state[i].toString() + " ");                
            }
            System.out.println("\n");
        }
        
        @Override
        public String toString() {
            String contents = Arrays.toString(state) + "\tPuzzle dimension:\t" + 
                    String.valueOf(cost); 
            return contents;
        }
      
        @Override
        public int compareTo(Node state1) {
            return (this.cost - state1.cost);
        }    
}
