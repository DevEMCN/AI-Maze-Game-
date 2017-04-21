package ie.gmit.sw.ai.sprites;

import java.util.Random;

import ie.gmit.sw.ai.Node;
import ie.gmit.sw.ai.Player;
import ie.gmit.sw.ai.fuzzy.EngageFuzzy;
import ie.gmit.sw.ai.nn.EngageNN;

public class NeuralSprite extends Sprite implements Runnable{
	
	private Player player;
	private Node[][] maze;
	private int row;
	private int col;
	//private double strength;
	Random rand = new Random();
	private int bombChoice = 0;
	private int bomb = 0;
	private int strength = 50;
	private int id;
	public NeuralSprite(String name, String... images) throws Exception{
		super(name, images);
		
	}
	
	public NeuralSprite(Node[][] maze, Player player, int row, int col,  double strength, int id){
		super();
		this.player = player;
		this.maze = maze;
		this.row = row;
		this.col = col;
		this.setId(id);
	}

	@Override
	public void run() {
		
		System.out.println("neural sprite running");
//		if(player == null)
//			System.out.println("player is null");
//		else
//			System.out.println("non null");
//		DepthLimitedDFSTraversator dt = new DepthLimitedDFSTraversator(10, this, player);
//		
//		dt.traverse(maze, maze[row][col]);
		
//		AStarTraversator astar = new AStarTraversator(maze[player.getRow()][player.getCol()], this);
//		astar.traverse(maze,  maze[row][col]);
	}
	
	public void moveSprite(int newX, int newY) throws InterruptedException {
		if (maze[newX][newY].getNodeType() != '0') {
			//maze[this.row][this.col].setNodeType(NodeType.WalkableNode);
			maze[this.row][this.col].setNodeType('\u0020');
			maze[newX][newY].setNodeType('\u0036');
			this.row = newX;
			this.col = newY;
			//System.out.println("row : " + x);
			
		}
		
	}
	
	public void engageNN(){
		double healthy = 0;
		EngageNN enn = new EngageNN();
		try {
			if(player.getHealth() >= 100)
				healthy = 2;
			else if (player.getHealth() >= 40)
				healthy = 1;
			else
				healthy = 0;
			
			bombChoice = rand.nextInt(2) + 1;
			if (bombChoice == 1)
					bomb = player.getBombs();
			else 
				bomb = player.getHbombs();
			int action = enn.action(healthy, player.getSword(), bomb, 1.0);
			if (action == 2){
				System.out.println("action is attack");
				EngageFuzzy ef = new EngageFuzzy();
				player.setHealth(ef.fight(player.getSwordStrength(), player.getHealth(), this.strength));
			}
			else if (action == 1) {
				System.out.println("action is panic");
			}
			else if (action == 3){
				System.out.println("hide");
				maze[this.row][this.col].setNodeType('\u0020');
				maze[row + 1][row + 1].setNodeType('\u0037');
				this.row = row + 1;
				this.col = row + 1;
			}
			else if (action == 4){
				System.out.println("run away");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
