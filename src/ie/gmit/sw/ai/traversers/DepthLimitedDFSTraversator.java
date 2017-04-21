package ie.gmit.sw.ai.traversers;

import ie.gmit.sw.ai.*;
import ie.gmit.sw.ai.sprites.FuzzySprite;
import ie.gmit.sw.ai.sprites.Sprite;
public class DepthLimitedDFSTraversator implements Traversator{
	private Node[][] maze;
	private int limit;
	private boolean keepRunning = true;
	private long time = System.currentTimeMillis();
	private int visitCount = 0;
	private FuzzySprite sprite;
	private Player player;
	
	public DepthLimitedDFSTraversator(int limit, FuzzySprite sprite, Player player){
		this.limit = limit;
		this.sprite = sprite;
		this.player = player;
	
	}
	
	public void traverse(Node[][] maze, Node node) {
		this.maze = maze;
		System.out.println("Search with limit " + limit);
		dfs(node, 1);
	}
	
	private void dfs(Node node, int depth){
		
		if (!keepRunning || depth > limit) return;
		
		node.setVisited(true);	
		visitCount++;
		
		if (node.isGoalNode()){
	        time = System.currentTimeMillis() - time; //Stop the clock
	        TraversatorStats.printStats(node, time, visitCount);
	        keepRunning = false;
			return;
		}
		
		try { //Simulate processing each expanded node
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//System.out.println("children" + node.adjacentNodes(maze));
		//Node[] children = node.children(maze);
		Node[] children = node.adjacentNodes(maze);
		//System.out.println(children);
		//System.out.println("children:" + children.toString());
		for (int i = 0; i < children.length; i++) {
			
			//System.out.println("test " + children[i]);
			if (children[i] != null && !children[i].isVisited()){
				children[i].setParent(node);
				try {
					//System.out.println("prev: " + previousNode.getRow() + " " + previousNode.getCol());
					//System.out.println("new: " + node.getRow() + " " + node.getCol());
					if(sprite.getId() != -1){
						System.out.println(sprite.getId());
					sprite.moveSprite(node.getRow(), node.getCol());
					}
					else
						break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dfs(children[i], depth + 1);
			}
		}
	}
}