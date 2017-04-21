package ie.gmit.sw.ai.sprites;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ie.gmit.sw.ai.Node;
import ie.gmit.sw.ai.Player;
import ie.gmit.sw.ai.traversers.DepthLimitedDFSTraversator;

public class NeuralSprite extends Sprite implements Runnable{
	
	private Player player;
	private Node[][] maze;
	private int row;
	private int col;
	public NeuralSprite(String name, String... images) throws Exception{
		super(name, images);
		
	}
	
	public NeuralSprite(Node[][] maze, Player player, int row, int col){
		super();
		this.player = player;
		this.maze = maze;
		this.row = row;
		this.col = col;
	}

	@Override
	public void run() {
		
		System.out.println("fuzzy sprite running");
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
}
