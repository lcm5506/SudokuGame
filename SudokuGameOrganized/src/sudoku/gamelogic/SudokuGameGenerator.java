package sudoku.gamelogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sudoku.constants.Difficulty;
import sudoku.constants.GameState;
import sudoku.problemdomain.Coordinate;
import sudoku.problemdomain.SudokuGame;

public class SudokuGameGenerator {

	public static SudokuGame generateNewSudokuGame (Difficulty d) {

		int[][] grid;


		do {
			grid = new int[SudokuGame.GRID_SIZE][SudokuGame.GRID_SIZE];
			fillSudokuGame(grid,d.difficulty);
		} while (!SudokuGameSolution.getSolution(SudokuUtilities.copyOf(grid)));
		// Populate the grid with number of random integers between 1 - 9 at random coordinates based on the difficulty value
		
		
		
		
		// Test i.e. print the grid created
		/*
		for (int i=0; i<SudokuGame.GRID_SIZE; i++) {
			for (int j=0; j<SudokuGame.GRID_SIZE; j++) {
				System.out.print(grid[j][i] + " ");
			}
			System.out.println("0");
		}
		*/
		return new SudokuGame(grid, GameState.NEW);
	}
	
	public static void fillSudokuGame(int[][] grid, int difficulty) {
		ArrayList<Coordinate> coordinateGenerated = new ArrayList<Coordinate>();
		Random myRandom = new Random();
		
		for (int i=0; i<difficulty;i++) {
			int x = myRandom.nextInt(9);
			int y = myRandom.nextInt(9);
			Coordinate tempCoordinate = new Coordinate(x,y);
			// Check if we have already generated random number for this randomly generated coordinate
			if (coordinateGenerated.contains(tempCoordinate)) {
				i--;
			} else {
				List<Integer> validNumbers = SudokuGameValidator.getValidNumbers(grid,tempCoordinate);				
				if (validNumbers.size() == 0) {
					i--;
				} else {
					int randomValidNumber = validNumbers.get(myRandom.nextInt(validNumbers.size()));
					grid[tempCoordinate.getX()][tempCoordinate.getY()] = randomValidNumber;
					coordinateGenerated.add(tempCoordinate);
				}
				// Monitoring purpose only
				//System.out.println("at "+tempCoordinate.getCol() + ", " + tempCoordinate.getRow() + " add " + newRandomNumber);
			}
		}
	}
}
