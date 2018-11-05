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

    private Integer[] state; // Represents an n-puzzle current state matrix
    private int cost; // Current cost to get to this node
    private int heuristicCost; // Cost it takes to move to a new state
    private int searchCost; // Cost to enqueue this node 
    private final List<Node> children; // Expanded children of this state
    private Node parentNode; // To track path from initial state to goal state 

    // Main constructor
    public Node(Integer[] state) {
        this.state = Arrays.copyOf(state, state.length);
        cost = 0;
        heuristicCost = 0;
        parentNode = null;
        children = new ArrayList<>();

    }

    // Returns this node's current state
    public Integer[] getCurrentState() {
        return Arrays.copyOf(state, state.length);
    }

    // Changes this nodes current state 
    public void setCurrentState(Integer[] state) {
        this.state = Arrays.copyOf(state, state.length);
    }

    // Gets the current cost to get to this state
    public int currentCost() {
        return cost;
    }

    public void setCost(int newCost) {
        cost = newCost;
    }

    public void setHeuristicCost(int cost) {
        heuristicCost = cost;
    }

    public int getHeuristicCost() {
        return heuristicCost;
    }

    public void setSearchCost(int cost) {
        searchCost = cost;
    }

    public int getSearchCost() {
        return searchCost;
    }

    // Returns the parent state for this state
    public void setParentNode(Node daddy) {
        parentNode = daddy;
    }

    // Returns the parent state for this state
    public Node getParentNode() {
        return parentNode;
    }

    // Returns the expanded states for this state
    public List<Node> getChildren() {
        List<Node> nodes = new ArrayList<>(children);
        return nodes;
    }

    // Add a child
    public void addChild(Node child) {
        children.add(child);
    }

    // Print the state
    public void printState() {
        int size = this.state.length;
        int dimension = new Double(Math.sqrt(size)).intValue();
        for (int i = 0; i < size; i++) {
            if (i % dimension == 0) {
                System.out.println("\n");
            }
            System.out.print(state[i].toString() + " ");
        }
        System.out.println("\n");
    }

    @Override
    public String toString() {
        String contents = Arrays.toString(state) + "\tPuzzle dimension:\t"
                + String.valueOf(cost);
        return contents;
    }

    @Override
    public int compareTo(Node state1) {
        return (this.searchCost - state1.searchCost);
    }
    
    @Override
    public boolean equals(Object o) {
        // Same object reference
        if (this == o) {
            return true;
        }

        // Not the same class
        if (!(o instanceof Node)) {
            return false;
        }

        Node other = (Node) o;
        return Arrays.deepEquals(this.state, other.state);

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Arrays.deepHashCode(this.state);
        return hash;
    }
}
