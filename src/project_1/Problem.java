/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    private Integer [] initial;
    private Integer [] goal;
    private final int puzzleMatrixDimension; // The puzzleMatrixDimension of the nxn matrix n-puzzle 
    
    Problem(Integer [] initialState, Integer [] goalState, int dimension){
        this.initial = initialState.clone();
        this.goal = goalState.clone();
        this.puzzleMatrixDimension = dimension;
    }
    
    public Integer [] getInitialState(){
        return initial.clone();
    }
    
    public void setInitialState(Integer [] initialGoal){
        initial = initialGoal.clone();
    }
    
    public void setGoalState(Integer [] goalState){
        goal = goalState.clone();
    }
    
    public Integer [] getGoalState(){
        return goal.clone();        
    }
    
    public int getPuzzleDimension(){
        return puzzleMatrixDimension;
    }
    
    public static class Operators{
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
            int dimension = node.getDimension();
            int blankPosition = Arrays.asList(newState).indexOf(0);
            int row = blankPosition/dimension;
            int col = blankPosition % dimension;
            if(row > 0){                
                // Swap with number in row above                
                newState[blankPosition] = newState[dimension*(row-1) + col];
                newState[dimension*(row-1) + col] = 0;
                
                // Createa new node with the new state and cost
                newNode = new Node(newState,dimension);                
                // Set the new cost
                int newCost = node.currentCost()+1;                
                newNode.setCost(newCost);                
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
            int dimension = node.getDimension();
            int blankPosition = Arrays.asList(newState).indexOf(0);
            int row = blankPosition/dimension;
            int col = blankPosition % dimension;
            if(row < dimension - 1 ){                
                // Swap with number in row below
                newState[blankPosition] = newState[dimension*(row+1) + col];
                newState[dimension*(row+1) + col] = 0;
                
                // Createa new node with the new state and cost
                newNode = new Node(newState,dimension);                
                // Set the new cost
                int newCost = node.currentCost()+1;                
                newNode.setCost(newCost);                
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
            int dimension = node.getDimension();
            int blankPosition = Arrays.asList(newState).indexOf(0);
            int row = blankPosition/dimension;
            int col = blankPosition % dimension;
            if(col > 0){                
                // Swap with number in column to the left 
                newState[blankPosition] = newState[dimension*row + (col - 1)];
                newState[dimension*row + (col - 1)] = 0;
                
                // Createa new node with the new state and cost
                newNode = new Node(newState,dimension);                
                // Set the new cost
                int newCost = node.currentCost()+1;                
                newNode.setCost(newCost);                
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
            int dimension = node.getDimension();
            int blankPosition = Arrays.asList(newState).indexOf(0);
            int row = blankPosition/dimension;
            int col = blankPosition % dimension;
            if( col < (dimension -1) ) {                
                // Swap with number in column to the right 
                newState[blankPosition] = newState[dimension*row + (col + 1)];
                newState[dimension*row + (col + 1)] = 0;
                
                // Createa new node with the new state and cost
                newNode = new Node(newState,dimension);                
                // Set the new cost
                int newCost = node.currentCost()+1;                
                newNode.setCost(newCost);                
            }            
            return newNode;
        }
    }
}
