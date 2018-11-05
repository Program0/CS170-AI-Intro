/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Marlo Zeroth
 */
public class GeneralSearch {

    private final HashSet<Node> visited; // To keep track of visited states
    private Problem problem; // To keep track of the puzzle used
    private int maxQueueSize; // Maximum size of the priority queue at any one time
    private int numberNodesExpanded; // The total number of nodes expanded.

    public GeneralSearch() {
        visited = new HashSet<>();
        maxQueueSize = 0;
        numberNodesExpanded = 0;
    }
    
    public int getTotalNodesExpanded(){
        return numberNodesExpanded;
    }
    
    public int getMaxQueueSize(){
        return maxQueueSize;
    }

    public Node generalSearch(Problem problem, QueueingFunction function) {
        this.problem = problem; // Set to make use of the puzzle goal in heuristics
        PriorityQueue<Node> nodes = makeQueue(makeNode(problem.getInitialState()));
        Node node;
        while (true) {
            if (nodes.isEmpty()) {
                return null;
            }
            node = removeFront(nodes);
            if (problem.goalTest(node.getCurrentState())) {
                return node;
            }
            nodes = function.queueFunction(nodes, expand(node, problem));
        }
    }

    /**
     * Queueing function to be implemented by different heuristics
     */
    public interface QueueingFunction {
        public abstract PriorityQueue<Node> queueFunction(PriorityQueue<Node> nodes, List<Node> newNodes);
    }
    
    /**
     * 
     */
    public class UniformCostHeuristic implements QueueingFunction {

        @Override
        public PriorityQueue<Node> queueFunction(PriorityQueue<Node> nodes,
                List<Node> newNodes) {
            for (Node node : newNodes) {
                node.setCost(uniformCost(node));
                node.setHeuristicCost(0);
                node.setSearchCost(node.currentCost() + node.getHeuristicCost());
                nodes.add(node);
            }
            return nodes;
        }
    }

    public class MissingTileHeuristic extends UniformCostHeuristic implements QueueingFunction {

        @Override
        public PriorityQueue<Node> queueFunction(PriorityQueue<Node> nodes,
                List<Node> newNodes) {
            for (Node node : newNodes) {
                node.setCost(uniformCost(node));
                node.setHeuristicCost(missingTileCount(node));
                node.setSearchCost(node.currentCost()+node.getHeuristicCost());
                nodes.add(node);
            }
            return nodes;
        }
    }

    public class ManhattanDistanceHeuristic extends UniformCostHeuristic implements QueueingFunction {

        @Override
        public PriorityQueue<Node> queueFunction(PriorityQueue<Node> nodes,
                List<Node> newNodes) {
            for (Node node : newNodes) {
                node.setCost(uniformCost(node));
                node.setHeuristicCost(manhattanDistance(node));
                node.setSearchCost(node.currentCost()+node.getHeuristicCost());
                nodes.add(node);
            }
            return nodes;
        }
    }

    private int uniformCost(Node node) {
        int cost = node.currentCost() + node.getParentNode().currentCost();
        return cost;
    }

    /**
     *
     */
    private int missingTileCount(Node node) throws IllegalArgumentException {
        int cost = 0;
        Integer[] state = node.getCurrentState();
        Integer[] goal = problem.getGoalState();
        int size = goal.length;
        if (state.length != goal.length) {
            throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < size; i++) {
                // Don't count the blank symbolized by 0
                if (!goal[i].equals(state[i]) && !state[i].equals(0)) {
                    cost++;
                }
            }
            return cost;
        }
    }

    /**
     *
     */
    private int manhattanDistance(Node node) throws IllegalArgumentException {
        int cost = 0;
        Integer[] state = node.getCurrentState();
        Integer[] goal = problem.getGoalState();
        int size = goal.length;
        int distance;
        int goalPosition;
        int tileRow, goalRow, tileColumn, goalColumn;
        int width = problem.getPuzzleDimension();
        if (state.length != goal.length) {
            throw new IllegalArgumentException();
        } else {
            for (int position = 0; position < size; position++) {
                // Don't count the blank symbolized by 0
                if (!state[position].equals(0)) {
                    goalPosition = Arrays.asList(goal).indexOf(state[position]);
                    tileRow = position / width;
                    tileColumn = position % width;
                    goalRow = goalPosition / width;
                    goalColumn = goalPosition % width;
                    distance = Math.abs(tileRow - goalRow) + Math.abs(tileColumn - goalColumn);
                    cost += distance;
                }
            }
            return cost;
        }
    }

    /**
     * Removes and returns the least expensive node in the priority queue and
     * adds it to the visited set if nodes is not empty. Returns null otherwise.
     *
     * @param nodes Priority queue of Nodes
     * @return Node or null if nodes is empty
     */
    private Node removeFront(PriorityQueue<Node> nodes) {

        Node node = null;
        if(maxQueueSize < nodes.size()){
            maxQueueSize = nodes.size();
        }
        if (!nodes.isEmpty()) {
            node = nodes.poll();
            System.out.println("The best state to expand with " + "g(" 
                    + node.currentCost() + ")" + " h("+node.getHeuristicCost() + ")" );
            node.printState();
            visited.add(node);
        }
        return node;
    }

    private PriorityQueue<Node> makeQueue(Node node) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(node);
        maxQueueSize = queue.size();
        return queue;
    }

    private Node makeNode(Integer[] initialState) {
        Node node = new Node(initialState);
        numberNodesExpanded = 1;
        return node;
    }

    /**
     * @return A list of new states if input state can be expanded and the
     * expanded nodes are not in the visited set. Returns an empty list
     * otherwise.
     * @param operators Operator object that modifies a Node input's state.
     * @param input Node containing the state to expand using an operators
     * object methods.
     */
    private List<Node> expand(Node input, Problem problem) {

        System.out.println("Expanding state:");
        input.printState();
        Problem.Operators operators = problem.new Operators();

        List<Node> expandedStateList = new ArrayList<>();

        Node moveUpState = operators.moveBlankUp(input);
        if (moveUpState != null && !visited.contains(moveUpState)) {
            expandedStateList.add(moveUpState);
            input.addChild(moveUpState);
            moveUpState.setParentNode(input);
            numberNodesExpanded++;
        }

        Node moveDownState = operators.moveBlankDown(input);
        if (moveDownState != null && !visited.contains(moveDownState)) {
            expandedStateList.add(moveDownState);
            input.addChild(moveDownState);
            moveDownState.setParentNode(input);
            numberNodesExpanded++;
        }

        Node moveLeftState = operators.moveBlankLeft(input);
        if (moveLeftState != null && !visited.contains(moveLeftState)) {
            expandedStateList.add(moveLeftState);
            input.addChild(moveLeftState);
            moveLeftState.setParentNode(input);
            numberNodesExpanded++;
        }

        Node moveRightState = operators.moveBlankRight(input);
        if (moveRightState != null && !visited.contains(moveRightState)) {
            expandedStateList.add(moveRightState);
            input.addChild(moveRightState);
            moveRightState.setParentNode(input);
            numberNodesExpanded++;
        }
        return expandedStateList;
    }
}
