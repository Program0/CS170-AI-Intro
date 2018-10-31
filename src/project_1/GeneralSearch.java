/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Marlo Zeroth
 */
public class GeneralSearch {

    private HashSet<Node> visited; // To keep track of visited states
    private Problem problem; // To keep track of the puzzle used

    public GeneralSearch(){
        visited = new HashSet<>();
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
            nodes = function.queueFunction(nodes, expand(node, problem.operators()));
        }
    }

    public interface QueueingFunction {

        public abstract PriorityQueue<Node> queueFunction(PriorityQueue<Node> nodes, List<Node> newNodes);
    }

    public class UniformCostHeuristic implements QueueingFunction {

        @Override
        public PriorityQueue<Node> queueFunction(PriorityQueue<Node> nodes,
                List<Node> newNodes) {
            for (Node node : newNodes) {
                node.setCost(uniformCostSearch(node));
                nodes.add(node);
            }
            return nodes;
        }

        protected int uniformCostSearch(Node node) {
            int cost = node.currentCost() + node.getParentNode().currentCost();
            return cost;
        }
    }

    public class MissingTileHeuristic extends UniformCostHeuristic implements QueueingFunction {

        @Override
        public PriorityQueue<Node> queueFunction(PriorityQueue<Node> nodes,
                List<Node> newNodes) {
            for(Node node : newNodes){
                int cost = uniformCostSearch(node) + missingTileCount(node);
                node.setCost(cost);
                nodes.add(node);
            }
            return nodes;
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
                    if (goal[i].equals(state[i]) && !state[i].equals(0)) {
                        cost++;
                    }
                }
                return cost;
            }
        }
    }
    
    public class ManhattanDistanceHeuristic extends UniformCostHeuristic implements QueueingFunction{
        @Override
        public PriorityQueue<Node> queueFunction(PriorityQueue<Node> nodes,
                List<Node> newNodes) {
            for(Node node : newNodes){
                int cost = uniformCostSearch(node) + manhattanDistance(node);
                node.setCost(cost);
                nodes.add(node);
            }
            return nodes;
        }
        
        private int manhattanDistance(Node node){
            int cost = 0;
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
        if (!nodes.isEmpty()) {
            node = nodes.poll();
            visited.add(node);
        }
        return node;
    }

    private PriorityQueue<Node> makeQueue(Node node) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(node);
        return queue;
    }

    private Node makeNode(Integer[] initialState) {
        Node node = new Node(initialState);
        return node;
    }

    /**
     * @return A list of new states if input state can be expanded and the
     * expanded nodes are not in the visited set. Returns and empty list
     * otherwise.
     * @param operators Operator object that modifies a Node input's state.
     * @param input Node containing the state to expand using an operators
     * object methods.
     */
    private List<Node> expand(Node input, Problem.Operators operators) {

        List<Node> expandedStateList = new ArrayList<>();

        Node moveUpState = operators.moveBlankUp(input);
        if (moveUpState != null && !visited.contains(moveUpState)) {
            expandedStateList.add(moveUpState);
            input.addChild(moveUpState);
            moveUpState.setParentNode(input);
        }

        Node moveDownState = operators.moveBlankDown(input);
        if (moveDownState != null && !visited.contains(moveDownState)) {
            expandedStateList.add(moveDownState);
            input.addChild(moveDownState);
            moveDownState.setParentNode(input);
        }

        Node moveLeftState = operators.moveBlankLeft(input);
        if (moveLeftState != null && !visited.contains(moveLeftState)) {
            expandedStateList.add(moveLeftState);
            input.addChild(moveLeftState);
            moveLeftState.setParentNode(input);
        }

        Node moveRightState = operators.moveBlankRight(input);
        if (moveRightState != null && !visited.contains(moveRightState)) {
            expandedStateList.add(moveRightState);
            input.addChild(moveRightState);
            moveRightState.setParentNode(input);
        }

        return expandedStateList;
    }

}
