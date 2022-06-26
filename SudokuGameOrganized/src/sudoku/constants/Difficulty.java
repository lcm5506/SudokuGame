package sudoku.constants;

public enum Difficulty {
	EASY(40),
	NORMAL(30),
	HARD(20),
	VERY_HARD(10);
	
	public final int difficulty;
	
	private Difficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
