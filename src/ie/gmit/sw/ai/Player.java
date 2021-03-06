 package ie.gmit.sw.ai;

public class Player {
	
	private int row;
	private int col;
	private int hbombs = 0;
	private int bombs = 0;
	private double health = 100;
	private int sword = 0;
	private double swordStrength = 0;
	private double bombStrength = 20;
	private double hbombStrength = 30;
	
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	
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

	public int getHbombs() {
		return hbombs;
	}

	public void addHbomb() {
		hbombs++;
	}

	public int getBombs() {
		return bombs;
	}

	public void addBombs() {
		this.bombs++;
	}

	public int getSword() {
		return sword;
	}

	public void addSword() {
		this.sword++;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double getSwordStrength() {
		return swordStrength;
	}

	public void setSwordStrength(double swordStrength) {
		this.swordStrength = swordStrength;
	}

	public double getBombStrength() {
		return bombStrength;
	}

	public void setBombStrength(double bombStrength) {
		this.bombStrength = bombStrength;
	}

	public double getHbombStrength() {
		return hbombStrength;
	}

	public void setHbombStrength(double hbombStrength) {
		this.hbombStrength = hbombStrength;
	}
}
