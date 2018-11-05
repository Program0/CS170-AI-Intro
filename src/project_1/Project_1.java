/**
 *
 * @author Marlo Zeroth
 */
package project_1;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Project_1 {

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

        // Gather user input for options
        Scanner reader = new Scanner(System.in);
        int option = getPuzzleChoice(reader);
        int heuristicOption;

        switch (option) {
            case 1:
                try {
                    System.out.println("Enter your choice of algorithm");
                    heuristicOption = getHeuristicChoice(reader);
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
                    optionalGoalPuzzle = getUserPuzzle(reader, EIGHT_PUZZLE_SIZE);
                    System.out.println("Enter your choice of algorithm");
                    heuristicOption = getHeuristicChoice(reader);
                    switch (heuristicOption) {
                        case 1:
                            nPuzzle = new Problem(optionalGoalPuzzle, optionalGoalPuzzle, EIGHT_PUZZLE_SIZE);
                            solution = search.generalSearch(nPuzzle, uniformCostHeuristic);
                            break;
                        case 2:
                            nPuzzle = new Problem(optionalGoalPuzzle, optionalGoalPuzzle, EIGHT_PUZZLE_SIZE);
                            solution = search.generalSearch(nPuzzle, missingTileHeuristic);
                            break;
                        default:
                            nPuzzle = new Problem(optionalGoalPuzzle, optionalGoalPuzzle, EIGHT_PUZZLE_SIZE);
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
        System.out.println("Path from solution note it is backwards:");
        Node node = solution;
        while (node != null) {
            System.out.println("State at depth:" + depth);
            node.printState();
            node = node.getParentNode();

            depth++;
        }
        System.out.println("Goal!!");
        System.out.println("The depth of the goal was " + (depth - 1));

        // Testing the equals method
    }

    public static int getPuzzleChoice(Scanner reader) {
        System.out.println("Welcome to Marlo Zeroth's 8-puzzle solver.");
        System.out.println("Type 1 to use a default puzzle, or 2 to enter your own puzzle.");
        return reader.nextInt();
    }

    public static int getHeuristicChoice(Scanner reader) {
        System.out.println("Enter your choice of algorithm");
        System.out.println("\t1. Uniform Cost Search");
        System.out.println("\t2. Missing Tile Heuristic");
        System.out.println("\t3. Manhattan Distance Heuristic");
        return reader.nextInt();
    }

    public static Integer[] getGoalPuzzle(int size) {
        Integer[] puzzle = new Integer[size];
        for (int i = 0; i < size - 1; i++) {
            puzzle[i] = i + 1;
        }
        puzzle[size - 1] = 0;
        return puzzle;
    }

    public static Integer[] getUserPuzzle(Scanner reader, int size) {
        List<Integer> lines = new ArrayList<>();
        boolean valid = false;
        Integer[] puzzle;
        Integer test;
        int linesEntered = 0;
        do {
            while (!valid) {
                System.out.println("Enter your puzzle, use a zero to represent the blank");
                System.out.println("Enter the row number " + Integer.toString(linesEntered + 1) + ", use space or tabs between numbers");
                String[] numbers = reader.next().split("\t\\s+");
                try {
                    test = Integer.parseInt(Arrays.toString(numbers));
                    if (numbers.length == size && test.compareTo(0) > 0) {
                        for (String s : numbers) {
                            lines.add(Integer.parseInt(s));
                        }
                        valid = true;
                    } else {
                        System.out.println("Enter a line of " + Integer.toString(size) + " integers separated by space or tabs.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Enter a line of " + Integer.toString(size) + " integers separated by space or tabs.");
                }

            }
            valid = false; // Go to the next line
            linesEntered++;
        } while (linesEntered < size);

        reader.close();
        puzzle = (Integer[]) lines.toArray();
        return puzzle;

    }
}
