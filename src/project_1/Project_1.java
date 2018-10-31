/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.HashSet;

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
        Integer [] testState = {1, 2, 3, 4, 5, 6, 7, 0, 8};
        Node newNode = new Node(testState);
        
        Integer [] goalState = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        
        Problem nPuzzle = new Problem (testState, goalState, 3);
        
        GeneralSearch search = new GeneralSearch();
        GeneralSearch.MissingTileHeuristic heuristic = search.new MissingTileHeuristic();
        Node solution = search.generalSearch(nPuzzle, heuristic);
        
        Node testNode = nPuzzle.operators().moveBlankUp(newNode);
        System.out.println("State before operator:");
        newNode.printState();
        System.out.println("State after operator:");
        testNode.printState();
        System.out.println("The node returns by general search");
        solution.printState();
        System.out.println("Path from solution note it is backwards:");
        Node node = solution;
        while(node != null){
            System.out.println("Node state:");
            node.printState();
            node = node.getParentNode();
        }
    }
        
}
