import java.util.ArrayList;

public class Square {

	@Override
	public String toString() {
		return "Square [row=" + row + ", col=" + col + ", isWall=" + isWall + ", visited=" + visited + "]";
	}

	private final int row, col;
	private final boolean isWall;
	private Square previous = null;
	private boolean visited = false;
	private int cost, runningCost;

	public Square(int x, int y, boolean isWall, int cost ) {
		this.row = x;
		this.col = y;
		this.isWall = isWall;
		this.cost = cost;
		this.runningCost = Integer.MAX_VALUE;
	}
	
	public int getRow() { return this.row; }
	public int getCol() { return this.col; }
	public boolean getIsWall() { return this.isWall; }
	public int getCost() { return this.cost; }

	public void visit() {
		this.visited = true;
	}

	public boolean isVisited() {
		return this.visited;
	}

	public void setPrevious(Square previous) {
		this.previous = previous;
	}

	public Square getPrevious() {
		return this.previous;
	}
	
	public void setRunningCost(int runningCost) {
		this.runningCost = runningCost;
	}
	
	public int getRunningCost() {
		return this.runningCost;
	}

}

