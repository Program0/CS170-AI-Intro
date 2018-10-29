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
 * The problem defines the initial and goal state and the operators 
 */

public class Problem {
    
    private Integer [] initial;
    private Integer [] goal;
    private int nPuzzleSize; // The nPuzzleSize of the nxn matrix n-puzzle 
    
    Problem(Integer [] initialState, Integer [] goalState, int dimension){
        this.initial = initialState.clone();
        this.goal = goalState.clone();
        this.nPuzzleSize = dimension;
    }
    
    public Integer [] initialState(){
        return initial;
    }
    
    public void setInitialState(Integer [] initialGoal){
        initial = initialGoal.clone();
    }
    
    public void setGoalState(Integer [] goalState){
        goal = goalState.clone();
    }
    
    public Integer [] goalState(){
        return goal;
    }
    
    public int puzzleDimension(){
        return nPuzzleSize;
    }
    
    public class Operators{
           
        public Node moveUp(Node currentState){
            Node newNode = null;
            Integer [] newState = currentState.getCurrentState();
            int dimension = currentState.getDimension();
            int blankPosition = Arrays.asList(newState).indexOf(0);
            int row = blankPosition/nPuzzleSize;
            int col = blankPosition % nPuzzleSize;
            if(row > 0){                
                // Swap with number in row above                
                newState[blankPosition] = newState[dimension*(row-1) + col];
                newState[dimension*(row-1) + col] = 0;
                
                // Createa new node with the new state and cost
                newNode = new Node(newState,dimension);                
                // Set the new cost
                int newCost = currentState.currentCost()+1;                
                newNode.setCost(newCost);                
            }            
            return newNode;
        }
        
        public Node moveDown(Node currentState){
            Node newNode = null;
            Integer [] newState = currentState.getCurrentState();
            int dimension = currentState.getDimension();
            int blankPosition = Arrays.asList(newState).indexOf(0);
            int row = blankPosition/nPuzzleSize;
            int col = blankPosition % nPuzzleSize;
            if(row < dimension - 1 ){                
                // Swap with number in row above                
                newState[blankPosition] = newState[dimension*(row+1) + col];
                newState[dimension*(row+1) + col] = 0;
                
                // Createa new node with the new state and cost
                newNode = new Node(newState,dimension);                
                // Set the new cost
                int newCost = currentState.currentCost()+1;                
                newNode.setCost(newCost);                
            }            
            return newNode;
        }
    }

}
