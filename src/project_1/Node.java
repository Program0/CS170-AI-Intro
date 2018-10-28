/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author super0
 */
public class Node implements Comparator<Node>, Comparable<Node> {
    
        private String state; // Represents an n-puzzle current state
        private int cost; // To implement A* search
        private List<Node> children; // Expanded children of this state
        private Node parentNode; // To track path from initial state to goal state 


        // Main constructor
        public Node(String state) {
            this.state = state;                       
            cost = 0;
        } 

        // Returns the string representation of this current state
        public String currentState(){
            return state;
        }
        
        // Changes the current state string representation
        public void setCurrentNode(String newNode){            
            state = newNode;
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
            return children;
        }
        
        @Override
        public String toString() {
            String contents = state + "\tPuzzle dimension:\t" + 
                    String.valueOf(cost); 
            return contents;
        }

        @Override
        public int compare(Node s1, Node s2) {
            Node state1 = (Node) s1;
            Node state2 = (Node) s2;

            // If contents of both objects's strings are same return 0
            if (state1.toString().equals(state2.toString())) {
                return 0;
            } else {
                return -1;
            }
        }

        @Override
        public int compareTo(Node state1) {
            return (this.cost - state1.cost);
        }    
}
