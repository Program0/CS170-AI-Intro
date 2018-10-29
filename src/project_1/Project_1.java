/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
        
        Problem nPuzzle = new Problem (testState, goalState, 3);
        
        Problem.Operators problem1 = new Problem.Operators();        
        
        Node testNode = problem1.moveBlankUp(newNode);
        System.out.println("State before operator:");
        newNode.printState();
        System.out.println("State after operator:");
        testNode.printState();
        System.out.println("Hello world!");
    }
    
    public interface QUEUEING_FUNCTION{
        
    }
    
    public Node generalSearch(Problem problem, QUEUEING_FUNCTION function){
        
    }

    public PriorityQueue<Node> MAKE_QUEUE(Node node){
        PriorityQueue<Node> queue = new PriorityQueue<>();        
        queue.add(node);
        return queue;
    }
    
    public Node MAKE_NODE(Problem initialState){
        Node node = new Node(initialState.getInitialState(), 
                initialState.getPuzzleDimension());
        return node;
    }
    
    /**
     * @param operators Operator object that modifies a Node input's state.
     * @return A list of new states if input state can be expanded or null otherwise
     * @param input NOde containing the state to expand using operators object methods
    */

    public List<Node> expand(Node input, Problem.Operators operators){
        
        List<Node> expandedStateList = new ArrayList<>();
        
        Node moveUpState = operators.moveBlankUp(input);
        if(moveUpState != null){
            expandedStateList.add(moveUpState);
            input.addChild(moveUpState);
            moveUpState.setParentNode(input);
        }
        
        Node moveDownState = operators.moveBlankDown(input);
        if(moveDownState != null){
            expandedStateList.add(moveDownState);
            input.addChild(moveDownState);
            moveDownState.setParentNode(input);
        }
        
        Node moveLeftState = operators.moveBlankLeft(input);
        if(moveLeftState != null){
            expandedStateList.add(moveLeftState);
            input.addChild(moveLeftState);
            moveLeftState.setParentNode(input);
        }
        
        Node moveRightState = operators.moveBlankRight(input);
        if(moveRightState != null){
            expandedStateList.add(moveRightState);
            input.addChild(moveRightState);
            moveRightState.setParentNode(input);
        }
        
        return expandedStateList;        
    }
    
}
