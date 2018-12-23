/**
 *
 * @author Marlo Zeroth
 */
package project_1;

import java.util.Scanner;
import java.util.HashSet;

public class Project_1 {

    // Gather user input for options
    public static final Scanner reader = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // For use with default 8-puzzle problem
        Integer[] defaultPuzzle = {1, 2, 3, 4, 0, 6, 7, 5, 8};
        Integer[] defaultGoalState = {1, 2, 3, 4, 5, 6, 7, 8, 0};

        // For use with all other n-puzzle problems.
        Integer[] optionalPuzzle;
        Integer[] optionalGoalPuzzle;

        // Used for n-puzzle sizes.
        final int EIGHT_PUZZLE_SIZE = 3;
        final int FIFTEEN_PUZZLE_SIZE = 4;
        final int TWENTYFOUR_PUZZLE_SIZE = 5;
        Problem nPuzzle;
        Node solution = null; // If the problem is solved this will not be null.

        // Set up the search heuristic to use on the problem
        GeneralSearch search = new GeneralSearch();

        GeneralSearch.ManhattanDistanceHeuristic manhattanDistanceHeuristic
                = search.new ManhattanDistanceHeuristic();

        GeneralSearch.MissingTileHeuristic missingTileHeuristic
                = search.new MissingTileHeuristic();

        GeneralSearch.UniformCostHeuristic uniformCostHeuristic = search.new UniformCostHeuristic();

        int option = getPuzzleChoice();
        int heuristicOption;

        // TODO Clean up the switch statement and implement the user interface
        // as a set of functions.
        switch (option) {
            case 1:
                try {
                    System.out.println("Enter your choice of algorithm");
                    heuristicOption = getHeuristicChoice();
                    switch (heuristicOption) {
                        case 1:
                            nPuzzle = new Problem(defaultPuzzle, defaultGoalState, EIGHT_PUZZLE_SIZE);
                            solution = search.generalSearch(nPuzzle, uniformCostHeuristic);
                            break;
                        case 2:
                            nPuzzle = new Problem(defaultPuzzle, defaultGoalState, EIGHT_PUZZLE_SIZE);
                            solution = search.generalSearch(nPuzzle, missingTileHeuristic);
                            break;
                        default:
                            nPuzzle = new Problem(defaultPuzzle, defaultGoalState, EIGHT_PUZZLE_SIZE);
                            solution = search.generalSearch(nPuzzle, manhattanDistanceHeuristic);
                            break;
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Entered illegal puzzle dimension. Dimension must be nxn matrix");
                    System.err.println("IllegalArgumentException: " + e.getMessage());
                }
                break;
            case 2:
                try {
                    optionalGoalPuzzle = getGoalPuzzle(EIGHT_PUZZLE_SIZE);
                    optionalPuzzle = getUserPuzzle(EIGHT_PUZZLE_SIZE);
                    heuristicOption = getHeuristicChoice();
                    switch (heuristicOption) {
                        case 1:
                            nPuzzle = new Problem(optionalPuzzle, optionalGoalPuzzle, EIGHT_PUZZLE_SIZE);
                            solution = search.generalSearch(nPuzzle, uniformCostHeuristic);
                            break;
                        case 2:
                            nPuzzle = new Problem(optionalPuzzle, optionalGoalPuzzle, EIGHT_PUZZLE_SIZE);
                            solution = search.generalSearch(nPuzzle, missingTileHeuristic);
                            break;
                        default:
                            nPuzzle = new Problem(optionalPuzzle, optionalGoalPuzzle, EIGHT_PUZZLE_SIZE);
                            solution = search.generalSearch(nPuzzle, manhattanDistanceHeuristic);
                            break;
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Entered illegal puzzle dimension. Dimension must be nxn matrix");
                    System.err.println("IllegalArgumentException: " + e.getMessage());
                }

                break;
            default:
                System.out.println("You did not enter a valid option. Exiting program");
                System.exit(0);
                break;
        }

        System.out.println("To solve this problem the search algorithm expanded a total of "
                + search.getTotalNodesExpanded() + " nodes.");
        System.out.println("The maximum number of nodes in the queue at any one time was "
                + search.getMaxQueueSize() + ".");
        int depth = 0;
        Node node = solution;
        while (node != null) {
            depth++;
        }
        System.out.println("Goal!!");
        System.out.println("The depth of the goal was " + (depth - 1));
        reader.close();
        System.exit(0);
    }

    /**
     * Returns the user choice whether to use the default puzzle or to enter a
     * custom puzzle.
     */
    public static int getPuzzleChoice() {
        System.out.println("Welcome to Marlo Zeroth's 8-puzzle solver.");
        System.out.println("Type 1 to use a default puzzle, or 2 to enter your own puzzle.");
        int choice = Integer.parseInt(reader.nextLine());
        return choice;
    }

    /**
     * Returns the user input for which heuristic to use for the problem.
     */
    public static int getHeuristicChoice() {
        System.out.println("\nEnter your choice of algorithm");
        System.out.println("\t1. Uniform Cost Search");
        System.out.println("\t2. Missing Tile Heuristic");
        System.out.println("\t3. Manhattan Distance Heuristic");
        int choice = Integer.parseInt(reader.nextLine());
        return choice;
    }

    /**
     * Returns an integer array for custom goal puzzle based on size.
     */
    public static Integer[] getGoalPuzzle(int size) {
        int dimension = size*size;
        Integer[] puzzle = new Integer[dimension];
        for (int i = 0; i < dimension - 1; i++) {
            puzzle[i] = i + 1;
        }
        puzzle[dimension - 1] = 0;
        return puzzle;
    }

    /**
     * Returns a one dimensional integer array to be used in building the custom
     * puzzle problem.
     */
    public static Integer[] getUserPuzzle(int size) {
        boolean valid = false;
        int arraySize = size * size;
        Integer[] puzzle = new Integer[arraySize];        
        HashSet<Integer> puzzleSet = new HashSet<>();

        do {
            System.out.println("\nEnter your puzzle, use a zero to represent the blank");
            int element = 0;
            String input;
            for (int i = 0; i < size; i++) {
                System.out.print("\nEnter row number " + Integer.toString(i + 1) + ", use space or tabs between numbers:  ");
                Scanner lineScanner = new Scanner(reader.nextLine());
                while (lineScanner.hasNext()) {
                    input = lineScanner.next();
                    puzzle[element] = new Integer(input);
                    puzzleSet.add(puzzle[element]); // Test for duplicate entries
                    element++;
                }
            }
            if(puzzleSet.size() == arraySize){
                valid = true;
            }
        } while (!valid);
        return puzzle;
    }
}
