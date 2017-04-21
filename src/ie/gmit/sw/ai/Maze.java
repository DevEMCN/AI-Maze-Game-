package ie.gmit.sw.ai;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ie.gmit.sw.ai.sprites.FuzzySprite;

public class Maze {
	//private char[][] maze;
	private Node[][] maze;
	private Player player;
	//private Node[][] nodeMaze;
	private ExecutorService ex = Executors.newFixedThreadPool(100);
	public Maze(int dimension, Player player){
		//maze = new char[dimension][dimension];
		maze = new Node[dimension][dimension];
		//nodeMaze = maze
		this.player = player;
		init();
		buildMaze();
		
		int featureNumber = (int)((dimension * dimension) * 0.01);
//		addFeature('\u0031', '0', featureNumber); //1 is a sword, 0 is a hedge
//		addFeature('\u0032', '0', featureNumber); //2 is help, 0 is a hedge
//		addFeature('\u0033', '0', featureNumber); //3 is a bomb, 0 is a hedge
//		addFeature('\u0034', '0', featureNumber); //4 is a hydrogen bomb, 0 is a hedge
//		
		featureNumber = 8;//(int)((dimension * dimension) * 0.01);
		addFeature('\u0036', '0', featureNumber); //6 is a Black Spider, 0 is a hedge
		addFeature('\u0037', '0', featureNumber); //7 is a Blue Spider, 0 is a hedge
		addFeature('\u0038', '0', featureNumber); //8 is a Brown Spider, 0 is a hedge
		addFeature('\u0039', '0', featureNumber); //9 is a Green Spider, 0 is a hedge
		addFeature('\u003A', '0', featureNumber); //: is a Grey Spider, 0 is a hedge
		addFeature('\u003B', '0', featureNumber); //; is a Orange Spider, 0 is a hedge
		addFeature('\u003C', '0', featureNumber); //< is a Red Spider, 0 is a hedge
		addFeature('\u003D', '0', featureNumber); //= is a Yellow Spider, 0 is a hedge
	}
	
	private void init(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				//maze[row][col] = '0'; //Index 0 is a hedge...
				maze[row][col] = new Node(row, col);
				maze[row][col].setNodeType('0');
				player = getPlayer();
//				System.out.println("player col: " + player.getCol());
			}
		}
	}
	
	private void addFeature(char feature, char replace, int number){
		int counter = 0;
		while (counter < number){
			int row = (int) (maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
			if (maze[row][col].getNodeType() == replace){
				maze[row][col].setNodeType(feature);
				if(maze[row][col].getNodeType() == feature){
//					if(player != null)
//						System.out.println("not null");
//					else
//						System.out.println("null");
//					//System.out.println("testing " + nodeMaze[row][col]);
					if(feature > 5){
						ex.execute(new FuzzySprite(maze, player, row, col));
					}
				}
				counter++;
			}
		}
	}
	
	
	private void buildMaze(){ 
		for (int row = 1; row < maze.length - 1; row++){
			for (int col = 1; col < maze[row].length - 1; col++){
				int num = (int) (Math.random() * 10);
				if (num > 5 && col + 1 < maze[row].length - 1){
					maze[row][col + 1].setNodeType('\u0020'); //\u0020 = 0x20 = 32 (base 10) = SPACE
				}else{
					if (row + 1 < maze.length - 1)maze[row + 1][col].setNodeType('\u0020');
				}
			}
		}		
	}
	
	public Node[][] getMaze(){
		return this.maze;
	}
	
	public Node get(int row, int col){
		return this.maze[row][col];
	}
	
	public void set(int row, int col, char c){
		this.maze[row][col].setNodeType(c);
		//this.player = player;
	}
	
	public void setPlayer(Player player){
		//System.out.println("called");
		this.player = player;
		//System.out.println("player col" + player.getCol());
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public int size(){
		return this.maze.length;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				sb.append(maze[row][col]);
				if (col < maze[row].length - 1) sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}