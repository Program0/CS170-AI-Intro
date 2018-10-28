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


/**
 * The problem defines the initial and goal state and the operators 
 */

public class Problem {
    
    private String initial;
    private String goal;
    private int dimension; // The dimension of the nxn matrix n-puzzle 
    
    Problem(String initialState, String goalState){
        this.initial = initialState;
        this.goal = goalState;
    }
    
    public String initialState(){
        return initial;
    }
    
    public void setInitialState(String initialGoal){
        initial = initialGoal;
    }
    
    public void setGoalState(String goalState){
        goal = goalState;
    }
    
    public String goalState(){
        return goal;
    }
    
    public class Operators{
           
        public Node moveUp(Node currentState){
            Node newState = null;
            int blankPosition = currentState.currentState().indexOf('0');
            int row = blankPosition/dimension;
            if(row > 0){
                newState = new Node(currentState.currentState());
                
                
            }            
            return newState;
        }
    }

}
