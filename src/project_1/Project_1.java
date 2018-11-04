/**
 *
 * @author Marlo Zeroth
 */

package project_1;

public class Project_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Welcome to Marlo Zeroth's 8-puzzle solver.");
        System.out.println("Type “1” to use a default puzzle, or “2” to enter your own puzzle.");
 
        int option = 0;
        Integer [] defaultPuzzle = {1, 2, 3, 4, 8, 0, 7, 6, 5};
        
        Integer [] goalState = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        
        Problem nPuzzle = new Problem (defaultPuzzle, goalState, 3);
        
        GeneralSearch search = new GeneralSearch();
        GeneralSearch.ManhattanDistanceHeuristic manhattanDistanceHeuristic = 
                search.new ManhattanDistanceHeuristic();
        GeneralSearch.MissingTileHeuristic missingTileHeuristic = 
                search.new MissingTileHeuristic();
        GeneralSearch.UniformCostHeuristic uniformCostHeuristic = search.new UniformCostHeuristic();
        
        Node solution = search.generalSearch(nPuzzle, manhattanDistanceHeuristic);        
        System.out.println("To solve this problem the search algorithm expanded a total of " 
                + search.getTotalNodesExpanded() + " nodes.");
        System.out.println("The maximum number of nodes in the queue at any one time was " 
                + search.getMaxQueueSize() + ".");
        int depth = 0;
        System.out.println("Path from solution note it is backwards:");
        Node node = solution;
        while(node != null){
            System.out.println("State at depth:" + depth);
            node.printState();
            node = node.getParentNode();
            
            depth++;
        }
        System.out.println("Goal");
        System.out.println("The depth of the goal was " + (depth-1));
    }
}
