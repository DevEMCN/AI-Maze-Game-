package ie.gmit.sw.ai;

public class Player {
	
	
	private int row;
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	private int col;
	
	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Player(int row, int col){
		this.row = row;
		this.col = col;
	}
}
