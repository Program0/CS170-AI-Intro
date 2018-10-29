/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author super0
 */
public class Project_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Integer [] testState = {1, 3, 2, 4, 5, 0, 8, 6, 7};
        Node newNode = new Node(testState, 3);
        
        Integer [] goalState = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        
        Problem problem1 = new Problem(testState, goalState, 3);
        Problem.Operators testOperator = problem1.new Operators();
        
        Node testNode = testOperator.moveUp(newNode);
        System.out.println("State before operator:");
        newNode.printState();
        System.out.println("State after operator:");
        testNode.printState();
        System.out.println("Hello world!");
    }
    
    
       
    /**
     * @return A list of new states if input state can be expanded or null otherwise
     * @param input operators  state to expand into new states
    */

    public List<Node> expand(Node input, Problem.Operators operators){
        List<Node> expandedStateList= new ArrayList<>();
        
        Node moveUpState = operators.moveUp(input);
        if(moveUpState != null){
            expandedStateList.add(moveUpState);
            input.addChild(moveUpState);
            moveUpState.setParentNode(input);
        }
        
        return expandedStateList;
        
    }
    
}
